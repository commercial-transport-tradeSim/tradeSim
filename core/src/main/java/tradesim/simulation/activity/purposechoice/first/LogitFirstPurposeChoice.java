package tradesim.simulation.activity.purposechoice.first;

import static java.lang.Math.exp;
import static java.util.stream.Collectors.toMap;
import static tradesim.model.activity.Purpose.DELIVERY;
import static tradesim.model.activity.Purpose.DROP_OFF;
import static tradesim.model.activity.Purpose.OTHER;
import static tradesim.model.activity.Purpose.SERVICE;

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
 * The Class LogitFirstPurposeChoice.
 */
public class LogitFirstPurposeChoice implements PurposeChoiceModel {
		
	private final LogitFirstPurposeChoiceUtilityFunction utilities;
	private final PurposeChoiceModelLogger log;
	
	/**
	 * Instantiates a LogitFirstPurposeChoice with the given LogitParameters, LogitFirstPurposeChoiceHelper, PurposeChoiceModelLogger.
	 *
	 * @param parameters the parameters
	 * @param helper the helper
	 * @param log the logger
	 */
	public LogitFirstPurposeChoice(Parameters parameters, LogitFirstPurposeChoiceHelper helper, PurposeChoiceModelLogger log) {
		super();
		this.log = log;
		this.utilities = new LogitFirstPurposeChoiceUtilityFunction(parameters, helper, log);
	}
	
	/**
	 * Convenience constuctor: Uses a NullPurposeChoiceModelLogger as logger.
	 *
	 * @param parameters the parameters
	 * @param helper the helper
	 */
	public LogitFirstPurposeChoice(Parameters parameters, LogitFirstPurposeChoiceHelper helper) {
		this(parameters, helper, new NullPurposeChoiceModelLogger());
	}
	
	@Override
	public List<Purpose> possibleResults(Business business, Vehicle vehicle, Tour tour, ActivityBuilder activity, double randomNumber) {
		this.log.logStart(business, vehicle);	
		
		// Calculate probabilities for each category using the given utilities object with respect to the availability flags above
		double U_other = utilities.calculateU_other(business, vehicle, tour, activity, randomNumber);
		this.log.log(business, vehicle, "U_other", U_other);
		double U_goods = utilities.calculateU_goods(business, vehicle, tour, activity, randomNumber);
		this.log.log(business, vehicle, "U_goods", U_goods);
		double U_service = utilities.calculateU_service(business, vehicle, tour, activity, randomNumber);
		this.log.log(business, vehicle, "U_service", U_service);
		double U_person = utilities.calculateU_person(business, vehicle, tour, activity, randomNumber);
		this.log.log(business, vehicle, "U_person", U_person);
		
		
		//nest: root --------------------------------------------------------------------------------
		double exp_U_goods = exp(U_goods);
		this.log.log(business, vehicle, "exp_U_goods", exp_U_goods);
		double exp_U_service = exp(U_service);
		this.log.log(business, vehicle, "exp_U_service", exp_U_service);
		double exp_U_person = exp(U_person);
		this.log.log(business, vehicle, "exp_U_person", exp_U_person);
		double exp_U_other = exp(U_other);
		this.log.log(business, vehicle, "exp_U_other", exp_U_other);
		
		//nest sum and logsum of root
		double nest_root_sum = exp_U_goods + exp_U_service + exp_U_person + exp_U_other;
		this.log.log(business, vehicle, "nest_root_sum", nest_root_sum);
		
		//nest probabilities of root
		double p_delivery_in_nest_root = (exp_U_goods)/nest_root_sum;
		this.log.log(business, vehicle, "p_delivery_in_nest_root", p_delivery_in_nest_root);
		double p_service_in_nest_root = (exp_U_service)/nest_root_sum;
		this.log.log(business, vehicle, "p_service_in_nest_root", p_service_in_nest_root);
		double p_dropoff_in_nest_root = (exp_U_person)/nest_root_sum;
		this.log.log(business, vehicle, "p_dropoff_in_nest_root", p_dropoff_in_nest_root);
		double p_other_in_nest_root = (exp_U_other)/nest_root_sum;
		this.log.log(business, vehicle, "p_other_in_nest_root", p_other_in_nest_root);
		//-------------------------------------------------------------------------------------------
		
		
		// Put probability of each category in a map. The probabilities are computed by multiplying the nest-probabilities along the paths in the nest structure.
		Map<Purpose, Double> probabilities = new HashMap<>();
		probabilities.put(DELIVERY,  p_delivery_in_nest_root);
		probabilities.put(DROP_OFF,  p_dropoff_in_nest_root);
		probabilities.put(OTHER,  p_other_in_nest_root);
		probabilities.put(SERVICE,  p_service_in_nest_root);
		
		this.log.logProbabilities(probabilities, business, vehicle);
		
		//Filter probabilities
		Map<Purpose, Double> filteredProbabilities = probabilities
			.entrySet()
			.stream()
			.filter(e -> !Double.isNaN(e.getValue()))
			.collect(toMap(Entry::getKey, Entry::getValue));
		
		DiscreteDistribution<Purpose> distribution = new DiscreteDistribution<>(filteredProbabilities);
		Purpose choice = distribution.draw(randomNumber);
		
		log.log(business, vehicle, "Selected " + choice.name(), 1.0);
		
		return List.of(choice);
	}

}

