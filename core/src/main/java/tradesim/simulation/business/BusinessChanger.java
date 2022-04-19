package tradesim.simulation.business;

import tradesim.model.business.BusinessBuilder;
import tradesim.util.input.config.Context;

/**
 * The Interface BusinessChanger provides a method for changing
 * {@link BusinessBuilder}.
 *
 * @param <C> the generic type
 */
public interface BusinessChanger<C extends Context> {
	
	/**
	 * Apply the change to the given {@link BusinessBuilder business}. 
	 *
	 * @param context the context
	 * @param business the business
	 * @return the business builder
	 */
	public BusinessBuilder apply(C context, BusinessBuilder business);

}
