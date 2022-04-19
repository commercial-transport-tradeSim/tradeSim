package tradesim.simulation.tour.lasttour;

import java.util.List;

import tradesim.model.business.Business;
import tradesim.model.tour.Tour;
import tradesim.model.vehicles.Vehicle;

/**
 * The Interface ContinueTourLogitHelper.
 */
public interface ContinueTourLogitHelper {
	
	/**
	 * Gets the checks if is medium vehicle.
	 *
	 * @param category the category
	 * @param business the business
	 * @param vehicle the vehicle
	 * @param currentTours the current tours
	 * @param randomNumber the random number
	 * @return the checks if is medium vehicle
	 */
	double getIS_MEDIUM_VEHICLE(boolean category, Business business, Vehicle vehicle, List<Tour> currentTours, double randomNumber);
	
	/**
	 * Gets the checks if is light vehicle.
	 *
	 * @param category the category
	 * @param business the business
	 * @param vehicle the vehicle
	 * @param currentTours the current tours
	 * @param randomNumber the random number
	 * @return the checks if is light vehicle
	 */
	double getIS_LIGHT_VEHICLE(boolean category, Business business, Vehicle vehicle, List<Tour> currentTours, double randomNumber);
	
	/**
	 * Gets the checks if is sector industry.
	 *
	 * @param category the category
	 * @param business the business
	 * @param vehicle the vehicle
	 * @param currentTours the current tours
	 * @param randomNumber the random number
	 * @return the checks if is sector industry
	 */
	double getIS_SECTOR_INDUSTRY(boolean category, Business business, Vehicle vehicle, List<Tour> currentTours, double randomNumber);
	
	/**
	 * Gets the checks if is sector service.
	 *
	 * @param category the category
	 * @param business the business
	 * @param vehicle the vehicle
	 * @param currentTours the current tours
	 * @param randomNumber the random number
	 * @return the checks if is sector service
	 */
	double getIS_SECTOR_SERVICE(boolean category, Business business, Vehicle vehicle, List<Tour> currentTours, double randomNumber);
	
	/**
	 * Gets the end minutes.
	 *
	 * @param category the category
	 * @param business the business
	 * @param vehicle the vehicle
	 * @param currentTours the current tours
	 * @param randomNumber the random number
	 * @return the end minutes
	 */
	double getEND_MINUTES(boolean category, Business business, Vehicle vehicle, List<Tour> currentTours, double randomNumber);	

}

