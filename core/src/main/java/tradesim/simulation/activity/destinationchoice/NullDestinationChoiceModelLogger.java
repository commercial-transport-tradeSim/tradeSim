package tradesim.simulation.activity.destinationchoice;

import java.util.Map;

import tradesim.model.activity.Purpose;
import tradesim.model.business.Sector;
import tradesim.model.opportunity.OpportunityType;
import tradesim.model.vehicles.VehicleCategory;

/**
 * The Class NullDestinationChoiceModelLogger logs nothing.
 */
public class NullDestinationChoiceModelLogger implements DestinationChoiceModelLogger {
	
	/**
	 * Log start of choice.
	 *
	 * @param sector the sector
	 * @param category the category
	 * @param purpose the purpose
	 * @param randomNumber the random number
	 */
	@Override
	public void logStart(Sector sector, VehicleCategory category, Purpose purpose, double randomNumber) {
		//Do Nothing, since this is a Null-Object Logger
	}
	
	/**
	 * Log probabilities.
	 *
	 * @param probabilities the probabilities
	 */
	@Override
	public void logProbabilities(Map<OpportunityType, Double> probabilities) {
		//Do Nothing, since this is a Null-Object Logger
	}
	
	/**
	 * Log category, key and value.
	 *
	 * @param category the category
	 * @param key the key
	 * @param value the value
	 */
	@Override
	public void log(OpportunityType category, String key, double value) {
		//Do Nothing, since this is a Null-Object Logger
	}
	
	/**
	 * Log key and value.
	 *
	 * @param key the key
	 * @param value the value
	 */
	@Override
	public void log(String key, double value) {
		//Do Nothing, since this is a Null-Object Logger
	}
	
}

