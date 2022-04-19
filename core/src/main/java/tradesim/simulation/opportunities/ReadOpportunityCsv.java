package tradesim.simulation.opportunities;

import java.io.File;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tradesim.model.opportunity.OpportunityType;
import tradesim.simulation.SimulationContext;
import tradesim.util.input.config.Configurable;
import tradesim.util.input.csv.CsvFile;

/**
 * The Class ReadOpportunityCsv is a builder for an {@link OpportunityLoader} for an arbitrary {@link OpportunityType} read from a csv file.
 */
@Getter
@Setter
@ToString
public class ReadOpportunityCsv implements Configurable<SimulationContext, OpportunityLoader<SimulationContext>> {
	
	private String file;
	private String type;
	
	/**
	 * Instantiates a new read opportunity csv.
	 */
	public ReadOpportunityCsv() {
		this.type = "OTHER";
	}
	
	/**
	 * Builds the {@link OpportunityLoader}.
	 *
	 * @param context the context
	 * @return the opportunity loader
	 */
	@Override
	public OpportunityLoader<SimulationContext> build(SimulationContext context) {
		return new CsvBasedOpportunityLoader<>(new CsvFile(new File(file)), OpportunityType.valueOf(type));
	}

	/**
	 * Validate the input parameters before building the {@link OpportunityLoader}.
	 */
	@Override
	public void validate() {
		if (!CsvFile.isCsvFile(file)) {
			throw new IllegalArgumentException("The given file '" + file + "' does not exist or is not a .csv file!");
		}
		
		try {
			OpportunityType.valueOf(type);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Could not parse the given opportunity type: " + type, e);
		}
	}
	


}
