package tradesim.util.input.config;

import tradesim.simulation.SimulateTours;
import tradesim.simulation.SimulationContext;
import tradesim.simulation.SimulationOptions;
import tradesim.simulation.activity.DrawDurationFromDistribution;
import tradesim.simulation.activity.DrawTripDistanceFromDistribution;
import tradesim.simulation.activity.SelectDestinationDistanceBased;
import tradesim.simulation.activity.SelectFirstPurposeLogitBased;
import tradesim.simulation.activity.SelectPurposeInStages;
import tradesim.simulation.activity.SelectPurposeNestedLogitBased;
import tradesim.simulation.business.LoadBusinesses;
import tradesim.simulation.opportunities.LoadEcommerceCustomerOpportunities;
import tradesim.simulation.opportunities.LoadOpportunities;
import tradesim.simulation.opportunities.ReadOpportunityCsv;
import tradesim.simulation.opportunities.UseBusinessOpportunities;
import tradesim.simulation.output.ExportCsv;
import tradesim.simulation.output.ExportMatsim;
import tradesim.simulation.output.PrintStatistics;
import tradesim.simulation.tour.ContinueToursLogitBased;
import tradesim.simulation.tour.ContinuteToursProbabilityBased;
import tradesim.simulation.tour.FilterToursByLength;
import tradesim.simulation.tour.SelectBestDistanceFitTour;
import tradesim.simulation.vehicles.CreateVehicles;
import tradesim.simulation.vehicles.SelectDeparture;
import tradesim.simulation.vehicles.SelectSpeedDistributionBased;

/**
 * The Class DefaultTagLoader.
 */
public class DefaultTagLoader {

	/**
	 * Registers default {@link Configurable} classes.
	 */
	public static void load() {
		ConfigurationBuilder.register(SimulationContext.class);
		ConfigurationBuilder.register(SimulationOptions.class);
		
		ConfigurationBuilder.register(LoadBusinesses.class);
		
		ConfigurationBuilder.register(CreateVehicles.class);
		ConfigurationBuilder.register(SelectDeparture.class);
		ConfigurationBuilder.register(SelectSpeedDistributionBased.class);
		
		
		ConfigurationBuilder.register(LoadOpportunities.class);
		ConfigurationBuilder.register(UseBusinessOpportunities.class);
		ConfigurationBuilder.register(ReadOpportunityCsv.class);
		ConfigurationBuilder.register(LoadEcommerceCustomerOpportunities.class);
		
				
		ConfigurationBuilder.register(SimulateTours.class);
		ConfigurationBuilder.register(SelectPurposeNestedLogitBased.class);
		ConfigurationBuilder.register(SelectFirstPurposeLogitBased.class);
		ConfigurationBuilder.register(SelectPurposeInStages.class);
		ConfigurationBuilder.register(DrawDurationFromDistribution.class);
		ConfigurationBuilder.register(DrawTripDistanceFromDistribution.class);
		ConfigurationBuilder.register(SelectDestinationDistanceBased.class);
		
		ConfigurationBuilder.register(ContinuteToursProbabilityBased.class);
		ConfigurationBuilder.register(ContinueToursLogitBased.class);
		ConfigurationBuilder.register(FilterToursByLength.class);
		ConfigurationBuilder.register(SelectBestDistanceFitTour.class);
		
		ConfigurationBuilder.register(ExportMatsim.class);
		ConfigurationBuilder.register(ExportCsv.class);
		ConfigurationBuilder.register(PrintStatistics.class);
		
	}
	
}
