package tradesim.simulation.activity;

import static java.lang.Math.abs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tradesim.model.activity.ActivityBuilder;
import tradesim.model.activity.Purpose;
import tradesim.model.business.Business;
import tradesim.model.opportunity.Opportunity;
import tradesim.model.opportunity.OpportunityType;
import tradesim.model.tour.Tour;
import tradesim.model.vehicles.Vehicle;
import tradesim.model.vehicles.VehicleCategory;
import tradesim.simulation.activity.destinationchoice.DefaultDestinationChoiceModelLogger;
import tradesim.simulation.activity.destinationchoice.DestinationTypeLogitModel;
import tradesim.simulation.activity.destinationchoice.DestinationTypeModelHelper;
import tradesim.simulation.activity.destinationchoice.DestinationTypeModelHelperImpl;
import tradesim.util.input.Parameters;
import tradesim.util.random.ParameterizedDistribution;
import tradesim.util.type.Time;
import tradesim.util.type.Tuple;

/**
 * The Class DistanceBasedDestinationChoice is a {@link DestinationChoiceModel}
 * selecting a destination based on the expected distance of a trip.
 */
public class DistanceBasedDestinationChoice implements DestinationChoiceModel {
	private static final double M_TO_KM = 0.001d;
	
	private final Map<OpportunityType, Collection<Opportunity>> opportunities;
	private final DestinationTypeLogitModel destinationTypeChoice;
	private final double detourFactor;
	private final double maxDelta;
	private final int maxDestinations;
	private final ParameterizedDistribution<Tuple<VehicleCategory, String>, Double> meanSpeedDistribution;

	/**
	 * Instantiates a new {@link DistanceBasedDestinationChoice}.
	 *
	 * @param opportunities the opportunities
	 * @param parameters    the parameters
	 * @param logging       the logging
	 * @param detourFactor  the detour factor
	 * @param maxDelta      the maximum distance delta
	 * @param maxDestinations the maximum number of destinations to consider
	 * @param meanSpeedDistribution the mean speed distribution
	 */
	public DistanceBasedDestinationChoice(Collection<Opportunity> opportunities, Parameters parameters, boolean logging,
			double detourFactor, double maxDelta, int maxDestinations, ParameterizedDistribution<Tuple<VehicleCategory, String>, Double> meanSpeedDistribution) {
		this.opportunities = new HashMap<>();

		for (OpportunityType type : OpportunityType.values()) {
			this.opportunities.put(type, opportunities.stream().filter(o -> o.getType().equals(type)).toList());
		}

		DestinationTypeModelHelper helper = new DestinationTypeModelHelperImpl();
		if (logging) {
			this.destinationTypeChoice = new DestinationTypeLogitModel(parameters, helper,
					new DefaultDestinationChoiceModelLogger());
		} else {
			this.destinationTypeChoice = new DestinationTypeLogitModel(parameters, helper);
		}

		this.detourFactor = detourFactor;
		this.maxDelta = maxDelta;
		this.maxDestinations = maxDestinations;
		this.meanSpeedDistribution = meanSpeedDistribution;
	}

	/**
	 * Selects a {@link Opportunity destinations} based on the expected distance of
	 * the given {@link ActivityBuilder activity}. The simulation's
	 * {@link Opportunity destinations} are filtered by {@link OpportunityType type}
	 * and such that only destinations matching the expected distance (+- maximum
	 * delta) are considered. If no destinations are in the expected range, the
	 * closest destination is selected.
	 *
	 * @param business     the business
	 * @param vehicle      the vehicle
	 * @param tour         the tour
	 * @param activity     the activity
	 * @param randomNumber the random number
	 * @return the selected destinations
	 */
	@Override
	public List<Opportunity> possibleResults(Business business, Vehicle vehicle, Tour tour, ActivityBuilder activity,
			double randomNumber) {

		if (activity.getPurpose().equals(Purpose.RETURN)) {
			return List.of(business.getLocation());
		}

		Opportunity currentLocaction = tour.currentLocation();
		OpportunityType type = destinationTypeChoice.select(business, vehicle, tour, activity, randomNumber);
		double expectedDistanceKm = activity.getExpectedTripDistanceKm();

		List<Opportunity> inRange = new ArrayList<>();
		Map<Opportunity, Double> diffs = new HashMap<>();
		this.opportunities.get(type).forEach(o -> {
			double dist = o.distance(currentLocaction) * M_TO_KM * detourFactor;
			double diff = abs(expectedDistanceKm - dist);
			
			diffs.put(o, diff);
			
			if (diff < maxDelta) {
				inRange.add(o);
			}

		});

		if (inRange.isEmpty()) {

			Opportunity nearest = diffs.entrySet().stream().min((e1, e2) -> e1.getValue().compareTo(e2.getValue()))
					.get().getKey();

			return List.of(nearest);

		} else {			
			return inRange.stream().sorted((d1,d2) -> Double.compare(diffs.get(d1), diffs.get(d2))).limit(maxDestinations).toList();
		}

	}

	/**
	 * Applies the selected destination to the given {@link ActivityBuilder
	 * activity}. Also sets the actual trip distance and start of the activity by
	 * applying the {@link Vehicle}'s mean speed.
	 *
	 * @param activity    the activity
	 * @param value       the value
	 * @param current     the current
	 * @param currentTime the current time
	 * @param vehicle     the vehicle
	 * @return the activity builder
	 */
	@Override
	public ActivityBuilder apply(ActivityBuilder activity, Opportunity value, Opportunity current, Time currentTime,
			Vehicle vehicle) {
		double distKm = current.distance(value) * M_TO_KM * detourFactor;
		
		double speed = this.meanSpeedDistribution.draw(activity.getRandom().nextDouble(), vehicle.getCategory(), distanceToRange(distKm));

		int durationSeconds = (int) ((distKm / speed) * 60.0 * 60.0);
		Time start = currentTime.plusSeconds(durationSeconds);

		return activity.withDestination(value).withActualTripDistance(distKm).startingAt(start);
	}
	
	private String distanceToRange(double distance) {
		if (distance < 0.25) {
			return "0-0.25";
		}
		if (distance < 0.5) {
			return "0.25-0.5";
		}
		if (distance < 0.75) {
			return "0.5-0.75";
		}
		if (distance < 2) {
			return "0.75-2";
		}
		if (distance < 5) {
			return "2-5";
		}
		if (distance < 15) {
			return "5-15";
		}
		if (distance < 30) {
			return "15-30";
		}
		if (distance < 50) {
			return "30-50";
		}
		if (distance < 100) {
			return "50-100";
		}
		return "100+";
	}

}
