package tradesim.simulation.output;

import java.io.File;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tradesim.simulation.SimulationContext;
import tradesim.util.input.config.Configurable;
import tradesim.util.input.config.MultipleSteps;
import tradesim.util.input.config.RunnableStep;
import tradesim.util.output.ResultFileWriter;


/**
 * The Class ExportMatsim is a builder for {@link ResultFileWriter}s to export simulated tours into a matsim format.
 */
@Getter
@Setter
@ToString
public class ExportMatsim implements Configurable<SimulationContext, RunnableStep> {
	
	private String carrierFile;
	private String vehicleTypeFile;
	
	/**
	 * Instantiates a new export matsim.
	 */
	public ExportMatsim() {
		this.carrierFile = "carrier_plans.xml";
		this.vehicleTypeFile = "carrier_vehicle_types.xml";
	}

	/**
	 * Builds the.
	 *
	 * @param context the context
	 * @return the runnable step
	 */
	@Override
	public RunnableStep build(SimulationContext context) {
		MultipleSteps steps = new MultipleSteps();
		
		File carrier = new File( context.getOutputPath().getAbsolutePath() + "/" + carrierFile); 
		File type = new File( context.getOutputPath().getAbsolutePath() + "/" + vehicleTypeFile); 
		
		steps.add(new MatsimCarrierFile(carrier, context));
		steps.add(new MatsimVehicleTypeFile(type, context));
		
		return steps;
	}

	/**
	 * Validate.
	 */
	@Override
	public void validate() {
		if (!carrierFile.endsWith(".xml")) {
			throw new IllegalArgumentException(carrierFile + "should end with .xml");
		}
		
		if (!vehicleTypeFile.endsWith(".xml")) {
			throw new IllegalArgumentException(vehicleTypeFile + "should end with .xml");
		}
	}

}
