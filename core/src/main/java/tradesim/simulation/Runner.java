package tradesim.simulation;

import java.io.File;
import java.io.IOException;

import tradesim.util.input.config.Configuration;
import tradesim.util.input.config.ConfigurationBuilder;

/**
 * The Class Runner is a main class for running the simulation from a configuration file.
 */
public class Runner {

	/**
	 * Parses the configuration file given in the agruments and runs the parsed {@link Configuration}.
	 *
	 * @param args the arguments
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.err.println("Invalid start arguments! Expected: ... <CONFIG_FILE>");
			System.exit(-1);
		}
		
		String file = args[0];
		
		Configuration<SimulationContext> context =
			new ConfigurationBuilder<SimulationContext>()
				.usingDefaultTags()
				.log()
				.using(new File(file))
				.build();
			
			SimulationContext c = new SimulationContext();
			context.run(c);
	}
}
