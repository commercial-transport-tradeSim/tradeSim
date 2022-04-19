package tradesim.simulation.activity;

import java.io.File;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tradesim.simulation.SimulationContext;
import tradesim.util.input.config.Configurable;
import tradesim.util.input.csv.CsvFile;

/**
 * The Class DrawTripDistanceFromDistribution is a builder for {@link DistributionBasedTripDistanceAssigner}.
 */
@Getter
@Setter
@ToString
public class DrawTripDistanceFromDistribution implements Configurable<SimulationContext, ActivityChanger> {
	//Configurable<SimulationContext, TripDistanceModel>, 
	private String file;
	private int defaultDistance;

	/**
	 * Instantiates a new draw trip distance from distribution.
	 */
	public DrawTripDistanceFromDistribution() {
		this.defaultDistance = 20;
	}
	
	/**
	 * Builds the {@link DistributionBasedTripDistanceAssigner}.
	 *
	 * @param context the context
	 * @return the trip distance model
	 */
	@Override
	public TripDistanceModel build(SimulationContext context) {

		CsvFile csv = new CsvFile(new File(file), ',');

		return new DistributionBasedTripDistanceAssigner(csv, defaultDistance);
	}

	/**
	 * Validate the existance of the csv file.
	 */
	@Override
	public void validate() {
		if (!CsvFile.isCsvFile(file)) {
			throw new IllegalArgumentException("The given file '" + file + "' does not exist or is not a .csv file!");
		}
		
		if (defaultDistance <= 0) {
			throw new IllegalArgumentException("The given default duration shoud be > 0 but was " + defaultDistance);
		}
	}
	
	

}
