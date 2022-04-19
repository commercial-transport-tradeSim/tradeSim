package tradesim.simulation.activity;

import java.util.List;

import tradesim.model.activity.ActivityBuilder;
import tradesim.model.business.Business;
import tradesim.model.tour.Tour;
import tradesim.model.vehicles.Vehicle;
import tradesim.util.type.Time;

/**
 * The Interface ActivityStartSelector extends the {@link ActivityChanger}
 * interface and restricts it to changing the start {@link Time} of an
 * {@link ActivityBuilder activity}.
 */
public interface ActivityStartSelector extends ActivityChanger {

	@Override
	default List<ActivityBuilder> select(Business business, Vehicle vehicle, Tour tour, ActivityBuilder activity,
			double randomNumber) {

		return possibleResults(business, vehicle, tour, activity, randomNumber).stream()
				.map(v -> apply(activity.clone(), v)).toList();

	}

	/**
	 * Selects a start {@link Time} for the given {@link ActivityBuilder activity}
	 * nondeterministically.
	 *
	 * @param business     the business
	 * @param vehicle      the vehicle
	 * @param tour         the tour
	 * @param activity     the activity
	 * @param randomNumber the random number
	 * @return the list of selected start times
	 */
	public List<Time> possibleResults(Business business, Vehicle vehicle, Tour tour, ActivityBuilder activity,
			double randomNumber);

	/**
	 * Applies the selected start {@link Time} to the given {@link ActivityBuilder
	 * activity}.
	 *
	 * @param activity the activity
	 * @param value    the value
	 * @return the modified activity with start time
	 */
	default ActivityBuilder apply(ActivityBuilder activity, Time value) {
		return activity.startingAt(value);
	}

}
