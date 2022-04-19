package tradesim.simulation.activity;

import java.io.File;
import java.io.IOException;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tradesim.model.vehicles.VehicleCategory;
import tradesim.simulation.SimulationContext;
import tradesim.util.input.Parameters;
import tradesim.util.input.config.Configurable;
import tradesim.util.input.csv.CsvFile;
import tradesim.util.input.csv.Row;
import tradesim.util.random.ParameterizedDistribution;
import tradesim.util.type.Tuple;

/**
 * The Class SelectDestinationDistanceBased is a builder for {@link DistanceBasedDestinationChoice}.
 */
@Getter
@Setter
@ToString
public class SelectDestinationDistanceBased implements Configurable<SimulationContext, ActivityChanger> {
//Configurable<SimulationContext, DestinationChoiceModel>, 
	private String parameters;
	private boolean logging;
	private double detourFactor;
	private double maxDeltaKm;
	private int maxDestinations;
	private String meanSpeedDistribution;
	
	/**
	 * Instantiates a new select destination distance based.
	 */
	public SelectDestinationDistanceBased() {
		this.logging = false;
		this.detourFactor = 1.5;
		this.maxDeltaKm = 3;
		this.maxDestinations = 10;
	}
	
	/**
	 * Builds the {@link DistanceBasedDestinationChoice}.
	 *
	 * @param context the context
	 * @return the destination choice model
	 */
	@Override
	public DestinationChoiceModel build(SimulationContext context) {
		CsvFile csv = new CsvFile(new File(meanSpeedDistribution), ',');
		
		ParameterizedDistribution<Tuple<VehicleCategory, String>, Double> distribution 
			= new ParameterizedDistribution<>(Tuple.instance(), 50.0d);
		
		distribution.addAll(csv, this::toKey, this::toValue);
		
		Parameters params;
		try {
			params = Parameters.loadFrom(new File(parameters));		
			
			return new DistanceBasedDestinationChoice(context.getOpportunities(), params, logging, detourFactor, maxDeltaKm, maxDestinations, distribution);
			
		} catch (IOException e) {
			throw new IllegalArgumentException("Could not load the given parameter file " + parameters, e);
		}
		
		
	}
	
	/**
	 * Validate the existence of the parameter file.
	 */
	@Override
	public void validate() {
		if (!new File(parameters).exists()) {
			throw new IllegalArgumentException("The given parameter file " + parameters+ "does not exist");
		}
		
		if (detourFactor < 1) {
			throw new IllegalArgumentException("The given detour factor should be >= 1 but was " + detourFactor);
		}
		
		if (maxDeltaKm <= 0) {
			throw new IllegalArgumentException("The given max delta should be > 0 km but was " + maxDeltaKm);
		}
		
		if (!CsvFile.isCsvFile(meanSpeedDistribution)) {
			throw new IllegalArgumentException("The given mean speed distribution file " + meanSpeedDistribution+ " is not a .csv file or does not exist");
		}
	}
	
	
	/**
	 * Maps csv row to list of keys.
	 *
	 * @param row the row
	 * @return the list
	 */
	private List<Object> toKey(Row row) {
		VehicleCategory category = VehicleCategory.fromInt(row.getIntValue("vehicle_category"));
		String range = row.getStringValue("distance_range");
		
		return List.of(category, range);
	} 
	
	/**
	 * Maps csv row to a value.
	 *
	 * @param row the row
	 * @return the double
	 */
	private Double toValue(Row row) {
		return row.getDoubleValue("speed");
	}

}
