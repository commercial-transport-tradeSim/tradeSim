package tradesim.simulation.activity.destinationchoice;

import static java.lang.Math.exp;
import static java.util.stream.Collectors.toMap;
import static tradesim.model.opportunity.OpportunityType.CONSTRUCTION_SITE;
import static tradesim.model.opportunity.OpportunityType.CUSTOMER;
import static tradesim.model.opportunity.OpportunityType.OTHER;
import static tradesim.model.opportunity.OpportunityType.OTHER_BUSINESS;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import tradesim.model.activity.ActivityBuilder;
import tradesim.model.business.Business;
import tradesim.model.opportunity.OpportunityType;
import tradesim.model.tour.Tour;
import tradesim.model.vehicles.Vehicle;
import tradesim.util.input.Parameters;
import tradesim.util.random.DiscreteDistribution;

/**
 * The Class DestinationTypeLogitModel.
 */
public class DestinationTypeLogitModel implements DestinationTypeModel {
	
	private final double lambda_root;
	
	private final DestinationTypeModelUtility utilities;
	private final DestinationChoiceModelLogger log;
	
	/**
	 * Instantiates a DestinationTypeLogitModel with the given LogitParameters, DestinationTypeModelHelper, DestinationChoiceModelLogger.
	 *
	 * @param logitParameters the logit parameters
	 * @param helper the helper
	 * @param log the logger
	 */
	public DestinationTypeLogitModel(Parameters logitParameters, DestinationTypeModelHelper helper, DestinationChoiceModelLogger log) {
		super();
		this.log = log;
		this.utilities = new DestinationTypeModelUtility(logitParameters, helper, log);
		this.lambda_root = 1.0;
	}
	
	/**
	 * Convenience constuctor: Uses a NullDestinationChoiceModelLogger as logger.
	 *
	 * @param logitParameters the logit parameters
	 * @param helper the helper
	 */
	public DestinationTypeLogitModel(Parameters logitParameters, DestinationTypeModelHelper helper) {
		this(logitParameters, helper, new NullDestinationChoiceModelLogger());
	}
	
	@Override
	public OpportunityType select(Business business, Vehicle vehicle, Tour tour, ActivityBuilder activity, double randomNumber) {
		this.log.logStart(business.getSector(), vehicle.getCategory(), activity.getPurpose(), randomNumber);
		
		
		// Calculate probabilities for each category using the given utilities object with respect to the availability flags above
		double U_business = utilities.calculateU_business(business, vehicle, tour, activity, randomNumber);
		this.log.log("U_business", U_business);
		double U_construction = utilities.calculateU_construction(business, vehicle, tour, activity, randomNumber);
		this.log.log("U_construction", U_construction);
		double U_customer = utilities.calculateU_customer(business, vehicle, tour, activity, randomNumber);
		this.log.log("U_customer", U_customer);
		double U_other = utilities.calculateU_other(business, vehicle, tour, activity, randomNumber);
		this.log.log("U_other", U_other);
		
		
		//nest: root --------------------------------------------------------------------------------
		double exp_U_business = exp(U_business/lambda_root);
		this.log.log("exp_U_business", exp_U_business);
		
		double exp_U_construction = exp(U_construction/lambda_root);
		this.log.log("exp_U_construction", exp_U_construction);
		
		double exp_U_customer = exp(U_customer/lambda_root);
		this.log.log("exp_U_customer", exp_U_customer);
		
		double exp_U_other = exp(U_other/lambda_root);
		this.log.log("exp_U_other", exp_U_other);
		
		//nest sum and logsum of root
		double nest_root_sum = exp_U_business + exp_U_construction + exp_U_customer + exp_U_other;
		this.log.log("nest_root_sum", nest_root_sum);
		
		//nest probabilities of root
		double p_OTHER_BUSINESS_in_nest_root = (exp_U_business)/nest_root_sum;
		this.log.log("p_OTHER_BUSINESS_in_nest_root", p_OTHER_BUSINESS_in_nest_root);
		
		double p_CONSTRUCTION_SITE_in_nest_root = (exp_U_construction)/nest_root_sum;
		this.log.log("p_CONSTRUCTION_SITE_in_nest_root", p_CONSTRUCTION_SITE_in_nest_root);
		
		double p_CUSTOMER_in_nest_root = (exp_U_customer)/nest_root_sum;
		this.log.log("p_CUSTOMER_in_nest_root", p_CUSTOMER_in_nest_root);
		
		double p_OTHER_in_nest_root = (exp_U_other)/nest_root_sum;
		this.log.log("p_OTHER_in_nest_root", p_OTHER_in_nest_root);
		//-------------------------------------------------------------------------------------------
		
		
		
		// Put probability of each category in a map. The probabilities are computed by multiplying the nest-probabilities along the paths in the nest structure.
		// If a category is marked '?' in the nest structure it is only added to the map if it is available
		Map<OpportunityType, Double> probabilities = new HashMap<>();
		probabilities.put(CONSTRUCTION_SITE,  p_CONSTRUCTION_SITE_in_nest_root);
		probabilities.put(CUSTOMER,  p_CUSTOMER_in_nest_root);
		probabilities.put(OTHER,  p_OTHER_in_nest_root);
		probabilities.put(OTHER_BUSINESS,  p_OTHER_BUSINESS_in_nest_root);
		
		this.log.logProbabilities(probabilities);
		
		//Filter probabilities
		Map<OpportunityType, Double> filteredProbabilities = probabilities
			.entrySet()
			.stream()
			.filter(e -> !Double.isNaN(e.getValue()))
			.collect(toMap(Entry::getKey, Entry::getValue));
		
		DiscreteDistribution<OpportunityType> distribution = new DiscreteDistribution<>(filteredProbabilities);
		OpportunityType selected = distribution.draw(randomNumber);
		log.log("Selected " + selected.name(), 1.0);
		
		return selected;
	}

}

