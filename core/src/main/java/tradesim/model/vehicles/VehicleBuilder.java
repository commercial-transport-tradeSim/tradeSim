package tradesim.model.vehicles;

import java.util.Random;

import lombok.Getter;
import tradesim.model.business.Business;
import tradesim.util.type.Time;

/**
 * The Class VehicleBuilder is a builder for {@link Vehicle} objects. It is a
 * mutable version of {@link Vehicle} and produces the immutable version upon
 * building it.
 */
public class VehicleBuilder {
	
	private static int ID_CNT = 0;

	@Getter private VehicleType type;
	private Business owner;
	private Time departure;
	private double meanSpeedKmh;
	@Getter private Random random;
	
	/**
	 * Instantiates a new vehicle builder.
	 *
	 * @param seed the seed for the random generator
	 */
	public VehicleBuilder(long seed) {
		this.random = new Random(seed);
	}
	
	/**
	 * Use the given {@link VehicleType}.
	 *
	 * @param type the vehicle type
	 * @return the vehicle builder
	 */
	public VehicleBuilder with(VehicleType type) {
		this.type = type;
		return this;
	}
	
	/**
	 * Use the given {@link Business owner}.
	 *
	 * @param owner the owner
	 * @return the vehicle builder
	 */
	public VehicleBuilder with(Business owner) {
		this.owner = owner;
		return this;
	}
		
	/**
	 * Use the given departure {@link Time}.
	 *
	 * @param departure the departure
	 * @return the vehicle builder
	 */
	public VehicleBuilder with(Time departure) {
		this.departure = departure;
		return this;
	}
	
	/**
	 * Use the given mean speed in kmh.
	 *
	 * @param meanSpeedKmh the mean speed kmh
	 * @return the vehicle builder
	 */
	public VehicleBuilder with(double meanSpeedKmh) {
		this.meanSpeedKmh = meanSpeedKmh;
		return this;
	}
		
	
	/**
	 * Builds the {@link Vehicle}.
	 *
	 * @return the immutable vehicle
	 */
	public Vehicle build() {
		validate();
		return new Vehicle(ID_CNT++, type, random, owner, departure, meanSpeedKmh);
	}

	/**
	 * Validate the state of the {@link VehicleBuilder} befor building it.
	 */
	private void validate() {
		
		if (type == null) {
			throw new IllegalStateException("Cannor build vehicle before type is set");
		}
		
		if (owner == null) {
			throw new IllegalStateException("Cannor build vehicle before owner is set");
		}
	}
}
