package tradesim.model.opportunity;

import java.util.Arrays;

/**
 * The Enum OpportunityType represents the four types of opportunities:
 * customer, construction site, other business and other.
 */
public enum OpportunityType {

	/** Customers. */
	CUSTOMER(1),
	
	/** Construction sites. */
	CONSTRUCTION_SITE(2),
	
	/** Other businesses. */
	OTHER_BUSINESS(3),
	
	/** Other opportunities. */
	OTHER(4);
	
	private final int number;
	
	private OpportunityType(int number) {
		this.number = number;
	}
	
	/**
	 *  Returns the int code of a {@link OpportunityType}.
	 *
	 * @return the int
	 */
	public int asInt() {
		return this.number;
	}
	
	/**
	 * returns the name of a {@link OpportunityType}.
	 *
	 * @return the name
	 */
	public String asString() {
		return this.name();
	}
	
	/**
	 * Parses the given int as {@link OpportunityType}.
	 *
	 * @param number the number
	 * @return the opportunity type
	 */
	public static OpportunityType fromInt(int number) {
		return Arrays.stream(OpportunityType.values())
					 .filter(p -> p.asInt() == number)
					 .findFirst()
					 .orElseGet(() -> {
						 throw new IllegalArgumentException("Cannot parse " + number + " as opportunity type!");
					 });
	}
}
