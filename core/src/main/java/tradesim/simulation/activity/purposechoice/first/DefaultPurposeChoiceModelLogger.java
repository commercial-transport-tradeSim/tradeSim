package tradesim.simulation.activity.purposechoice.first;

import java.util.Map;

import tradesim.model.activity.Purpose;
import tradesim.model.business.Business;
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
		this.log = new ConsoleLogger(this.getClass().getSimpleName());
	}
	
	/**
	 * Log start of choice.
	 *
	 * @param business the business
	 * @param vehicle the vehicle
	 */
	@Override
	public void logStart(Business business, Vehicle vehicle) {
		log.write("Start choice model PurposeChoiceModel for " + String.format("business sector: %s, vehicle category: %s", String.valueOf(business.getSector()), String.valueOf(vehicle.getCategory())));
	}
	
	/**
	 * Log probabilities.
	 *
	 * @param probabilities the probabilities
	 * @param business the business
	 * @param vehicle the vehicle
	 */
	@Override
	public void logProbabilities(Map<Purpose, Double> probabilities, Business business, Vehicle vehicle) {
		StringBuilder message = new StringBuilder();
		message.append("Probabilities:");
		probabilities.entrySet().forEach(entry -> message
				.append(String.format("%s = %s, ", entry.getKey(), entry.getValue())));
		log.write(message.toString());

	}
	
	/**
	 * Log category, key and value.
	 *
	 * @param business the business
	 * @param vehicle the vehicle
	 * @param category the category
	 * @param key the key
	 * @param value the value
	 */
	@Override
	public void log(Business business, Vehicle vehicle, Purpose category, String key, double value) {
		log.write("Utility Parameter - "  + String.format("%s: %s = %s", String.valueOf(category), key, value));
	}
	
	/**
	 * Log key and value.
	 *
	 * @param business the business
	 * @param vehicle the vehicle
	 * @param key the key
	 * @param value the value
	 */
	@Override
	public void log(Business business, Vehicle vehicle, String key, double value) {
		log.write(String.format("Utility %s = %s", String.valueOf(key), value));
	}
	
}

