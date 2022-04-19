package tradesim.util.type;

import java.util.Objects;

import lombok.Getter;


/**
 * The Class Tuple is a {@link Fillable fillable} 2-Tuple.
 * 
 * @author Jelle Kübler
 *
 * @param <F> type of the first element
 * @param <S> type of the second element
 */
@Getter
public class Tuple<F,S> implements Fillable<Tuple<F,S>> {
	
	private final F first;
	private final S second;
	
	/**
	 * Instantiates a new tuple with the given values.
	 *
	 * @param first the first value
	 * @param second the second value
	 */
	public Tuple(F first, S second) {
		this.first = first;
		this.second = second;
	}
	
	/**
	 * Returns a hash code.
	 *
	 * @return the int hash code
	 */
	@Override
	public int hashCode() {
		return Objects.hash(first, second);
	}
	
	/**
	 * Checks whether the given object equals this {@link Tuple}.
	 * If obj is a {@link Tuple}, equality is determined elementwise.
	 *
	 * @param obj the object to be checked
	 * @return true, if equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {return true;}
		if (obj == null) {return false;}
		if (!this.getClass().equals(obj.getClass())) {return false;}
		
		@SuppressWarnings("unchecked")
		Tuple<F, S> that = (Tuple<F, S>) obj;
		
		return this.first.equals(that.first) 
			&& this.second.equals(that.second);		
	}
	
	/**
	 * Returns a {@link Tuple} of the given elements.
	 *
	 * @throws IllegalArgumentException if the given set of elements does not contain exactly 2 elements.
	 * @throws ClassCastException if one of the elements cannot be cast to the respective type in the quadruple.
	 * @param elements the elements
	 * @return the tuple
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Tuple<F, S> fill(Object... elements) {
		if (elements.length != 2) {
			throw new IllegalArgumentException("Expected 2 elements to fill triple but got " + elements.length + ": " + elements.toString());
		}
		
		try {
			F first = (F) elements[0];
			S second = (S) elements[1];
			
			return new Tuple<F, S>(first, second);
			
		} catch (ClassCastException e) {
			throw new IllegalArgumentException("Could not fill the triple with the givene elements due to unmatching types: " + elements.toString(), e);
		}
		

	}

	
	/**
	 * Returns an empty {@link Tuple} instance.
	 *
	 * @param <F> type of the first element
	 * @param <S> type of the second element
	 * @return the tuple
	 */
	public static <F, S> Tuple<F, S> instance() {
		return new Tuple<F, S>(null, null);
	}
	
	/**
	 * Returns a string representation.
	 *
	 * @return the string representation
	 */
	@Override
	public String toString() {
		return "[" + first.toString() + ", " + second.toString() + "]";
	}
}
