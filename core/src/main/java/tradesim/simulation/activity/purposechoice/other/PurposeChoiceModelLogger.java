package tradesim.simulation.activity.purposechoice.other;

import java.util.Map;

import tradesim.model.activity.Purpose;
import tradesim.model.business.Business;
import tradesim.model.tour.Tour;
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
	 * @param tour the tour
	 * @param randomNumber the random number
	 */
	public void logStart(Business business, Vehicle vehicle, Tour tour, double randomNumber);
	
	/**
	 * Log probabilities.
	 *
	 * @param probabilities the probabilities
	 */
	public void logProbabilities(Map<Purpose, Double> probabilities);
	
	/**
	 * Log category, key and value.
	 *
	 * @param category the category
	 * @param key the key
	 * @param value the value
	 */
	public void log(Purpose category, String key, double value);
			
	/**
	 * Log key and value.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public void log(String key, double value);
	
}

