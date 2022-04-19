package tradesim.simulation.activity.purposechoice.first;

import tradesim.model.activity.ActivityBuilder;
import tradesim.model.activity.Purpose;
import tradesim.model.business.Business;
import tradesim.model.tour.Tour;
import tradesim.model.vehicles.Vehicle;
import tradesim.util.input.Parameters;

// TODO: Auto-generated Javadoc
/**
 * The Class LogitFirstPurposeChoiceUtilityFunction.
 */
public class LogitFirstPurposeChoiceUtilityFunction {
	
	private final double asc_goods;
	private final double asc_person;
	private final double asc_service;
	private final double b_goods_lightFzg;
	private final double b_goods_mediumFzg;
	private final double b_goods_wzDL;
	private final double b_goods_wzInd;
	private final double b_person_lightFzg;
	private final double b_person_mediumFzg;
	private final double b_person_wzDL;
	private final double b_person_wzInd;
	private final double b_service_lightFzg;
	private final double b_service_mediumFzg;
	private final double b_service_wzDL;
	private final double b_service_wzInd;
	
	private final LogitFirstPurposeChoiceHelper helper;
	private final PurposeChoiceModelLogger log;
	
	/**
	 * Instantiates a new logit first purpose choice utility function.
	 *
	 * @param parameters the parameters
	 * @param helper the helper
	 * @param log the log
	 */
	public LogitFirstPurposeChoiceUtilityFunction(Parameters parameters, LogitFirstPurposeChoiceHelper helper, PurposeChoiceModelLogger log) {
		super();
		this.helper = helper;
		this.log = log;
		
		this.asc_goods = parameters.get("asc_goods");
		this.asc_person = parameters.get("asc_person");
		this.asc_service = parameters.get("asc_service");
		this.b_goods_lightFzg = parameters.get("b_goods_lightFzg");
		this.b_goods_mediumFzg = parameters.get("b_goods_mediumFzg");
		this.b_goods_wzDL = parameters.get("b_goods_wzDL");
		this.b_goods_wzInd = parameters.get("b_goods_wzInd");
		this.b_person_lightFzg = parameters.get("b_person_lightFzg");
		this.b_person_mediumFzg = parameters.get("b_person_mediumFzg");
		this.b_person_wzDL = parameters.get("b_person_wzDL");
		this.b_person_wzInd = parameters.get("b_person_wzInd");
		this.b_service_lightFzg = parameters.get("b_service_lightFzg");
		this.b_service_mediumFzg = parameters.get("b_service_mediumFzg");
		this.b_service_wzDL = parameters.get("b_service_wzDL");
		this.b_service_wzInd = parameters.get("b_service_wzInd");
		

	}
	
	/**
	 * Instantiates a new logit first purpose choice utility function.
	 *
	 * @param parameters the parameters
	 * @param helper the helper
	 */
	public LogitFirstPurposeChoiceUtilityFunction(Parameters parameters, LogitFirstPurposeChoiceHelper helper) {
		this(parameters, helper, new NullPurposeChoiceModelLogger());
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
		return   (0.0d);
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
		
		double IS_SECTOR_INDUSTRY_value = helper.getIS_SECTOR_INDUSTRY(category, business, vehicle, tour, activity, randomNumber);
		this.log.log(business, vehicle, category, "IS_SECTOR_INDUSTRY_value", IS_SECTOR_INDUSTRY_value);
		
		double IS_SECTOR_SERVICE_value = helper.getIS_SECTOR_SERVICE(category, business, vehicle, tour, activity, randomNumber);
		this.log.log(business, vehicle, category, "IS_SECTOR_SERVICE_value", IS_SECTOR_SERVICE_value);
		
		double IS_LIGHT_VEHICLE_value = helper.getIS_LIGHT_VEHICLE(category, business, vehicle, tour, activity, randomNumber);
		this.log.log(business, vehicle, category, "IS_LIGHT_VEHICLE_value", IS_LIGHT_VEHICLE_value);
		
		double IS_MEDIUM_VEHICLE_value = helper.getIS_MEDIUM_VEHICLE(category, business, vehicle, tour, activity, randomNumber);
		this.log.log(business, vehicle, category, "IS_MEDIUM_VEHICLE_value", IS_MEDIUM_VEHICLE_value);
		
		
		
		return   (asc_goods)
				+(b_goods_wzInd*IS_SECTOR_INDUSTRY_value)
				+(b_goods_wzDL*IS_SECTOR_SERVICE_value)
				+(IS_LIGHT_VEHICLE_value*b_goods_lightFzg)
				+(IS_MEDIUM_VEHICLE_value*b_goods_mediumFzg);
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
		
		double IS_SECTOR_INDUSTRY_value = helper.getIS_SECTOR_INDUSTRY(category, business, vehicle, tour, activity, randomNumber);
		this.log.log(business, vehicle, category, "IS_SECTOR_INDUSTRY_value", IS_SECTOR_INDUSTRY_value);
		
		double IS_SECTOR_SERVICE_value = helper.getIS_SECTOR_SERVICE(category, business, vehicle, tour, activity, randomNumber);
		this.log.log(business, vehicle, category, "IS_SECTOR_SERVICE_value", IS_SECTOR_SERVICE_value);
		
		double IS_LIGHT_VEHICLE_value = helper.getIS_LIGHT_VEHICLE(category, business, vehicle, tour, activity, randomNumber);
		this.log.log(business, vehicle, category, "IS_LIGHT_VEHICLE_value", IS_LIGHT_VEHICLE_value);
		
		double IS_MEDIUM_VEHICLE_value = helper.getIS_MEDIUM_VEHICLE(category, business, vehicle, tour, activity, randomNumber);
		this.log.log(business, vehicle, category, "IS_MEDIUM_VEHICLE_value", IS_MEDIUM_VEHICLE_value);
		
		
		
		return   (asc_service)
				+(b_service_wzInd*IS_SECTOR_INDUSTRY_value)
				+(b_service_wzDL*IS_SECTOR_SERVICE_value)
				+(IS_LIGHT_VEHICLE_value*b_service_lightFzg)
				+(IS_MEDIUM_VEHICLE_value*b_service_mediumFzg);
	}
	
	/**
	 * Calculate U person.
	 *
	 * @param business the business
	 * @param vehicle the vehicle
	 * @param tour the tour
	 * @param activity the activity
	 * @param randomNumber the random number
	 * @return the double
	 */
	public double calculateU_person(Business business, Vehicle vehicle, Tour tour, ActivityBuilder activity, double randomNumber) {
		Purpose category = Purpose.DROP_OFF;
		
		double IS_SECTOR_INDUSTRY_value = helper.getIS_SECTOR_INDUSTRY(category, business, vehicle, tour, activity, randomNumber);
		this.log.log(business, vehicle, category, "IS_SECTOR_INDUSTRY_value", IS_SECTOR_INDUSTRY_value);
		
		double IS_SECTOR_SERVICE_value = helper.getIS_SECTOR_SERVICE(category, business, vehicle, tour, activity, randomNumber);
		this.log.log(business, vehicle, category, "IS_SECTOR_SERVICE_value", IS_SECTOR_SERVICE_value);
		
		double IS_LIGHT_VEHICLE_value = helper.getIS_LIGHT_VEHICLE(category, business, vehicle, tour, activity, randomNumber);
		this.log.log(business, vehicle, category, "IS_LIGHT_VEHICLE_value", IS_LIGHT_VEHICLE_value);
		
		double IS_MEDIUM_VEHICLE_value = helper.getIS_MEDIUM_VEHICLE(category, business, vehicle, tour, activity, randomNumber);
		this.log.log(business, vehicle, category, "IS_MEDIUM_VEHICLE_value", IS_MEDIUM_VEHICLE_value);
		
		
		
		return   (asc_person)
				+(b_person_wzInd*IS_SECTOR_INDUSTRY_value)
				+(b_person_wzDL*IS_SECTOR_SERVICE_value)
				+(IS_LIGHT_VEHICLE_value*b_person_lightFzg)
				+(IS_MEDIUM_VEHICLE_value*b_person_mediumFzg);
	}
}

