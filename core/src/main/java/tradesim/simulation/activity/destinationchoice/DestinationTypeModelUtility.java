package tradesim.simulation.activity.destinationchoice;

import tradesim.model.activity.ActivityBuilder;
import tradesim.model.business.Business;
import tradesim.model.opportunity.OpportunityType;
import tradesim.model.tour.Tour;
import tradesim.model.vehicles.Vehicle;
import tradesim.util.input.Parameters;

/**
 * The Class DestinationTypeModelUtility.
 */
public class DestinationTypeModelUtility {
	
	private final double asc_bau;
	private final double asc_kund;
	private final double asc_sonst;
	private final double b_goods_bau;
	private final double b_goods_kund;
	private final double b_goods_sonst;
	private final double b_lightFzg_bau;
	private final double b_lightFzg_kund;
	private final double b_lightFzg_sonst;
	private final double b_mediumFzg_bau;
	private final double b_mediumFzg_kund;
	private final double b_mediumFzg_sonst;
	private final double b_people_bau;
	private final double b_people_kund;
	private final double b_people_sonst;
	private final double b_service_bau;
	private final double b_service_kund;
	private final double b_service_sonst;
	private final double b_wzF_bau;
	private final double b_wzG_kund;
	private final double b_wzH_kund;
	private final double b_wzQ_kund;
	private final double b_wzH_sonst;
	private final double b_wzG_business;
	private final double b_wzH_business;
	
	private final DestinationTypeModelHelper helper;
	private final DestinationChoiceModelLogger log;
	
	/**
	 * Instantiates a new destination type model utility.
	 *
	 * @param logitParameters the logit parameters
	 * @param helper the helper
	 * @param log the log
	 */
	public DestinationTypeModelUtility(Parameters logitParameters, DestinationTypeModelHelper helper, DestinationChoiceModelLogger log) {
		super();
		this.helper = helper;
		this.log = log;
		
		this.asc_bau = logitParameters.get("asc_bau");
		this.asc_kund = logitParameters.get("asc_kund");
		this.asc_sonst = logitParameters.get("asc_sonst");
		this.b_goods_bau = logitParameters.get("b_goods_bau");
		this.b_goods_kund = logitParameters.get("b_goods_kund");
		this.b_goods_sonst = logitParameters.get("b_goods_sonst");
		this.b_lightFzg_bau = logitParameters.get("b_lightFzg_bau");
		this.b_lightFzg_kund = logitParameters.get("b_lightFzg_kund");
		this.b_lightFzg_sonst = logitParameters.get("b_lightFzg_sonst");
		this.b_mediumFzg_bau = logitParameters.get("b_mediumFzg_bau");
		this.b_mediumFzg_kund = logitParameters.get("b_mediumFzg_kund");
		this.b_mediumFzg_sonst = logitParameters.get("b_mediumFzg_sonst");
		this.b_people_bau = logitParameters.get("b_people_bau");
		this.b_people_kund = logitParameters.get("b_people_kund");
		this.b_people_sonst = logitParameters.get("b_people_sonst");
		this.b_service_bau = logitParameters.get("b_service_bau");
		this.b_service_kund = logitParameters.get("b_service_kund");
		this.b_service_sonst = logitParameters.get("b_service_sonst");
		
		this.b_wzF_bau = logitParameters.get("b_wzF_bau");
		this.b_wzG_kund = logitParameters.get("b_wzG_kund");
		this.b_wzH_kund = logitParameters.get("b_wzH_kund");
		this.b_wzQ_kund = logitParameters.get("b_wzQ_kund");
		this.b_wzH_sonst = logitParameters.get("b_wzH_sonst");
		this.b_wzG_business = logitParameters.get("b_wzG_business");
		this.b_wzH_business = logitParameters.get("b_wzH_business");
		

	}
	
	/**
	 * Instantiates a new destination type model utility.
	 *
	 * @param logitParameters the logit parameters
	 * @param helper the helper
	 */
	public DestinationTypeModelUtility(Parameters logitParameters, DestinationTypeModelHelper helper) {
		this(logitParameters, helper, new NullDestinationChoiceModelLogger());
	}
	
	
	/**
	 * Calculate U business.
	 *
	 * @param business the business
	 * @param vehicle the vehicle
	 * @param tour the tour
	 * @param activity the activity
	 * @param randomNumber the random number
	 * @return the double
	 */
	public double calculateU_business(Business business, Vehicle vehicle, Tour tour, ActivityBuilder activity, double randomNumber) {
		OpportunityType category = OpportunityType.OTHER_BUSINESS;
		
		double IS_TRADE_G_value = helper.getIS_TRADE_G_value(category, business, vehicle, tour, activity, randomNumber);
		this.log.log(category, "IS_TRADE_G_value", IS_TRADE_G_value);
		
		double IS_TRADE_H_value = helper.getIS_TRADE_H_value(category, business, vehicle, tour, activity, randomNumber);
		this.log.log(category, "IS_TRADE_H_value", IS_TRADE_H_value);
		
		return IS_TRADE_G_value * b_wzG_business + IS_TRADE_H_value * b_wzH_business;
	}
	
	/**
	 * Calculate U construction.
	 *
	 * @param business the business
	 * @param vehicle the vehicle
	 * @param tour the tour
	 * @param activity the activity
	 * @param randomNumber the random number
	 * @return the double
	 */
	public double calculateU_construction(Business business, Vehicle vehicle, Tour tour, ActivityBuilder activity, double randomNumber) {
		OpportunityType category = OpportunityType.CONSTRUCTION_SITE;
		
		double IS_PURPOSE_DELIVERY_value = helper.getIS_PURPOSE_DELIVERY(category, business, vehicle, tour, activity, randomNumber);
		this.log.log(category, "IS_PURPOSE_DELIVERY_value", IS_PURPOSE_DELIVERY_value);
		
		double IS_PURPOSE_SERVICE_value = helper.getIS_PURPOSE_SERVICE(category, business, vehicle, tour, activity, randomNumber);
		this.log.log(category, "IS_PURPOSE_SERVICE_value", IS_PURPOSE_SERVICE_value);
		
		double IS_PURPOSE_DROP_OFF_value = helper.getIS_PURPOSE_DROP_OFF(category, business, vehicle, tour, activity, randomNumber);
		this.log.log(category, "IS_PURPOSE_DROP_OFF_value", IS_PURPOSE_DROP_OFF_value);

		double IS_TRADE_F_value = helper.getIS_TRADE_F_value(category, business, vehicle, tour, activity, randomNumber);
		this.log.log(category, "IS_TRADE_F_value", IS_TRADE_F_value);
		
		double IS_LIGHT_VEHICLE_value = helper.getIS_LIGHT_VEHICLE(category, business, vehicle, tour, activity, randomNumber);
		this.log.log(category, "IS_LIGHT_VEHICLE_value", IS_LIGHT_VEHICLE_value);
		
		double IS_MEDIUM_VEHICLE_value = helper.getIS_MEDIUM_VEHICLE(category, business, vehicle, tour, activity, randomNumber);
		this.log.log(category, "IS_MEDIUM_VEHICLE_value", IS_MEDIUM_VEHICLE_value);

		return   (asc_bau)
				+(IS_PURPOSE_DELIVERY_value*b_goods_bau)
				+(IS_PURPOSE_SERVICE_value*b_service_bau)
				+(IS_PURPOSE_DROP_OFF_value*b_people_bau)
				+(IS_TRADE_F_value*b_wzF_bau)
				+(IS_LIGHT_VEHICLE_value*b_lightFzg_bau)
				+(IS_MEDIUM_VEHICLE_value*b_mediumFzg_bau);
	}
	
	/**
	 * Calculate U customer.
	 *
	 * @param business the business
	 * @param vehicle the vehicle
	 * @param tour the tour
	 * @param activity the activity
	 * @param randomNumber the random number
	 * @return the double
	 */
	public double calculateU_customer(Business business, Vehicle vehicle, Tour tour, ActivityBuilder activity, double randomNumber) {
		OpportunityType category = OpportunityType.CUSTOMER;
		
		double IS_PURPOSE_DELIVERY_value = helper.getIS_PURPOSE_DELIVERY(category, business, vehicle, tour, activity, randomNumber);
		this.log.log(category, "IS_PURPOSE_DELIVERY_value", IS_PURPOSE_DELIVERY_value);
		
		double IS_PURPOSE_SERVICE_value = helper.getIS_PURPOSE_SERVICE(category, business, vehicle, tour, activity, randomNumber);
		this.log.log(category, "IS_PURPOSE_SERVICE_value", IS_PURPOSE_SERVICE_value);
		
		double IS_PURPOSE_DROP_OFF_value = helper.getIS_PURPOSE_DROP_OFF(category, business, vehicle, tour, activity, randomNumber);
		this.log.log(category, "IS_PURPOSE_DROP_OFF_value", IS_PURPOSE_DROP_OFF_value);
		
		double IS_TRADE_G_value = helper.getIS_TRADE_G_value(category, business, vehicle, tour, activity, randomNumber);
		this.log.log(category, "IS_TRADE_G_value", IS_TRADE_G_value);
		
		double IS_TRADE_H_value = helper.getIS_TRADE_H_value(category, business, vehicle, tour, activity, randomNumber);
		this.log.log(category, "IS_TRADE_H_value", IS_TRADE_H_value);
		
		double IS_TRADE_Q_value = helper.getIS_TRADE_Q_value(category, business, vehicle, tour, activity, randomNumber);
		this.log.log(category, "IS_TRADE_Q_value", IS_TRADE_Q_value);
		
		double IS_LIGHT_VEHICLE_value = helper.getIS_LIGHT_VEHICLE(category, business, vehicle, tour, activity, randomNumber);
		this.log.log(category, "IS_LIGHT_VEHICLE_value", IS_LIGHT_VEHICLE_value);
		
		double IS_MEDIUM_VEHICLE_value = helper.getIS_MEDIUM_VEHICLE(category, business, vehicle, tour, activity, randomNumber);
		this.log.log(category, "IS_MEDIUM_VEHICLE_value", IS_MEDIUM_VEHICLE_value);

		return   (asc_kund)
				+(IS_PURPOSE_DELIVERY_value*b_goods_kund)
				+(IS_PURPOSE_SERVICE_value*b_service_kund)
				+(IS_PURPOSE_DROP_OFF_value*b_people_kund)
				+(IS_TRADE_G_value*b_wzG_kund)
				+(IS_TRADE_H_value*b_wzH_kund)
				+(IS_TRADE_Q_value*b_wzQ_kund)
				+(IS_LIGHT_VEHICLE_value*b_lightFzg_kund)
				+(IS_MEDIUM_VEHICLE_value*b_mediumFzg_kund);
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
		OpportunityType category = OpportunityType.OTHER;
		
		double IS_PURPOSE_DELIVERY_value = helper.getIS_PURPOSE_DELIVERY(category, business, vehicle, tour, activity, randomNumber);
		this.log.log(category, "IS_PURPOSE_DELIVERY_value", IS_PURPOSE_DELIVERY_value);
		
		double IS_PURPOSE_SERVICE_value = helper.getIS_PURPOSE_SERVICE(category, business, vehicle, tour, activity, randomNumber);
		this.log.log(category, "IS_PURPOSE_SERVICE_value", IS_PURPOSE_SERVICE_value);
		
		double IS_PURPOSE_DROP_OFF_value = helper.getIS_PURPOSE_DROP_OFF(category, business, vehicle, tour, activity, randomNumber);
		this.log.log(category, "IS_PURPOSE_DROP_OFF_value", IS_PURPOSE_DROP_OFF_value);
		
		double IS_TRADE_H_value = helper.getIS_TRADE_H_value(category, business, vehicle, tour, activity, randomNumber);
		this.log.log(category, "IS_TRADE_H_value", IS_TRADE_H_value);
		
		double IS_LIGHT_VEHICLE_value = helper.getIS_LIGHT_VEHICLE(category, business, vehicle, tour, activity, randomNumber);
		this.log.log(category, "IS_LIGHT_VEHICLE_value", IS_LIGHT_VEHICLE_value);
		
		double IS_MEDIUM_VEHICLE_value = helper.getIS_MEDIUM_VEHICLE(category, business, vehicle, tour, activity, randomNumber);
		this.log.log(category, "IS_MEDIUM_VEHICLE_value", IS_MEDIUM_VEHICLE_value);

		return   (asc_sonst)
				+(IS_PURPOSE_DELIVERY_value*b_goods_sonst)
				+(IS_PURPOSE_SERVICE_value*b_service_sonst)
				+(IS_PURPOSE_DROP_OFF_value*b_people_sonst)
				+(IS_TRADE_H_value*b_wzH_sonst)
				+(IS_LIGHT_VEHICLE_value*b_lightFzg_sonst)
				+(IS_MEDIUM_VEHICLE_value*b_mediumFzg_sonst);
	}
}

