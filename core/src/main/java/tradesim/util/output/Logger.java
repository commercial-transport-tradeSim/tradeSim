package tradesim.util.output;

/**
 * The Class Logger is an abstract logging object to write debug information.
 */
public abstract class Logger {
	
	/** The id. */
	protected final String id;
	
	/**
	 * Instantiates a new logger with the given id.
	 *
	 * @param id the id
	 */
	public Logger(String id) {
		this.id = id;
	}

	/**
	 * Writes the given string to the log (depending on the concrete implementation).
	 *
	 * @param string the string
	 */
	public abstract void write(String string);

}
