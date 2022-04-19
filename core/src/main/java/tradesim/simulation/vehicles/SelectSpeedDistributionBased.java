package tradesim.simulation.vehicles;

import java.io.File;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tradesim.model.vehicles.VehicleCategory;
import tradesim.simulation.SimulationContext;
import tradesim.util.input.config.Configurable;
import tradesim.util.input.csv.CsvFile;
import tradesim.util.input.csv.Row;
import tradesim.util.random.ParameterizedDistribution;
import tradesim.util.type.Singleton;


/**
 * The Class SelectSpeedDistributionBased is a builder for a {@link DistributionBasedVehicleSpeed} {@link VehicleChanger}.
 */
@Getter
@Setter
@ToString
public class SelectSpeedDistributionBased implements Configurable<SimulationContext, VehicleChanger<SimulationContext>> {
	
	private String meanSpeedDistribution;
	
	/**
	 * Builds the {@link DistributionBasedVehicleSpeed}.
	 *
	 * @param context the context
	 * @return the vehicle changer
	 */
	@Override
	public VehicleChanger<SimulationContext> build(SimulationContext context) {
		CsvFile csv = new CsvFile(new File(meanSpeedDistribution), ',');
		
		ParameterizedDistribution<Singleton<VehicleCategory>, Double> distribution 
			= new ParameterizedDistribution<>(Singleton.instance(), 70.0d);
		
		distribution.addAll(csv, this::toSingleKey, this::toValue);
		
		return new DistributionBasedVehicleSpeed(distribution);
	}

	/**
	 * Validate the speed distribution csv file.
	 */
	@Override
	public void validate() {
		if (!CsvFile.isCsvFile(meanSpeedDistribution)) {
			throw new IllegalArgumentException("The given mean speed distribution file " + meanSpeedDistribution+ " is not a .csv file or does not exist");
		}
	}
	
	
	/**
	 * Map csv row to a single key.
	 *
	 * @param row the row
	 * @return the list
	 */
	private List<Object> toSingleKey(Row row) {
		VehicleCategory category = VehicleCategory.fromInt(row.getIntValue("category"));
		
		return List.of(category);
	} 
	
	/**
	 * Map csv row to a value.
	 *
	 * @param row the row
	 * @return the double
	 */
	private Double toValue(Row row) {
		return row.getDoubleValue("speed");
	}

	

}
