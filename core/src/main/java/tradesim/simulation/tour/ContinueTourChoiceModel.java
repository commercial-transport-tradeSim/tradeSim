package tradesim.simulation.tour;

import java.util.List;

import tradesim.model.business.Business;
import tradesim.model.tour.Tour;
import tradesim.model.vehicles.Vehicle;

/**
 * The Interface ContinueTourChoiceModel provides a method for models to
 * determine whether a {@link Vehicle} should continue with a new {@link Tour}.
 */
public interface ContinueTourChoiceModel {

	/**
	 * Determines whether the given {@link Vehicle} should continue with a new tour.
	 *
	 * @param business     the business
	 * @param vehicle      the vehicle
	 * @param currentTours the completed tours
	 * @param randomNumber the random number
	 * @return true, if successful
	 */
	public boolean select(Business business, Vehicle vehicle, List<Tour> currentTours, double randomNumber);

}
