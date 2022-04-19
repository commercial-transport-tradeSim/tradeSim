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
import tradesim.util.type.Quadruple;
import tradesim.util.type.TimeOfDay;

/**
 * The Class DistributionBasedTripDistanceAssigner is a
 * {@link TripDistanceModel} using a distribution.
 */
public class DistributionBasedTripDistanceAssigner implements TripDistanceModel {

	private final ParameterizedDistribution<Quadruple<Purpose, Sector, VehicleCategory, TimeOfDay>, Double> distribution;

	/**
	 * Instantiates a new {@link DistributionBasedTripDistanceAssigner} with a
	 * {@link ParameterizedDistribution} of distance depending on {@link Purpose},
	 * {@link Sector} and {@link VehicleCategory} defined by the given {@link CsvFile}.
	 *
	 * @param file            the distribution file
	 * @param defaultDistance the default distance
	 */
	public DistributionBasedTripDistanceAssigner(CsvFile file, double defaultDistance) {
		this.distribution = new ParameterizedDistribution<>(Quadruple.instance(), defaultDistance);

		this.distribution.addAll(file, this::toKey, this::toValue);
	}

	/**
	 * Draws distance for the trip to the given {@link ActivityBuilder activity} from the distribution.
	 *
	 * @param business     the business
	 * @param vehicle      the vehicle
	 * @param tour         the tour
	 * @param activity     the activity
	 * @param randomNumber the random number
	 * @return the list
	 */
	@Override
	public List<Double> possibleResults(Business business, Vehicle vehicle, Tour tour, ActivityBuilder activity,
			double randomNumber) {

		return List.of(
				distribution.draw(randomNumber, activity.getPurpose(), business.getSector(), vehicle.getCategory(), tour.currentTime().toTimeOfDay()));
	}

	/**
	 * Maps the given csf {@link Row} to a distribution key.
	 *
	 * @param row the row
	 * @return the list
	 */
	private List<Object> toKey(Row row) {
		int purpose = row.getIntValue("purpose");
		int sector = row.getIntValue("sector");
		int category = row.getIntValue("vehicle_category");
		int time_of_day = row.getIntValue("time_of_day");

		return List.of(Purpose.fromInt(purpose), Sector.fromInt(sector), VehicleCategory.fromInt(category), TimeOfDay.fromInt(time_of_day));
	}

	/**
	 * maps the given csv {@link Row} to a distance value
	 *
	 * @param row the row
	 * @return the double
	 */
	private double toValue(Row row) {
		return row.getDoubleValue("value");
	}

}
