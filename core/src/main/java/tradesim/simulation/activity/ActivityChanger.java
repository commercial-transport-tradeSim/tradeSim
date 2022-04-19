package tradesim.simulation.activity;

import java.util.List;

import tradesim.model.activity.Activity;
import tradesim.model.activity.ActivityBuilder;
import tradesim.model.business.Business;
import tradesim.model.tour.Tour;
import tradesim.model.vehicles.Vehicle;

/**
 * The Interface ActivityChanger provides a method for changing
 * {@link ActivityBuilder}s nondeterministically producing multiple modified
 * clones of a given {@link ActivityBuilder}.
 */
public interface ActivityChanger {

	/**
	 * Creates multiple clones of the given {@link ActivityBuilder} and modifies one
	 * or more of its properties. The given {@link ActivityBuilder} is to be next
	 * {@link Activity} of the given {@link Tour} which is performed by the given
	 * {@link Vehicle} of the given {@link Business}.
	 *
	 * @param business     the business
	 * @param vehicle      the vehicle
	 * @param tour         the tour
	 * @param activity     the activity
	 * @param randomNumber the random number
	 * @return the list of activities
	 */
	public List<ActivityBuilder> select(Business business, Vehicle vehicle, Tour tour, ActivityBuilder activity,
			double randomNumber);

}
