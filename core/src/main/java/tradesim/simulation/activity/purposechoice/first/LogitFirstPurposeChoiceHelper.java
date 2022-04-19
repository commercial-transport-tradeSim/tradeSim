package tradesim.simulation.activity.purposechoice.first;

import tradesim.model.activity.ActivityBuilder;
import tradesim.model.activity.Purpose;
import tradesim.model.business.Business;
import tradesim.model.tour.Tour;
import tradesim.model.vehicles.Vehicle;

/**
 * The Interface LogitFirstPurposeChoiceHelper.
 */
public interface LogitFirstPurposeChoiceHelper {
	
	/**
	 * Gets the checks if is light vehicle.
	 *
	 * @param category the category
	 * @param business the business
	 * @param vehicle the vehicle
	 * @param tour the tour
	 * @param activity the activity
	 * @param randomNumber the random number
	 * @return the checks if is light vehicle
	 */
	double getIS_LIGHT_VEHICLE(Purpose category, Business business, Vehicle vehicle, Tour tour, ActivityBuilder activity, double randomNumber);
	
	/**
	 * Gets the checks if is medium vehicle.
	 *
	 * @param category the category
	 * @param business the business
	 * @param vehicle the vehicle
	 * @param tour the tour
	 * @param activity the activity
	 * @param randomNumber the random number
	 * @return the checks if is medium vehicle
	 */
	double getIS_MEDIUM_VEHICLE(Purpose category, Business business, Vehicle vehicle, Tour tour, ActivityBuilder activity, double randomNumber);
	
	/**
	 * Gets the checks if is sector industry.
	 *
	 * @param category the category
	 * @param business the business
	 * @param vehicle the vehicle
	 * @param tour the tour
	 * @param activity the activity
	 * @param randomNumber the random number
	 * @return the checks if is sector industry
	 */
	double getIS_SECTOR_INDUSTRY(Purpose category, Business business, Vehicle vehicle, Tour tour, ActivityBuilder activity, double randomNumber);
	
	/**
	 * Gets the checks if is sector service.
	 *
	 * @param category the category
	 * @param business the business
	 * @param vehicle the vehicle
	 * @param tour the tour
	 * @param activity the activity
	 * @param randomNumber the random number
	 * @return the checks if is sector service
	 */
	double getIS_SECTOR_SERVICE(Purpose category, Business business, Vehicle vehicle, Tour tour, ActivityBuilder activity, double randomNumber);
	
}

