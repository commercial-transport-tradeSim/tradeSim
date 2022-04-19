package tradesim.simulation.tour.lasttour;

import java.util.List;

import tradesim.model.business.Business;
import tradesim.model.tour.Tour;
import tradesim.model.vehicles.Vehicle;
import tradesim.util.input.Parameters;

/**
 * The Class ContinueTourLogitUtility.
 */
public class ContinueTourLogitUtility {
	
	private final double asc_last;
	private final double b_lightFzg_last;
	private final double b_mediumFzg_last;
	private final double b_time_last;
	private final double b_wzDL_last;
	private final double b_wzInd_last;
	
	private final ContinueTourLogitHelper helper;
	private final ContinueTourChoiceModelLogger log;
	
	/**
	 * Instantiates a new continue tour logit utility.
	 *
	 * @param parameters the parameters
	 * @param helper the helper
	 * @param log the logger
	 */
	public ContinueTourLogitUtility(Parameters parameters, ContinueTourLogitHelper helper, ContinueTourChoiceModelLogger log) {
		super();
		this.helper = helper;
		this.log = log;
		
		this.asc_last = parameters.get("asc_last");
		this.b_lightFzg_last = parameters.get("b_lightFzg_last");
		this.b_mediumFzg_last = parameters.get("b_mediumFzg_last");
		this.b_time_last = parameters.get("b_time_last");
		this.b_wzDL_last = parameters.get("b_wzDL_last");
		this.b_wzInd_last = parameters.get("b_wzInd_last");		

	}
	
	/**
	 * Instantiates a new continue tour logit utility.
	 *
	 * @param parameters the parameters
	 * @param helper the helper
	 */
	public ContinueTourLogitUtility(Parameters parameters, ContinueTourLogitHelper helper) {
		this(parameters, helper, new NullContinueTourChoiceModelLogger());
	}
	
	
	/**
	 * Calculate U more.
	 *
	 * @param business the business
	 * @param vehicle the vehicle
	 * @param currentTours the current tours
	 * @param randomNumber the random number
	 * @return the double
	 */
	public double calculateU_more(Business business, Vehicle vehicle, List<Tour> currentTours, double randomNumber) {
		return 0.0d;
	}
	
	/**
	 * Calculate U last.
	 *
	 * @param business the business
	 * @param vehicle the vehicle
	 * @param currentTours the current tours
	 * @param randomNumber the random number
	 * @return the double
	 */
	public double calculateU_last(Business business, Vehicle vehicle, List<Tour> currentTours, double randomNumber) {
		boolean category = false;
		
		double END_MINUTES_value = helper.getEND_MINUTES(category, business, vehicle, currentTours, randomNumber);
		this.log.log(category, "END_MINUTES_value", END_MINUTES_value);
		
		double IS_SECTOR_INDUSTRY_value = helper.getIS_SECTOR_INDUSTRY(category, business, vehicle, currentTours, randomNumber);
		this.log.log(category, "IS_SECTOR_INDUSTRY_value", IS_SECTOR_INDUSTRY_value);
		
		double IS_SECTOR_SERVICE_value = helper.getIS_SECTOR_SERVICE(category, business, vehicle, currentTours, randomNumber);
		this.log.log(category, "IS_SECTOR_SERVICE_value", IS_SECTOR_SERVICE_value);
		
		double IS_LIGHT_VEHICLE_value = helper.getIS_LIGHT_VEHICLE(category, business, vehicle, currentTours, randomNumber);
		this.log.log(category, "IS_LIGHT_VEHICLE_value", IS_LIGHT_VEHICLE_value);
		
		double IS_HEAVY_VEHICLE_value = helper.getIS_MEDIUM_VEHICLE(category, business, vehicle, currentTours, randomNumber);
		this.log.log(category, "IS_HEAVY_VEHICLE_value", IS_HEAVY_VEHICLE_value);
		
		
		
		return   (asc_last)
				+(b_time_last*END_MINUTES_value)
				+(b_wzInd_last*IS_SECTOR_INDUSTRY_value)
				+(b_wzDL_last*IS_SECTOR_SERVICE_value)
				+(IS_LIGHT_VEHICLE_value*b_lightFzg_last)
				+(IS_HEAVY_VEHICLE_value*b_mediumFzg_last);
	}
}

