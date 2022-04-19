package tradesim.simulation.activity.destinationchoice;

import tradesim.model.activity.ActivityBuilder;
import tradesim.model.business.Business;
import tradesim.model.opportunity.OpportunityType;
import tradesim.model.tour.Tour;
import tradesim.model.vehicles.Vehicle;

/**
 * The Interface DestinationTypeModelHelper.
 */
public interface DestinationTypeModelHelper {
	
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
	double getIS_LIGHT_VEHICLE(OpportunityType category, Business business, Vehicle vehicle, Tour tour, ActivityBuilder activity, double randomNumber);
	
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
	double getIS_MEDIUM_VEHICLE(OpportunityType category, Business business, Vehicle vehicle, Tour tour, ActivityBuilder activity, double randomNumber);
	
	/**
	 * Gets the checks if is purpose delivery.
	 *
	 * @param category the category
	 * @param business the business
	 * @param vehicle the vehicle
	 * @param tour the tour
	 * @param activity the activity
	 * @param randomNumber the random number
	 * @return the checks if is purpose delivery
	 */
	double getIS_PURPOSE_DELIVERY(OpportunityType category, Business business, Vehicle vehicle, Tour tour, ActivityBuilder activity, double randomNumber);
	
	/**
	 * Gets the checks if is purpose drop off.
	 *
	 * @param category the category
	 * @param business the business
	 * @param vehicle the vehicle
	 * @param tour the tour
	 * @param activity the activity
	 * @param randomNumber the random number
	 * @return the checks if is purpose drop off
	 */
	double getIS_PURPOSE_DROP_OFF(OpportunityType category, Business business, Vehicle vehicle, Tour tour, ActivityBuilder activity, double randomNumber);
	
	/**
	 * Gets the checks if is purpose service.
	 *
	 * @param category the category
	 * @param business the business
	 * @param vehicle the vehicle
	 * @param tour the tour
	 * @param activity the activity
	 * @param randomNumber the random number
	 * @return the checks if is purpose service
	 */
	double getIS_PURPOSE_SERVICE(OpportunityType category, Business business, Vehicle vehicle, Tour tour, ActivityBuilder activity, double randomNumber);
	
	/**
	 * Gets the checks if is trade industry.
	 *
	 * @param category the category
	 * @param business the business
	 * @param vehicle the vehicle
	 * @param tour the tour
	 * @param activity the activity
	 * @param randomNumber the random number
	 * @return the checks if is trade industry
	 */
	double getIS_TRADE_INDUSTRY(OpportunityType category, Business business, Vehicle vehicle, Tour tour, ActivityBuilder activity, double randomNumber);
	
	/**
	 * Gets the checks if is trade service.
	 *
	 * @param category the category
	 * @param business the business
	 * @param vehicle the vehicle
	 * @param tour the tour
	 * @param activity the activity
	 * @param randomNumber the random number
	 * @return the checks if is trade service
	 */
	double getIS_TRADE_SERVICE(OpportunityType category, Business business, Vehicle vehicle, Tour tour, ActivityBuilder activity, double randomNumber);

	/**
	 * Gets the i S TRAD E F value.
	 *
	 * @param category the category
	 * @param business the business
	 * @param vehicle the vehicle
	 * @param tour the tour
	 * @param activity the activity
	 * @param randomNumber the random number
	 * @return the i S TRAD E F value
	 */
	double getIS_TRADE_F_value(OpportunityType category, Business business, Vehicle vehicle, Tour tour, ActivityBuilder activity, double randomNumber);

	/**
	 * Gets the i S TRAD E G value.
	 *
	 * @param category the category
	 * @param business the business
	 * @param vehicle the vehicle
	 * @param tour the tour
	 * @param activity the activity
	 * @param randomNumber the random number
	 * @return the i S TRAD E G value
	 */
	double getIS_TRADE_G_value(OpportunityType category, Business business, Vehicle vehicle, Tour tour,	ActivityBuilder activity, double randomNumber);

	/**
	 * Gets the i S TRAD E H value.
	 *
	 * @param category the category
	 * @param business the business
	 * @param vehicle the vehicle
	 * @param tour the tour
	 * @param activity the activity
	 * @param randomNumber the random number
	 * @return the i S TRAD E H value
	 */
	double getIS_TRADE_H_value(OpportunityType category, Business business, Vehicle vehicle, Tour tour,	ActivityBuilder activity, double randomNumber);

	/**
	 * Gets the i S TRAD E Q value.
	 *
	 * @param category the category
	 * @param business the business
	 * @param vehicle the vehicle
	 * @param tour the tour
	 * @param activity the activity
	 * @param randomNumber the random number
	 * @return the i S TRAD E Q value
	 */
	double getIS_TRADE_Q_value(OpportunityType category, Business business, Vehicle vehicle, Tour tour,	ActivityBuilder activity, double randomNumber);
	
}

