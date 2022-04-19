package tradesim.util.input.config;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class Configuration is a collection of {@link Configurable}
 * {@link RunnableStep}s.
 * 
 * @author Jelle Kübler
 *
 * @param <C> type of the context object to be used
 */
@Getter
@Setter
@ToString
public class Configuration<C extends Context> {

	private List<Configurable<C, RunnableStep>> steps;
	
	public Configuration() {
		this.steps = new ArrayList<>();
	}

	/**
	 * Builds the {@link Configurable} {@link RunnableStep}s and runs them in the
	 * given order.
	 *
	 * @param context the context
	 */
	public void run(C context) {

		steps.stream().map(s -> s.validateAndBuild(context)).forEach(s -> {
			System.out.println();
			s.run();
		});
	}

}
