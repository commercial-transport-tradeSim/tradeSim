package tradesim.simulation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tradesim.model.business.Sector;
import tradesim.model.vehicles.VehicleCategory;
import tradesim.simulation.activity.ActivityChanger;
import tradesim.simulation.tour.ContinueTourChoiceModel;
import tradesim.simulation.tour.ContinuteToursProbabilityBased;
import tradesim.simulation.tour.FilterToursByLength;
import tradesim.simulation.tour.SelectBestDistanceFitTour;
import tradesim.simulation.tour.TourFilter;
import tradesim.simulation.tour.TourSelector;
import tradesim.util.input.config.Configurable;
import tradesim.util.input.config.RunnableStep;
import tradesim.util.input.csv.CsvFile;
import tradesim.util.input.csv.Row;
import tradesim.util.random.ParameterizedDistribution;
import tradesim.util.type.Triple;
import tradesim.util.type.Tuple;


/**
 * The Class SimulateTours is a builder for the runnable {@link TourSimulationRunner}.
 */
@Getter
@Setter
@ToString
public class SimulateTours implements Configurable<SimulationContext, RunnableStep> {

	private List<Configurable<SimulationContext, ActivityChanger>> activityChangers;
	private Configurable<SimulationContext, TourSelector> tourSelector;
	private Configurable<SimulationContext, TourFilter> tourFilter;
	private Configurable<SimulationContext, ContinueTourChoiceModel> continueChoice;
	private String tourLengthDistribution;
	private double defaultDistance;
	private int defaultNumberOfTrips;
	private int maxParallelTours;
	
	/**
	 * Instantiates a new simulate tours.
	 */
	public SimulateTours() {
		this.tourFilter = new FilterToursByLength();
		this.tourSelector = new SelectBestDistanceFitTour();
		this.continueChoice = new ContinuteToursProbabilityBased();
		this.activityChangers = new ArrayList<>();
		
		this.defaultDistance = 10;
		this.defaultNumberOfTrips = 5;
		this.maxParallelTours = 100;
	}
	
	/**
	 * Builds the {@link TourSimulationRunner}.
	 *
	 * @param context the context
	 * @return the runnable step
	 */
	@Override
	public RunnableStep build(SimulationContext context) {
		TourSelector selector = tourSelector.validateAndBuild(context);
		TourFilter filter = tourFilter.validateAndBuild(context);
		ContinueTourChoiceModel choice = continueChoice.validateAndBuild(context);
		
		List<ActivityChanger> changers = 
			activityChangers.stream()
							.map(c -> c.validateAndBuild(context))
							.toList();
		
		ParameterizedDistribution<Triple<Integer, Sector, VehicleCategory>, Tuple<Double, Integer>> distribution =
				new ParameterizedDistribution<>(Triple.instance(), new Tuple<>(defaultDistance, defaultNumberOfTrips));
		
		distribution.addAll(new CsvFile(new File(tourLengthDistribution), ','), this::toKey, this::toValue);
		
		return new TourSimulationRunner(context, changers, choice, selector, filter, distribution, maxParallelTours);		
	}
	
	/**
	 * Validates the activity changers, tour selector, tour filter and continue choice model.
	 */
	@Override
	public void validate() {
		activityChangers.forEach(c -> c.validate());
		tourSelector.validate();
		tourFilter.validate();
		continueChoice.validate();
		
		if (!CsvFile.isCsvFile(tourLengthDistribution)) {
			throw new IllegalArgumentException("The given tour lenght distribution file '" + tourLengthDistribution + "' does not exist or is not a .csv file!");
		}
	}
	
	/**
	 * Map the csv row to a value tuple.
	 *
	 * @param row the row
	 * @return the tuple
	 */
	private Tuple<Double, Integer> toValue(Row row) {
		double dist = row.getDoubleValue("dist");
		int num = row.getIntValue("num");
		
		return new Tuple<>(dist, num);
	}
	
	/**
	 * Map csv row to list of keys.
	 *
	 * @param row the row
	 * @return the list
	 */
	private List<Object> toKey(Row row) {
		int index = row.getIntValue("index");
		Sector sector = Sector.fromInt(row.getIntValue("sector"));
		VehicleCategory category = VehicleCategory.fromInt(row.getIntValue("vehicle_category"));
		
		return List.of(index, sector, category);
	}
	
}
