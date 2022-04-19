package tradesim.util.random;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import tradesim.util.input.csv.CsvFile;
import tradesim.util.input.csv.Row;
import tradesim.util.type.Fillable;

/**
 * The Class ParameterizedDistribution.
 *
 * @param <K> the key type
 * @param <V> the value type
 */
public class ParameterizedDistribution<K extends Fillable<K>, V> {
	
	private final Map<K, Collection<V>> map;
	private final K instance;
	private final V defaultValue;
	
	/**
	 * Instantiates a new parameterized distribution.
	 *
	 * @param instance the instance
	 * @param defaultValue the default value
	 */
	public ParameterizedDistribution(K instance, V defaultValue) {
		this.map = new HashMap<>();
		this.instance = instance;
		this.defaultValue = defaultValue;
	}
	
	/**
	 * Adds the key value pair.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public void add(K key, V value) {
		if (!this.map.containsKey(key)) {
			this.map.put(key, new ArrayList<>());
		}
		
		this.map.get(key).add(value);
	}
	
	/**
	 * Adds the value to the key composed of the given objects.
	 *
	 * @param value the value
	 * @param keys the keys
	 */
	public void add(V value, Object ... keys) {
		this.add(instance.fill(keys), value);
	}
	
	/**
	 * Adds the all key value pairs by applying the given mappers to the rows of the given csv.
	 *
	 * @param csv the csv
	 * @param keyMapper the key mapper
	 * @param valueMapper the value mapper
	 */
	public void addAll(CsvFile csv, Function<Row, List<Object>> keyMapper, Function<Row, V> valueMapper) {
		csv.getRows()
		   .forEach(r -> this.add(valueMapper.apply(r), keyMapper.apply(r).toArray() ));
	}
	
	/**
	 * Draws a value from the distribution for the key composed of the given objects.
	 *
	 * @param randomNumber the random number
	 * @param keys the keys
	 * @return the v
	 */
	public V draw(double randomNumber, Object ... keys) {
		return this.draw(instance.fill(keys), randomNumber);
	}
	
	/**
	 * Draws a value from the distribution for the given key.
	 *
	 * @param key the key
	 * @param randomNumber the random number
	 * @return the v
	 */
	public V draw(K key, double randomNumber) {
		if (!this.map.containsKey(key)) {
			System.out.println("WARNING: Selected default value " + defaultValue.toString() + " as the key " + key.toString() + " has no observations in the dataset!");
			return defaultValue;
		}
		
		Map<V, Long> counts =
			this.map.get(key)
					.stream()
					.collect(groupingBy(identity(), counting()));
		
		return new DiscreteDistribution<V>(counts).draw(randomNumber);//TODO store distributions instead of recompution on draw
	}

}
