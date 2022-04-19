package tradesim.simulation.vehicles;

import java.io.File;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tradesim.simulation.SimulationContext;
import tradesim.util.input.config.Configurable;
import tradesim.util.input.csv.CsvFile;

/**
 * The Class SelectDeparture is a builder for the {@link DistributionBasedDepartureTime} {@link VehicleChanger}.
 */
@Getter
@Setter
@ToString
public class SelectDeparture implements Configurable<SimulationContext, VehicleChanger<SimulationContext>> {

	private String distributionFile;
	
	/**
	 * Builds the {@link DistributionBasedDepartureTime}.
	 *
	 * @param context the context
	 * @return the vehicle changer
	 */
	@Override
	public VehicleChanger<SimulationContext> build(SimulationContext context) {
		return new DistributionBasedDepartureTime(new CsvFile(new File(distributionFile)));
	}

	/**
	 * Validate the distribution csv.
	 */
	@Override
	public void validate() {
		if (!CsvFile.isCsvFile(distributionFile)) {
			throw new IllegalArgumentException("The given distribution file " + distributionFile+ " is not a .csv file or does not exist");
		}
	}
	
	

}
