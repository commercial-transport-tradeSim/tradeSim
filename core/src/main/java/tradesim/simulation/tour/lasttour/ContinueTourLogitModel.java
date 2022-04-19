package tradesim.simulation.tour.lasttour;

import static java.lang.Math.exp;

import java.util.List;
import java.util.Map;

import tradesim.model.business.Business;
import tradesim.model.tour.Tour;
import tradesim.model.vehicles.Vehicle;
import tradesim.simulation.tour.ContinueTourChoiceModel;
import tradesim.util.input.Parameters;
import tradesim.util.type.Time;

/**
 * The Class ContinueTourLogitModel.
 */
public class ContinueTourLogitModel implements ContinueTourChoiceModel {
	
	
	private final ContinueTourLogitUtility utilities;
	private final ContinueTourChoiceModelLogger log;
	
	/**
	 * Instantiates a ContinueTourLogitModel with the given ContinueTourLogitHelper, ContinueTourChoiceModelLogger.
	 *
	 * @param parameters the parameters
	 * @param helper the helper
	 * @param log the logger
	 */
	public ContinueTourLogitModel(Parameters parameters, ContinueTourLogitHelper helper, ContinueTourChoiceModelLogger log) {
		super();
		this.log = log;
		this.utilities = new ContinueTourLogitUtility(parameters, helper, log);
	}
	
	/**
	 * Convenience constuctor: Uses a NullContinueTourChoiceModelLogger as logger.
	 *
	 * @param parameters the parameters
	 * @param helper the helper
	 */
	public ContinueTourLogitModel(Parameters parameters, ContinueTourLogitHelper helper) {
		this(parameters, helper, new NullContinueTourChoiceModelLogger());
	}
	
	/**
	 * Selects alternative true or false to determine whether the vehicle should continue with another tour.
	 *
	 * @param business the business
	 * @param vehicle the vehicle
	 * @param currentTours the current tours
	 * @param randomNumber the random number
	 * @return true, if successful
	 */
	public boolean select(Business business, Vehicle vehicle, List<Tour> currentTours, double randomNumber) {
		Time time;
		if (currentTours.size() > 0) {
			time = currentTours.get(currentTours.size() -1).currentTime();
		} else {
			time = vehicle.getDeparture();
		}
		
		this.log.logStart(business.getSector(), vehicle.getCategory(), time, randomNumber);
		
		// Calculate single utilitiy/probability for non zero utility function
		double utility = utilities.calculateU_last(business, vehicle, currentTours, randomNumber);
		this.log.log("utility last", utility);
		double probability = exp(utility) / (1.0 + exp(utility));
		this.log.log("probability last", probability);
		
		Map<String, Double> probabilities = Map.of("last", probability);
		this.log.logProbabilities(probabilities);
		
		boolean selected = (randomNumber > probability);
		this.log.log("Selected " + selected, 1.0);
		
		return selected;
	}

}

