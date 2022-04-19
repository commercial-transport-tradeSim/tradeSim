package tradesim.simulation.activity.purposechoice.first;

import java.util.Map;

import tradesim.model.activity.Purpose;
import tradesim.model.business.Business;
import tradesim.model.vehicles.Vehicle;

/**
 * The Interface PurposeChoiceModelLogger.
 */
public interface PurposeChoiceModelLogger {
	
	/**
	 * Log start of choice.
	 *
	 * @param business the business
	 * @param vehicle the vehicle
	 */
	public void logStart(Business business, Vehicle vehicle);
	
	/**
	 * Log probabilities.
	 *
	 * @param probabilities the probabilities
	 * @param business the business
	 * @param vehicle the vehicle
	 */
	public void logProbabilities(Map<Purpose, Double> probabilities, Business business, Vehicle vehicle);
	
	/**
	 * Log category, key and value.
	 *
	 * @param business the business
	 * @param vehicle the vehicle
	 * @param category the category
	 * @param key the key
	 * @param value the value
	 */
	public void log(Business business, Vehicle vehicle, Purpose category, String key, double value);
			
	/**
	 * Log key and value.
	 *
	 * @param business the business
	 * @param vehicle the vehicle
	 * @param key the key
	 * @param value the value
	 */
	public void log(Business business, Vehicle vehicle, String key, double value);
	
}

