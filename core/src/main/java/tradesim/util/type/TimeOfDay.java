package tradesim.util.type;

import java.util.Arrays;

/**
 * The Enum TimeOfDay models different time periods of a day.
 */
public enum TimeOfDay {
	
	/** The morning. */
	morning(1),
	
	/** The day. */
	day(2), 
	
	/** The night. */
	night(3);
	
	private final int number;
	
	private TimeOfDay(int number) {
		this.number = number;
	}
	
	/**
	 * Returns the int encoding of the {@link TimeOfDay}.
	 *
	 * @return the int encoding
	 */
	public int asInt() {
		return this.number;
	}
	
	/**
	 * Returns the {@link TimeOfDay} as {@link String}.
	 *
	 * @return the string
	 */
	public String asString() {
		return this.name();
	}
	
	/**
	 * Parses the given int as {@link TimeOfDay}.
	 * Throws an {@link IllegalArgumentException} if the given int is not a valid {@link TimeOfDay} encoding.
	 *
	 * @param number the number
	 * @return the time of day
	 */
	public static TimeOfDay fromInt(int number) {
		return Arrays.stream(TimeOfDay.values())
					 .filter(p -> p.asInt() == number)
					 .findFirst()
					 .orElseGet(() -> {
						 throw new IllegalArgumentException("Cannot parse " + number + " as time of day!");
					 });
	}

}
