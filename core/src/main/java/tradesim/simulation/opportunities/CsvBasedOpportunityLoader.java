package tradesim.simulation.opportunities;

import java.util.Collection;

import tradesim.model.opportunity.Opportunity;
import tradesim.model.opportunity.OpportunityType;
import tradesim.util.input.config.Context;
import tradesim.util.input.csv.CsvFile;
import tradesim.util.input.csv.Row;
import tradesim.util.type.Location;

/**
 * The Class CsvBasedOpportunityLoader.
 *
 * @param <C> type of the used context
 */
public class CsvBasedOpportunityLoader<C extends Context> implements OpportunityLoader<C> {

	private final CsvFile file;
	private final OpportunityType type;
	
	/**
	 * Instantiates a new csv based opportunity loader.
	 *
	 * @param file the opportunities csv file
	 * @param type the type
	 */
	public CsvBasedOpportunityLoader(CsvFile file, OpportunityType type) {
		this.file = file;
		this.type = type;
	}
	
	/**
	 * Load opportunities.
	 *
	 * @param context the context
	 * @return the collection
	 */
	@Override
	public Collection<Opportunity> loadOpportunities(C context) {
		return file.getRows().map(this::parse).toList();
	}
	
	/**
	 * Parses the.
	 *
	 * @param row the row
	 * @return the opportunity
	 */
	private Opportunity parse(Row row) {
		double x = row.getDoubleValue("x");
		double y = row.getDoubleValue("y");
		
		Location location = new Location(x, y);
		
		String id = row.getStringValue("id");
		
		return new Opportunity(type, location, id);
		
	}

}
