package tradesim.simulation.output;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.Comparator;

import tradesim.model.activity.Activity;
import tradesim.model.tour.Tour;
import tradesim.simulation.SimulationContext;
import tradesim.util.output.ResultFileWriter;

// TODO: Auto-generated Javadoc
/**
 * The Class ActivityFileWriter is a {@link ResultFileWriter} for csv format of simulated {@link Activity activities}.
 */
public class ActivityFileWriter extends ResultFileWriter<SimulationContext> {
	private static final String SEP = ";";

	/**
	 * Instantiates a new activity file writer.
	 *
	 * @param file the file
	 * @param context the context
	 */
	public ActivityFileWriter(File file, SimulationContext context) {
		super(file, context);
	}

	/**
	 * Gets the file content: csv format of simulated {@link Activity activities}.
	 *
	 * @param context the context
	 * @param writer the file writer
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Override
	protected void writeFileContent(SimulationContext context, Writer writer) throws IOException {
		writer.write(header() + "\n");
		
		context.getTours()
			   .entrySet()
			   .stream()
			   .flatMap(e -> e.getValue().stream())
			   .sorted(Comparator.comparing(Tour::getId))
			   .forEach(t -> writeRows(t, writer));

	}
	
	/**
	 * Represents {@link Tour} as csv rows.
	 *
	 * @param tour the tour
	 * @param writer the writer
	 */
	private void writeRows(Tour tour, Writer writer) {
		tour.getActivities()
		   .stream()
		   .sorted(Comparator.comparing(Activity::getId))
		   .forEach(a -> this.writeRow(tour, a, writer));
	}

	/**
	 * Returns csv header.
	 *
	 * @return the string
	 */
	private String header() {
		return "tourId" + SEP
			 + "activityId" + SEP 
			 + "vehicleId" + SEP 
			 + "businessId" + SEP 
			 + "start" + SEP 
			 + "duration" + SEP 
			 + "expectedTripDistance" + SEP 
			 + "actualTripDistance" +  SEP 
			 + "purpose" + SEP 
			 + "destinationType" + SEP 
			 + "expectedTourDistance" + SEP 
			 + "expectedNumOfTrips" + SEP 
			 + "expectedDistanceSum" + SEP 
			 + "actualDistanceSum" + SEP 
			 + "x" + SEP 
			 + "y";
	}
	
	/**
	 * Represents {@link Activity} as csv row.
	 *
	 * @param tour the tour
	 * @param activity the activity
	 * @param writer the writer
	 * @return the string
	 */
	private void writeRow(Tour tour, Activity activity, Writer writer) {
		try {
			writer.write(
			       tour.getId() + SEP 
				 + activity.getId() + SEP 
				 + tour.getVehicle().getId() + SEP 
				 + tour.getBusiness().getId() + SEP 
				 + activity.getStart() + SEP 
				 + activity.getDuration() + SEP 
				 + activity.getExpectedTripDistanceKm() + SEP 
				 + activity.getActualTripDistanceKm()  +  SEP 
				 + activity.getPurpose() + SEP 
				 + activity.getDestination().getType() + SEP 
				 + tour.getExpectedDistanceKm() + SEP 
				 + tour.getExpectedNumberOfTrips()  + SEP 
				 + tour.expectedDistanceSum() + SEP 
				 + tour.actualDistanceSum() +SEP 
				 + activity.getDestination().getLocation().getX() + SEP 
				 + activity.getDestination().getLocation().getY() + "\n"
			);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
