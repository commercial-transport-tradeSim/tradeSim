package tradesim.simulation.output;

import java.io.File;
import java.io.IOException;
import java.io.Writer;

import tradesim.simulation.SimulationContext;
import tradesim.util.output.ResultFileWriter;

/**
 * The Class MatsimVehicleTypeFile .
 */
public class MatsimVehicleTypeFile extends ResultFileWriter<SimulationContext> {
	

	
	/**
	 * Instantiates a new matsim vehicle type file.
	 *
	 * @param file the file
	 * @param context the context
	 */
	public MatsimVehicleTypeFile(File file, SimulationContext context) {
		super(file, context);
	}

	/**
	 * Gets the file content.
	 *
	 * @param context the context
	 * @param writer the file writer
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Override
	protected void writeFileContent(SimulationContext context, Writer writer) throws IOException {
		writer.write(
		"""
		<?xml version="1.0" encoding="UTF-8"?>
		<vehicleDefinitions xmlns="http://www.matsim.org/files/dtd" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://www.matsim.org/files/dtd http://www.matsim.org/files/dtd/vehicleDefinitions_v2.0.xsd">
			<vehicleType id="1">
				<capacity seats="3" standingRoomInPersons="0" other="200.0">
				</capacity>
				<length meter="4.6"/>
				<width meter="1.8"/>
				<maximumVelocity meterPerSecond="50"/>
				<costInformation fixedCostsPerDay="80.0" costsPerMeter="4.7E-4" costsPerSecond="0.008">
					<attributes>
						<attribute name="costsPerSecondInService" class="java.lang.Double">0.008</attribute>
						<attribute name="costsPerSecondWaiting" class="java.lang.Double">0.008</attribute>
					</attributes>
				</costInformation>
				<passengerCarEquivalents pce="1.0"/>
				<networkMode networkMode="car"/>
				<flowEfficiencyFactor factor="1.0"/>
			</vehicleType>
			<vehicleType id="2">
				<capacity seats="0" standingRoomInPersons="0" other="1200.0">
				</capacity>
				<length meter="8.9"/>
				<width meter="2.5"/>
				<maximumVelocity meterPerSecond="44.44"/>
				<costInformation fixedCostsPerDay="80.0" costsPerMeter="4.7E-4" costsPerSecond="0.008">
					<attributes>
						<attribute name="costsPerSecondInService" class="java.lang.Double">0.008</attribute>
						<attribute name="costsPerSecondWaiting" class="java.lang.Double">0.008</attribute>
					</attributes>
				</costInformation>
				<passengerCarEquivalents pce="1.0"/>
				<networkMode networkMode="car"/>
				<flowEfficiencyFactor factor="1.0"/>
			</vehicleType>
			<vehicleType id="3">
				<capacity seats="0" standingRoomInPersons="0" other="1200.0">
				</capacity>
				<length meter="10"/>
				<width meter="2.8"/>
				<maximumVelocity meterPerSecond="44.44"/>
				<costInformation fixedCostsPerDay="80.0" costsPerMeter="4.7E-4" costsPerSecond="0.008">
					<attributes>
						<attribute name="costsPerSecondInService" class="java.lang.Double">0.008</attribute>
						<attribute name="costsPerSecondWaiting" class="java.lang.Double">0.008</attribute>
					</attributes>
				</costInformation>
				<passengerCarEquivalents pce="1.0"/>
				<networkMode networkMode="car"/>
				<flowEfficiencyFactor factor="1.0"/>
			</vehicleType>
		</vehicleDefinitions>		
		""");

	}

}
