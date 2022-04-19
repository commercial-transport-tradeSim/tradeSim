package tradesim.model.activity;

import lombok.Getter;
import tradesim.model.opportunity.Opportunity;
import tradesim.util.type.Time;

/**
 * The class Activity models activities performed by agents in the simulation.
 * Immutable data object holding the properties of an activity.
 * 
 * @author Jelle Kübler
 */
@Getter
public class Activity {

	private final int id;
	private final Time start;
	private final Time duration;
	private final double expectedTripDistanceKm;
	private final double actualTripDistanceKm;
	private final Purpose purpose;
	private final Opportunity destination;
	
	/**
	 * Instantiates a new activity with the given properties.
	 *
	 * @param id the id
	 * @param start the start
	 * @param duration the duration
	 * @param purpose the purpose
	 * @param destination the destination
	 * @param expectedTripDistanceKm the expected trip distance km
	 * @param actualTripDistanceKm the actual trip distance km
	 */
	public Activity(int id, Time start, Time duration, Purpose purpose, Opportunity destination, double expectedTripDistanceKm, double actualTripDistanceKm) {
		this.id = id;
		this.start = start;
		this.duration = duration;
		this.expectedTripDistanceKm = expectedTripDistanceKm;
		this.actualTripDistanceKm = actualTripDistanceKm;
		this.purpose = purpose;
		this.destination = destination;
	}
	
	/**
	 * Gets the end time of the activity.
	 * The end of the activity equals the start of the activity plus its duration.
	 *
	 * @return the end time
	 */
	public Time getEnd() {
		return start.plus(duration);
	}

	/**
	 * Creates a copy of the activity.
	 *
	 * @return a copy of the activity
	 */
	public Activity clone() {
		return new Activity(id, start, duration, purpose, destination, expectedTripDistanceKm, actualTripDistanceKm);
	}
}
