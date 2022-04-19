package tradesim.simulation.opportunities;

import static tradesim.model.opportunity.OpportunityType.CUSTOMER;

import java.util.Collection;
import java.util.Random;

import tradesim.model.opportunity.Opportunity;
import tradesim.simulation.SimulationContext;
import tradesim.simulation.opportunities.orders.DefaultECommerceParticipationSelectorLogger;
import tradesim.simulation.opportunities.orders.ECommerceParticipationLogitModel;
import tradesim.simulation.opportunities.orders.ECommerceParticipationModelHelper;
import tradesim.simulation.opportunities.orders.ECommerceParticipationModelHelperImpl;
import tradesim.util.input.Parameters;
import tradesim.util.input.csv.CsvFile;
import tradesim.util.type.Holder;
import tradesim.util.type.Location;

/**
 * The Class CustomerOpportunityModel is an {@link OpportunityLoader} for homes of {@link PrivatePerson}s.
 */
public class CustomerOpportunityModel implements OpportunityLoader<SimulationContext> {
	
	private final CsvFile populationFile;
	private final Parameters eCommerceParameters;
	private final double fraction;
	private final boolean logging;
	
	/**
	 * Instantiates a new customer opportunity model.
	 *
	 * @param populationFile the population file
	 * @param eCommerceParameters the e commerce parameters
	 * @param fraction the fraction
	 * @param logging the logging
	 */
	public CustomerOpportunityModel(CsvFile populationFile, Parameters eCommerceParameters, double fraction, boolean logging) {
		this.populationFile = populationFile;
		this.eCommerceParameters = eCommerceParameters;
		this.fraction = fraction;
		this.logging = logging;
	}

	
	/**
	 * Load homes of {@link PrivatePerson}s if they participate in e-commerce as {@link Opportunity opportunities}.
	 *
	 * @param context the context
	 * @return the collection of opportunities
	 */
	@Override
	public Collection<Opportunity> loadOpportunities(SimulationContext context) {
		
		Random random = new Random(context.getRandom().nextLong());
		ECommerceParticipationSelector selector;
		ECommerceParticipationModelHelper helper = new ECommerceParticipationModelHelperImpl();		
		
		if (logging) {
			selector = new ECommerceParticipationLogitModel(eCommerceParameters, helper, new DefaultECommerceParticipationSelectorLogger());
		} else {
			selector = new ECommerceParticipationLogitModel(eCommerceParameters, helper);
		}
		
		Holder<Double> counter = new Holder<Double>(0.0d);
		
		return
			new PopulationParser().parse(populationFile)
								  .stream()
								  .filter(p -> filterFraction(counter, random))
								  .filter(p -> selector.select(p, random.nextDouble()))
								  .map(this::personToOpportunity)
								  .toList();
	}
	
	
	/**
	 * Filter fraction of the population.
	 *
	 * @param counter the counter
	 * @param random the random generator to consume random values if a removed by filter
	 * @return true, if successful
	 */
	private boolean filterFraction(Holder<Double> counter, Random random) {
		counter.setValue(counter.getValue() + fraction);

		Double cnt = counter.getValue();
		
		if (cnt >= 1.0) {
			counter.setValue(cnt - Math.floor(cnt));
			return true;
		} else {
			consumeRandom(random);
			return false;
		}
	}
	
	
	/**
	 * Consume random number to ensure a deterministic simulation
	 * if only a fraction of the population is simulated.
	 *
	 * @param random the random generator
	 */
	private void consumeRandom(Random random) {
		random.nextDouble();
	}


	/**
	 * Person to opportunity.
	 *
	 * @param person the person
	 * @return the opportunity
	 */
	private Opportunity personToOpportunity(PrivatePerson person) {
		Location location = new Location(person.getHome_x(), person.getHome_y());
		return new Opportunity(CUSTOMER, location, person.getHome_link());
	}
	
}
