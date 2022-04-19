package tradesim.simulation.tour.lasttour;

import java.util.Map;

import tradesim.model.business.Sector;
import tradesim.model.vehicles.VehicleCategory;
import tradesim.util.output.ConsoleLogger;
import tradesim.util.output.Logger;
import tradesim.util.type.Time;

/**
 * The Class DefaultContinueTourChoiceModelLogger.
 */
public class DefaultContinueTourChoiceModelLogger implements ContinueTourChoiceModelLogger {
	
	private final Logger log;
	
	/**
	 * Instantiates a new default continue tour choice model logger.
	 */
	public DefaultContinueTourChoiceModelLogger() {
		this.log = new ConsoleLogger("DefaultECommerceParticipationSelectorLogger");
	}
	
	/**
	 * Log start of choice.
	 *
	 * @param sector the sector
	 * @param category the category
	 * @param time the time
	 * @param randomNumber the random number
	 */
	@Override
	public void logStart(Sector sector, VehicleCategory category, Time time, double randomNumber) {
		log.write("Start choice model ContinueTourChoiceModel for sector: " + sector + ", vehicle category: " +  category + ", time: " + time + ", min: " + time.toMinutes() + ", randomNumber: " + randomNumber );
	}
	
	/**
	 * Log probabilities.
	 *
	 * @param probabilities the probabilities
	 */
	@Override
	public void logProbabilities(Map<String, Double> probabilities) {
		StringBuilder message = new StringBuilder();
		message.append("Probabilities" );
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
	public void log(boolean category, String key, double value) {
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

