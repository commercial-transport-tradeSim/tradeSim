package tradesim.simulation.vehicles;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tradesim.model.vehicles.FleetFactory;
import tradesim.simulation.SimulationContext;
import tradesim.util.input.config.Configurable;
import tradesim.util.input.config.RunnableStep;


/**
 * The Class CreateVehicles is a builder for a {@link FleetFactory}.
 */
@Getter
@Setter
@ToString
public class CreateVehicles implements Configurable<SimulationContext, RunnableStep> {

	private double factor;
	private List<Configurable<SimulationContext, VehicleChanger<SimulationContext>>> changers;
	
	/**
	 * Instantiates a new creates the vehicles.
	 */
	public CreateVehicles() {
		this.factor = 1.0;
		this.changers = new ArrayList<>();
	}

	/**
	 * Builds the {@link FleetFactory}.
	 *
	 * @param context the context
	 * @return the runnable step
	 */
	@Override
	public RunnableStep build(SimulationContext context) {
		
		List<VehicleChanger<SimulationContext>> vehicleChangers = 
			changers.stream()
					.map(c -> c.validateAndBuild(context))
					.toList();
		
		return new FleetFactory(factor, vehicleChangers, context);
	}

	/**
	 * Validate the factor.
	 */
	@Override
	public void validate() {
		if (factor < 0.0 || 1.0 < factor) {
			throw new IllegalArgumentException("The given factor" + factor + " is out of range [0.0, 1.0]");
		}
	}




}
