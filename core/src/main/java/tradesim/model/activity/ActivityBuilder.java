package tradesim.model.activity;

import java.util.Random;

import javax.print.attribute.standard.Destination;

import lombok.Getter;
import tradesim.model.opportunity.Opportunity;
import tradesim.util.random.RandomCloner;
import tradesim.util.type.Time;

/**
 * The Class ActivityBuilder is a builder for {@link Activity} objects. It is a
 * mutable version of {@link Activity} and produces the immutable version upon
 * building it.
 *
 * @author Jelle Kübler
 */
public class ActivityBuilder {

	private static int ID_CNT = 0;

	@Getter
	private final Random random;
	@Getter
	private Time start;
	@Getter
	private Time duration;
	@Getter
	private double expectedTripDistanceKm;
	@Getter
	private Purpose purpose;
	@Getter
	private Opportunity destination;
	@Getter
	private double actualTripDistanceKm;

	/**
	 * Instantiates a new activity builder with a new {@link Random} provider using
	 * the given seed.
	 *
	 * @param seed the seed of the {@link ActivityBuilder}'s random number generator
	 */
	public ActivityBuilder(long seed) {
		this.random = new Random(seed);
	}

	/**
	 * Instantiates a new activity builder with the given {@link Random} object.
	 * This constructor is only used for cloning an {@link ActivityBuilder} with an
	 * exact copy of the {@link Random} provider.
	 *
	 * @param random the random number generator.
	 */
	private ActivityBuilder(Random random) {
		this.random = random;
	}

	/**
	 * Sets the activitie's starting {@link Time}.
	 *
	 * @param start the start time
	 * @return the activity builder
	 */
	public ActivityBuilder startingAt(Time start) {
		this.start = start;
		return this;
	}

	/**
	 * Sets the activitie's duration.
	 *
	 * @param duration the duration
	 * @return the activity builder
	 */
	public ActivityBuilder ofDuration(Time duration) {
		this.duration = duration;
		return this;
	}

	/**
	 * Sets the activitie's {@link Purpose}.
	 *
	 * @param purpose the purpose
	 * @return the activity builder
	 */
	public ActivityBuilder withPurpose(Purpose purpose) {
		this.purpose = purpose;
		return this;
	}

	/**
	 * Sets the activitie's expected trip distance in km.
	 *
	 * @param distanceKm the expected distance in km
	 * @return the activity builder
	 */
	public ActivityBuilder withExpectedTripDistance(double distanceKm) {
		this.expectedTripDistanceKm = distanceKm;
		return this;
	}

	/**
	 * Sets the activitie's actual trip distance in km.
	 *
	 * @param distanceKm the actual distance in km
	 * @return the activity builder
	 */
	public ActivityBuilder withActualTripDistance(double distanceKm) {
		this.actualTripDistanceKm = distanceKm;
		return this;
	}

	/**
	 * Sets the activitie's destination {@link Opportunity}.
	 *
	 * @param destination the destination
	 * @return the activity builder
	 */
	public ActivityBuilder withDestination(Opportunity destination) {
		this.destination = destination;
		return this;
	}

	/**
	 * Builds the {@link Activity}.
	 *
	 * @return the immutable activity
	 */
	public Activity build() {
		validate();
		return new Activity(ID_CNT++, start, duration, purpose, destination, expectedTripDistanceKm,
				actualTripDistanceKm);
	}

	/**
	 * Clones the {@link ActivityBuilder}. Therefore also creates an exact copy of
	 * the builder's {@link Random} object, hence the resulting clone is a true
	 * copy.
	 *
	 * @return the activity builder
	 */
	public ActivityBuilder clone() {
		Random clonedRandom = RandomCloner.cloneRandom(this.random);
		return new ActivityBuilder(clonedRandom).withPurpose(purpose).startingAt(start).ofDuration(duration)
				.withExpectedTripDistance(expectedTripDistanceKm).withActualTripDistance(actualTripDistanceKm)
				.withDestination(destination);
	}

	/**
	 * Validate the state of the {@link ActivityBuilder} before building the {@link Activity}.
	 * I.e. a {@link Purpose} and a {@link Destination} is required.
	 */
	private void validate() {
		if (purpose == null) {
			throw new IllegalStateException("Cannor build activity before purpose is set");
		}

		if (destination == null) {
			throw new IllegalStateException("Cannor build activity before destination is set");
		}
		
		//TODO
	}
}
