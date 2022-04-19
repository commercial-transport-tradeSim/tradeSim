package tradesim.simulation.opportunities.orders;

import java.util.Map;

import tradesim.simulation.opportunities.PrivatePerson;
import tradesim.util.output.ConsoleLogger;
import tradesim.util.output.Logger;

/**
 * The Class DefaultECommerceParticipationSelectorLogger.
 */
public class DefaultECommerceParticipationSelectorLogger implements ECommerceParticipationSelectorLogger {
	
	private final Logger log;
	
	/**
	 * Instantiates a new default E commerce participation selector logger.
	 */
	public DefaultECommerceParticipationSelectorLogger() {
		this.log = new ConsoleLogger("DefaultECommerceParticipationSelectorLogger");
	}
	
	/**
	 * Log start of choice.
	 *
	 * @param person the person
	 * @param randomNumber the random number
	 */
	@Override
	public void logStart(PrivatePerson person, double randomNumber) {
		log.write("Start choice model ECommerceParticipationSelector for age: " + person.getAge() + ", sex: " + person.getSex() + ", income hh: " + person.getEconStatus() + ", occupation: " + person.getOccupation() + ", randomNumber: " + randomNumber);
	}
	
	/**
	 * Log probabilities.
	 *
	 * @param probabilities the probabilities
	 */
	@Override
	public void logProbabilities(Map<String, Double> probabilities) {
		StringBuilder message = new StringBuilder();
		message.append("Probabilities:");
		probabilities.entrySet().forEach(entry -> message
				.append(String.format("%s = %s, ", entry.getKey(), entry.getValue())));
		log.write(message.toString());

	}
	
	/**
	 * Log category, key and value.
	 *
	 * @param category the category
	 * @param key the key
	 * @param value the value
	 */
	@Override
	public void log(String category, String key, double value) {
		log.write("Utility Parameter - "  + String.format("%s: %s = %s", String.valueOf(category), key, value));
	}
	
	/**
	 * Log key and value.
	 *
	 * @param key the key
	 * @param value the value
	 */
	@Override
	public void log(String key, double value) {
		log.write(String.format("Utility %s = %s", String.valueOf(key), value));
	}
	
}

