package tradesim.data.config;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tradesim.util.input.config.Configuration;
import tradesim.util.input.config.ConfigurationBuilder;

public class ConfigurationParserTest {
	
	private File file;

	
	@BeforeEach
	public void setup() {
		ConfigurationBuilder.register(TestA.class);
		ConfigurationBuilder.register(TestB.class);
		ConfigurationBuilder.register(ContextMock.class);
		
		this.file = new File("src/test/resources/test.yaml");
	}
	
	@Test
	public void parse() throws IOException {
		Configuration<ContextMock> c = 
			new ConfigurationBuilder<ContextMock>()
				.usingDefaultTags()
				.using(file)
				.hideLog()
				.log()
				.build();
		assertEquals(3, c.getSteps().size());

	}
	
}
