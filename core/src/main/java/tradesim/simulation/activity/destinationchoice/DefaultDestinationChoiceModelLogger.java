package tradesim.simulation.activity.destinationchoice;

import java.util.Map;

import tradesim.model.activity.Purpose;
import tradesim.model.business.Sector;
import tradesim.model.opportunity.OpportunityType;
import tradesim.model.vehicles.VehicleCategory;
import tradesim.util.output.ConsoleLogger;
import tradesim.util.output.Logger;

/**
 * The Class DefaultDestinationChoiceModelLogger.
 */
public class DefaultDestinationChoiceModelLogger implements DestinationChoiceModelLogger {
	
	private final Logger log;
	
	/**
	 * Instantiates a new default destination choice model logger.
	 */
	public DefaultDestinationChoiceModelLogger() {
		this.log = new ConsoleLogger("DefaultDestinationChoiceModelLogger");
	}
	
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
		log.write("Start choice model DestinationChoiceModel for sector: " + sector + ", category: " + category + ", purpose: " + purpose+ ", randomNumber: " + randomNumber);
	}
	
	/**
	 * Log probabilities.
	 *
	 * @param probabilities the probabilities
	 */
	@Override
	public void logProbabilities(Map<OpportunityType, Double> probabilities) {
		StringBuilder message = new StringBuilder();
		message.append("Probabilities");
		message.append(": ");
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
	public void log(OpportunityType category, String key, double value) {
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

