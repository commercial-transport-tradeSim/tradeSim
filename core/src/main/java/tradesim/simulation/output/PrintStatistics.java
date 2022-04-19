package tradesim.simulation.output;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tradesim.model.business.Sector;
import tradesim.model.business.Trade;
import tradesim.model.opportunity.OpportunityType;
import tradesim.model.vehicles.Vehicle;
import tradesim.model.vehicles.VehicleCategory;
import tradesim.model.vehicles.VehicleType;
import tradesim.simulation.SimulationContext;
import tradesim.util.input.config.Configurable;
import tradesim.util.input.config.RunnableStep;

/**
 * The Class PrintStatistics is a builder for a {@link RunnableStep} to print statistics about the loaded businesses, opportunities and vehicles.
 */
@Getter
@Setter
@ToString
public class PrintStatistics implements Configurable<SimulationContext, RunnableStep> {
	
	private boolean active;

	/**
	 * Builds the {@link RunnableStep}.
	 *
	 * @param context the context
	 * @return the runnable step
	 */
	@Override
	public RunnableStep build(SimulationContext context) {
		return () -> {
			
			if (active) {
				System.out.println("Businesses:");
				System.out.println("Count: " + context.getBusinesses().size());
				System.out.println("\nBusinesses per Trade:");
				for (Trade trade : Trade.values()) {
					System.out.println(trade + ": " + context.getBusinesses().stream().filter(b -> b.getTrade().equals(trade)).count());
				}
				System.out.println("\nBusinesses per Sector:");
				for (Sector sector : Sector.values()) {
					System.out.println(sector + ": " + context.getBusinesses().stream().filter(b -> b.getSector().equals(sector)).count());
				}
				
				List<Vehicle> vehicles = context.getFleets().stream().flatMap(f -> f.getVehicles().stream()).toList();
				System.out.println("\nVehicles:");
				System.out.println("Count: " + vehicles.size());
				System.out.println("\nvehicles per Type:");
				for (VehicleType type : VehicleType.values()) {
					System.out.println(type + ": " + vehicles.stream().filter(v -> v.getType().equals(type)).count());
				}
				System.out.println("\nVehicles per Category:");
				for (VehicleCategory category : VehicleCategory.values()) {
					System.out.println(category + ": " + vehicles.stream().filter(v -> v.getCategory().equals(category)).count());
				}
				
				System.out.println("\nOpportunities:");
				System.out.println("Count: " + context.getOpportunities().size());
				System.out.println("\nOpportunities per Type:");
				for (OpportunityType type : OpportunityType.values()) {
					System.out.println(type + ": " + context.getOpportunities().stream().filter(o -> o.getType().equals(type)).count());
				}
			}
			
			
			
		};		
	}

	@Override
	public void validate() {
	}

}
