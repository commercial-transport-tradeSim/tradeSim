package tradesim.util.type;

import java.util.Objects;

import lombok.Getter;


/**
 * The Class Singleton represents a {@link Fillable fillable} 1-tuple. 
 * 
 * @author Jelle Kübler
 *
 * @param <F> type of the element
 */
@Getter
public class Singleton<F> implements Fillable<Singleton<F>> {
	
	private final F first;
	
	/**
	 * Instantiates a new singleton with the given element.
	 *
	 * @param first the first
	 */
	public Singleton(F first) {
		this.first = first;
	}
	
	/**
	 * Returns the hash code of the {@link Singleton}.
	 *
	 * @return the int hashcode
	 */
	@Override
	public int hashCode() {
		return Objects.hash(first);
	}
	
	/**
	 * Checks whether the given object equals this {@link Singleton}.
	 * If obj is a {@link Singleton}, equality is determined elementwise. 
	 *
	 * @param obj the object ot be compared
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {return true;}
		if (obj == null) {return false;}
		if (!this.getClass().equals(obj.getClass())) {return false;}
		
		@SuppressWarnings("unchecked")
		Singleton<F> that = (Singleton<F>) obj;
		
		return this.first.equals(that.first);		
	}
	
	/**
	 * Fills a {@link Singleton} with the given elements.
	 *
	 * @throws IllegalArgumentException if the given set of elements does not contain exactly 1 elements.
	 * @throws ClassCastException if one of the elements cannot be cast to the respective type in the singleton.
	 * @param elements the elements
	 * @return a singleton containing the given elements
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Singleton<F> fill(Object... elements) {
		if (elements.length != 1) {
			throw new IllegalArgumentException("Expected 1 elements to fill triple but got " + elements.length + ": " + elements.toString());
		}
		
		try {
			F first = (F) elements[0];
			
			return new Singleton<F>(first);
			
		} catch (ClassCastException e) {
			throw new IllegalArgumentException("Could not fill the triple with the givene elements due to unmatching types: " + elements.toString(), e);
		}
		

	}

	
	/**
	 * Returns an empty {@link Singleton} instance.
	 *
	 * @param <F> the type of the first element
	 * @return the singleton
	 */
	public static <F> Singleton<F> instance() {
		return new Singleton<F>(null);
	}
	
	/**
	 * Returns a string representation.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "[" + first.toString() + "]";
	}
}
