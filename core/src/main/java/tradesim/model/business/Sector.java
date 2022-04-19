package tradesim.model.business;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * The Enum Sector models different sectors of business.
 */
public enum Sector {

	/**
	 * INDUSTRY .
	 */
	INDUSTRY(1) {
		@Override
		Collection<Trade> getContainedTrades() {
			return List.of();
		}
	},
	
	/**
	 * SERVICE .
	 */
	SERVICE(2) {
		@Override
		Collection<Trade> getContainedTrades() {
			return List.of();
		}
	},
	
	/**
	 * OTHER .
	 */
	OTHER(3) {
		@Override
		Collection<Trade> getContainedTrades() {
			return List.of();
		}
	};
	
	private final int number;
	
	private Sector(int number) {
		this.number = number;
	}
	
	/**
	 *  Returns the int code of a {@link Trade}.
	 *
	 * @return the int
	 */
	public int asInt() {
		return this.number;
	}
	
	/**
	 * returns the name of a {@link Trade}.
	 *
	 * @return the name
	 */
	public String asString() {
		return this.name();
	}
	
	/**
	 * Gets the {@link Trade} contained in the {@link Sector}.
	 *
	 * @return the contained {@link Trade}s
	 */
	abstract Collection<Trade> getContainedTrades();
	
	/**
	 * Parses the given int as {@link Sector}.
	 *
	 * @param number the number
	 * @return the sector
	 */
	public static Sector fromInt(int number) {
		return Arrays.stream(Sector.values())
					 .filter(p -> p.asInt() == number)
					 .findFirst()
					 .orElseGet(() -> {
						 throw new IllegalArgumentException("Cannot parse " + number + " as sector!");
					 });
	}
}
