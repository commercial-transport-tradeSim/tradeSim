package tradesim.simulation.activity;

import java.util.List;

import tradesim.model.activity.ActivityBuilder;
import tradesim.model.business.Business;
import tradesim.model.opportunity.Opportunity;
import tradesim.model.tour.Tour;
import tradesim.model.vehicles.Vehicle;
import tradesim.util.type.Time;

/**
 * The Interface DestinationChoiceModel extends the {@link ActivityChanger}
 * interface and restricts it to changing the {@link Opportunity destination} of an
 * {@link ActivityBuilder activity}.
 */
public interface DestinationChoiceModel extends ActivityChanger {

	@Override
	default List<ActivityBuilder> select(Business business, Vehicle vehicle, Tour tour, ActivityBuilder activity,
			double randomNumber) {

		return possibleResults(business, vehicle, tour, activity, randomNumber).stream()
				.map(v -> apply(activity.clone(), v, tour.currentLocation(), tour.currentTime(), tour.getVehicle()))
				.toList();

	}

	/**
	 * Selects a {@link Opportunity destination} for the given {@link ActivityBuilder activity}
	 * nondeterministically.
	 *
	 * @param business     the business
	 * @param vehicle      the vehicle
	 * @param tour         the tour
	 * @param activity     the activity
	 * @param randomNumber the random number
	 * @return the list of selected destinations
	 */
	public List<Opportunity> possibleResults(Business business, Vehicle vehicle, Tour tour, ActivityBuilder activity,
			double randomNumber);

	/**
	 * Applies the selected {@link Opportunity destination} to the given {@link ActivityBuilder activity}.
	 *
	 * @param activity    the activity
	 * @param value       the value
	 * @param previous    the previous
	 * @param currentTime the current time
	 * @param vehicle     the vehicle
	 * @return the modified activity with destination
	 */
	default ActivityBuilder apply(ActivityBuilder activity, Opportunity value, Opportunity previous, Time currentTime,
			Vehicle vehicle) {
		return activity.withDestination(value);
	}

}
