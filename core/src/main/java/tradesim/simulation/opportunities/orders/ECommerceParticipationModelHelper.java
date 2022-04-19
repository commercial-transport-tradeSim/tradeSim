package tradesim.simulation.opportunities.orders;

import tradesim.simulation.opportunities.PrivatePerson;

/**
 * The Interface ECommerceParticipationModelHelper.
 */
public interface ECommerceParticipationModelHelper {
	
	/**
	 * Gets the age.
	 *
	 * @param category the category
	 * @param recipient the recipient
	 * @param randomNumber the random number
	 * @return the age
	 */
	double getAGE(String category, PrivatePerson recipient, double randomNumber);
	
	/**
	 * Gets the household monthly income eur.
	 *
	 * @param category the category
	 * @param recipient the recipient
	 * @param randomNumber the random number
	 * @return the household monthly income eur
	 */
	double getHOUSEHOLD_MONTHLY_INCOME_EUR(String category, PrivatePerson recipient, double randomNumber);
	
	/**
	 * Gets the checks if is employed.
	 *
	 * @param category the category
	 * @param recipient the recipient
	 * @param randomNumber the random number
	 * @return the checks if is employed
	 */
	double getIS_EMPLOYED(String category, PrivatePerson recipient, double randomNumber);
	
	/**
	 * Gets the checks if is gender male.
	 *
	 * @param category the category
	 * @param recipient the recipient
	 * @param randomNumber the random number
	 * @return the checks if is gender male
	 */
	double getIS_GENDER_MALE(String category, PrivatePerson recipient, double randomNumber);
	
}

