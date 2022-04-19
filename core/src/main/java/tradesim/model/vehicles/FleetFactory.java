package tradesim.model.vehicles;

import static java.lang.Math.ceil;
import static java.util.stream.Collectors.toList;

import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;

import tradesim.model.business.Business;
import tradesim.simulation.SimulationContext;
import tradesim.simulation.vehicles.VehicleChanger;
import tradesim.util.input.config.RunnableStep;

/**
 * A factory for creating Fleet objects.
 */
public class FleetFactory implements RunnableStep {

	private final double factor;
	private final List<VehicleChanger<SimulationContext>> changers;
	private final SimulationContext context;
	
	/**
	 * Instantiates a new fleet factory.
	 *
	 * @param factor the factor
	 * @param changers the changers to be applied to each {@link VehicleBuilder vehicle}
	 * @param context the context
	 */
	public FleetFactory(double factor, List<VehicleChanger<SimulationContext>> changers, SimulationContext context) {
		this.factor = factor;
		this.changers = changers;
		this.context = context;
	}
	
	/**
	 * Run the vehicle factory.
	 */
	@Override
	public void run() {
		System.out.println("Create Vehicles");
		
		Collection<Fleet> fleets=
			context.getBusinesses()
				   .stream()
				   .map(b -> createFleet(context, b))
				   .collect(toList());
		
		context.getFleets().addAll(fleets);
		
		System.out.println("Finished Creating Vehicles");
	}
	
	/**
	 * Creates a new Fleet object.
	 * Using the fleet size of the given {@link Business} per {@link VehicleType}
	 * the appropriate amount (scaled by the factories factor) of {@link Vehicle}s is created.
	 * After that the factory applies {@link VehicleChanger}s to determine the vehicles properties.
	 * 
	 * If the factor is larger than 1, the fleetsize is scaled with the factor and then rounded.
	 * If the factor is in [0,1) the complete fleet is generated. Afterwards the vehicles are filtered,
	 * each heaving a chance defined by the given factor of being simulated. 
	 *
	 * @param context the context
	 * @param business the business
	 * @return the fleet
	 */
	private Fleet createFleet(SimulationContext context, Business business) {
		double factor = (this.factor >= 1) ? this.factor : 1.0;
		
		Collection<Vehicle> vehicles =
		   business.getFleetSizes()
				   .entrySet()
				   .stream()
				   .flatMap(e -> IntStream.range(0, (int) ceil(e.getValue() * factor))
				    					  .mapToObj(i -> new VehicleBuilder(business.getRandom().nextLong()).with(e.getKey()).with(business))
				   ).map(v -> {
					   
					   changers.forEach(c -> c.apply(context, business, v));
					   return v.build();
				   }).collect(toList());
		
		if (this.factor < 1.0) {
			vehicles = vehicles.stream().filter(v -> v.getRandom().nextDouble() < this.factor).toList();
		}

	   
	   return new Fleet(business, vehicles);
	}

}
