package tradesim.simulation.activity;

import java.util.List;

import tradesim.model.activity.ActivityBuilder;
import tradesim.model.business.Business;
import tradesim.model.tour.Tour;
import tradesim.model.vehicles.Vehicle;

/**
 * The Interface TripDistanceModel extends the {@link ActivityChanger}
 * interface and restricts it to changing the expected trip distance of an
 * {@link ActivityBuilder activity}.
 */
public interface TripDistanceModel extends ActivityChanger {
	
	@Override
	default List<ActivityBuilder> select(Business business, Vehicle vehicle, Tour tour, ActivityBuilder activity, double randomNumber) {

		return 
		possibleResults(business, vehicle, tour, activity, randomNumber)
			.stream()
			.map(v -> apply(activity.clone(),v))
			.toList();

	}
	
	/**
	 * Selects an expected trip distance for the given {@link ActivityBuilder activity}
	 * nondeterministically.
	 *
	 * @param business the business
	 * @param vehicle the vehicle
	 * @param tour the tour
	 * @param activity the activity
	 * @param randomNumber the random number
	 * @return the list of selected trip distances
	 */
	public List<Double> possibleResults(Business business, Vehicle vehicle, Tour tour, ActivityBuilder activity, double randomNumber);
	
	/**
	 * Applies the selected expected trip distance to the given {@link ActivityBuilder activity}.
	 *
	 * @param activity the activity
	 * @param value the value
	 * @return the modified activity with expected trip distance
	 */
	default ActivityBuilder apply(ActivityBuilder activity, Double value) {
		return activity.withExpectedTripDistance(value);
	}
	
}
