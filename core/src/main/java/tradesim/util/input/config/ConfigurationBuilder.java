package tradesim.util.input.config;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.nodes.Tag;

/**
 * The Class ConfigurationBuilder is a builder for {@link Configuration}s
 * using a {@link YamlParser}.
 *
 * @param <C> the type of the context object
 */
public class ConfigurationBuilder<C extends Context> {
	
	static Map<Class<?>, Tag> DEFAULT_TAGS = new HashMap<>();
	
	/**
	 * Registers the given class as yaml tag.
	 *
	 * @param clazz the clazz
	 */
	public static void register(Class<?> clazz) {
		String tag = clazz.getSimpleName();
		tag = "!" + Character.toLowerCase(tag.charAt(0)) + tag.substring(1);
		DEFAULT_TAGS.put(clazz, new Tag(tag));
	}
	
	static {
		DefaultTagLoader.load();
	}
	
	private File configFile;
	private Map<Class<?>, Tag> tags;
	private boolean log;
	
	/**
	 * Instantiates a new configuration builder.
	 */
	public ConfigurationBuilder() {
		this.tags = new HashMap<>();
		this.log = false;
	}
	
	
	/**
	 * Use the given configuration file.
	 *
	 * @param configFile the config file
	 * @return the configuration builder
	 */
	public ConfigurationBuilder<C> using(File configFile) {
		this.configFile = configFile;
		return this;
	}
	
	/**
	 * Add the registered default tags.
	 *
	 * @return the configuration builder
	 */
	public ConfigurationBuilder<C> usingDefaultTags() {
		this.tags = new HashMap<>(DEFAULT_TAGS);
		return this;
	}	
	
	/**
	 * Add the given tags.
	 *
	 * @param tags the tags
	 * @return the configuration builder
	 */
	public ConfigurationBuilder<C> usingTags(Map<Class<?>, Tag> tags) {
		this.tags = new HashMap<>(tags);
		return this;
	}
	
	/**
	 * Adds the given tags.
	 *
	 * @param tags the tags
	 * @return the configuration builder
	 */
	public ConfigurationBuilder<C> addTags(Map<Class<?>, Tag> tags) {
		this.tags.putAll(tags);
		return this;
	}
	
	/**
	 * Log the parsed configuration.
	 *
	 * @return the configuration builder
	 */
	public ConfigurationBuilder<C> log() {
		this.log = true;
		return this;
	}
	
	/**
	 * Hide the log of the configuration.
	 *
	 * @return the configuration builder
	 */
	public ConfigurationBuilder<C> hideLog() {
		this.log = false;
		return this;
	}
	
	
	/**
	 * Builds the {@link Configuration}.
	 *
	 * @return the configuration
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public Configuration<C> build() throws IOException {
		
		if (log) {
			System.out.println("Used config tags:");
			this.tags.forEach((clazz, tag) -> {
				System.out.println(" - " + tag.getValue() + ": " + clazz.getCanonicalName());
			});
			System.out.println();
		}
		
		YamlParser parser = new YamlParser(this.tags);
		Configuration<C> config = parser.parse(configFile);
		
		if(log) {
			System.out.println(parser.serialize(config));
		}
		
		return config;
	}
	


}
