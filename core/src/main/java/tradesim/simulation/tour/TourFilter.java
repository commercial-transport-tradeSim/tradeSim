package tradesim.simulation.tour;

import tradesim.model.business.Business;
import tradesim.model.tour.Tour;
import tradesim.model.vehicles.Vehicle;

/**
 * The Interface TourFilter.
 */
public interface TourFilter {

	/**
	 * Test the given {@link Tour}.
	 *
	 * @param business the business
	 * @param vehicle the vehicle
	 * @param tour the tour
	 * @return true, if successful
	 */
	public boolean test(Business business, Vehicle vehicle, Tour tour);
	
}
