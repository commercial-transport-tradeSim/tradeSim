package tradesim.util.input.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * The Class MultipleSteps is a {@link RunnableStep} wrapping multiple
 * {@link RunnableStep}s.
 */
public class MultipleSteps implements RunnableStep {

	private final List<RunnableStep> steps;

	/**
	 * Instantiates a new multiple step wrapper.
	 */
	public MultipleSteps() {
		this(List.of());
	}

	/**
	 * Instantiates a new multiple steps wrapper for the given steps.
	 *
	 * @param steps the steps to be wrapped
	 */
	public MultipleSteps(RunnableStep... steps) {
		this(Arrays.asList(steps));
	}

	/**
	 * Instantiates a new multiple steps wrapper for the given steps.
	 *
	 * @param steps the steps to be wrapped
	 */
	public MultipleSteps(Collection<RunnableStep> steps) {
		this.steps = new ArrayList<>(steps);
	}

	/**
	 * Adds the given {@link RunnableStep}.
	 *
	 * @param step the step to be wrapped
	 */
	public void add(RunnableStep step) {
		this.addAll(step);
	}

	/**
	 * Adds all given {@link RunnableStep}s.
	 *
	 * @param steps the steps to be wrapped
	 */
	public void addAll(RunnableStep... steps) {
		this.addAll(Arrays.asList(steps));
	}

	/**
	 * Adds all given {@link RunnableStep}s.
	 *
	 * @param steps the steps to be wrapped
	 */
	public void addAll(Collection<RunnableStep> steps) {
		this.steps.addAll(steps);
	}

	/**
	 * Runs all wrapped {@link RunnableStep}s.
	 */
	@Override
	public void run() {
		this.steps.forEach(s -> s.run());
	}

}
