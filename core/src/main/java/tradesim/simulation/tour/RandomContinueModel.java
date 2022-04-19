package tradesim.simulation.tour;

import java.util.List;

import tradesim.model.business.Business;
import tradesim.model.tour.Tour;
import tradesim.model.vehicles.Vehicle;

/**
 * The Class RandomContinueModel is a probability based {@link ContinueTourChoiceModel}.
 */
public class RandomContinueModel implements ContinueTourChoiceModel {
	
	private final int max;
	private final double probability;

	/**
	 * Instantiates a new random continue model.
	 *
	 * @param probability the probability
	 * @param max the max
	 */
	public RandomContinueModel(double probability, int max) {
		this.max = max;
		this.probability = probability;
	}
	
	/**
	 * Decides whether the given {@link Vehicle} should make another tour.
	 *
	 * @param business the business
	 * @param vehicle the vehicle
	 * @param currentTours the current tours
	 * @param randomNumber the random number
	 * @return true, if successful
	 */
	@Override
	public boolean select(Business business, Vehicle vehicle, List<Tour> currentTours, double randomNumber) {
		return currentTours.size() < max && randomNumber < probability;
	}

}
