package tradesim.util.random;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;

// TODO: Auto-generated Javadoc
/**
 * The Class DiscreteDistribution represents a distribution of discrete values with arbitrary probabilities.
 *
 * @param <T> type of the discrete values
 */
public class DiscreteDistribution<T> {
	
	private final NavigableMap<Double,T> distribution;

	/**
	 * Instantiates a new discrete distribution with the given values and their probabilities.
	 *
	 * @param probabilities the probabilities
	 */
	public DiscreteDistribution(Map<T,? extends Number> probabilities) {
		this.distribution = computeDistribution(probabilities);
	}

	/**
	 * Computes the distribution for the given value-probability map.
	 *
	 * @param probabilities the probabilities
	 * @return the navigable distribution map
	 */
	protected NavigableMap<Double,T> computeDistribution(Map<T,? extends Number> probabilities) {
		if (0 >= probabilities.size()) {
			throw new IllegalArgumentException("At least one element is required to select from.");
		}

		NavigableMap<Double,T> cumul = new TreeMap<Double,T>();

		Double total = 0.0;

		for (Number val :  probabilities.values()) {
			total += val.doubleValue();
		}

		if (total == 0.0) return cumulativeUniformDistribution(probabilities.keySet());


		if (total.isInfinite()) {
			Set<T> infinite = infiniteValues(probabilities);
			return cumulativeUniformDistribution(infinite);
		}


		Double current = 0.0;

		for (T t: probabilities.keySet()) {
			Number num = probabilities.get(t);
			double val = num.doubleValue();

			double increment = val/total;	

			if (current + increment > current) {
				current += increment;
				cumul.put(current,t);
			}
		}

		return cumul;
	}

	/**
	 * Return keys from the distribution with infinite values for probabilities.
	 *
	 * @param distribution the distribution
	 * @return the set of keys with infinite values
	 */
	protected Set<T> infiniteValues(Map<T,? extends Number> distribution) {
		Set<T> result = new LinkedHashSet<T>();

		for (T key: distribution.keySet()) {
			Double value = distribution.get(key).doubleValue();

			if (value.isInfinite()) {
				result.add(key);
			}
		}

		return result;
	}

	/**
	 * Create a uniform distribution.
	 *
	 * @param distribution the distribution
	 * @return the navigable map
	 */
	protected NavigableMap<Double,T> cumulativeUniformDistribution(Collection<T> distribution) {
		NavigableMap<Double,T> cumul = new TreeMap<Double,T>();

		Double current = 0.0;
		double increment = 1.0/distribution.size();

		for (T t: distribution) {
			current += increment;
			cumul.put(current,t);
		}


		return cumul;
	}


	/**
	 * Draws a value based on the given random value.
	 *
	 * @param rnd the random number
	 * @return a value from the distribution
	 */
	public T draw(double rnd) {
		if (rnd < 0 || 1 < rnd) {
			throw new IllegalArgumentException("A random number in [0,1) is expected");
		}
		
		Map.Entry<Double,T> entry = distribution.higherEntry(rnd);

		return entry != null ? entry.getValue() :  distribution.lastEntry().getValue();
	}


}
