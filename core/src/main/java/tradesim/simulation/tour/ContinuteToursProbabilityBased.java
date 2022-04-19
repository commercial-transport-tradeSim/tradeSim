package tradesim.simulation.tour;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tradesim.simulation.SimulationContext;
import tradesim.util.input.config.Configurable;


/**
 * The Class ContinuteToursProbabilityBased is a builder for the {@link ContinueTourChoiceModel}.
 */
@Getter
@Setter
@ToString
public class ContinuteToursProbabilityBased implements Configurable<SimulationContext, ContinueTourChoiceModel> {

	private int max;
	private double probability;
	
	/**
	 * Instantiates a new continute tours probability based.
	 */
	public ContinuteToursProbabilityBased() {
		this.max = 2;
		this.probability = 0.5;
	}
	
	/**
	 * Builds the {@link ContinueTourChoiceModel}.
	 *
	 * @param context the context
	 * @return the continue tour choice model
	 */
	@Override
	public ContinueTourChoiceModel build(SimulationContext context) {
		return new RandomContinueModel(probability, max);
	}

	/**
	 * Validate probability in [0,1] and the maximum number of tours per day.
	 */
	@Override
	public void validate() {
		if (probability < 0 || 1 < probability) {
			throw new IllegalArgumentException("The given probability should be in [0,1] but was " + probability);
		}
		
		if (max < 1) {
			throw new IllegalArgumentException("The given max number of tours per vehicle should be at least 1 but was " + max);
		}
		
	}

}
