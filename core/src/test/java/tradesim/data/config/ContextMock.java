package tradesim.data.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tradesim.util.input.config.Context;

@Getter
@Setter
@ToString
public class ContextMock implements Context {
	
	private int threads;
	private String out; 

}
