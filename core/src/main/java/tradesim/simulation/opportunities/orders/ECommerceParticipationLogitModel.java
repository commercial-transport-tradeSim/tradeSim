package tradesim.simulation.opportunities.orders;

import static java.lang.Math.exp;

import java.util.Map;

import tradesim.simulation.opportunities.ECommerceParticipationSelector;
import tradesim.simulation.opportunities.PrivatePerson;
import tradesim.util.input.Parameters;

/**
 * The Class ECommerceParticipationLogitModel.
 */
public class ECommerceParticipationLogitModel implements ECommerceParticipationSelector {
	
	
	private final ECommerceParticipationModelUtility utilities;
	private final ECommerceParticipationSelectorLogger log;
	
	/**
	 * Instantiates a ECommerceParticipationLogitModel with the given ECommerceParticipationModelHelper, ECommerceParticipationSelectorLogger.
	 *
	 * @param logitParameters the logit parameters
	 * @param helper the helper
	 * @param log the logger
	 */
	public ECommerceParticipationLogitModel(Parameters logitParameters, ECommerceParticipationModelHelper helper, ECommerceParticipationSelectorLogger log) {
		super();
		this.log = log;
		this.utilities = new ECommerceParticipationModelUtility(logitParameters, helper, log);
	}
	
	/**
	 * Convenience constuctor: Uses a NullECommerceParticipationSelectorLogger as logger.
	 *
	 * @param logitParameters the logit parameters
	 * @param helper the helper
	 */
	public ECommerceParticipationLogitModel(Parameters logitParameters, ECommerceParticipationModelHelper helper) {
		this(logitParameters, helper, new NullECommerceParticipationSelectorLogger());
	}
	
	@Override
	public boolean select(PrivatePerson recipient, double randomNumber) {
		this.log.logStart(recipient, randomNumber);
		
		// Calculate single utilitiy/probability for non zero utility function
		double utility = utilities.calculateU_part(recipient, randomNumber);
		this.log.log("utility_part", utility);
		double probability = exp(utility) / (1.0 + exp(utility));
		this.log.log("probability_part", probability);
		
		Map<String, Double> probabilities = Map.of("Participation", probability);
		this.log.logProbabilities(probabilities);
		
		boolean selected = (randomNumber < probability);
		this.log.log("Selected " + selected, 1.0);
		
		return selected;
	}

}

