package tradesim.simulation.opportunities;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tradesim.model.opportunity.OpportunityFactory;
import tradesim.simulation.SimulationContext;
import tradesim.util.input.config.Configurable;
import tradesim.util.input.config.RunnableStep;

/**
 * The Class LoadOpportunities is a collection of confugurable {@link OpportunityLoader} builders.
 */
@Getter
@Setter
@ToString
public class LoadOpportunities implements Configurable<SimulationContext, RunnableStep> {
	
	private List<Configurable<SimulationContext, OpportunityLoader<SimulationContext>>> loaders;
	
	/**
	 * Instantiates a new load opportunities.
	 */
	public LoadOpportunities() {
		this.loaders = new ArrayList<>();
	}

	/**
	 * Builds all {@link OpportunityLoader}s.
	 *
	 * @param context the context
	 * @return the runnable step
	 */
	@Override
	public RunnableStep build(SimulationContext context) {
		List<OpportunityLoader<SimulationContext>> builtLoades =
				loaders.stream().map(l -> l.validateAndBuild(context)).toList();
		
		return new OpportunityFactory(builtLoades, context);
	}

	/**
	 * Validates all {@link OpportunityLoader} builders.
	 */
	@Override
	public void validate() {
		loaders.forEach(l -> l.validate());
	}

}
