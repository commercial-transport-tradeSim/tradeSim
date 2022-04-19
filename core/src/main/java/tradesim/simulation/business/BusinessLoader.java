package tradesim.simulation.business;

import static java.util.stream.Collectors.toList;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import tradesim.model.business.BusinessBuilder;
import tradesim.model.business.BusinessParser;
import tradesim.simulation.SimulationContext;
import tradesim.util.input.config.RunnableStep;
import tradesim.util.input.csv.CsvFile;
import tradesim.util.type.Holder;

/**
 * The Class BusinessLoader loads businesses from a csv file.
 */
public class BusinessLoader implements RunnableStep {
	
	private final CsvFile dataFile;
	private final CsvFile opportunityIdFile;
	private final double fraction;
	private final List<BusinessChanger<SimulationContext>> changers;
	private final Random random;
	private final SimulationContext context;
	
	/**
	 * Instantiates a new business loader.
	 *
	 * @param file the business data file
	 * @param idFile the id file
	 * @param fraction the fraction of businesses to be parsed
	 * @param seed the seed
	 * @param changers the changers to be applied to each created business
	 * @param context the context
	 */
	public BusinessLoader(File file, File idFile, double fraction, long seed, List<BusinessChanger<SimulationContext>> changers, SimulationContext context) {
		this.dataFile = new CsvFile(file);
		
		if (idFile == null) {
			this.opportunityIdFile = null;
		} else {
			this.opportunityIdFile = new CsvFile(idFile);
		}
		
		this.fraction = fraction;
		this.changers = changers;
		this.random = new Random(seed);
		this.context = context;
	}

	/**
	 * Runs the business loader.
	 */
	@Override
	public void run() {
		System.out.println("Load Businesses");
		
		BusinessParser parser;
		if (opportunityIdFile == null) {
			parser = new BusinessParser();
		} else {
			parser = new BusinessParser(createIdMap());
		}
			
		
		
		Collection<BusinessBuilder> businesses = parser.parse(dataFile, random::nextLong);
		
		Holder<Double> counter = new Holder<Double>(0.0d);
		businesses = businesses.stream()
							   .filter(b -> filterFraction(counter))
							   .map(b -> {
								   changers.forEach(c -> c.apply(context, b));
								   return b;
							   })
							   .collect(toList());
		
		

		context.getBusinesses()
			   .addAll(businesses.stream()
					   			 .map(b -> b.build())
					   			 .collect(toList())
			   );
		
		System.out.println("Finished Loading Businesses");
	}

	/**
	 * Filter fraction of businesses.
	 *
	 * @param counter the counter
	 * @return true, if the business should be created
	 */
	private boolean filterFraction(Holder<Double> counter) {
		counter.setValue(counter.getValue() + fraction);

		Double cnt = counter.getValue();
		
		if (cnt >= 1.0) {
			counter.setValue(cnt - Math.floor(cnt));
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Creates the id map.
	 *
	 * @return the map of business ids
	 */
	private Map<Integer,String> createIdMap() {
		return this.opportunityIdFile.getRows().collect(Collectors.toMap(r -> r.getIntValue("business_id"), r -> r.getStringValue("opportunity_id")));
	}

}
