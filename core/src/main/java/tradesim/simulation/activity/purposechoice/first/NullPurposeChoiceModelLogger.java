package tradesim.simulation.activity.purposechoice.first;

import java.util.Map;

import tradesim.model.activity.Purpose;
import tradesim.model.business.Business;
import tradesim.model.vehicles.Vehicle;

/**
 * The Class NullPurposeChoiceModelLogger logs nothing.
 */
public class NullPurposeChoiceModelLogger implements PurposeChoiceModelLogger {
	
	/**
	 * Log start of choice.
	 *
	 * @param business the business
	 * @param vehicle the vehicle
	 */
	@Override
	public void logStart(Business business, Vehicle vehicle) {
		//Do Nothing, since this is a Null-Object Logger
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
		//Do Nothing, since this is a Null-Object Logger
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
		//Do Nothing, since this is a Null-Object Logger
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
		//Do Nothing, since this is a Null-Object Logger
	}
	
}

