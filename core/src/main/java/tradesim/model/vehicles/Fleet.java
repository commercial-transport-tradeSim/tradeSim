package tradesim.model.vehicles;

import java.util.Collection;

import lombok.Getter;
import lombok.ToString;
import tradesim.model.business.Business;

/**
 * The Class Fleet models a {@link Vehicle} fleet of a {@link Business}.
 * 
 * @author Jelle Kübler
 *
 */
@Getter
@ToString
public class Fleet {

	private final Business business;
	private final Collection<Vehicle> vehicles;

	/**
	 * Instantiates a new fleet with the given {@link Vehicle}s of the given {@link Business}.
	 *
	 * @param business the business
	 * @param vehicles the vehicles
	 */
	public Fleet(Business business, Collection<Vehicle> vehicles) {
		this.business = business;
		this.vehicles = vehicles;
	}
	
}
