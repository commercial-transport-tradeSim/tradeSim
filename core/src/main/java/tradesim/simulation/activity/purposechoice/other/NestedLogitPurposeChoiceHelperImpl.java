package tradesim.simulation.activity.purposechoice.other;

import tradesim.model.activity.ActivityBuilder;
import tradesim.model.activity.Purpose;
import tradesim.model.business.Business;
import tradesim.model.tour.Tour;
import tradesim.model.vehicles.Vehicle;
import tradesim.model.vehicles.VehicleCategory;

/**
 * The Class NestedLogitPurposeChoiceHelperImpl.
 */
public class NestedLogitPurposeChoiceHelperImpl implements NestedLogitPurposeChoiceHelper {

	/**
	 * Tour contains activity.
	 *
	 * @param tour the tour
	 * @param purpose the purpose
	 * @return true, if successful
	 */
	private boolean tourContainsActivity(Tour tour, Purpose purpose) {
		return tour.getActivities().stream().anyMatch(a -> a.getPurpose().equals(purpose));
	}
	
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
	@Override
	public double getCS_GOODS(Purpose category, Business business, Vehicle vehicle, Tour tour, ActivityBuilder activity,
			double randomNumber) {
		return (tourContainsActivity(tour, Purpose.DELIVERY)) ? 1.0 : 0.0;
	}

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
	@Override
	public double getCS_PERSON(Purpose category, Business business, Vehicle vehicle, Tour tour, ActivityBuilder activity,
			double randomNumber) {
		return (tourContainsActivity(tour, Purpose.DROP_OFF)) ? 1.0 : 0.0;
	}

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
	@Override
	public double getCS_PRIVATE(Purpose category, Business business, Vehicle vehicle, Tour tour, ActivityBuilder activity,
			double randomNumber) {
		return (tourContainsActivity(tour, Purpose.PRIVATE)) ? 1.0 : 0.0;
	}

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
	@Override
	public double getCS_RETURN(Purpose category, Business business, Vehicle vehicle, Tour tour, ActivityBuilder activity,
			double randomNumber) {
		return (tourContainsActivity(tour, Purpose.RETURN)) ? 1.0 : 0.0;
	}

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
	@Override
	public double getCS_SERVICE(Purpose category, Business business, Vehicle vehicle, Tour tour, ActivityBuilder activity,
			double randomNumber) {
		return (tourContainsActivity(tour, Purpose.SERVICE)) ? 1.0 : 0.0;
	}

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
	public double getIS_LIGHT_VEHICLE(Purpose category, Business business, Vehicle vehicle, Tour tour, ActivityBuilder activity,
			double randomNumber) {
		return vehicle.getCategory().equals(VehicleCategory.LIGHT) ? 1.0d : 0.0d;
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
	public double getIS_MEDIUM_VEHICLE(Purpose category, Business business, Vehicle vehicle, Tour tour, ActivityBuilder activity,
			double randomNumber) {
		return vehicle.getCategory().equals(VehicleCategory.MEDIUM) ? 1.0d : 0.0d;
	}

}
