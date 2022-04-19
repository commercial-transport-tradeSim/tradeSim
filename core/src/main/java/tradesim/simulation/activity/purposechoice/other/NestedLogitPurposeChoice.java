package tradesim.simulation.activity.purposechoice.other;

import static java.lang.Math.exp;
import static java.lang.Math.log;
import static java.util.stream.Collectors.toMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import tradesim.model.activity.ActivityBuilder;
import tradesim.model.activity.Purpose;
import tradesim.model.business.Business;
import tradesim.model.tour.Tour;
import tradesim.model.vehicles.Vehicle;
import tradesim.simulation.activity.PurposeChoiceModel;
import tradesim.util.input.Parameters;
import tradesim.util.random.DiscreteDistribution;

/**
 * The Class NestedLogitPurposeChoice.
 */
public class NestedLogitPurposeChoice implements PurposeChoiceModel {
	
	private final double lambda_commercial;
	private final double lambda_root;
	
	private final NestedLogitPurposeChoiceUtilityFunction utilities;
	private final PurposeChoiceModelLogger log;
	
	/**
	 * Instantiates a NestedLogitPurposeChoice with the given LogitParameters, NestedLogitPurposeChoiceHelper, PurposeChoiceModelLogger.
	 *
	 * @param parameters the parameters
	 * @param helper the helper
	 * @param log the logger
	 */
	public NestedLogitPurposeChoice(Parameters parameters, NestedLogitPurposeChoiceHelper helper, PurposeChoiceModelLogger log) {
		super();
		this.log = log;
		this.utilities = new NestedLogitPurposeChoiceUtilityFunction(parameters, helper, log);
		this.lambda_commercial = parameters.get("lambda_commercial");
		this.lambda_root = 1.0;
	}
	
	/**
	 * Convenience constuctor: Uses a NullPurposeChoiceModelLogger as logger.
	 *
	 * @param parameters the parameters
	 * @param helper the helper
	 */
	public NestedLogitPurposeChoice(Parameters parameters, NestedLogitPurposeChoiceHelper helper) {
		this(parameters, helper, new NullPurposeChoiceModelLogger());
	}
	
	@Override
	public List<Purpose> possibleResults(Business business, Vehicle vehicle, Tour tour, ActivityBuilder activity, double randomNumber) {
		this.log.logStart(business, vehicle, tour, randomNumber);
				
		// Calculate probabilities for each category using the given utilities object with respect to the availability flags above
		double U_private = utilities.calculateU_private(business, vehicle, tour, activity, randomNumber);
		this.log.log("U_private", U_private);
		
		double U_return = utilities.calculateU_return(business, vehicle, tour, activity, randomNumber);
		this.log.log("U_return", U_return);
		
		double U_goods = utilities.calculateU_goods(business, vehicle, tour, activity, randomNumber);
		this.log.log("U_goods", U_goods);
		
		double U_service = utilities.calculateU_service(business, vehicle, tour, activity, randomNumber);
		this.log.log("U_service", U_service);
		
		double U_people = utilities.calculateU_people(business, vehicle, tour, activity, randomNumber);
		this.log.log("U_people", U_people);
		
		double U_other = utilities.calculateU_other(business, vehicle, tour, activity, randomNumber);
		this.log.log("U_other", U_other);
		
		
		//nest: commercial --------------------------------------------------------------------------------
		double exp_U_goods = exp(U_goods/lambda_commercial);
		this.log.log("exp_U_goods", exp_U_goods);
		
		double exp_U_service = exp(U_service/lambda_commercial);
		this.log.log("exp_U_service", exp_U_service);
		
		double exp_U_people = exp(U_people/lambda_commercial);
		this.log.log("exp_U_people", exp_U_people);
		
		double exp_U_other = exp(U_other/lambda_commercial);
		this.log.log("exp_U_other", exp_U_other);
		
		//nest sum and logsum of commercial
		double nest_commercial_sum = exp_U_goods + exp_U_service + exp_U_people + exp_U_other;
		this.log.log("nest_commercial_sum", nest_commercial_sum);
		
		double logsum_nest_commercial = log(nest_commercial_sum);
		this.log.log("exp_U_goods", exp_U_goods);
		
		//nest probabilities of commercial
		double p_delivery_in_nest_commercial = (exp_U_goods)/nest_commercial_sum;
		this.log.log("p_delivery_in_nest_commercial", p_delivery_in_nest_commercial);
		
		double p_service_in_nest_commercial = (exp_U_service)/nest_commercial_sum;
		this.log.log("p_service_in_nest_commercial", p_service_in_nest_commercial);
		
		double p_dropoff_in_nest_commercial = (exp_U_people)/nest_commercial_sum;
		this.log.log("p_dropoff_in_nest_commercial", p_dropoff_in_nest_commercial);
		
		double p_other_in_nest_commercial = (exp_U_other)/nest_commercial_sum;
		this.log.log("p_other_in_nest_commercial", p_other_in_nest_commercial);
		//-------------------------------------------------------------------------------------------------
		
		
		//nest: root --------------------------------------------------------------------------------
		double exp_U_private = exp(U_private/lambda_root);
		this.log.log("exp_U_private", exp_U_private);
		
		double exp_U_return = exp(U_return/lambda_root);
		this.log.log("exp_U_return", exp_U_return);
		
		double exp_nest_commercial = exp((logsum_nest_commercial*lambda_commercial)/lambda_root);
		this.log.log("exp_nest_commercial", exp_nest_commercial);
		
		//nest sum and logsum of root
		double nest_root_sum = exp_U_private + exp_U_return + exp_nest_commercial;
		this.log.log("nest_root_sum", nest_root_sum);
		
		//nest probabilities of root
		double p_private_in_nest_root = (exp_U_private)/nest_root_sum;
		this.log.log("p_private_in_nest_root", p_private_in_nest_root);
		
		double p_return_in_nest_root = (exp_U_return)/nest_root_sum;
		this.log.log("p_return_in_nest_root", p_return_in_nest_root);
		
		double p_commercial_in_nest_root = (exp_nest_commercial)/nest_root_sum;
		this.log.log("p_commercial_in_nest_root", p_commercial_in_nest_root);
		//-------------------------------------------------------------------------------------------
		
		
		
		// Put probability of each category in a map. The probabilities are computed by multiplying the nest-probabilities along the paths in the nest structure.
		Map<Purpose, Double> probabilities = new HashMap<>();
		probabilities.put(Purpose.DELIVERY,  p_commercial_in_nest_root*p_delivery_in_nest_commercial);
		probabilities.put(Purpose.DROP_OFF,  p_commercial_in_nest_root*p_dropoff_in_nest_commercial);
		probabilities.put(Purpose.OTHER,  p_commercial_in_nest_root*p_other_in_nest_commercial);
		probabilities.put(Purpose.PRIVATE,  p_private_in_nest_root);
		probabilities.put(Purpose.RETURN,  p_return_in_nest_root);
		probabilities.put(Purpose.SERVICE,  p_commercial_in_nest_root*p_service_in_nest_commercial);
		
		this.log.logProbabilities(probabilities);
				
		//Filter probabilities
		Map<Purpose, Double> filteredProbabilities = probabilities
			.entrySet()
			.stream()
			.filter(e -> !Double.isNaN(e.getValue()))
			.collect(toMap(Entry::getKey, Entry::getValue));
				
		DiscreteDistribution<Purpose> distribution = new DiscreteDistribution<>(filteredProbabilities);
		Purpose p = distribution.draw(randomNumber);
		
		this.log.log("Selected " + p.name(), 1.0);
		return List.of(p);

	}

}

