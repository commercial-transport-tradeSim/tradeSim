package tradesim.simulation.business;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tradesim.simulation.SimulationContext;
import tradesim.util.input.config.Configurable;
import tradesim.util.input.config.RunnableStep;
import tradesim.util.input.csv.CsvFile;

/**
 * The Class LoadBusinesses is a builder for the {@link BusinessLoader} class.
 * The parameters can be inferred from yaml configurations.
 */
@Getter
@Setter
@ToString
public class LoadBusinesses implements Configurable<SimulationContext, RunnableStep> {

	private String file;
	private String opportunityIds;
	private double fraction;
	private List<Configurable<SimulationContext, BusinessChanger<SimulationContext>>> changers;
	
	/**
	 * Instantiates a new load businesses.
	 */
	public LoadBusinesses() {
		this.fraction = 1.0;
		this.changers = new ArrayList<>();
		this.opportunityIds = null;
	}

	/**
	 * Builds the.
	 *
	 * @param context the context
	 * @return the runnable step to load businesses
	 */
	@Override
	public RunnableStep build(SimulationContext context) {
		List<BusinessChanger<SimulationContext>> businessChangers =
			changers.stream()
					.map(c -> c.build(context))
					.toList();
		
		File ids = null;
		if (opportunityIds != null) {
			ids = new File(opportunityIds);
		}
		
		return new BusinessLoader(new File(file), ids, fraction, context.getRandom().nextLong(), businessChangers, context);
	}

	/**
	 * Validate the input parameters before building the runnable step.
	 */
	@Override
	public void validate() {
		if (opportunityIds!=null && !CsvFile.isCsvFile(opportunityIds)) {
			throw new IllegalArgumentException("The given opportunityIds file '" + opportunityIds + "' does not exist or is not a .csv file!");
		}
		
		if (!CsvFile.isCsvFile(file)) {
			throw new IllegalArgumentException("The given file '" + file + "' does not exist or is not a .csv file!");
		}
	}



}
