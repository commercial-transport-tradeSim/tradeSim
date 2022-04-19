package tradesim.simulation.activity.purposechoice.other;

import tradesim.model.activity.ActivityBuilder;
import tradesim.model.activity.Purpose;
import tradesim.model.business.Business;
import tradesim.model.tour.Tour;
import tradesim.model.vehicles.Vehicle;

/**
 * The Interface NestedLogitPurposeChoiceHelper.
 */
public interface NestedLogitPurposeChoiceHelper {
	
	/**
	 * Gets the cs goods.
	 *
	 * @param category the category
	 * @param business the business
	 * @param vehicle the vehicle
	 * @param tour the tour
	 * @param activity the activity
	 * @param randomNumber the random number
	 * @return the cs goods
	 */
	double getCS_GOODS(Purpose category, Business business, Vehicle vehicle, Tour tour, ActivityBuilder activity, double randomNumber);
		
	/**
	 * Gets the cs person.
	 *
	 * @param category the category
	 * @param business the business
	 * @param vehicle the vehicle
	 * @param tour the tour
	 * @param activity the activity
	 * @param randomNumber the random number
	 * @return the cs person
	 */
	double getCS_PERSON(Purpose category, Business business, Vehicle vehicle, Tour tour, ActivityBuilder activity, double randomNumber);
	
	/**
	 * Gets the cs private.
	 *
	 * @param category the category
	 * @param business the business
	 * @param vehicle the vehicle
	 * @param tour the tour
	 * @param activity the activity
	 * @param randomNumber the random number
	 * @return the cs private
	 */
	double getCS_PRIVATE(Purpose category, Business business, Vehicle vehicle, Tour tour, ActivityBuilder activity, double randomNumber);
	
	/**
	 * Gets the cs return.
	 *
	 * @param category the category
	 * @param business the business
	 * @param vehicle the vehicle
	 * @param tour the tour
	 * @param activity the activity
	 * @param randomNumber the random number
	 * @return the cs return
	 */
	double getCS_RETURN(Purpose category, Business business, Vehicle vehicle, Tour tour, ActivityBuilder activity, double randomNumber);
	
	/**
	 * Gets the cs service.
	 *
	 * @param category the category
	 * @param business the business
	 * @param vehicle the vehicle
	 * @param tour the tour
	 * @param activity the activity
	 * @param randomNumber the random number
	 * @return the cs service
	 */
	double getCS_SERVICE(Purpose category, Business business, Vehicle vehicle, Tour tour, ActivityBuilder activity, double randomNumber);
	
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
	
}

