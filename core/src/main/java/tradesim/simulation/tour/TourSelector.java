package tradesim.simulation.tour;

import java.util.List;

import tradesim.model.business.Business;
import tradesim.model.tour.Tour;
import tradesim.model.vehicles.Vehicle;

/**
 * The Interface TourSelector.
 */
public interface TourSelector {
	
	/**
	 * Select one {@link Tour} from the given list of tours.
	 *
	 * @param business the business
	 * @param vehicle the vehicle
	 * @param potentialTours the potential tours
	 * @param randomNumber the random number
	 * @return the tour
	 */
	public Tour select(Business business, Vehicle vehicle, List<Tour> potentialTours, double randomNumber);

}
