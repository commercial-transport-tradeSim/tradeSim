package tradesim.simulation.output;

import java.io.File;
import java.io.IOException;
import java.io.Writer;

import tradesim.model.vehicles.Vehicle;
import tradesim.simulation.SimulationContext;
import tradesim.util.output.ResultFileWriter;

/**
 * The Class VehicelFileWriter is a {@link ResultFileWriter} for csv format of {@link Vehicle}s.
 */
public class VehicleFileWriter extends ResultFileWriter<SimulationContext> {
	private static final String SEP = ";";

	/**
	 * Instantiates a new vehicle file writer.
	 *
	 * @param file the file
	 * @param context the context
	 */
	public VehicleFileWriter(File file, SimulationContext context) {
		super(file, context);
	}

	/**
	 * Gets the file content: csv format of the simulated vehicles.
	 *
	 * @param context the context
	 * @param writer the file writer
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Override
	protected void writeFileContent(SimulationContext context, Writer writer) throws IOException {
		writer.write(header() + "\n");
		

		context.getFleets()
			   .stream()
			   .flatMap(f -> f.getVehicles().stream())
			   .forEach(v -> writeRow(v, writer));
	}
	
	/**
	 * Header of the vehicle csv.
	 *
	 * @return the string
	 */
	private String header() {
		return "id" + SEP + "businessId" + SEP + "type" + SEP + "category" + SEP + "departure" + SEP + "meanSpeedKmh";
	}
	
	/**
	 * Returns csv row representing a vehicle.
	 *
	 * @param vehicle the vehicle
	 */
	private void writeRow(Vehicle vehicle, Writer writer) {
		try {
			writer.write(vehicle.getId() + SEP + vehicle.getOwner().getId() + SEP + vehicle.getType() + SEP + vehicle.getCategory() + SEP + vehicle.getDeparture().toString() + SEP + vehicle.getMeanSpeedKmh() + "\n");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
