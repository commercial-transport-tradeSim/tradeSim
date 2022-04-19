package tradesim.util.output;

/**
 * The Class ConsoleLogger is a {@link Logger} writing to the system console.
 */
public class ConsoleLogger extends Logger {

	/**
	 * Instantiates a new console logger with the given id.
	 *
	 * @param id the id
	 */
	public ConsoleLogger(String id) {
		super(id);
	}

	/**
	 * Writes the given string to the system console.
	 *
	 * @param string the string to be written
	 */
	@Override
	public void write(String string) {
		System.out.println("["+id+"] - " + string);
	}

}
