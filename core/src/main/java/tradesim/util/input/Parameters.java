package tradesim.util.input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * The Class Parameters a collection of parameter values.
 */
public class Parameters {
	
	private final double defaultValue = 0.0;
	private final Map<String, Double> values;

	/**
	 * Instantiates a new {@link Parameters} with the given parameter values.
	 *
	 * @param values the values
	 */
	public Parameters(Map<String, Double> values) {
		this.values = values;
	}
	
	/**
	 * Gets the value of the given parameter.
	 *
	 * @param string the string
	 * @return the double
	 */
	public double get(String string) {
		return values.getOrDefault(string, defaultValue);
	}
	
	/**
	 * Loads parameters from the given file.
	 *
	 * @param file the file to be parsed
	 * @return the parameters
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static Parameters loadFrom(File file) throws FileNotFoundException, IOException {
		Map<String, Double> parameters = new HashMap<>();
		ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       String[] parts = line.split("=");
		       String key = parts[0].trim();
		       Double value = Double.parseDouble(engine.eval(parts[1]).toString());
		       
		       parameters.put(key, value);
		    }
		} catch (NumberFormatException | ScriptException e) {
			throw new IllegalArgumentException("Could not evaluate equation in given parameter file " + file.getAbsolutePath(), e);
		}
		
		return new Parameters(parameters);
	}

}
