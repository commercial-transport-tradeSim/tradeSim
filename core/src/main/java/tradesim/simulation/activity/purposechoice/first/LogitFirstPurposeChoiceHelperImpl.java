package tradesim.simulation.activity.purposechoice.first;

import tradesim.model.activity.ActivityBuilder;
import tradesim.model.activity.Purpose;
import tradesim.model.business.Business;
import tradesim.model.business.Sector;
import tradesim.model.tour.Tour;
import tradesim.model.vehicles.Vehicle;
import tradesim.model.vehicles.VehicleCategory;

/**
 * The Class LogitFirstPurposeChoiceHelperImpl.
 */
public class LogitFirstPurposeChoiceHelperImpl implements LogitFirstPurposeChoiceHelper {

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
	@Override
	public double getIS_LIGHT_VEHICLE(Purpose category, Business business, Vehicle vehicle, Tour tour,
			ActivityBuilder activity, double randomNumber) {
		return vehicle.getCategory().equals(VehicleCategory.LIGHT) ? 1.0 : 0.0;
	}

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
	@Override
	public double getIS_MEDIUM_VEHICLE(Purpose category, Business business, Vehicle vehicle, Tour tour,
			ActivityBuilder activity, double randomNumber) {
		return vehicle.getCategory().equals(VehicleCategory.MEDIUM) ? 1.0 : 0.0;
	}

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
	@Override
	public double getIS_SECTOR_INDUSTRY(Purpose category, Business business, Vehicle vehicle, Tour tour,
			ActivityBuilder activity, double randomNumber) {
		return business.getSector().equals(Sector.INDUSTRY) ? 1.0 : 0.0;
	}

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
	@Override
	public double getIS_SECTOR_SERVICE(Purpose category, Business business, Vehicle vehicle, Tour tour,
			ActivityBuilder activity, double randomNumber) {
		return business.getSector().equals(Sector.INDUSTRY) ? 1.0 : 0.0;
	}

}
