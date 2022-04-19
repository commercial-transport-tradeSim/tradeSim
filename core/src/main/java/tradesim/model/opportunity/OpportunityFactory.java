package tradesim.model.opportunity;

import java.util.List;

import tradesim.simulation.SimulationContext;
import tradesim.simulation.opportunities.OpportunityLoader;
import tradesim.util.input.config.RunnableStep;

/**
 * A factory for creating {@link Opportunity} objects.
 */
public class OpportunityFactory implements RunnableStep {

	private final List<OpportunityLoader<SimulationContext>> loaders;
	private final SimulationContext context;
	
	/**
	 * Instantiates a new opportunity factory.
	 *
	 * @param loaders the opprotunity loaders
	 * @param context the context
	 */
	public OpportunityFactory(List<OpportunityLoader<SimulationContext>> loaders, SimulationContext context) {
		this.loaders = loaders;
		this.context = context;
	}
	
	
	/**
	 * Runs all opportunity loaders and stores the loaded {@link Opportunity opportunities} in the given context.
	 */
	@Override
	public void run() {
		System.out.println("Load Opportunities");
		loaders.forEach(l -> context.getOpportunities().addAll(l.loadOpportunities(context)));
		System.out.println("Finished Loading Opportunities");
	}

}
