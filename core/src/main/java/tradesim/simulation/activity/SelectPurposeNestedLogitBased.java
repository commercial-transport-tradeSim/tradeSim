package tradesim.simulation.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tradesim.model.activity.Purpose;
import tradesim.simulation.SimulationContext;
import tradesim.simulation.activity.purposechoice.other.DefaultPurposeChoiceModelLogger;
import tradesim.simulation.activity.purposechoice.other.NestedLogitPurposeChoice;
import tradesim.simulation.activity.purposechoice.other.NestedLogitPurposeChoiceHelper;
import tradesim.simulation.activity.purposechoice.other.NestedLogitPurposeChoiceHelperImpl;
import tradesim.util.input.Parameters;
import tradesim.util.input.config.Configurable;

/**
 * The Class SelectPurposeNestedLogitBased is a builder for a {@link NestedLogitPurposeChoice}.
 */
@Getter
@Setter
@ToString
public class SelectPurposeNestedLogitBased implements Configurable<SimulationContext, PurposeChoiceModel> {


	private String parameters;
	private int max;
	private boolean logging;
	
	/**
	 * Instantiates a new select purpose nested logit based.
	 */
	public SelectPurposeNestedLogitBased() {
		this.max = 5;
		this.logging = false;
	}
	
	/**
	 * Builds the {@link NestedLogitPurposeChoice}.
	 *
	 * @param context the context
	 * @return the purpose choice model
	 */
	@Override
	public PurposeChoiceModel build(SimulationContext context) {
		try {
			
			Parameters params = Parameters.loadFrom(new File(parameters));
			NestedLogitPurposeChoiceHelper helper = new NestedLogitPurposeChoiceHelperImpl();
			
			NestedLogitPurposeChoice nestedLogitPurposeChoice;
			if (logging) {
				nestedLogitPurposeChoice = new NestedLogitPurposeChoice(params, helper, new DefaultPurposeChoiceModelLogger());
			} else {
				nestedLogitPurposeChoice = new NestedLogitPurposeChoice(params, helper);
			}			
			
			return (b,v,t,a,r) -> {
				
				if (t.length() >= max || t.length() >= t.getExpectedNumberOfTrips()-1 || t.currentTime().getHour() > 24) {
					return List.of(Purpose.RETURN);
					
				} else {
					return nestedLogitPurposeChoice.possibleResults(b, v, t, a, r);
				}
				
			};
			
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("The given file '" + parameters + "' does not exist.",e);
		} catch (IOException e) {
			throw new IllegalStateException("The given file '" + parameters + "' does not be read.",e);
		}
	}

	/**
	 * Validate the existance of the parameter file.
	 */
	@Override
	public void validate() {
		if (!new File(parameters).exists()) {
			throw new IllegalArgumentException("The given file '" + parameters + "' does not exist.");
		}
	}

}
