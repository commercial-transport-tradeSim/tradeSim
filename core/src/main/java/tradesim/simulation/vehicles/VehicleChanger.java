package tradesim.simulation.vehicles;

import tradesim.model.business.Business;
import tradesim.model.vehicles.VehicleBuilder;
import tradesim.util.input.config.Context;

/**
 * The Interface VehicleChanger provides a method for changing
 * {@link VehicleBuilder}s.
 *
 * @param <C> type of the context
 */
public interface VehicleChanger<C extends Context> {
	
	/**
	 * Applies the changer to the given {@link VehicleBuilder}.
	 *
	 * @param context the context
	 * @param business the business
	 * @param builder the builder
	 * @return the vehicle builder
	 */
	public VehicleBuilder apply(C context, Business business, VehicleBuilder builder);

}
