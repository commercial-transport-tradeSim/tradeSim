package tradesim.simulation.opportunities.orders;

import java.util.List;

import tradesim.simulation.opportunities.PrivatePerson;

/**
 * The Class ECommerceParticipationModelHelperImpl.
 */
public class ECommerceParticipationModelHelperImpl implements ECommerceParticipationModelHelper {

	
	/**
	 * Gets the age.
	 *
	 * @param category the category
	 * @param recipient the recipient
	 * @param randomNumber the random number
	 * @return the age
	 */
	public double getAGE(String category, PrivatePerson recipient, double randomNumber) {
		return recipient.getAge();
	}
	
	/**
	 * Gets the household monthly income eur.
	 *
	 * @param category the category
	 * @param recipient the recipient
	 * @param randomNumber the random number
	 * @return the household monthly income eur
	 */
	public double getHOUSEHOLD_MONTHLY_INCOME_EUR(String category, PrivatePerson recipient, double randomNumber) {
		return recipient.getNetIncomeHH();
	}
	
	/**
	 * Gets the checks if is gender male.
	 *
	 * @param category the category
	 * @param recipient the recipient
	 * @param randomNumber the random number
	 * @return the checks if is gender male
	 */
	public double getIS_GENDER_MALE(String category, PrivatePerson recipient, double randomNumber) {
		return (recipient.getSex().equals("male")) ? 1.0 : 0.0;
	}

	/**
	 * Gets the checks if is employed.
	 *
	 * @param category the category
	 * @param recipient the recipient
	 * @param randomNumber the random number
	 * @return the checks if is employed
	 */
	@Override
	public double getIS_EMPLOYED(String category, PrivatePerson recipient, double randomNumber) {
		return List.of("fulltime", "parttime").contains(recipient.getOccupation()) ? 1.0 : 0.0;
	}

}

