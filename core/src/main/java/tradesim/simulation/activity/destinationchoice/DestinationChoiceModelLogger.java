package tradesim.simulation.activity.destinationchoice;

import java.util.Map;

import tradesim.model.activity.Purpose;
import tradesim.model.business.Sector;
import tradesim.model.opportunity.OpportunityType;
import tradesim.model.vehicles.VehicleCategory;

/**
 * The Interface DestinationChoiceModelLogger.
 */
public interface DestinationChoiceModelLogger {
	
	/**
	 * Log start.
	 *
	 * @param sector the sector
	 * @param category the category
	 * @param purpose the purpose
	 * @param randomNumber the random number
	 */
	public void logStart(Sector sector, VehicleCategory category, Purpose purpose, double randomNumber);
	
	/**
	 * Log probabilities.
	 *
	 * @param probabilities the probabilities
	 */
	public void logProbabilities(Map<OpportunityType, Double> probabilities);
	
	/**
	 * Log category, key and value.
	 *
	 * @param category the category
	 * @param key the key
	 * @param value the value
	 */
	public void log(OpportunityType category, String key, double value);
			
	/**
	 * Log key and value.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public void log(String key, double value);
	
}

