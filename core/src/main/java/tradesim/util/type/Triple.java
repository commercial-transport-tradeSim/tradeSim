package tradesim.util.type;

import java.util.Objects;

import lombok.Getter;


/**
 * The Class Triple is a {@link Fillable fillable} 3-Tuple.
 * 
 * @author Jelle Kübler
 *
 * @param <F> type of the first element
 * @param <S> type of the second element
 * @param <T> type of the third element
 */
@Getter
public class Triple<F,S,T> implements Fillable<Triple<F,S,T>> {
	
	private final F first;
	private final S second;
	private final T third;
	
	/**
	 * Instantiates a new triple with the given values.
	 *
	 * @param first the first
	 * @param second the second
	 * @param third the third
	 */
	public Triple(F first, S second, T third) {
		this.first = first;
		this.second = second;
		this.third = third;
	}
	
	/**
	 * Returns the hash code of the triple.
	 *
	 * @return the int hash code
	 */
	@Override
	public int hashCode() {
		return Objects.hash(first, second, third);
	}
	
	/**
	 * Checks whether the given obj is equal to this {@link Triple}.
	 * If obj is a {@link Triple}, equality is determined elementwise.
	 *
	 * @param obj the obj to be checked
	 * @return true, if equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {return true;}
		if (obj == null) {return false;}
		if (!this.getClass().equals(obj.getClass())) {return false;}
		
		@SuppressWarnings("unchecked")
		Triple<F, S, T> that = (Triple<F, S, T>) obj;
		
		return this.first.equals(that.first) 
			&& this.second.equals(that.second)
			&& this.third.equals(that.third);		
	}

	/**
	 * Fills a new {@link Triple} with the given elements.
	 * 
	 * @throws IllegalArgumentException if the given set of elements does not contain exactly 3 elements.
	 * @throws ClassCastException if one of the elements cannot be cast to the respective type in the quadruple.
	 * @param elements the elements
	 * @return a triple containing the given elements
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Triple<F, S, T> fill(Object... elements) {
		if (elements.length != 3) {
			throw new IllegalArgumentException("Expected 3 elements to fill triple but got " + elements.length + ": " + elements.toString());
		}
		
		try {
			F first = (F) elements[0];
			S second = (S) elements[1];
			T third = (T) elements[2];
			
			return new Triple<F, S, T>(first, second, third);
			
		} catch (ClassCastException e) {
			throw new IllegalArgumentException("Could not fill the triple with the givene elements due to unmatching types: " + elements.toString(), e);
		}
		

	}

	/**
	 * Returns an empty {@link Triple} instance.
	 *
	 * @param <F> the type of the first element
	 * @param <S> the type of the second element
	 * @param <T> the type of the third element
	 * @return an empty triple
	 */
	public static <F, S, T> Triple<F, S, T> instance() {
		return new Triple<F, S, T>(null, null, null);
	}
	
	/**
	 * Returns a s string representation.
	 *
	 * @return the string represenation
	 */
	@Override
	public String toString() {
		return "[" + first.toString() + ", " + second.toString() + ", " + third.toString() + "]";
	}
}
