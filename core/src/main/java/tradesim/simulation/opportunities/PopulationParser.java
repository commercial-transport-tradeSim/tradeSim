package tradesim.simulation.opportunities;

import java.util.Collection;

import tradesim.util.input.csv.CsvFile;
import tradesim.util.input.csv.Row;

/**
 * The Class PopulationParser is a parser for csv matsim populations.
 */
public class PopulationParser {
	
	/**
	 * Parses the csv {@link Row} as {@link PrivatePerson}.
	 *
	 * @param row the row
	 * @return the private person
	 */
	public PrivatePerson parse(Row row) {
		String id = row.getStringValue("person_id");
		String sex = row.getStringValue("sex");
		int age = row.getIntValue("age");
		String occupation = row.getStringValue("occupation");
		double income = row.getDoubleValue("netIncomeHH");
		String econStatus = row.getStringValue("econStatus");
		double home_x = row.getDoubleValue("home_x");
		double home_y = row.getDoubleValue("home_y");
		String home_link = row.getStringValue("home_link");
		
		return new PrivatePerson(id, sex, age, occupation, income, econStatus, home_x, home_y, home_link);
	}
	
	/**
	 * Parses the given {@link CsvFile} as population.
	 *
	 * @param file the file
	 * @return the collection of {@link PrivatePerson}
	 */
	public Collection<PrivatePerson> parse(CsvFile file) {				
		return 
			file.getRows()
			    .map(this::parse)
			    .toList();
	}
		
}
