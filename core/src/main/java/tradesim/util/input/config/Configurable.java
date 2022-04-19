package tradesim.util.input.config;

/**
 * The Interface Configurable defines methods to parse yaml configurations of
 * objects. {@link Configurable}s are the builders for the objects to be
 * configured. The parameters to be parsed are inferred by a yaml parser using
 * the JavaBean property of the concrete implementation. 
 * (Therefore, the implementing class should add the lombok Getter, Setter and ToString annotations)
 *
 * @param <C> type of the context
 * @param <R> type of the object to be configured
 */
public interface Configurable<C extends Context, R> {

	/**
	 * Validate the configuration and build the object.
	 *
	 * @param context the context
	 * @return the configured object.
	 */
	default R validateAndBuild(C context) {
		validate();
		return build(context);
	}

	/**
	 * Builds the configured object.
	 *
	 * @param context the context
	 * @return the configured object
	 */
	public R build(C context);

	/**
	 * Validates the configuration.
	 */
	public void validate();

}
