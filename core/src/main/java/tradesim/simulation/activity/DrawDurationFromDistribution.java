package tradesim.simulation.activity;

import java.io.File;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tradesim.simulation.SimulationContext;
import tradesim.util.input.config.Configurable;
import tradesim.util.input.csv.CsvFile;

/**
 * The Class DrawDurationFromDistribution is a builder for {@link DistributionBasedActivityDurationAssigner}.
 */
@Getter
@Setter
@ToString
public class DrawDurationFromDistribution implements Configurable<SimulationContext, ActivityChanger> {
	//Configurable<SimulationContext, ActivityDurationModel>, 
	private String file;
	private int defaultDuration;

	/**
	 * Instantiates a new draw duration from distribution.
	 */
	public DrawDurationFromDistribution() {
		this.defaultDuration = 15;
	}
	
	/**
	 * Builds the {@link DistributionBasedActivityDurationAssigner}.
	 *
	 * @param context the context
	 * @return the activity duration model
	 */
	@Override
	public ActivityDurationModel build(SimulationContext context) {

		CsvFile csv = new CsvFile(new File(file), ',');

		return new DistributionBasedActivityDurationAssigner(csv, defaultDuration);
	}

	/**
	 * Validate the existance of the distribution csv file.
	 */
	@Override
	public void validate() {
		if (!CsvFile.isCsvFile(file)) {
			throw new IllegalArgumentException("The given file '" + file + "' does not exist or is not a .csv file!");
		}
		
		if (defaultDuration < 1) {
			throw new IllegalArgumentException("The given default duration shoud be >= 1 but was " + defaultDuration);
		}
	}
	
	

}
