package tradesim.simulation;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import lombok.Getter;
import lombok.Setter;
import tradesim.model.business.Business;
import tradesim.model.opportunity.Opportunity;
import tradesim.model.tour.Tour;
import tradesim.model.vehicles.Fleet;
import tradesim.model.vehicles.Vehicle;
import tradesim.util.input.config.Context;
import tradesim.util.input.config.RunnableStep;

/**
 * The Class SimulationContext is a {@link Context} object holding results of
 * the various {@link RunnableStep simulation steps}.
 * 
 * @author Jelle Kübler
 *
 */
@Getter
@Setter
public class SimulationContext implements Context {

	private final Collection<Business> businesses;
	private final Collection<Fleet> fleets;
	private final Collection<Opportunity> opportunities;

	private final Map<Vehicle, Collection<Tour>> tours;
	private Random random;
	private File outputPath;

	/**
	 * Instantiates a new simulation context.
	 */
	public SimulationContext() {
		this.businesses = new ArrayList<>();
		this.random = new Random(42);
		this.fleets = new ArrayList<>();
		this.opportunities = new ArrayList<>();
		this.tours = new HashMap<>();
	}

	/**
	 * Gets the fleet of the given {@link Business}.
	 *
	 * @param business the business
	 * @return the fleet
	 */
	public Fleet getFleet(Business business) {
		return fleets.stream().filter(f -> f.getBusiness().equals(business)).findFirst().get();
	}

}
