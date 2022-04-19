package tradesim.simulation.opportunities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tradesim.model.business.Business;
import tradesim.simulation.SimulationContext;
import tradesim.util.input.config.Configurable;

/**
 * The Class UseBusinessOpportunities is a builder for a {@link OpportunityLoader} for business opportunities.
 */
@Getter
@Setter
@ToString
public class UseBusinessOpportunities implements Configurable<SimulationContext, OpportunityLoader<SimulationContext>> {

	private boolean active;
	
	/**
	 * Instantiates a new use opportunities from already loaded {@link Business} objects.
	 */
	public UseBusinessOpportunities() {
		active = true;
	}

	/**
	 * Builds the runnable {@link OpportunityLoader} for businesses.
	 *
	 * @param context the context
	 * @return the opportunity loader
	 */
	@Override
	public OpportunityLoader<SimulationContext> build(SimulationContext context) {
		return c -> c.getBusinesses().stream().map(Business::getLocation).toList();
	}

	@Override
	public void validate() {}

}
