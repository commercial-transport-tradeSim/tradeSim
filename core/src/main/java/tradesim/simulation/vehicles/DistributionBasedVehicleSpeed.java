package tradesim.simulation.vehicles;

import tradesim.model.business.Business;
import tradesim.model.vehicles.VehicleBuilder;
import tradesim.model.vehicles.VehicleCategory;
import tradesim.simulation.SimulationContext;
import tradesim.util.random.ParameterizedDistribution;
import tradesim.util.type.Singleton;

/**
 * The Class DistributionBasedVehicleSpeed is a {@link VehicleChanger} to select a vehicle speed.
 */
public class DistributionBasedVehicleSpeed implements VehicleChanger<SimulationContext> {

	private final ParameterizedDistribution<Singleton<VehicleCategory>, Double> meanSpeedDistribution;
	
	/**
	 * Instantiates a new speed selector with the given distribution.
	 *
	 * @param meanSpeedDistribution the mean speed distribution
	 */
	public DistributionBasedVehicleSpeed(ParameterizedDistribution<Singleton<VehicleCategory>, Double> meanSpeedDistribution) {
		this.meanSpeedDistribution = meanSpeedDistribution;
	}
	
	/**
	 * Applies the speed selector to the given {@link VehicleBuilder vehicle}.
	 *
	 * @param context the context
	 * @param business the business
	 * @param builder the vehicle builder
	 * @return the vehicle builder
	 */
	@Override
	public VehicleBuilder apply(SimulationContext context, Business business, VehicleBuilder builder) {
		return builder.with(meanSpeedDistribution.draw(builder.getRandom().nextDouble(), builder.getType().getVehicleCategory()));
	}
	
	

}
