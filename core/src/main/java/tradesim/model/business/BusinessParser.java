package tradesim.model.business;

import static java.util.stream.Collectors.toList;
import static tradesim.model.opportunity.OpportunityType.OTHER_BUSINESS;

import java.awt.geom.Point2D;
import java.util.Collection;
import java.util.Map;
import java.util.function.LongSupplier;

import tradesim.model.opportunity.Opportunity;
import tradesim.model.vehicles.VehicleType;
import tradesim.util.input.csv.CsvFile;
import tradesim.util.input.csv.Row;
import tradesim.util.type.Location;

/**
 * The Class BusinessParser.
 */
public class BusinessParser {
	
	private final Map<Integer, String> opportunityIds;
	
	/**
	 * Instantiates a new business parser.
	 *
	 * @param opportunityIds the opportunity ids
	 */
	public BusinessParser(Map<Integer, String> opportunityIds) {
		this.opportunityIds = opportunityIds;
	}
	
	/**
	 * Instantiates a new business parser.
	 */
	public BusinessParser() {
		this(Map.of());
	}
	
	/**
	 * Parses the given row as {@link BusinessBuilder business}.
	 *
	 * @param row the row
	 * @param seed the seed
	 * @return the business builder
	 */
	public BusinessBuilder parse(Row row, long seed) {
		int id = row.getIntValue("id");
		
		int employees = row.getIntValue("num_employees");
		String name = row.getStringValue("name");
		Trade trade = Trade.valueOf(row.getStringValue("trade"));
		
		double x = row.getDoubleValue("location_x");
		double y = row.getDoubleValue("location_y");
		
		Location location = new Location(new Point2D.Double(x,y));
		Opportunity opportunity = new Opportunity(OTHER_BUSINESS, location, this.opportunityIds.getOrDefault(id, name));
		
		BusinessBuilder builder = 
			new BusinessBuilder(seed).with(trade)
								 .with(name)
								 .with(employees)
								 .with(opportunity);
		
		//Parse fleet size dynamically
		for (VehicleType type : VehicleType.values()) {
			builder.with(parseFleetSize(type, row),  type);
		}
		
		return builder;
								 
	}
	
	/**
	 * Parses the fleet size.
	 *
	 * @param type the type
	 * @param row the row
	 * @return the int
	 */
	private int parseFleetSize(VehicleType type, Row row) {
		String col = "fleet_size:" + type.asInt();
		
		if (row.hasColumn(col)) {
			return row.getIntValue(col);
		} else {
			return 0;
		}
	}
	
	/**
	 * Parses the csv vile to {@link BusinessBuilder business} objects.
	 *
	 * @param csv the csv
	 * @param seeds the seeds
	 * @return the collection
	 */
	public Collection<BusinessBuilder> parse(CsvFile csv, LongSupplier seeds) {
		return csv.getRows()
				  .map(r -> parse(r, seeds.getAsLong()))
				  .collect(toList());
	}

}
