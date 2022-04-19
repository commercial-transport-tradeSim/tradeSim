package tradesim.simulation.output;

import java.io.File;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tradesim.simulation.SimulationContext;
import tradesim.util.input.config.Configurable;
import tradesim.util.input.config.MultipleSteps;
import tradesim.util.input.config.RunnableStep;
import tradesim.util.output.ResultFileWriter;

/**
 * The Class ExportCsv is a builder for runnable {@link ResultFileWriter}s to write csv result files.
 */
@Getter
@Setter
@ToString
public class ExportCsv implements Configurable<SimulationContext, RunnableStep> {
	
	private String vehicleFile;
	private String activityFile;

	
	/**
	 * Instantiates a new export csv.
	 */
	public ExportCsv() {
		this.vehicleFile = "vehicle.csv";
		this.vehicleFile = "vehicle.csv";
	}

	/**
	 * Builds the {@link ResultFileWriter}s.
	 *
	 * @param context the context
	 * @return the runnable step
	 */
	@Override
	public RunnableStep build(SimulationContext context) {
		MultipleSteps steps = new MultipleSteps();
		
		File vehicle = new File( context.getOutputPath().getAbsolutePath() + "/" + vehicleFile); 
		File activity = new File( context.getOutputPath().getAbsolutePath() + "/" + activityFile); 
		
		steps.add(new VehicleFileWriter(vehicle, context));
		steps.add(new ActivityFileWriter(activity, context));

		return steps;
	}

	/**
	 * Validates the file names to assert they end with .csv.
	 */
	@Override
	public void validate() {
		if (!vehicleFile.endsWith(".csv")) {
			throw new IllegalArgumentException(vehicleFile + "should end with .csv");
		}
		
		if (!activityFile.endsWith(".csv")) {
			throw new IllegalArgumentException(activityFile + "should end with .csv");
		}
	}

}
