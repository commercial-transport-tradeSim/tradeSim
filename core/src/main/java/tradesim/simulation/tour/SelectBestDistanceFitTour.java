package tradesim.simulation.tour;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tradesim.simulation.SimulationContext;
import tradesim.util.input.config.Configurable;

/**
 * The Class SelectBestDistanceFitTour is a builder for a {@link DistanceBasedTourSelector}.
 */
@Getter
@Setter
@ToString
public class SelectBestDistanceFitTour implements Configurable<SimulationContext, TourSelector> {

	private boolean logging;
	
	/**
	 * Instantiates a new select best distance fit tour.
	 */
	public SelectBestDistanceFitTour() {
		this.logging = false;
	}
		
	/**
	 * Builds the {@link DistanceBasedTourSelector}.
	 *
	 * @param context the context
	 * @return the tour selector
	 */
	@Override
	public TourSelector build(SimulationContext context) {
		return new DistanceBasedTourSelector();
	}

	@Override
	public void validate() {}
	
}
