package tradesim.simulation.opportunities.orders;

import java.util.Map;

import tradesim.simulation.opportunities.PrivatePerson;

/**
 * The Class NullECommerceParticipationSelectorLogger.
 */
public class NullECommerceParticipationSelectorLogger implements ECommerceParticipationSelectorLogger {
	
	/**
	 * Log start of choice.
	 *
	 * @param person the person
	 * @param randomNumber the random number
	 */
	@Override
	public void logStart(PrivatePerson person, double randomNumber) {
		//Do Nothing, since this is a Null-Object Logger
	}
	
	/**
	 * Log probabilities.
	 *
	 * @param probabilities the probabilities
	 */
	@Override
	public void logProbabilities(Map<String, Double> probabilities) {
		//Do Nothing, since this is a Null-Object Logger
	}
	
	/**
	 * Log category key and value.
	 *
	 * @param category the category
	 * @param key the key
	 * @param value the value
	 */
	@Override
	public void log(String category, String key, double value) {
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

