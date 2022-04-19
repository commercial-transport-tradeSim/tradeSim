package tradesim.simulation;

import java.io.File;
import java.util.Random;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tradesim.util.input.config.Configurable;
import tradesim.util.input.config.RunnableStep;


/**
 * The Class SimulationOptions is a builder for a {@link RunnableStep} to set simulation options.
 */
@Getter
@Setter
@ToString
public class SimulationOptions implements Configurable<SimulationContext, RunnableStep> {

	private long seed;
	private String output;
	
	/**
	 * Instantiates a new simulation options.
	 */
	public SimulationOptions() {
		this.seed = 42;
		this.output = "output";
	}
	
	
	/**
	 * Builds the {@link RunnableStep}.
	 *
	 * @param context the context
	 * @return the runnable step
	 */
	@Override
	public RunnableStep build(SimulationContext context) {
		return () -> {
			context.setRandom(new Random(seed));
			context.setOutputPath(new File(output));
			context.getOutputPath().mkdirs();
		};
	}


	@Override
	public void validate() {}

}
