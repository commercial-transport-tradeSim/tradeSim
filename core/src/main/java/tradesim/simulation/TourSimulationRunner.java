package tradesim.simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import tradesim.model.activity.ActivityBuilder;
import tradesim.model.business.Business;
import tradesim.model.business.Sector;
import tradesim.model.tour.Tour;
import tradesim.model.vehicles.Vehicle;
import tradesim.model.vehicles.VehicleCategory;
import tradesim.simulation.activity.ActivityChanger;
import tradesim.simulation.tour.ContinueTourChoiceModel;
import tradesim.simulation.tour.TourFilter;
import tradesim.simulation.tour.TourSelector;
import tradesim.util.input.config.RunnableStep;
import tradesim.util.random.ParameterizedDistribution;
import tradesim.util.type.Time;
import tradesim.util.type.Triple;
import tradesim.util.type.Tuple;

/**
 * The Class TourSimulationRunner is a {@link RunnableStep simulation step} to
 * simulate {@link Vehicle}'s {@link Tour}s.
 */
public class TourSimulationRunner implements RunnableStep {

	private final SimulationContext context;
	private final List<ActivityChanger> activityChangers;
	private final ContinueTourChoiceModel continueChoice;
	private final TourSelector tourSelector;
	private final TourFilter tourFilter;
	private final Map<Vehicle, List<Tour>> finishedTours;
	private final Map<Vehicle, List<Tour>> potentialTours;
	private final ParameterizedDistribution<Triple<Integer, Sector, VehicleCategory>, Tuple<Double, Integer>> tourLengthDistribution;
	private final int pruneCount;

	/**
	 * Instantiates a new simulation runner.
	 *
	 * @param context                the context
	 * @param activityChangers       the activity changers
	 * @param selector               the selector
	 * @param tourSelector           the tour selector
	 * @param tourFilter             the tour filter
	 * @param tourLengthDistribution the tour length distribution
	 * @param pruneCount 			 the prune count determines the maximum potential tours per vehicle at all times
	 */
	public TourSimulationRunner(SimulationContext context, List<ActivityChanger> activityChangers,
			ContinueTourChoiceModel selector, TourSelector tourSelector, TourFilter tourFilter,
			ParameterizedDistribution<Triple<Integer, Sector, VehicleCategory>, Tuple<Double, Integer>> tourLengthDistribution, int pruneCount) {

		this.context = context;
		this.potentialTours = new ConcurrentHashMap<>();
		this.finishedTours = new ConcurrentHashMap<>();
		this.activityChangers = activityChangers;
		this.continueChoice = selector;
		this.tourSelector = tourSelector;
		this.tourFilter = tourFilter;
		this.tourLengthDistribution = tourLengthDistribution;
		this.pruneCount = pruneCount;
		init();
	}

	/**
	 * Creates list with empty {@link Tour} for the given {@link Vehicle}. Selects
	 * an expected tour distance and an expected number of trips.
	 *
	 * @param vehicle      the vehicle
	 * @param business     the business
	 * @param previousTour the previous tour
	 * @return the list
	 */
	private List<Tour> initTourList(Vehicle vehicle, Business business, List<Tour> previousTour) {
		List<Tour> list = new ArrayList<Tour>();

		Time departure = previousTour.size() > 0 ? previousTour.get(previousTour.size() - 1).currentTime()
				: vehicle.getDeparture();
		Tuple<Double, Integer> distAndNum = this.tourLengthDistribution.draw(vehicle.getRandom().nextDouble(),
				previousTour.size() + 1, business.getSector(), vehicle.getCategory());
		double distKm = distAndNum.getFirst();
		int numOfTrips = distAndNum.getSecond();

		list.add(new Tour(vehicle, business, vehicle.getRandom().nextLong(), departure, distKm, numOfTrips));
		return list;
	}

	/**
	 * Creates empty {@link Tour}s for each simulated vehicle.
	 */
	private void init() {
		context.getFleets().stream().forEach(f -> f.getVehicles().forEach(v -> {
			potentialTours.put(v, initTourList(v, f.getBusiness(), List.of()));
			finishedTours.put(v, new ArrayList<>());
		}));

	}

	/**
	 * Runs the simulation. Iteratively updates potential tours until all pending
	 * tours are closed.
	 */
	@Override
	public void run() {
		while (!potentialTours.isEmpty()) {

			updatePotentialTours();

			System.out.println(potentialTours.values().stream().mapToInt(List::size).sum() + ":"
					+ finishedTours.values().stream().mapToInt(List::size).sum());
		}

		context.getTours().putAll(finishedTours);
	}

	/**
	 * Updates all potential tours: Updates all vehicles with unclosed potential
	 * {@link Tour}s. For all vehicles that are finished in that process (meanng all
	 * its potential tours returned to the depot), one of the potential tours is
	 * selected. Furthermore, finished vehicles get a new empty potential tour if
	 * they continue on another tour.
	 */
	private void updatePotentialTours() {
		List<Vehicle> finishedVehicles =  
		this.potentialTours.keySet().parallelStream().filter(v -> updateVehicle(v)).toList();

		System.out.println("Finished vehicles: " + finishedVehicles.size());

		finishedVehicles.parallelStream().forEach(v -> {
			Business owner = v.getOwner();
			if (this.potentialTours.get(v).isEmpty()) {
				potentialTours.remove(v);
				return;
			}

			this.finishedTours.get(v)
					.add(tourSelector.select(owner, v, potentialTours.get(v), v.getRandom().nextDouble()));
			potentialTours.remove(v);

			if (continueChoice.select(owner, v, finishedTours.get(v), v.getRandom().nextDouble())) {
				potentialTours.put(v, initTourList(v, owner, finishedTours.get(v)));
			}

		});

	}

	/**
	 * Update all potential tours of the given {@link Vehicle}.
	 *
	 * @param vehicle the vehicle
	 * @return true, if the vehicle is finished meaning that all its potential
	 *         {@link Tour}s have returned to the depot
	 */
	private boolean updateVehicle(Vehicle vehicle) {
		List<Tour> tourList = this.potentialTours.get(vehicle);
		List<Tour> newTours =
			tourList.stream()
					.filter(Tour::isActive).map(this::updateTour)
					.flatMap(List::stream)
					.filter(t -> tourFilter.test(vehicle.getOwner(), vehicle, t))
					.sorted(Tour::compareTo)
					.limit(pruneCount)
					.toList();

		tourList.clear();
		tourList.addAll(newTours);

		return tourList.stream().allMatch(Tour::isFinished);
	}

	/**
	 * Update the given {@link Tour}: Create a new empty {@link ActivityBuilder
	 * activity} and apply the {@link ActivityChanger}s. Each application of an
	 * {@link ActivityChanger} can produce multiple clones of the activity. For each
	 * resulting potential activities a clone of the given tour is created and the
	 * activity is added.
	 *
	 * @param tour the tour
	 * @return the list of potential tours
	 */
	private List<Tour> updateTour(Tour tour) {
		List<ActivityBuilder> potentialActivities = new ArrayList<>();
		potentialActivities.add(new ActivityBuilder(tour.getRandom().nextLong()));

		List<ActivityBuilder> newActivities = new ArrayList<>();
		activityChangers.forEach(changer -> { //DO NOT USE PARALLEL STREAM HERE: THE ORDER OF ACTIVITY CHANGERS IS IMPORTANT
			potentialActivities.forEach(a -> newActivities.addAll(this.updateActivity(tour, a, changer)));
			potentialActivities.clear();
			potentialActivities.addAll(newActivities);
			newActivities.clear();
		});

		return potentialActivities.stream().map(a -> tour.clone().add(a.build())).toList();
	}

	/**
	 * Applying the given {@link ActivityChanger} to the given {@link ActivityBuilder}.
	 *
	 * @param <T>      type of the {@link ActivityChanger}'s result
	 * @param tour     the tour
	 * @param activity the activity
	 * @param changer  the changer
	 * @return the list
	 */
	private <T> List<ActivityBuilder> updateActivity(Tour tour, ActivityBuilder activity, ActivityChanger changer) {
		return changer.select(tour.getBusiness(), tour.getVehicle(), tour, activity, activity.getRandom().nextDouble());
	}

}
