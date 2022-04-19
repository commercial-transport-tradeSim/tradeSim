package tradesim.simulation.tour.lasttour;

import java.util.List;

import tradesim.model.business.Business;
import tradesim.model.business.Sector;
import tradesim.model.tour.Tour;
import tradesim.model.vehicles.Vehicle;
import tradesim.model.vehicles.VehicleCategory;

/**
 * The Class ContinueTourLogitModelHelperImpl.
 */
public class ContinueTourLogitModelHelperImpl implements ContinueTourLogitHelper {

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
	@Override
	public double getIS_MEDIUM_VEHICLE(boolean category, Business business, Vehicle vehicle,
			List<Tour> currentTours, double randomNumber) {
		return vehicle.getCategory().equals(VehicleCategory.MEDIUM) ? 1.0 : 0.0;
	}

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
	@Override
	public double getIS_LIGHT_VEHICLE(boolean category, Business business, Vehicle vehicle,
			List<Tour> currentTours, double randomNumber) {
		return vehicle.getCategory().equals(VehicleCategory.LIGHT) ? 1.0 : 0.0;
	}

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
	@Override
	public double getIS_SECTOR_INDUSTRY(boolean category, Business business, Vehicle vehicle,
			List<Tour> currentTours, double randomNumber) {
		return business.getSector().equals(Sector.INDUSTRY) ? 1.0 : 0.0;
	}

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
	@Override
	public double getIS_SECTOR_SERVICE(boolean category, Business business, Vehicle vehicle,
			List<Tour> currentTours, double randomNumber) {
		return business.getSector().equals(Sector.SERVICE) ? 1.0 : 0.0;
	}

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
	@Override
	public double getEND_MINUTES(boolean category, Business business, Vehicle vehicle, List<Tour> currentTours,	double randomNumber) {
		
		if (currentTours.size() > 0) {
			return currentTours.get(currentTours.size() - 1).currentTime().toMinutes();
		}
		
		return 0;
	}

}
