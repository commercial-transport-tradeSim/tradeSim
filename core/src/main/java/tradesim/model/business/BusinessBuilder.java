package tradesim.model.business;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import lombok.Getter;
import tradesim.model.opportunity.Opportunity;
import tradesim.model.vehicles.VehicleType;

/**
 * The Class BusinessBuilder is a builder for {@link Business} objects. Its a
 * mutable version of {@link Business} and produces the immutable version upon
 * building it.
 * 
 * @author Jelle Kübler
 */
public class BusinessBuilder {

	private static int ID_CNT;

	private String name;
	private Trade trade;
	private Opportunity opportunity;
	private int numberOfEmployees;
	private Map<VehicleType, Integer> fleetSize;
	@Getter private Random random;

	/**
	 * Instantiates a new business builder.
	 *
	 * @param seed the seed for the random generator
	 */
	public BusinessBuilder(long seed) {
		this.fleetSize = new HashMap<>();
		this.random = new Random(seed);
	}

	/**
	 * Sets the name of the {@link BusinessBuilder business}.
	 *
	 * @param name the name
	 * @return the business builder
	 */
	public BusinessBuilder with(String name) {
		this.name = name;
		return this;
	}

	/**
	 * Sets the {@link Trade} of the {@link BusinessBuilder business}.
	 *
	 * @param trade the trade
	 * @return the business builder
	 */
	public BusinessBuilder with(Trade trade) {
		this.trade = trade;
		return this;
	}

	/**
	 * Sets the {@link Opportunity location} of the {@link BusinessBuilder business}.
	 *
	 * @param opportunity the opportunity
	 * @return the business builder
	 */
	public BusinessBuilder with(Opportunity opportunity) {
		this.opportunity = opportunity;
		return this;
	}

	/**
	 * Sets the number of employees of the {@link BusinessBuilder business}.
	 *
	 * @param numberOfEmployees the number of employees
	 * @return the business builder
	 */
	public BusinessBuilder with(int numberOfEmployees) {
		this.numberOfEmployees = numberOfEmployees;
		return this;
	}

	/**
	 * Sets the number of vehicles of the given {@link VehicleType} of the {@link BusinessBuilder business}.
	 *
	 * @param number the number
	 * @param type   the type
	 * @return the business builder
	 */
	public BusinessBuilder with(int number, VehicleType type) {
		this.fleetSize.put(type, number);
		return this;
	}

	/**
	 * Builds the {@link Business}.
	 *
	 * @return the immutable business
	 */
	public Business build() {
		validate();
		return new Business(ID_CNT++, name, trade, opportunity, numberOfEmployees, random, fleetSize);
	}

	/**
	 * Validate the state of the {@link BusinessBuilder} before building the {@link Business}.
	 * I.e. a seed, a {@link Trade} and a {@link Opportunity location} is required.
	 */
	private void validate() {

		if (trade == null) {
			throw new IllegalStateException("Cannor build business before trade is set");
		}

		if (opportunity == null) {
			throw new IllegalStateException("Cannor build business before location is set");
		}

	}

}
