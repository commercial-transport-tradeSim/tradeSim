package tradesim.util.type;

import java.util.Objects;

import lombok.Getter;

/**
 * The Class Quadruple is a {@link Fillable fillable} 4-tuple.
 * 
 * @author Jelle Kübler
 *
 * @param <F> type of the first element
 * @param <S> type of the second element
 * @param <T> type of the third element
 * @param <O> type of the fourth element
 */
@Getter
public class Quadruple<F,S,T,O> implements Fillable<Quadruple<F, S, T, O>> {
	
	private final F first;
	private final S second;
	private final T third;
	private final O fourth;
	
	/**
	 * Instantiates a new quadruple with the given elements.
	 *
	 * @param first the first
	 * @param second the second
	 * @param third the third
	 * @param fourth the fourth
	 */
	public Quadruple(F first, S second, T third, O fourth) {
		this.first = first;
		this.second = second;
		this.third = third;
		this.fourth = fourth;
	}
	
	/**
	 * Returns the hashcode of the tuple.
	 *
	 * @return the int hashcode
	 */
	@Override
	public int hashCode() {
		return Objects.hash(first, second, third, fourth);
	}
	
	/**
	 * Checks whether the given Object is equal to this {@link Quadruple}.
	 * If obj is a {@link Quadruple}, equality is determined elementwise.
	 *
	 * @param obj the obj to be checked for equality
	 * @return true, if they are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {return true;}
		if (obj == null) {return false;}
		if (!this.getClass().equals(obj.getClass())) {return false;}
		
		@SuppressWarnings("unchecked")
		Quadruple<F, S, T, O> that = (Quadruple<F, S, T, O>) obj;
		
		return this.first.equals(that.first) 
			&& this.second.equals(that.second)
			&& this.third.equals(that.third)
			&& this.fourth.equals(that.fourth);		
	}
	
	/**
	 * Fills a new {@link Quadruple} with the given elements.
	 * 
	 * @throws IllegalArgumentException if the given set of elements does not contain exactly 4 elements.
	 * @throws ClassCastException if one of the elements cannot be cast to the respective type in the quadruple.
	 * @param elements the elements
	 * @return a quadruple containing the given elements
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Quadruple<F, S, T, O> fill(Object... elements) {
		if (elements.length != 4) {
			throw new IllegalArgumentException("Expected 4 elements to fill triple but got " + elements.length + ": " + elements.toString());
		}
		
		try {
			F first = (F) elements[0];
			S second = (S) elements[1];
			T third = (T) elements[2];
			O fourth = (O) elements[3];
			
			return new Quadruple<F, S, T, O>(first, second, third, fourth);
			
		} catch (ClassCastException e) {
			throw new IllegalArgumentException("Could not fill the triple with the givene elements due to unmatching types: " + elements.toString(), e);
		}
		

	}

	/**
	 * Returns an empty {@link Quadruple}.
	 *
	 * @param <F> the type of the first element
	 * @param <S> the type of the second element
	 * @param <T> the type of the third element
	 * @param <O> the type of the fourth element
	 * @return an empty quadruple instance
	 */
	public static <F, S, T, O> Quadruple<F, S, T, O> instance() {
		return new Quadruple<F, S, T, O>(null, null, null, null);
	}
	
	/**
	 * returns a string representation of the {@link Quadruple}.
	 *
	 * @return the string representation
	 */
	@Override
	public String toString() {
		return "[" + first.toString() + ", " + second.toString() + ", " + third.toString() + ", " + fourth.toString() + "]";
	}

}
