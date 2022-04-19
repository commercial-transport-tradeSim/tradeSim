package tradesim.model.opportunity;

import lombok.Getter;
import lombok.ToString;
import tradesim.model.activity.Activity;
import tradesim.util.type.Location;

/**
 * The Class Opportunity represents locations at which simulated agents can perform {@link Activity activities}.
 */
@Getter
@ToString
public class Opportunity {
	
	private final String id;
	private final Location location;
	private final OpportunityType type;
	
	
	/**
	 * Instantiates a new opportunity.
	 *
	 * @param type the type
	 * @param location the location
	 * @param id the id
	 */
	public Opportunity(OpportunityType type, Location location, String id) {
		this.id = id;
		this.location = location;
		this.type = type;
	}
	
	/**
	 * Returns distance to the given {@link Opportunity}.
	 *
	 * @param opportunity the opportunity
	 * @return the double
	 */
	public double distance(Opportunity opportunity) {
		return this.location.distance(opportunity.getLocation());
	}
	
}
