package tradesim.simulation.activity.purposechoice.other;

import java.util.Map;

import tradesim.model.activity.Purpose;
import tradesim.model.business.Business;
import tradesim.model.tour.Tour;
import tradesim.model.vehicles.Vehicle;
import tradesim.util.output.ConsoleLogger;
import tradesim.util.output.Logger;

/**
 * The Class DefaultPurposeChoiceModelLogger.
 */
public class DefaultPurposeChoiceModelLogger implements PurposeChoiceModelLogger {
	
	private final Logger log;
	
	/**
	 * Instantiates a new default purpose choice model logger.
	 */
	public DefaultPurposeChoiceModelLogger() {
		this.log = new ConsoleLogger("DefaultPurposeChoiceModelLogger");
	}
	
	/**
	 * Log start.
	 *
	 * @param business the business
	 * @param vehicle the vehicle
	 * @param tour the tour
	 * @param randomNumber the random number
	 */
	@Override
	public void logStart(Business business, Vehicle vehicle, Tour tour, double randomNumber) {
		log.write("Start choice model PurposeChoiceModel for sector " +  business.getSector() + ", vehicle " + vehicle.getCategory() + " activity no " + (tour.length()+1) + ", randomNumber " + randomNumber );
	}
	
	/**
	 * Log probabilities.
	 *
	 * @param probabilities the probabilities
	 */
	@Override
	public void logProbabilities(Map<Purpose, Double> probabilities) {
		StringBuilder message = new StringBuilder();
		message.append("Probabilities :" );
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
	public void log(Purpose category, String key, double value) {
		log.write("Utility Parameter - "  + String.format("%s: %s = %s", String.valueOf(category), key, value) );
	}
	
	/**
	 * Log key and value.
	 *
	 * @param key the key
	 * @param value the value
	 */
	@Override
	public void log(String key, double value) {
		log.write(String.format("Utility %s = %s", String.valueOf(key), value) );
	}
	
}

