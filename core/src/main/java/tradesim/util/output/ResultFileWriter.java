package tradesim.util.output;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import tradesim.util.input.config.Context;
import tradesim.util.input.config.RunnableStep;

/**
 * The Class ResultFileWriter is an abstract class for writing simulation results to files.
 *
 * @param <C> type of the context used in the simulation
 */
public abstract class ResultFileWriter<C extends Context> implements RunnableStep {
	
	/** The file. */
	protected final File file;
	
	/** The context. */
	protected final C context;
	
	/**
	 * Instantiates a new result file writer for the given {@link File}
	 * and the given {@link Context}.
	 *
	 * @param file the result file
	 * @param context the context
	 */
	public ResultFileWriter(File file, C context) {
		this.file = file;
		this.context = context;
	}
	
	/**
	 * Runs the file writer.
	 */
	@Override
	public void run() {
		System.out.println("Write File " + file.getName());
		try {
			this.write(context);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Finished Writing File " + file.getName());
	}


	/**
	 * Writes the file content to the result file.
	 *
	 * @param context the context
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void write(C context) throws IOException {		
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
	    writeFileContent(context, writer);
	    writer.close();
	}

	/**
	 * Gets the file content.
	 *
	 * @param context the context
	 * @param writer the writer to write a file
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected abstract void writeFileContent(C context, Writer writer) throws IOException;

}
