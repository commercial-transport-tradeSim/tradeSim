package tradesim.model.vehicles;

import static tradesim.model.vehicles.VehicleType.AGRICULTURAL_TRACTOR;
import static tradesim.model.vehicles.VehicleType.BUS;
import static tradesim.model.vehicles.VehicleType.MOTORCYCLE;
import static tradesim.model.vehicles.VehicleType.OTHER_TRACTOR;
import static tradesim.model.vehicles.VehicleType.OTHER_VEHICLE;
import static tradesim.model.vehicles.VehicleType.PASSENGER_VEHICLE;
import static tradesim.model.vehicles.VehicleType.SEMI_TAILOR_TRUCK;
import static tradesim.model.vehicles.VehicleType.TRUCK_PAYLOAD_3_5T;
import static tradesim.model.vehicles.VehicleType.TRUCK_PAYLOAD_OVER_3_5T;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * The Enum VehicleCategory separates {@link VehicleType}s into three categories: light, medium and heavy vehicles.
 */
public enum VehicleCategory {

	/**
	 * LIGHT .
	 */
	LIGHT(1) {
		@Override
		public Collection<VehicleType> getContainedVehicleTypes() {
			return List.of(MOTORCYCLE, PASSENGER_VEHICLE);
		}
	},
	
	/**
	 *  MEDIUM .
	 */
	MEDIUM(2) {
		@Override
		public Collection<VehicleType> getContainedVehicleTypes() {
			return List.of(TRUCK_PAYLOAD_3_5T);
		}
	},
	
	/**
	 * HEAVY .
	 */
	HEAVY(3) {
		@Override
		public Collection<VehicleType> getContainedVehicleTypes() {
			return List.of(TRUCK_PAYLOAD_OVER_3_5T, SEMI_TAILOR_TRUCK, BUS, AGRICULTURAL_TRACTOR, OTHER_TRACTOR, OTHER_VEHICLE);
		}
	};
	
	private final int number;
	
	private VehicleCategory(int number) {
		this.number = number;
	}
	
	/**
	 * Returns int code of the {@link VehicleCategory}.
	 *
	 * @return the int
	 */
	public int asInt() {
		return this.number;
	}
	
	/**
	 * Returns name of the {@link VehicleCategory}.
	 *
	 * @return the name
	 */
	public String asString() {
		return this.name();
	}
	
	/**
	 * Gets the contained vehicle types.
	 *
	 * @return the contained vehicle types
	 */
	public abstract Collection<VehicleType> getContainedVehicleTypes();
	
	/**
	 * Parses the given int as {@link VehicleCategory}.
	 *
	 * @param number the number
	 * @return the vehicle category
	 */
	public static VehicleCategory fromInt(int number) {
		return Arrays.stream(VehicleCategory.values())
					 .filter(p -> p.asInt() == number)
					 .findFirst()
					 .orElseGet(() -> {
						 throw new IllegalArgumentException("Cannot parse " + number + " as vehicle type!");
					 });
	}

}
