package tradesim.simulation.activity;

import java.util.List;

import tradesim.model.activity.Activity;
import tradesim.model.activity.ActivityBuilder;
import tradesim.model.activity.Purpose;
import tradesim.model.business.Business;
import tradesim.model.tour.Tour;
import tradesim.model.vehicles.Vehicle;

/**
 * The Class StagedPurposeChoice is a {@link PurposeChoiceModel} consisting of two stages,
 * the first stage being applied to the first {@link Activity} of a {@link Tour}.
 */
public class StagedPurposeChoice implements PurposeChoiceModel {

	private final PurposeChoiceModel firstChoice;
	private final PurposeChoiceModel secondChoice;
	
	/**
	 * Instantiates a new staged purpose choice with the given stages.
	 *
	 * @param firstChoice the first choice
	 * @param secondChoice the second choice
	 */
	public StagedPurposeChoice(PurposeChoiceModel firstChoice, PurposeChoiceModel secondChoice) {
		this.firstChoice = firstChoice;
		this.secondChoice = secondChoice;
	}
	
	
	/**
	 * Selects the purpose for the given {@link ActivityBuilder activity}.
	 * If it is the first activity of the given {@link Tour} the first stage is applied.
	 * Otherwise the other stage is applied (by delegateion).
	 *
	 * @param business the business
	 * @param vehicle the vehicle
	 * @param tour the tour
	 * @param activity the activity
	 * @param randomNumber the random number
	 * @return the list
	 */
	@Override
	public List<ActivityBuilder> select(Business business, Vehicle vehicle, Tour tour, ActivityBuilder activity,
			double randomNumber) {
		if (tour.length() == 0) {
			return firstChoice.select(business, vehicle, tour, activity, randomNumber);
		} else {
			return secondChoice.select(business, vehicle, tour, activity, randomNumber);
		}
	}


	/**
	 * Possible results should not be called, as  StagedPurposeChoice#select() delegates to one of the two stages.
	 *
	 * @param business the business
	 * @param vehicle the vehicle
	 * @param tour the tour
	 * @param activity the activity
	 * @param randomNumber the random number
	 * @return the list
	 */
	@Override
	public List<Purpose> possibleResults(Business business, Vehicle vehicle, Tour tour, ActivityBuilder activity,
			double randomNumber) {
		throw new UnsupportedOperationException("Should not be calls since StagedPurposeChoice delegates the choice");
	}
	
	/**
	 * Apply should not be called, as StagedPurposeChoice#select() delegates to one of the two stages.
	 *
	 * @param activity the activity
	 * @param value the value
	 * @return the activity builder
	 */
	@Override
	public ActivityBuilder apply(ActivityBuilder activity, Purpose value) {
		throw new UnsupportedOperationException("Should not be calls since StagedPurposeChoice delegates the value application");
	}
	


}
