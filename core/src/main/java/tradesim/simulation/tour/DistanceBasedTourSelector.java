package tradesim.simulation.tour;

import java.util.Comparator;
import java.util.List;

import tradesim.model.business.Business;
import tradesim.model.tour.Tour;
import tradesim.model.vehicles.Vehicle;

/**
 * The Class DistanceBasedTourSelector is a {@link TourSelector} taking the
 * expected and actual distance of the potential tours into account.
 */
public class DistanceBasedTourSelector implements TourSelector {

	/**
	 * Selects one of the given potential tours.
	 *
	 * @param business       the business
	 * @param vehicle        the vehicle
	 * @param potentialTours the potential tours
	 * @param randomNumber   the random number
	 * @return the tour
	 */
	@Override
	public Tour select(Business business, Vehicle vehicle, List<Tour> potentialTours, double randomNumber) {
		return potentialTours.stream().min(comparator).get();
	}
	
	private final static Comparator<Tour> comparator = 
			Comparator.comparing((Tour t) -> Math.abs(t.getExpectedDistanceKm() - t.actualDistanceSum()))
					  .thenComparing((Tour t) -> Math.abs(t.getExpectedNumberOfTrips() - t.length()));

}
