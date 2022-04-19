package tradesim.simulation.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tradesim.simulation.SimulationContext;
import tradesim.simulation.activity.purposechoice.first.DefaultPurposeChoiceModelLogger;
import tradesim.simulation.activity.purposechoice.first.LogitFirstPurposeChoice;
import tradesim.simulation.activity.purposechoice.first.LogitFirstPurposeChoiceHelper;
import tradesim.simulation.activity.purposechoice.first.LogitFirstPurposeChoiceHelperImpl;
import tradesim.util.input.Parameters;
import tradesim.util.input.config.Configurable;

/**
 * The Class SelectFirstPurposeLogitBased is a builder for {@link LogitFirstPurposeChoice}.
 */
@Getter
@Setter
@ToString
public class SelectFirstPurposeLogitBased implements Configurable<SimulationContext, PurposeChoiceModel> {

	private String parameters;
	private boolean logging;
	
	/**
	 * Instantiates a new select first purpose logit based.
	 */
	public SelectFirstPurposeLogitBased() {
		this.logging = false;
	}
	
	/**
	 * Builds the.
	 *
	 * @param context the context
	 * @return the purpose choice model
	 */
	@Override
	public PurposeChoiceModel build(SimulationContext context) {
		try {
			
			Parameters params = Parameters.loadFrom(new File(parameters));
			LogitFirstPurposeChoiceHelper helper = new LogitFirstPurposeChoiceHelperImpl();
			
			LogitFirstPurposeChoice firstPurposeChoice;
			if (logging) {
				firstPurposeChoice = new LogitFirstPurposeChoice(params, helper, new DefaultPurposeChoiceModelLogger());
			} else {
				firstPurposeChoice = new LogitFirstPurposeChoice(params, helper);
			}			
			

			return firstPurposeChoice;
	
			
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("The given file '" + parameters + "' does not exist.",e);
		} catch (IOException e) {
			throw new IllegalStateException("The given file '" + parameters + "' does not be read.",e);
		}
	}

	/**
	 * Validate the existence of the parameter file.
	 */
	@Override
	public void validate() {
		if (!new File(parameters).exists()) {
			throw new IllegalArgumentException("The given file '" + parameters + "' does not exist.");
		}
	}

}
