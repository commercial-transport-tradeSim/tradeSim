package tradesim.simulation.tour.lasttour;

import java.util.Map;

import tradesim.model.business.Sector;
import tradesim.model.vehicles.VehicleCategory;
import tradesim.util.type.Time;

/**
 * The Class NullContinueTourChoiceModelLogger logging nothing.
 */
public class NullContinueTourChoiceModelLogger implements ContinueTourChoiceModelLogger {
	
	/**
	 * Log start.
	 *
	 * @param sector the sector
	 * @param category the category
	 * @param time the time
	 * @param randomNumber the random number
	 */
	@Override
	public void logStart(Sector sector, VehicleCategory category, Time time, double randomNumber) {
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
	 * Log.
	 *
	 * @param category the category
	 * @param key the key
	 * @param value the value
	 */
	@Override
	public void log(boolean category, String key, double value) {
		//Do Nothing, since this is a Null-Object Logger
	}
	
	/**
	 * Log.
	 *
	 * @param key the key
	 * @param value the value
	 */
	@Override
	public void log(String key, double value) {
		//Do Nothing, since this is a Null-Object Logger
	}
	
}

