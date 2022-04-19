package tradesim.simulation.tour;

import java.io.File;
import java.io.IOException;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tradesim.simulation.SimulationContext;
import tradesim.simulation.tour.lasttour.ContinueTourLogitModel;
import tradesim.simulation.tour.lasttour.ContinueTourLogitModelHelperImpl;
import tradesim.simulation.tour.lasttour.DefaultContinueTourChoiceModelLogger;
import tradesim.util.input.Parameters;
import tradesim.util.input.config.Configurable;


/**
 * The Class ContinueToursLogitBased is a builder for a logit based {@link ContinueTourChoiceModel}.
 */
@Getter
@Setter
@ToString
public class ContinueToursLogitBased implements Configurable<SimulationContext, ContinueTourChoiceModel> {

	private String parameters;
	private boolean logging;
	
	/**
	 * Instantiates a new continue tours logit based.
	 */
	public ContinueToursLogitBased() {
		this.logging = false;
	}
	
	/**
	 * Builds the {@link ContinueTourLogitModel}.
	 *
	 * @param context the context
	 * @return the continue tour choice model
	 */
	@Override
	public ContinueTourChoiceModel build(SimulationContext context) {
		try {
			
			Parameters params = Parameters.loadFrom(new File(parameters));
			ContinueTourLogitModelHelperImpl helper = new ContinueTourLogitModelHelperImpl();
			
			ContinueTourChoiceModel model;
			if (logging) {
				model = new ContinueTourLogitModel(params, helper, new DefaultContinueTourChoiceModelLogger());
			} else {
				model = new ContinueTourLogitModel(params, helper);
			}
			
			return (b,v,t,r) -> {
				return (t.get(t.size()-1).currentTime().getHour() >= 24) ? false : model.select(b, v, t, r);
			};
						
			
		} catch (IOException e) {
			throw new IllegalArgumentException("Could not load the given parameters: " + parameters, e);
		}
		
	}

	/**
	 * Validate the parameter file.
	 */
	@Override
	public void validate() {
		if (!new File(parameters).exists()) {
			throw new IllegalArgumentException("The given parameter file '" + parameters + "' does not exist!");
		}
	}
	
	

}
