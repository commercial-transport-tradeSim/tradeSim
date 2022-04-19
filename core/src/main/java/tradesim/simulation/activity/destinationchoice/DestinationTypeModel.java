package tradesim.simulation.activity.destinationchoice;

import tradesim.model.activity.ActivityBuilder;
import tradesim.model.business.Business;
import tradesim.model.opportunity.OpportunityType;
import tradesim.model.tour.Tour;
import tradesim.model.vehicles.Vehicle;

/**
 * The Interface DestinationTypeModel.
 */
public interface DestinationTypeModel {

	/**
	 * Select a {@link OpportunityType} for the given {@link ActivityBuilder activity}.
	 *
	 * @param business the business
	 * @param vehicle the vehicle
	 * @param tour the tour
	 * @param activity the activity
	 * @param randomNumber the random number
	 * @return the opportunity type
	 */
	public OpportunityType select(Business business, Vehicle vehicle, Tour tour, ActivityBuilder activity,
			double randomNumber);

}
