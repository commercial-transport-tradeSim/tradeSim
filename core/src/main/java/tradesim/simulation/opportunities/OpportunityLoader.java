package tradesim.simulation.opportunities;

import java.util.Collection;

import tradesim.model.opportunity.Opportunity;
import tradesim.util.input.config.Context;
/**
 * The Interface OpportunityLoader.
 *
 * @param <C> type of the context to be used
 */
public interface OpportunityLoader<C extends Context> {
	
	/**
	 * Load opportunities.
	 *
	 * @param context the context
	 * @return the collection
	 */
	public Collection<Opportunity> loadOpportunities(C context);

}
