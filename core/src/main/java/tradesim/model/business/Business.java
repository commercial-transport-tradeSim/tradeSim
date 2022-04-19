package tradesim.model.business;

import java.util.Map;
import java.util.Random;

import lombok.Getter;
import lombok.ToString;
import tradesim.model.opportunity.Opportunity;
import tradesim.model.vehicles.VehicleType;


/**
 * The class Business models businesses.
 * Immutable data object holding the properties of a business.
 * 
 * @author Jelle Kübler
 *
 */
@Getter
@ToString
public class Business {
	
	private final int id;
	private final String name;
	private final Trade trade;
	private final Opportunity location;
	private final int numberOfEmployees;
	private final Random random;
	private final Map<VehicleType, Integer> fleetSizes;
	
	/**
	 * Instantiates a new business with the given properties.
	 *
	 * @param id the id
	 * @param name the name
	 * @param trade the trade
	 * @param location the location
	 * @param numberOfEmployees the number of employees
	 * @param random the random generator
	 * @param fleetSize the fleet size map
	 */
	public Business(int id, String name, Trade trade, Opportunity location, int numberOfEmployees, Random random, Map<VehicleType, Integer> fleetSize) {
		this.id = id;
		this.name = name;
		this.trade = trade;
		this.location = location;
		this.numberOfEmployees = numberOfEmployees;
		this.random = random;
		this.fleetSizes = fleetSize;
	}

	/**
	 * Gets the {@link Business} sector.
	 *
	 * @return the sector
	 */
	public Sector getSector() {
		return this.trade.getSector();
	}
}
