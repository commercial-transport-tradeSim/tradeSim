package tradesim.util.type;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class Holder wraps a value adding a layer of indirection to make values
 * in final holders editable.
 * 
 * @author Jelle Kübler
 *
 * @param <T> the type of the ewrapped value.
 */
@Getter
@Setter
public class Holder<T> {

	private T value;

	/**
	 * Instantiates a new holder with the given value.
	 *
	 * @param value the value to be wrapped
	 */
	public Holder(T value) {
		this.value = value;
	}

}
