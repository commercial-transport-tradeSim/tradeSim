package tradesim.simulation.activity;

import java.util.List;

import tradesim.model.activity.ActivityBuilder;
import tradesim.model.activity.Purpose;
import tradesim.model.business.Business;
import tradesim.model.business.Sector;
import tradesim.model.tour.Tour;
import tradesim.model.vehicles.Vehicle;
import tradesim.model.vehicles.VehicleCategory;
import tradesim.util.input.csv.CsvFile;
import tradesim.util.input.csv.Row;
import tradesim.util.random.ParameterizedDistribution;
import tradesim.util.type.Time;
import tradesim.util.type.Triple;

/**
 * The Class DistributionBasedActivityDurationAssigner is an
 * {@link ActivityDurationModel} using a distribution.
 */
public class DistributionBasedActivityDurationAssigner implements ActivityDurationModel {

	private final ParameterizedDistribution<Triple<Purpose, Sector, VehicleCategory>, Integer> distribution;

	/**
	 * Instantiates a new {@link DistributionBasedActivityDurationAssigner} using a
	 * {@link ParameterizedDistribution} with durations depending on
	 * {@link Purpose}, {@link Sector} and {@link VehicleCategory} defined by the
	 * given {@link CsvFile}.
	 *
	 * @param file            the distribution file
	 * @param defaultDuration the default duration
	 */
	public DistributionBasedActivityDurationAssigner(CsvFile file, int defaultDuration) {
		this.distribution = new ParameterizedDistribution<Triple<Purpose, Sector, VehicleCategory>, Integer>(
				Triple.instance(), defaultDuration);

		this.distribution.addAll(file, this::toKey, this::toValue);
	}

	/**
	 * Draws a duration for the given {@link ActivityBuilder activity}.
	 *
	 * @param business     the business
	 * @param vehicle      the vehicle
	 * @param tour         the tour
	 * @param activity     the activity
	 * @param randomNumber the random number
	 * @return the list
	 */
	@Override
	public List<Time> possibleResults(Business business, Vehicle vehicle, Tour tour, ActivityBuilder activity,
			double randomNumber) {

		int minutes = distribution.draw(randomNumber, activity.getPurpose(), business.getSector(),
				vehicle.getCategory());

		return List.of(Time.ofMinutes(minutes));
	}

	/**
	 * Maps the given csv {@link Row} to a distribution key.
	 *
	 * @param row the row
	 * @return the list
	 */
	private List<Object> toKey(Row row) {
		int purpose = row.getIntValue("purpose");
		int sector = row.getIntValue("sector");
		int category = row.getIntValue("vehicle_category");

		return List.of(Purpose.fromInt(purpose), Sector.fromInt(sector), VehicleCategory.fromInt(category));
	}

	/**
	 * maps the given csv {@link Row} to a duration value.
	 *
	 * @param row the row
	 * @return the int
	 */
	private int toValue(Row row) {
		return row.getIntValue("value");
	}

}
