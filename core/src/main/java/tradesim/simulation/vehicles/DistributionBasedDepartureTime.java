package tradesim.simulation.vehicles;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;

import java.util.Map;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;
import tradesim.model.business.Business;
import tradesim.model.vehicles.VehicleBuilder;
import tradesim.simulation.SimulationContext;
import tradesim.util.input.csv.CsvFile;
import tradesim.util.random.DiscreteDistribution;
import tradesim.util.type.Time;

/**
 * The Class DistributionBasedDepartureTime is a {@link VehicleChanger} to select a departure time.
 * 
 * @author Jelle Kübler
 *
 */
@Getter
@Setter
public class DistributionBasedDepartureTime implements VehicleChanger<SimulationContext> {

	private final DiscreteDistribution<Time> distribution;
	
	/**
	 * Instantiates a departure time selector with the distribution in the given {@link CsvFile}.
	 *
	 * @param file the file
	 */
	public DistributionBasedDepartureTime(CsvFile file) {
			
		Map<Time,Long> counts = 
			file.getRows()
				.map(r -> r.getStringValue("departureTime"))
				.map(s -> Time.parse(s))
				.collect(Collectors.groupingBy(identity() ,counting()));
					
		
		this.distribution = new DiscreteDistribution<>(counts);
	}
	
	/**
	 * Applies the departure time selector to the given {@link VehicleBuilder vehicle}.
	 *
	 * @param context the context
	 * @param business the business
	 * @param vehicle the vehicle
	 * @return the vehicle builder
	 */
	@Override
	public VehicleBuilder apply(SimulationContext context, Business business, VehicleBuilder vehicle) {
		Time departure = this.distribution.draw(vehicle.getRandom().nextDouble());
		return vehicle.with(departure);
	}

}
