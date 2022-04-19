package tradesim.simulation.activity.destinationchoice;

import tradesim.model.activity.ActivityBuilder;
import tradesim.model.activity.Purpose;
import tradesim.model.business.Business;
import tradesim.model.business.Sector;
import tradesim.model.business.Trade;
import tradesim.model.opportunity.OpportunityType;
import tradesim.model.tour.Tour;
import tradesim.model.vehicles.Vehicle;
import tradesim.model.vehicles.VehicleCategory;

/**
 * The Class DestinationTypeModelHelperImpl.
 */
public class DestinationTypeModelHelperImpl implements DestinationTypeModelHelper {

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
	public double getIS_LIGHT_VEHICLE(OpportunityType category, Business business, Vehicle vehicle, Tour tour,	ActivityBuilder activity, double randomNumber) {
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
	public double getIS_MEDIUM_VEHICLE(OpportunityType category, Business business, Vehicle vehicle, Tour tour,	ActivityBuilder activity, double randomNumber) {
		return vehicle.getCategory().equals(VehicleCategory.MEDIUM) ? 1.0 : 0.0;
	}

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
	@Override
	public double getIS_PURPOSE_DELIVERY(OpportunityType category, Business business, Vehicle vehicle, Tour tour,	ActivityBuilder activity, double randomNumber) {
		return activity.getPurpose().equals(Purpose.DELIVERY) ? 1.0 : 0.0;
	}

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
	@Override
	public double getIS_PURPOSE_DROP_OFF(OpportunityType category, Business business, Vehicle vehicle, Tour tour,	ActivityBuilder activity, double randomNumber) {
		return activity.getPurpose().equals(Purpose.DROP_OFF) ? 1.0 : 0.0;
	}

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
	@Override
	public double getIS_PURPOSE_SERVICE(OpportunityType category, Business business, Vehicle vehicle, Tour tour, ActivityBuilder activity, double randomNumber) {
		return activity.getPurpose().equals(Purpose.SERVICE) ? 1.0 : 0.0;
	}

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
	@Override
	public double getIS_TRADE_INDUSTRY(OpportunityType category, Business business, Vehicle vehicle, Tour tour,	ActivityBuilder activity, double randomNumber) {
		return business.getSector().equals(Sector.INDUSTRY) ? 1.0 : 0.0;
	}

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
	@Override
	public double getIS_TRADE_SERVICE(OpportunityType category, Business business, Vehicle vehicle, Tour tour,	ActivityBuilder activity, double randomNumber) {
		return business.getSector().equals(Sector.SERVICE) ? 1.0 : 0.0;
	}

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
	@Override
	public double getIS_TRADE_F_value(OpportunityType category, Business business, Vehicle vehicle, Tour tour,
			ActivityBuilder activity, double randomNumber) {
		return business.getTrade().equals(Trade.F) ? 1.0 : 0.0;
	}

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
	@Override
	public double getIS_TRADE_G_value(OpportunityType category, Business business, Vehicle vehicle, Tour tour,
			ActivityBuilder activity, double randomNumber) {
		return business.getTrade().equals(Trade.G) ? 1.0 : 0.0;
	}

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
	@Override
	public double getIS_TRADE_H_value(OpportunityType category, Business business, Vehicle vehicle, Tour tour,
			ActivityBuilder activity, double randomNumber) {
		return business.getTrade().equals(Trade.H) ? 1.0 : 0.0;
	}

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
	@Override
	public double getIS_TRADE_Q_value(OpportunityType category, Business business, Vehicle vehicle, Tour tour,
			ActivityBuilder activity, double randomNumber) {
		return business.getTrade().equals(Trade.Q) ? 1.0 : 0.0;
	}

}
