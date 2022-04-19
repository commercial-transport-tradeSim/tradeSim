package tradesim.model.vehicles;

import static tradesim.model.vehicles.VehicleCategory.HEAVY;
import static tradesim.model.vehicles.VehicleCategory.LIGHT;
import static tradesim.model.vehicles.VehicleCategory.MEDIUM;

import java.util.Arrays;

/**
 * The Enum VehicleType models different types of vehicles.
 */
public enum VehicleType {

	/**
	 * MOTORCYCLE .
	 */
	MOTORCYCLE(1) {
		@Override
		public VehicleCategory getVehicleCategory() {
			return LIGHT;
		}
	},
	
	/**
	 * PASSENGER_VEHICLE .
	 */
	PASSENGER_VEHICLE(2) {
		@Override
		public VehicleCategory getVehicleCategory() {
			return LIGHT;
		}
	},
	
	/**
	 * TRUCK_PAYLOAD_3_5T .
	 */
	TRUCK_PAYLOAD_3_5T(3) {
		@Override
		public VehicleCategory getVehicleCategory() {
			return MEDIUM;
		}
	},
	
	/**
	 * TRUCK_PAYLOAD_OVER_3_5T .
	 */
	TRUCK_PAYLOAD_OVER_3_5T(4) {
		@Override
		public VehicleCategory getVehicleCategory() {
			return HEAVY;
		}
	},
	
	/**
	 * SEMI_TAILOR_TRUCK .
	 */
	SEMI_TAILOR_TRUCK(5) {
		@Override
		public VehicleCategory getVehicleCategory() {
			return HEAVY;
		}
	},
	
	/**
	 * BUS .
	 */
	BUS(6) {
		@Override
		public VehicleCategory getVehicleCategory() {
			return HEAVY;
		}
	},
	
	/**
	 * AGRICULTURAL_TRACTOR .
	 */
	AGRICULTURAL_TRACTOR(7) {
		@Override
		public VehicleCategory getVehicleCategory() {
			return HEAVY;
		}
	},
	
	/**
	 * OTHER_TRACTOR .
	 */
	OTHER_TRACTOR(8) {
		@Override
		public VehicleCategory getVehicleCategory() {
			return HEAVY;
		}
	},
	
	/**
	 * OTHER_VEHICLE .
	 */
	OTHER_VEHICLE(9) {
		@Override
		public VehicleCategory getVehicleCategory() {
			return HEAVY;
		}
	}
	;
	
	private final int number;
	
	private VehicleType(int number) {
		this.number = number;
	}
	
	/**
	 *  Returns the int code of a {@link VehicleType}.
	 *
	 * @return the int
	 */
	public int asInt() {
		return this.number;
	}
	
	/**
	 * returns the name of a {@link VehicleType}.
	 *
	 * @return the name
	 */
	public String asString() {
		return this.name();
	}
	
	/**
	 * Gets the {@link VehicleCategory}.
	 *
	 * @return the vehicle category
	 */
	public abstract VehicleCategory getVehicleCategory();
	
	/**
	 * Parses the given int as {@link VehicleCategory}.
	 *
	 * @param number the number
	 * @return the vehicle type
	 */
	public static VehicleType fromInt(int number) {
		return Arrays.stream(VehicleType.values())
					 .filter(p -> p.asInt() == number)
					 .findFirst()
					 .orElseGet(() -> {
						 throw new IllegalArgumentException("Cannot parse " + number + " as vehicle type!");
					 });
	}
}
