package tradesim.util.input.config;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.util.Map;

import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

/**
 * The Class YamlParser is a parser for yaml {@link Configuration}s.
 */
public class YamlParser {
	private final Yaml yaml;

	/**
	 * Instantiates a new yaml parser with the given tags.
	 *
	 * @param tags the yaml class tags
	 */
	public YamlParser(Map<Class<?>, Tag> tags) {
		super();
		yaml = new Yaml(constructor(tags), representer(tags));
	}

	/**
	 * Create yaml {@link Constructor} for the given tags.
	 *
	 * @param tags the tags
	 * @return the constructor
	 */
	private static Constructor constructor(Map<Class<?>, Tag> tags) {
		Constructor constructor = new Constructor(Configuration.class);
		tags.forEach((type, tag) -> constructor.addTypeDescription(new TypeDescription(type, tag)));
		return constructor;
	}

	/**
	 * Creates yaml {@link Representer} with the given tags.
	 *
	 * @param tags the tags
	 * @return the representer
	 */
	private static Representer representer(Map<Class<?>, Tag> tags) {
		Representer representer = new Representer();
		tags.forEach((c,t) -> representer.addClassTag(c, t));
		return representer;
	}

	/**
	 * Creates a {@link Reader} for the given configuration {@link File}.
	 *
	 * @param configurationFile the configuration file
	 * @return the reader
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected Reader readerFor(File configurationFile) throws IOException {
		return Files.newBufferedReader(configurationFile.toPath());
	}

	/**
	 * Returns the {@link Yaml} parser.
	 *
	 * @return the yaml
	 */
	protected Yaml yaml() {
		return this.yaml;
	}
	
	/**
	 * Parses the given configuration file.
	 *
	 * @param <C> the generic type
	 * @param configurationFile the configuration file
	 * @return the configuration
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@SuppressWarnings("unchecked")
	public <C extends Context> Configuration<C> parse(File configurationFile) throws IOException {
		Reader reader = readerFor(configurationFile);
		return (Configuration<C>) yaml().load(reader);
	}

	/**
	 * Serializes the given {@link Configuration}.
	 *
	 * @param <C> the generic type
	 * @param configuration the configuration
	 * @return the string
	 */
	public <C extends Context> String serialize(Configuration<C> configuration) {
		return yaml().dump(configuration);
	}

}
