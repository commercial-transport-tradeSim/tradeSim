package tradesim.simulation.output;

import java.io.File;
import java.io.IOException;
import java.io.Writer;

import lombok.Getter;
import tradesim.model.activity.Activity;
import tradesim.model.activity.Purpose;
import tradesim.model.business.Business;
import tradesim.model.tour.Tour;
import tradesim.model.vehicles.Vehicle;
import tradesim.simulation.SimulationContext;
import tradesim.util.output.ResultFileWriter;

/**
 * The Class MatsimCarrierFile is ad {@link ResultFileWriter} for matsim format
 * of simulated {@link Business}es, {@link Vehicle}s and {@link Tour}s.
 */
@Getter
public class MatsimCarrierFile extends ResultFileWriter<SimulationContext> {

	private static final String INDENT = "	";
	private static final String BR = "\n";

	/**
	 * Instantiates a new matsim carrier file writer.
	 *
	 * @param file    the result file
	 * @param context the context
	 */
	public MatsimCarrierFile(File file, SimulationContext context) {
		super(file, context);
	}

	/**
	 * Gets the file content: matsim format of {@link Business}es, {@link Vehicle}s and {@link Tour}s.
	 *
	 * @param context the context
	 * @param writer the file writer
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void writeFileContent(SimulationContext context, Writer writer) throws IOException {

		writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + BR);
		writer.write("<carriers>" + BR);

		for (Business business : context.getBusinesses()) {
			writer.write(INDENT + indent(matsimCarrier(context, business)) + BR);
		}

		writer.write("</carriers>");

	}

	/**
	 * Matsim carrier format of {@link Business}.
	 *
	 * @param context  the context
	 * @param business the business
	 * @return the string
	 */
	private String matsimCarrier(SimulationContext context, Business business) {
		String xml = "";

		xml += "<carrier id=\"" + matsimCarrierId(business) + "\">" + BR;

		xml += INDENT + indent(matsimCarrierAttributes(business)) + BR;
		xml += INDENT + indent(matsimCarrierCapabilities(context, business)) + BR;
		xml += INDENT + indent(matsimCarrierServices(context, business)) + BR;
		xml += INDENT + indent(matsimCarrierPlan(context, business)) + BR;

		xml += "</carrier>";

		return xml;
	}

	/**
	 * Matsim carrier id of a {@link Business}.
	 *
	 * @param business the business
	 * @return the string
	 */
	private String matsimCarrierId(Business business) {
		return business.getId() + business.getTrade().toString();
	}

	/**
	 * Matsim carrier attributes of a {@link Business}.
	 *
	 * @param business the business
	 * @return the string
	 */
	private String matsimCarrierAttributes(Business business) {
		String xml = "";

		xml += "<attributes>" + BR;
		xml += INDENT + "<attribute name=\"carrierMode\" class=\"java.lang.String\">" + masimCarrierMode(business)
				+ "</attribute>" + BR;
		xml += INDENT + "<attribute name=\"market\" class=\"java.lang.String\">" + matsimMarket(business)
				+ "</attribute>" + BR;
		xml += "</attributes>";

		return xml;

	}

	/**
	 * Masim carrier mode.
	 *
	 * @param business the business
	 * @return the string
	 */
	private String masimCarrierMode(Business business) {
		return "car";
	}

	/**
	 * Matsim market of a {@link Business}.
	 *
	 * @param business the business
	 * @return the string
	 */
	private String matsimMarket(Business business) {
		return business.getTrade().name();
	}

	/**
	 * Matsim carrier capabilities of a {@link Business}.
	 *
	 * @param context  the context
	 * @param business the business
	 * @return the string
	 */
	private String matsimCarrierCapabilities(SimulationContext context, Business business) {
		String xml = "";

		xml += "<capabilities fleetSize=\"FINITE\">" + BR;
		xml += INDENT + "<vehicles>" + BR;

		for (Vehicle v : context.getFleet(business).getVehicles()) {
			xml += INDENT + INDENT + matsimVehicle(business, v) + BR;
		}

		xml += INDENT + "</vehicles>" + BR;
		xml += "</capabilities>";

		return xml;
	}

	/**
	 * Matsim vehicle format of a {@link Vehicle}.
	 *
	 * @param business the business
	 * @param vehicle  the vehicle
	 * @return the string
	 */
	private String matsimVehicle(Business business, Vehicle vehicle) {
		return "<vehicle id=\"" + matsimVehicleId(business, vehicle) + "\" depotLinkId=\"" + matsimDepotLink(business)
				+ "\" typeId=\"" + matsimVehType(vehicle) + "\" earliestStart=\"00:00:00\" latestEnd=\"24:00:00\"/>";
	}

	/**
	 * Matsim vehicle type of a {@link Vehicle}.
	 *
	 * @param vehicle the vehicle
	 * @return the string
	 */
	private String matsimVehType(Vehicle vehicle) {
		return "" + vehicle.getType().asInt();
	}

	/**
	 * Matsim depot link of a {@link Business}.
	 *
	 * @param business the business
	 * @return the string
	 */
	private String matsimDepotLink(Business business) {
		return business.getLocation().getId();
	}

	/**
	 * Matsim vehicle id of a {@link Vehicle}.
	 *
	 * @param business the business
	 * @param vehicle  the vehicle
	 * @return the string
	 */
	private String matsimVehicleId(Business business, Vehicle vehicle) {
		return matsimCarrierId(business) + "_" + vehicle.getId() + "_vehType_" + vehicle.getType().asInt();
	}

	/**
	 * Matsim carrier services of a {@link Business}.
	 *
	 * @param context  the context
	 * @param business the business
	 * @return the string
	 */
	private String matsimCarrierServices(SimulationContext context, Business business) {
		String xml = "";

		xml += "<services>" + BR;

		for (Vehicle vehicle : context.getFleet(business).getVehicles()) {
			for (Tour tour : context.getTours().get(vehicle)) {
				for (Activity activity : tour.getActivities()) {

					xml += INDENT + indent(matsimCarrierService(business, vehicle, tour, activity)) + BR;
				}
			}
		}

		xml += "</services>";
		return xml;
	}

	/**
	 * Matsim carrier service format of an {@link Activity}.
	 *
	 * @param business the business
	 * @param vehicle  the vehicle
	 * @param tour     the tour
	 * @param activity the activity
	 * @return the string
	 */
	private String matsimCarrierService(Business business, Vehicle vehicle, Tour tour, Activity activity) {
		String xml = "";

		String start = activity.getStart().toString();
		String end = activity.getEnd().toString();
		String dur = activity.getDuration().toString();

		xml += "<service id=\"" + matsimServiceId(business, vehicle, tour, activity) + "\" to=\"" + matsimToId(activity)
				+ "\" capacityDemand=\"" + matsimCapacityDemand(business, vehicle, tour, activity)
				+ "\" earliestStart=\"" + start + "\" latestEnd=\"" + end + "\" serviceDuration=\"" + dur + "\">" + BR;
		// xml += INDENT + "<attributes>" + BR;
		// xml += INDENT + INDENT + "<attribute name=\"customer\"
		// class=\"java.lang.String\">" + matsimCarrierServiceCustomer(business,
		// vehicle, tour, activity) + "</attribute>" + BR;
		// xml += INDENT + "</attributes>" + BR;
		xml += "</service>";

		return xml;
	}

	/**
	 * Matsim destination id of an {@link Activity}.
	 *
	 * @param activity the activity
	 * @return the string
	 */
	private String matsimToId(Activity activity) {
		return activity.getDestination().getId();
	}

	/**
	 * Matsim service id of an {@link Activity}.
	 *
	 * @param business the business
	 * @param vehicle  the vehicle
	 * @param tour     the tour
	 * @param activity the activity
	 * @return the string
	 */
	private String matsimServiceId(Business business, Vehicle vehicle, Tour tour, Activity activity) {
		return matsimVehicleId(business, vehicle) + "_" + activity.getId();
	}

	/**
	 * Matsim capacity demand of an {@link Activity}.
	 *
	 * @param business the business
	 * @param vehicle  the vehicle
	 * @param tour     the tour
	 * @param activity the activity
	 * @return the string
	 */
	private String matsimCapacityDemand(Business business, Vehicle vehicle, Tour tour, Activity activity) {
		return "0.001";
	}

	/**
	 * Matsim carrier plan format of a {@link Business}.
	 *
	 * @param context  the context
	 * @param business the business
	 * @return the string
	 */
	private String matsimCarrierPlan(SimulationContext context, Business business) {
		String xml = "";

		xml += "<plan selected=\"true\">" + BR;

		for (Vehicle vehicle : context.getFleet(business).getVehicles()) {
			for (Tour tour : context.getTours().get(vehicle)) {
				xml += INDENT + indent(matsimCarrierTour(business, vehicle, tour)) + BR;
			}
		}

		xml += "</plan>";

		return xml;
	}

	/**
	 * Matsim carrier tour format of a {@link Tour}.
	 *
	 * @param business the business
	 * @param vehicle  the vehicle
	 * @param tour     the tour
	 * @return the string
	 */
	private String matsimCarrierTour(Business business, Vehicle vehicle, Tour tour) {
		String xml = "";

		xml += "<tour vehicleId=\"" + matsimVehicleId(business, vehicle) + "\">" + BR;
		xml += INDENT + "<act type=\"start\" end_time=\"" + tour.getDeparture().toString() + "\"/>" + BR;

		for (Activity activity : tour.getActivities()) {
			xml += INDENT + indent(matsimTourActivity(business, vehicle, tour, activity)) + BR;
		}

		xml += "</tour>";

		return xml;
	}

	/**
	 * Matsim tour activity format of an {@link Activity}.
	 *
	 * @param business the business
	 * @param vehicle  the vehicle
	 * @param tour     the tour
	 * @param activity the activity
	 * @return the string
	 */
	private String matsimTourActivity(Business business, Vehicle vehicle, Tour tour, Activity activity) {
		String xml = "";

		xml += "<leg expected_dep_time=\"" + tour.getDeparture(activity) + "\" expected_transp_time=\""
				+ tour.getTripDuration(activity) + "\">" + BR;
		// xml += INDENT + "<route>" + tour.getTripOrigin(activity).toString() + " " +
		// activity.getDestination().toString() + "</route>" + BR;
		xml += "</leg>" + BR;
		
		if (activity.getPurpose().equals(Purpose.RETURN)) {
			xml += "<act type=\"end\"/>";

		} else {
			xml += "<act type=\"service\" serviceId=\"" + matsimServiceId(business, vehicle, tour, activity) + "\"/>";
		}

		return xml;
	}

	/**
	 * Indent the given string.
	 *
	 * @param string the string
	 * @return the string
	 */
	private String indent(String string) {
		return string.replace("\r\n", "\n").replace("\n", "\n" + INDENT);
	}

}
