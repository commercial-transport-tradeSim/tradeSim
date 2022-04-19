package tradesim.simulation.opportunities;

import java.io.File;
import java.io.IOException;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tradesim.simulation.SimulationContext;
import tradesim.util.input.Parameters;
import tradesim.util.input.config.Configurable;
import tradesim.util.input.csv.CsvFile;


/**
 * The Class LoadEcommerceCustomerOpportunities is a builder for a customer {@link OpportunityLoader}.
 */
@Getter
@Setter
@ToString
public class LoadEcommerceCustomerOpportunities implements Configurable<SimulationContext, OpportunityLoader<SimulationContext>> {

	private String population;
	private String parameters;
	private double fraction;
	private boolean logging;
	
	/**
	 * Instantiates a new load ecommerce customer opportunities.
	 */
	public LoadEcommerceCustomerOpportunities() {
		this.fraction = 1.0;
		this.logging = false;
	}

	
	/**
	 * Builds the {@link CustomerOpportunityModel}.
	 *
	 * @param context the context
	 * @return the opportunity loader
	 */
	@Override
	public OpportunityLoader<SimulationContext> build(SimulationContext context) {
		try {
			
			CsvFile file = new CsvFile(population, ',');
			Parameters params = Parameters.loadFrom(new File(parameters));

			return new CustomerOpportunityModel(file, params, fraction, logging);
			
		} catch (IOException e) {
			throw new IllegalArgumentException("Could not load population or parameters: " + population + ", " + parameters, e);
		}
	}

	/**
	 * Validates the existence of the population csv and the parameter file.
	 */
	@Override
	public void validate() {
		if (!CsvFile.isCsvFile(population)) {
			throw new IllegalArgumentException("The given population file '" + population + "' does not exist or is not a .csv file!");
		}
		
		if (!new File(parameters).exists()) {
			throw new IllegalArgumentException("The given parameter file '" + parameters + "' does not exist!");
		}

	}

	
	
	
}
