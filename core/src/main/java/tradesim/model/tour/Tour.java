package tradesim.model.tour;

import static tradesim.util.random.RandomCloner.cloneRandom;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import lombok.Getter;
import lombok.ToString;
import tradesim.model.activity.Activity;
import tradesim.model.activity.Purpose;
import tradesim.model.business.Business;
import tradesim.model.opportunity.Opportunity;
import tradesim.model.vehicles.Vehicle;
import tradesim.util.type.Time;

/**
 * The class Tour models vehicle tours performed by {@link Vehicle}s consisting of {@link Activity activities}.
 * 
 * @author Jelle Kübler
 *
 */
@Getter
@ToString
public class Tour implements Comparable<Tour> {
	
	private static int ID_CNT = 0;
	
	private final int id;
	private final Business business;
	private final Vehicle vehicle;
	private final List<Activity> activities;
	private final Random random;
	
	private final Time departure;
	private final double expectedDistanceKm;
	private final int expectedNumberOfTrips;
	
	/**
	 * Instantiates a new {@link Tour} for the given {@link Vehicle} with the given expected distance and expected number of activities.
	 *
	 * @param vehicle the vehicle
	 * @param business the business
	 * @param seed the seed
	 * @param departure the departure
	 * @param distKm the dist km
	 * @param expNumOfTrips the exp num of trips
	 */
	public Tour(Vehicle vehicle, Business business, long seed, Time departure, double distKm, int expNumOfTrips) {
		this(ID_CNT++, vehicle, business, new Random(seed), departure, distKm, expNumOfTrips);
	}
	
	/**
	 * Instantiates a new tour.
	 *
	 * @param id the id
	 * @param vehicle the vehicle
	 * @param business the business
	 * @param random the random
	 * @param departure the departure
	 * @param expDistKm the exp dist km
	 * @param expNumOfTrips the exp num of trips
	 */
	private Tour(int id, Vehicle vehicle, Business business, Random random, Time departure, double expDistKm, int expNumOfTrips) {
		this.id = id;
		this.business = business;
		this.vehicle = vehicle;
		this.activities = new ArrayList<>();
		this.random = random;
		this.departure = departure;
		this.expectedDistanceKm = expDistKm;
		this.expectedNumberOfTrips = expNumOfTrips;
	}
	
	/**
	 * Length.
	 *
	 * @return the int
	 */
	public int length() {
		return this.activities.size();
	}
	
	/**
	 * Actual distance sum.
	 *
	 * @return the double
	 */
	public double actualDistanceSum() {
		return this.getActivities().stream().mapToDouble(Activity::getActualTripDistanceKm).sum();
	}
	
	/**
	 * Expected distance sum.
	 *
	 * @return the double
	 */
	public double expectedDistanceSum() {
		return this.getActivities().stream().mapToDouble(Activity::getExpectedTripDistanceKm).sum();
	}
	
	/**
	 * Current location.
	 *
	 * @return the opportunity
	 */
	public Opportunity currentLocation() {
		if (activities.isEmpty()) {
			return business.getLocation();
		} else {
			return activities.get(activities.size() - 1).getDestination();			
		}
	}
	
	/**
	 * Current time.
	 *
	 * @return the time
	 */
	public Time currentTime() {
		if (activities.isEmpty()) {
			return departure;
		} else {
			return activities.get(activities.size() - 1).getEnd();			
		}
	}
	
	/**
	 * Gets the departure.
	 *
	 * @param activity the activity
	 * @return the departure
	 */
	public Time getDeparture(Activity activity) {
		int index = this.activities.indexOf(activity);
		
		if (index == -1) {
			throw new IllegalArgumentException("Activity " + activity.toString() + " is not contained in this tour: " + this.toString());
			
		} else if (index == 0) {
			return departure;
		
		} else {
			return this.activities.get(index-1).getEnd();
		}
	}
	
	/**
	 * Gets the trip origin.
	 *
	 * @param activity the activity
	 * @return the trip origin
	 */
	public Opportunity getTripOrigin(Activity activity) {
		int index = this.activities.indexOf(activity);
		
		if (index == -1) {
			throw new IllegalArgumentException("Activity " + activity.toString() + " is not contained in this tour: " + this.toString());
			
		} else if (index == 0) {
			return business.getLocation();
		
		} else {
			return this.activities.get(index-1).getDestination();
		}
	}
	
	/**
	 * Gets the trip duration.
	 *
	 * @param activity the activity
	 * @return the trip duration
	 */
	public Time getTripDuration(Activity activity) {
		return activity.getStart().minus(this.getDeparture(activity));
	}
	
	/**
	 * Current purpose.
	 *
	 * @return the purpose
	 */
	public Purpose currentPurpose() {
		if (activities.isEmpty()) {
			return null;
		} else {
			return activities.get(activities.size() - 1).getPurpose();			
		}
	}
	
	/**
	 * Adds the.
	 *
	 * @param activity the activity
	 * @return the tour
	 */
	public Tour add(Activity activity) {
		this.activities.add(activity);
		return this;
	}
	
	/**
	 * Checks if is active.
	 *
	 * @return true, if is active
	 */
	public boolean isActive() {
		return !isFinished();
	}
	
	/**
	 * Checks if is finished.
	 *
	 * @return true, if is finished
	 */
	public boolean isFinished() {
		if (activities.isEmpty()) {
			return false;
		} else {
			return activities.get(activities.size()-1).getPurpose().equals(Purpose.RETURN);
		}
	}
	
	/**
	 * Clone.
	 *
	 * @return the tour
	 */
	public Tour clone() {
		Random clonedRandom = cloneRandom(random);
		Tour newTour = new Tour(id, vehicle, business, clonedRandom, departure, expectedDistanceKm, expectedNumberOfTrips);
		activities.forEach(a -> newTour.add(a.clone()));
		return newTour;
	}

	private final static Comparator<Tour> comparator = 
		Comparator.comparing((Tour t) -> Math.abs(t.expectedDistanceSum() - t.actualDistanceSum()))
				  .thenComparing((Tour t) -> Math.abs(t.getExpectedNumberOfTrips() - t.length()));
	
	/**
	 * Compare to.
	 *
	 * @param o the o
	 * @return the int
	 */
	@Override
	public int compareTo(Tour o) {
		return comparator.compare(this, o);
	}
	

}
