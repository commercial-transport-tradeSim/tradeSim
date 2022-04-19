package tradesim.model.activity;

import java.util.Arrays;

/**
 * The Enum Purpose models possible purposes for vehicle trips/activities.
 */
public enum Purpose {

	/** The private. */
	PRIVATE(1),
	
	/** The return. */
	RETURN(2),
	
	/** The delivery. */
	DELIVERY(3),
	
	/** The service. */
	SERVICE(4),
	
	/** The drop off. */
	DROP_OFF(5),
	
	/** The other. */
	OTHER(6);
	
	private final int number;
	
	private Purpose(int number) {
		this.number = number;
	}
	
	/**
	 * Returns the int encoding of the {@link Purpose}.
	 *
	 * @return the int encoding
	 */
	public int asInt() {
		return this.number;
	}
	
	/**
	 * Returns the {@link Purpose} as {@link String}.
	 *
	 * @return the string
	 */
	public String asString() {
		return this.name();
	}
	
	/**
	 * Parses the given int as {@link Purpose}.
	 * Throws an {@link IllegalArgumentException} if the given int is not a valid {@link Purpose} encoding.
	 *
	 * @param number the number
	 * @return the purpose
	 */
	public static Purpose fromInt(int number) {
		return Arrays.stream(Purpose.values())
					 .filter(p -> p.asInt() == number)
					 .findFirst()
					 .orElseGet(() -> {
						 throw new IllegalArgumentException("Cannot parse " + number + " as purpose!");
					 });
	}

}
