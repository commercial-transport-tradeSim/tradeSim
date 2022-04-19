package tradesim.simulation.activity.purposechoice.other;

import tradesim.model.activity.ActivityBuilder;
import tradesim.model.activity.Purpose;
import tradesim.model.business.Business;
import tradesim.model.tour.Tour;
import tradesim.model.vehicles.Vehicle;
import tradesim.util.input.Parameters;

/**
 * The Class NestedLogitPurposeChoiceUtilityFunction.
 */
public class NestedLogitPurposeChoiceUtilityFunction {
	
	private final double asc_goods;
	private final double asc_other;
	private final double asc_people;
	private final double asc_private;
	private final double asc_return;
	private final double asc_service;
	private final double klLkw_goods;
	private final double klLkw_other;
	private final double klLkw_people;
	private final double klLkw_service;
	private final double pkw_goods;
	private final double pkw_other;
	private final double pkw_people;
	private final double pkw_return;
	private final double pkw_service;
	private final double prevGoods_goods;
	private final double prevGoods_other;
	private final double prevGoods_people;
	private final double prevGoods_private;
	private final double prevGoods_return;
	private final double prevGoods_service;
	private final double prevPeople_people;
	private final double prevPrivate_private;
	private final double prevReturn_return;
	private final double prevService_service;
	
	private final NestedLogitPurposeChoiceHelper helper;
	private final PurposeChoiceModelLogger log;
	
	/**
	 * Instantiates a new nested logit purpose choice utility function.
	 *
	 * @param logitParameters the logit parameters
	 * @param helper the helper
	 * @param log the logger
	 */
	public NestedLogitPurposeChoiceUtilityFunction(Parameters logitParameters, NestedLogitPurposeChoiceHelper helper, PurposeChoiceModelLogger log) {
		super();
		this.helper = helper;
		this.log = log;
		
		this.asc_goods = logitParameters.get("asc_goods");
		this.asc_other = logitParameters.get("asc_other");
		this.asc_people = logitParameters.get("asc_people");
		this.asc_private = logitParameters.get("asc_private");
		this.asc_return = logitParameters.get("asc_return");
		this.asc_service = logitParameters.get("asc_service");
		this.klLkw_goods = logitParameters.get("klLkw_goods");
		this.klLkw_other = logitParameters.get("klLkw_other");
		this.klLkw_people = logitParameters.get("klLkw_people");
		this.klLkw_service = logitParameters.get("klLkw_service");
		this.pkw_goods = logitParameters.get("pkw_goods");
		this.pkw_other = logitParameters.get("pkw_other");
		this.pkw_people = logitParameters.get("pkw_people");
		this.pkw_return = logitParameters.get("pkw_return");
		this.pkw_service = logitParameters.get("pkw_service");
		this.prevGoods_goods = logitParameters.get("prevGoods_goods");
		this.prevGoods_other = logitParameters.get("prevGoods_other");
		this.prevGoods_people = logitParameters.get("prevGoods_people");
		this.prevGoods_private = logitParameters.get("prevGoods_private");
		this.prevGoods_return = logitParameters.get("prevGoods_return");
		this.prevGoods_service = logitParameters.get("prevGoods_service");
		this.prevPeople_people = logitParameters.get("prevPeople_people");
		this.prevPrivate_private = logitParameters.get("prevPrivate_private");
		this.prevReturn_return = logitParameters.get("prevReturn_return");
		this.prevService_service = logitParameters.get("prevService_service");

	}
	
	/**
	 * Instantiates a new nested logit purpose choice utility function.
	 *
	 * @param logitParameters the logit parameters
	 * @param helper the helper
	 */
	public NestedLogitPurposeChoiceUtilityFunction(Parameters logitParameters, NestedLogitPurposeChoiceHelper helper) {
		this(logitParameters, helper, new NullPurposeChoiceModelLogger());
	}
	
	
	/**
	 * Calculate U private.
	 *
	 * @param business the business
	 * @param vehicle the vehicle
	 * @param tour the tour
	 * @param activity the activity
	 * @param randomNumber the random number
	 * @return the double
	 */
	public double calculateU_private(Business business, Vehicle vehicle, Tour tour, ActivityBuilder activity, double randomNumber) {
		Purpose category = Purpose.PRIVATE;
		
		double CS_PRIVATE_value = helper.getCS_PRIVATE(category, business, vehicle, tour, activity, randomNumber);
		this.log.log(category, "CS_PRIVATE_value", CS_PRIVATE_value);
		
		double CS_GOODS_value = helper.getCS_GOODS(category, business, vehicle, tour, activity, randomNumber);
		this.log.log(category, "CS_GOODS_value", CS_GOODS_value);
		

		return   (asc_private)
				+(prevPrivate_private*((CS_PRIVATE_value > 0.0d) ? 1.0d : 0.0d))
				+(prevGoods_private*((CS_GOODS_value > 0.0d) ? 1.0d : 0.0d));
	}
	
	
	/**
	 * Calculate U return.
	 *
	 * @param business the business
	 * @param vehicle the vehicle
	 * @param tour the tour
	 * @param activity the activity
	 * @param randomNumber the random number
	 * @return the double
	 */
	public double calculateU_return(Business business, Vehicle vehicle, Tour tour, ActivityBuilder activity, double randomNumber) {
		Purpose category = Purpose.RETURN;
		
		double CS_RETURN_value = helper.getCS_RETURN(category, business, vehicle, tour, activity, randomNumber);
		this.log.log(category, "CS_RETURN_value", CS_RETURN_value);
		
		double CS_GOODS_value = helper.getCS_GOODS(category, business, vehicle, tour, activity, randomNumber);
		this.log.log(category, "CS_GOODS_value", CS_GOODS_value);
				
		double IS_LIGHT_VEHICLE_value = helper.getIS_LIGHT_VEHICLE(category, business, vehicle, tour, activity, randomNumber);
		this.log.log(category, "IS_LIGHT_VEHICLE_value", IS_LIGHT_VEHICLE_value);
		

		return   (asc_return)
				+(prevReturn_return*((CS_RETURN_value > 0.0d) ? 1.0d : 0.0d))
				+(prevGoods_return*((CS_GOODS_value > 0.0d) ? 1.0d : 0.0d))
				+(pkw_return*IS_LIGHT_VEHICLE_value);
	}
	
	
	/**
	 * Calculate U goods.
	 *
	 * @param business the business
	 * @param vehicle the vehicle
	 * @param tour the tour
	 * @param activity the activity
	 * @param randomNumber the random number
	 * @return the double
	 */
	public double calculateU_goods(Business business, Vehicle vehicle, Tour tour, ActivityBuilder activity, double randomNumber) {
		Purpose category = Purpose.DELIVERY;
		
		double CS_GOODS_value = helper.getCS_GOODS(category, business, vehicle, tour, activity, randomNumber);
		this.log.log(category, "CS_GOODS_value", CS_GOODS_value);
		
		double IS_LIGHT_VEHICLE_value = helper.getIS_LIGHT_VEHICLE(category, business, vehicle, tour, activity, randomNumber);
		this.log.log(category, "IS_LIGHT_VEHICLE_value", IS_LIGHT_VEHICLE_value);
		
		double IS_MEDIUM_VEHICLE_value = helper.getIS_MEDIUM_VEHICLE(category, business, vehicle, tour, activity, randomNumber);
		this.log.log(category, "IS_MEDIUM_VEHICLE_value", IS_MEDIUM_VEHICLE_value);		

		return   (asc_goods)
				+(prevGoods_goods*((CS_GOODS_value > 0.0d) ? 1.0d : 0.0d))
				+(pkw_goods*IS_LIGHT_VEHICLE_value)
				+(klLkw_goods*IS_MEDIUM_VEHICLE_value);
	}
	
	
	/**
	 * Calculate U service.
	 *
	 * @param business the business
	 * @param vehicle the vehicle
	 * @param tour the tour
	 * @param activity the activity
	 * @param randomNumber the random number
	 * @return the double
	 */
	public double calculateU_service(Business business, Vehicle vehicle, Tour tour, ActivityBuilder activity, double randomNumber) {
		Purpose category = Purpose.SERVICE;
		
		double CS_GOODS_value = helper.getCS_GOODS(category, business, vehicle, tour, activity, randomNumber);
		this.log.log(category, "CS_GOODS_value", CS_GOODS_value);
		
		double CS_SERVICE_value = helper.getCS_SERVICE(category, business, vehicle, tour, activity, randomNumber);
		this.log.log(category, "CS_SERVICE_value", CS_SERVICE_value);
		
		double IS_LIGHT_VEHICLE_value = helper.getIS_LIGHT_VEHICLE(category, business, vehicle, tour, activity, randomNumber);
		this.log.log(category, "IS_LIGHT_VEHICLE_value", IS_LIGHT_VEHICLE_value);
		
		double IS_MEDIUM_VEHICLE_value = helper.getIS_MEDIUM_VEHICLE(category, business, vehicle, tour, activity, randomNumber);
		this.log.log(category, "IS_MEDIUM_VEHICLE_value", IS_MEDIUM_VEHICLE_value);	
		

		return   (asc_service)
				+(prevGoods_service*((CS_GOODS_value > 0.0d) ? 1.0d : 0.0d))
				+(prevService_service*((CS_SERVICE_value > 0.0d) ? 1.0d : 0.0d))
				+(pkw_service*IS_LIGHT_VEHICLE_value)
				+(klLkw_service*IS_MEDIUM_VEHICLE_value);
	}
	
	
	/**
	 * Calculate U people.
	 *
	 * @param business the business
	 * @param vehicle the vehicle
	 * @param tour the tour
	 * @param activity the activity
	 * @param randomNumber the random number
	 * @return the double
	 */
	public double calculateU_people(Business business, Vehicle vehicle, Tour tour, ActivityBuilder activity, double randomNumber) {
		Purpose category = Purpose.DROP_OFF;
		
		double CS_GOODS_value = helper.getCS_GOODS(category, business, vehicle, tour, activity, randomNumber);
		this.log.log(category, "CS_GOODS_value", CS_GOODS_value);
		
		double CS_PERSON_value = helper.getCS_PERSON(category, business, vehicle, tour, activity, randomNumber);
		this.log.log(category, "CS_PERSON_value", CS_PERSON_value);
		
		double IS_LIGHT_VEHICLE_value = helper.getIS_LIGHT_VEHICLE(category, business, vehicle, tour, activity, randomNumber);
		this.log.log(category, "IS_LIGHT_VEHICLE_value", IS_LIGHT_VEHICLE_value);
		
		double IS_MEDIUM_VEHICLE_value = helper.getIS_MEDIUM_VEHICLE(category, business, vehicle, tour, activity, randomNumber);
		this.log.log(category, "IS_MEDIUM_VEHICLE_value", IS_MEDIUM_VEHICLE_value);	
		
		
		return   (asc_people)
				+(prevGoods_people*((CS_GOODS_value > 0.0d) ? 1.0d : 0.0d))
				+(prevPeople_people*((CS_PERSON_value > 0.0d) ? 1.0d : 0.0d))
				+(pkw_people*IS_LIGHT_VEHICLE_value)
				+(klLkw_people*IS_MEDIUM_VEHICLE_value);
	}
	
	
	/**
	 * Calculate U other.
	 *
	 * @param business the business
	 * @param vehicle the vehicle
	 * @param tour the tour
	 * @param activity the activity
	 * @param randomNumber the random number
	 * @return the double
	 */
	public double calculateU_other(Business business, Vehicle vehicle, Tour tour, ActivityBuilder activity, double randomNumber) {
		Purpose category = Purpose.OTHER;
		
		double CS_GOODS_value = helper.getCS_GOODS(category, business, vehicle, tour, activity, randomNumber);
		this.log.log(category, "CS_GOODS_value", CS_GOODS_value);
		
		double IS_LIGHT_VEHICLE_value = helper.getIS_LIGHT_VEHICLE(category, business, vehicle, tour, activity, randomNumber);
		this.log.log(category, "IS_LIGHT_VEHICLE_value", IS_LIGHT_VEHICLE_value);
		
		double IS_MEDIUM_VEHICLE_value = helper.getIS_MEDIUM_VEHICLE(category, business, vehicle, tour, activity, randomNumber);
		this.log.log(category, "IS_MEDIUM_VEHICLE_value", IS_MEDIUM_VEHICLE_value);	
		
		return   (asc_other)
				+(prevGoods_other*((CS_GOODS_value > 0.0d) ? 1.0d : 0.0d))
				+(pkw_other*IS_LIGHT_VEHICLE_value)
				+(klLkw_other*IS_MEDIUM_VEHICLE_value);
	}
}

