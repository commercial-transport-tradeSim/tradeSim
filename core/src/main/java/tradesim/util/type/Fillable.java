package tradesim.util.type;

/**
 * The Interface Fillable.
 * 
 * Fillables provide a method to create an object by
 * casting an arbitrary set {@link Object}s and 'filling' them into the required
 * constructor.
 * 
 * @author Jelle Kübler
 * @param <R> the type of object to be filled (usually matching the concrete
 *            implementation of Fillable)
 */
public interface Fillable<R> {

	/**
	 * Fill the constructor of the generic type with the given elements.
	 *
	 * @param elements the elements
	 * @return an instance of the generic type R
	 */
	public R fill(Object... elements);

}
