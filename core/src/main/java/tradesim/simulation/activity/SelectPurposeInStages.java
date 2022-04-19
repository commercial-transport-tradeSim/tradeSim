package tradesim.simulation.activity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tradesim.simulation.SimulationContext;
import tradesim.util.input.config.Configurable;

/**
 * The Class SelectPurposeInStages is a builder for a {@link StagedPurposeChoice}.
 */
@Getter
@Setter
@ToString
public class SelectPurposeInStages implements  Configurable<SimulationContext, ActivityChanger> {
//Configurable<SimulationContext, PurposeChoiceModel>,
	private Configurable<SimulationContext, PurposeChoiceModel> first;
	private Configurable<SimulationContext, PurposeChoiceModel> other;
			
	/**
	 * Builds the {@link StagedPurposeChoice}.
	 *
	 * @param context the context
	 * @return the purpose choice model
	 */
	@Override
	public PurposeChoiceModel build(SimulationContext context) {
		return new StagedPurposeChoice(first.validateAndBuild(context), other.validateAndBuild(context));
	}

	/**
	 * Validate the two stages.
	 */
	@Override
	public void validate() {
		first.validate();
		other.validate();
	}

}
