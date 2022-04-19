package tradesim.simulation.tour;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tradesim.simulation.SimulationContext;
import tradesim.util.input.config.Configurable;

/**
 * The Class FilterToursByLength is a builder for a {@link TourFilter} checking the expected distance.
 */
@Getter
@Setter
@ToString
public class FilterToursByLength implements Configurable<SimulationContext, TourFilter> {
	
	private double maxDeltaFactor;
	private boolean active;
	
	/**
	 * Instantiates a new filter tours by length.
	 */
	public FilterToursByLength() {
		this.maxDeltaFactor = 2;
		active = true;
	}

	/**
	 * Builds the {@link TourFilter}.
	 *
	 * @param context the context
	 * @return the tour filter
	 */
	@Override
	public TourFilter build(SimulationContext context) {
		if (!active) {
			return (b,v,t) -> true;
		}
		
		return (business, vehicle, tour) -> tour.actualDistanceSum() <= tour.getExpectedDistanceKm()*maxDeltaFactor;
	}

	/**
	 * Validate the maximum delta factor.
	 */
	@Override
	public void validate() {
		if (maxDeltaFactor < 1) {
			throw new IllegalArgumentException("Maximum distance delta factor of tours shoud be at least 1 but was " + maxDeltaFactor);
		}
	}

}
