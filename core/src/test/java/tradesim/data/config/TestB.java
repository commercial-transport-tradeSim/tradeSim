package tradesim.data.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tradesim.util.input.config.Configurable;
import tradesim.util.input.config.ConfigurationBuilder;
import tradesim.util.input.config.RunnableStep;

@Getter
@Setter
@ToString
public class TestB implements Configurable<ContextMock, RunnableStep> {
	static {
		ConfigurationBuilder.register(TestB.class);
	}
	
	private double percent;

	@Override
	public RunnableStep build(ContextMock context) {
		return null;
	}

	@Override
	public void validate() {
	}


	
}
