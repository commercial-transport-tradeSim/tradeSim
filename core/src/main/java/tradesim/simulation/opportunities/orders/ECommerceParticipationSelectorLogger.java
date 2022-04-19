package tradesim.simulation.opportunities.orders;

import java.util.Map;

import tradesim.simulation.opportunities.PrivatePerson;

/**
 * The Interface ECommerceParticipationSelectorLogger.
 */
public interface ECommerceParticipationSelectorLogger {
	
	/**
	 * Log start of choice.
	 *
	 * @param person the person
	 * @param randomNumber the random number
	 */
	public void logStart(PrivatePerson person, double randomNumber);
	
	/**
	 * Log probabilities.
	 *
	 * @param probabilities the probabilities
	 */
	public void logProbabilities(Map<String, Double> probabilities);
	
	/**
	 * Log category, key and value.
	 *
	 * @param category the category
	 * @param key the key
	 * @param value the value
	 */
	public void log(String category, String key, double value);
			
	/**
	 * Log key and value.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public void log(String key, double value);
	
}

