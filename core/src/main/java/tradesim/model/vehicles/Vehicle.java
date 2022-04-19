package tradesim.model.vehicles;

import java.util.Random;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tradesim.model.business.Business;
import tradesim.util.type.Time;

/**
 * The Class Vehicle models vehicle agents in the simulation.
 * Immutable data object holding the properties of a vehicle.
 * 
 * @author Jelle Kübler
 *
 */
@Getter
@Setter
@ToString
public class Vehicle {
	
	private final int id;
	private final VehicleType type;
	private final Random random;
	private final Business owner;
	private final Time departure;
	private final double meanSpeedKmh;
	
	/**
	 * Instantiates a new vehicle with the given properties.
	 *
	 * @param id the id
	 * @param type the type
	 * @param random the random generator
	 * @param owner the owner
	 * @param departure the departure
	 * @param meanSpeedKmh the mean speed kmh
	 */
	public Vehicle(int id, VehicleType type, Random random, Business owner, Time departure, double meanSpeedKmh) {
		this.id = id;
		this.type = type;
		this.random = random;
		this.owner = owner;
		this.departure = departure;
		this.meanSpeedKmh = meanSpeedKmh;
	}
	
	/**
	 * Gets the {@link VehicleCategory} derived from the vehicles {@link VehicleType}.
	 *
	 * @return the category
	 */
	public VehicleCategory getCategory() {
		return this.type.getVehicleCategory();
	}
	
}
