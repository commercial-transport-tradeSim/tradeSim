package tradesim.simulation.opportunities;

/**
 * The Interface ECommerceParticipationSelector determines whether a
 * {@link PrivatePerson} participates in e-commerce.
 */
public interface ECommerceParticipationSelector {

	/**
	 * Determine whether the given {@link PrivatePerson} participates in e-commerce.
	 *
	 * @param recipient    the recipient
	 * @param randomNumber the random number
	 * @return true, if successful
	 */
	boolean select(PrivatePerson recipient, double randomNumber);

}
