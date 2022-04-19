package tradesim.simulation.tour.lasttour;

import java.util.Map;

import tradesim.model.business.Sector;
import tradesim.model.vehicles.VehicleCategory;
import tradesim.util.type.Time;

/**
 * The Interface ContinueTourChoiceModelLogger.
 */
public interface ContinueTourChoiceModelLogger {
	
	/**
	 * Log start of choice.
	 *
	 * @param sector the sector
	 * @param category the category
	 * @param time the time
	 * @param randomNumber the random number
	 */
	public void logStart(Sector sector, VehicleCategory category, Time time, double randomNumber);
	
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
	public void log(boolean category, String key, double value);
			
	/**
	 * Log key and value.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public void log(String key, double value);
	
}

