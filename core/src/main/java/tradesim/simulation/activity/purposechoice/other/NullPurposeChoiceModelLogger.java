package tradesim.simulation.activity.purposechoice.other;

import java.util.Map;

import tradesim.model.activity.Purpose;
import tradesim.model.business.Business;
import tradesim.model.tour.Tour;
import tradesim.model.vehicles.Vehicle;

/**
 * The Class NullPurposeChoiceModelLogger.
 */
public class NullPurposeChoiceModelLogger implements PurposeChoiceModelLogger {
	
	/**
	 * Log start of choice.
	 *
	 * @param business the business
	 * @param vehicle the vehicle
	 * @param tour the tour
	 * @param randomNumber the random number
	 */
	@Override
	public void logStart(Business business, Vehicle vehicle, Tour tour, double randomNumber) {
		//Do Nothing, since this is a Null-Object Logger
	}
	
	/**
	 * Log probabilities.
	 *
	 * @param probabilities the probabilities
	 */
	@Override
	public void logProbabilities(Map<Purpose, Double> probabilities) {
		//Do Nothing, since this is a Null-Object Logger
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

