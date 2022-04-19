package tradesim.simulation.example;

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
import tradesim.simulation.tour.FilterToursByLength;
import tradesim.simulation.tour.SelectBestDistanceFitTour;
import tradesim.simulation.vehicles.CreateVehicles;
import tradesim.simulation.vehicles.SelectDeparture;
import tradesim.util.input.config.Configuration;

public class Example {
	
	public static void main(String[] args) {
		
		SimulationContext context = new SimulationContext();
		
		Configuration<SimulationContext> configuration = new Configuration<>();
		
		
		SimulationOptions simulationOptions = new SimulationOptions();
		simulationOptions.setSeed(42);
		simulationOptions.setOutput("output/results/template");		
		configuration.getSteps().add(simulationOptions);
		
		
		LoadBusinesses loadBusinesses = new LoadBusinesses();
		loadBusinesses.setFile("template/data/businesses.csv");
		loadBusinesses.setOpportunityIds("template/data/opportunities/business_opportunity_ids.csv");
		loadBusinesses.setFraction(1); //scale factor for fleet size
		configuration.getSteps().add(loadBusinesses);
		
		
		CreateVehicles createVehicles = new CreateVehicles();
		createVehicles.setFactor(0.75); //Share of active vehicles
		
		SelectDeparture selectDeparture = new SelectDeparture();
		selectDeparture.setDistributionFile("template/data/distributions/departureTimesStrings.csv");
		createVehicles.getChangers().add(selectDeparture);
		
		configuration.getSteps().add(createVehicles);
		
		
		LoadOpportunities loadOpportunities = new LoadOpportunities();
		
		UseBusinessOpportunities useBusinessOpportunities = new UseBusinessOpportunities();
		useBusinessOpportunities.setActive(true);
		loadOpportunities.getLoaders().add(useBusinessOpportunities);
		
		ReadOpportunityCsv readOpportunityCsv_cons = new ReadOpportunityCsv();
		readOpportunityCsv_cons.setFile("template/data/opportunities/construction_opportunities.csv");
		readOpportunityCsv_cons.setType("CONSTRUCTION_SITE");
		loadOpportunities.getLoaders().add(readOpportunityCsv_cons);
		
		ReadOpportunityCsv readOpportunityCsv_other = new ReadOpportunityCsv();
		readOpportunityCsv_other.setFile("template/data/opportunities/other_opportunities.csv");
		readOpportunityCsv_other.setType("OTHER");
		loadOpportunities.getLoaders().add(readOpportunityCsv_other);
		
		LoadEcommerceCustomerOpportunities loadEcommerceCustomerOpportunities
		 = new LoadEcommerceCustomerOpportunities();
		loadEcommerceCustomerOpportunities.setPopulation("template/data/opportunities/population.csv");
		loadEcommerceCustomerOpportunities.setParameters("template/data/parameters/paramsECommerceParticipation.txt");
		loadEcommerceCustomerOpportunities.setFraction(1); //fraction of population to consider
		loadEcommerceCustomerOpportunities.setLogging(false);
		loadOpportunities.getLoaders().add(loadEcommerceCustomerOpportunities);
		
		configuration.getSteps().add(loadOpportunities);
		
		
		
		SimulateTours simulateTours = new SimulateTours();
		simulateTours.setTourLengthDistribution("template/data/distributions/tourDistAndNumOfTrips.csv");
		simulateTours.setDefaultDistance(20);
		simulateTours.setDefaultNumberOfTrips(5);
		simulateTours.setMaxParallelTours(10);
		
		SelectPurposeInStages selectPurposeInStages = new SelectPurposeInStages();
		
		SelectFirstPurposeLogitBased selectFirstPurposeLogitBased = new SelectFirstPurposeLogitBased();
		selectFirstPurposeLogitBased.setParameters("template/data/parameters/firstTripParams.txt");
		selectFirstPurposeLogitBased.setLogging(false);
		selectPurposeInStages.setFirst(selectFirstPurposeLogitBased);
		
		SelectPurposeNestedLogitBased selectPurposeNestedLogitBased = new SelectPurposeNestedLogitBased();
		selectPurposeNestedLogitBased.setParameters("template/data/parameters/activityPurpParams.txt");
		selectPurposeNestedLogitBased.setMax(15);
		selectPurposeNestedLogitBased.setLogging(false);
		selectPurposeInStages.setOther(selectPurposeNestedLogitBased);
		
		simulateTours.getActivityChangers().add(selectPurposeInStages);
		
		DrawDurationFromDistribution drawDurationFromDistribution = new DrawDurationFromDistribution();
		drawDurationFromDistribution.setFile("template/data/distributions/activityDurationData.csv");
		drawDurationFromDistribution.setDefaultDuration(10);
		simulateTours.getActivityChangers().add(drawDurationFromDistribution);
		
		DrawTripDistanceFromDistribution drawTripDistanceFromDistribution = new DrawTripDistanceFromDistribution();
		drawTripDistanceFromDistribution.setFile("template/data/distributions/tripDistDataOverTime.csv");
		drawTripDistanceFromDistribution.setDefaultDistance(10);
		simulateTours.getActivityChangers().add(drawTripDistanceFromDistribution);
		
		SelectDestinationDistanceBased selectDestinationDistanceBased = new SelectDestinationDistanceBased();
		selectDestinationDistanceBased.setParameters("template/data/parameters/destinationParams.txt");
		selectDestinationDistanceBased.setLogging(false);
		selectDestinationDistanceBased.setDetourFactor(1.2);
		selectDestinationDistanceBased.setMaxDeltaKm(2.5);
		selectDestinationDistanceBased.setMaxDestinations(10);
		selectDestinationDistanceBased.setMeanSpeedDistribution("template/data/distributions/speedDataDistanceRange.csv");
		simulateTours.getActivityChangers().add(selectDestinationDistanceBased);
		
		FilterToursByLength filterToursByLength = new FilterToursByLength();
		filterToursByLength.setMaxDeltaFactor(50);
		filterToursByLength.setActive(false);
		simulateTours.setTourFilter(filterToursByLength);
		
		SelectBestDistanceFitTour selectBestDistanceFitTour = new SelectBestDistanceFitTour();
		selectBestDistanceFitTour.setLogging(false);
		simulateTours.setTourSelector(selectBestDistanceFitTour);
		
		ContinueToursLogitBased continueToursLogitBased = new ContinueToursLogitBased();
		continueToursLogitBased.setParameters("template/data/parameters/lastTourParams.txt");
		continueToursLogitBased.setLogging(false);
		simulateTours.setContinueChoice(continueToursLogitBased);
		
		configuration.getSteps().add(simulateTours);
		
		
		ExportMatsim exportMatsim = new ExportMatsim();
		exportMatsim.setVehicleTypeFile("matsim_vehicle_types.xml");
		exportMatsim.setCarrierFile("matsim_carriers.xml");
		configuration.getSteps().add(exportMatsim);
		
		ExportCsv exportCsv = new ExportCsv();
		exportCsv.setVehicleFile("vehicle.csv");
		exportCsv.setActivityFile("activity.csv");
		configuration.getSteps().add(exportCsv);
		
		PrintStatistics printStatistics = new PrintStatistics();
		printStatistics.setActive(true);
		configuration.getSteps().add(printStatistics);
		
		
		configuration.run(context);
		
	}

}
