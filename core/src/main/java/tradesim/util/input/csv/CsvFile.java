package tradesim.util.input.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.opencsv.CSVReader;

/**
 * The Class CsvFile represents the content of a csv file.
 */
public class CsvFile {
	private char seperator;
	private Map<String,Integer> columns;
	private Map<Integer, Map<Integer,String>> data;
	
	/**
	 * Instantiates a new csv file with the content at the given file path
	 * using the default value separator ';'.
	 *
	 * @param filePath the csv file path
	 */
	public CsvFile(String filePath) {
		this(new File(filePath));
	}
	
	/**
	 * Instantiates a new csv file with the content of the given file path
	 * using the given value separator.
	 *
	 * @param filePath the file path
	 * @param separator the separator
	 */
	public CsvFile(String filePath, char separator) {
		this(new File(filePath), separator);
	}
	
	/**
	 * Instantiates a new csv file with the content of the given {@link File}
	 * using the default value separator ';'.
	 *
	 * @param file the file
	 */
	public CsvFile(File file) {
		this(file, ';');
	}
	
	/**
	 * Instantiates a new csv file with the content of the given {@link File}
	 * using the given value separator.
	 *
	 * @param file the file
	 * @param separator the separator
	 */
	public CsvFile(File file, char separator) {
		this.seperator = separator;
		this.columns = new HashMap<>();
		this.data = new HashMap<>();
		parse(file);
	}
	
	/**
	 * Checks if the given {@link File} is csv file.
	 * (exists and has file extension '.csv')
	 *
	 * @param filePath the file path
	 * @return true, if is csv file
	 */
	public static boolean isCsvFile(String filePath) {
		return (filePath.endsWith(".csv") || filePath.endsWith(".csv.gz") || filePath.endsWith(".csv.zip"))
				&& new File(filePath).exists();
	}
	
	/**
	 * Parses the content of the given file.
	 *
	 * @param file the file to be parsed
	 */
	private void parse(File file) {
		try (CSVReader reader = new CSVReader(new FileReader(file), this.seperator)) {
			parse(reader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("File not found", e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("IO exception", e);
		}
	}
	

	/**
	 * Parses the content of the given {@link CSVReader}.
	 *
	 * @param reader the csv reader
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void parse(CSVReader reader) throws IOException {
		String[] header = reader.readNext();
		IntStream.range(0, header.length).forEach(i -> addColumn(header[i], i));
		
		String[] line;
		while ((line = reader.readNext()) != null) {
			this.addRow(line);
		}
	}
	
	/**
	 * Returns the csv columns header.
	 *
	 * @return the header
	 */
	public Collection<String> header() {
		return this.columns.keySet();
	}
	
	/**
	 * Returns the number of rows in the csv file.
	 *
	 * @return the number of rows
	 */
	public int length() {
		return this.data.size();
	}
	
	/**
	 * Gets the row with the given index.
	 *
	 * @param i the index
	 * @return the row at index i
	 */
	public Row getRow(int i) {
		return new Row(this, i);
	}
	
	/**
	 * Gets the csv file's {@link Row rows} as {@link Stream}.
	 *
	 * @return the rows
	 */
	public Stream<Row> getRows() {
		return this.data.keySet().stream().map(i -> getRow(i));
	}
	
	/**
	 * Adds the column with the given name and index.
	 *
	 * @throws IllegalArgumentException if the given column inex is already used
	 * @param name the name
	 * @param index the index
	 */
	private void addColumn(String name, int index) {
		if (this.columns.values().contains(index)) {
			throw new IllegalArgumentException("Index " + index + " already used: " + this.columns);
		}

		this.columns.put(name.replace("\"",""), index);
	}
	
	/**
	 * Checks whether a column with the given name exists.
	 *
	 * @param column the column
	 * @return true, if the given column exists
	 */
	public boolean hasColumn(String column) {
		return this.columns.containsKey(column);
	}
	
	/**
	 * Adds a row with the given values.
	 *
	 * @throws IllegalArgumentException if the length of the value array does not math the number of columns
	 * @param values the values
	 */
	private void addRow(String[] values) {
		if (!IntStream.range(0, values.length).allMatch(i -> this.columns.values().contains(i))) {
			throw new IllegalArgumentException("Column indices do not cover [0,"+values.length+"): " + this.columns);
		}
		
		Map<Integer, String> row = new HashMap<Integer, String>();
		this.data.put(this.data.size(), row);

		for (int i = 0; i < values.length; i++) {
			row.put(i, fixDecimalPoint(values[i]));
		}
	}

	/**
	 * Fixes the decimal point from ',' to '.'.
	 * Also removes '"' symbols.
	 *
	 * @param s the s
	 * @return the string
	 */
	private String fixDecimalPoint(String s) {
		String t = s.replace(',', '.').replace("\"", "");
		return t.replaceFirst("#DIV/0!", "0");
	}
	
	
	/**
	 * Gets the string value in the given row and column.
	 *
	 * @param row the row
	 * @param column the column
	 * @return the string value
	 */
	public String getStringValue(int row, int column) {
		return getValueOrDefault(row, column, "");
	}
	
	/**
	 * Gets the string value in the given row and column.
	 *
	 * @param row the row
	 * @param column the column
	 * @return the string value
	 */
	public String getStringValue(int row, String column) {
		return getValueOrDefault(row, column, "");
	}
	
	/**
	 * Gets the int value in the given row and column.
	 *
	 * @param row the row
	 * @param column the column
	 * @return the int value
	 */
	public int getIntValue(int row, int column) {
		return Integer.parseInt(getValueOrDefault(row, column, "0"));
	}
	
	/**
	 * Gets the int value in the given row and column.
	 *
	 * @param row the row
	 * @param column the column
	 * @return the int value
	 */
	public int getIntValue(int row, String column) {
		return Integer.parseInt(getValueOrDefault(row, column, "0"));
	}
	
	/**
	 * Gets the long value in the given row and column.
	 *
	 * @param row the row
	 * @param column the column
	 * @return the long value
	 */
	public long getLongValue(int row, int column) {
		return Long.parseLong(getValueOrDefault(row, column, "0L"));
	}
	
	/**
	 * Gets the long value in the given row and column.
	 *
	 * @param row the row
	 * @param column the column
	 * @return the long value
	 */
	public long getLongValue(int row, String column) {
		return Long.parseLong(getValueOrDefault(row, column, "0L"));
	}
	
	/**
	 * Gets the float value in the given row and column.
	 *
	 * @param row the row
	 * @param column the column
	 * @return the float value
	 */
	public float getFloatValue(int row, int column) {
		return Float.parseFloat(getValueOrDefault(row, column, "0.0f"));
	}
	
	/**
	 * Gets the float value in the given row and column.
	 *
	 * @param row the row
	 * @param column the column
	 * @return the float value
	 */
	public float getFloatValue(int row, String column) {
		return Float.parseFloat(getValueOrDefault(row, column, "0.0f"));
	}
	
	/**
	 * Gets the double value in the given row and column.
	 *
	 * @param row the row
	 * @param column the column
	 * @return the double value
	 */
	public double getDoubleValue(int row, int column) {
		return Double.parseDouble(getValueOrDefault(row, column, "0.0d"));
	}
	
	/**
	 * Gets the double value in the given row and column.
	 *
	 * @param row the row
	 * @param column the column
	 * @return the double value
	 */
	public double getDoubleValue(int row, String column) {
		return Double.parseDouble(getValueOrDefault(row, column, "0.0d"));
	}
	
	/**
	 * Gets the boolean value in the given row and column.
	 *
	 * @param row the row
	 * @param column the column
	 * @return the bool value
	 */
	public boolean getBoolValue(int row, int column) {
		return Boolean.parseBoolean(getValueOrDefault(row, column, "false"));
	}
	
	/**
	 * Gets the boolean value in the given row and column.
	 *
	 * @param row the row
	 * @param column the column
	 * @return the bool value
	 */
	public boolean getBoolValue(int row, String column) {
		return Boolean.parseBoolean(getValueOrDefault(row, column, "false"));
	}
	
	/**
	 * Gets the value in the given row and column.
	 * If the row does not exist returns the given default value.
	 *
	 * @throws IllegalArgumentException if the given column does not exist
	 * @param row the row
	 * @param column the column
	 * @param def the default value
	 * @return the value or default
	 */
	private String getValueOrDefault(int row, String column, String def) {
		if (!this.columns.keySet().contains(column)) {
			throw new IllegalArgumentException("Column '" + column + "' does not exist: " + columns.keySet());
		}
		
		int columnIndex = this.columns.get(column);
		return this.getValueOrDefault(row, columnIndex, def);
	}
	
	/**
	 * Gets the value or default.
	 *
	 * @param row the row
	 * @param column the column
	 * @param def the def
	 * @return the value or default
	 */
	private String getValueOrDefault(int row, int column, String def) {
		if (!this.data.keySet().contains(row)) {
			throw new IllegalArgumentException("Row " + row + " does not exist!");
		}
		
		if (!this.columns.values().contains(column)) {
			throw new IllegalArgumentException("Column " + column + " does not exist!");
		}
		
		return this.data.get(row).getOrDefault(column, def);
	}

	/**
	 * Prints the csv content.
	 */
	public void print() {
		
		this.header().forEach(h -> {System.out.print(h + ";");});
		
		System.out.print("\n");
		
		this.data.entrySet()
				 .stream()
				 .sorted((e1,e2) -> Integer.compare(e1.getKey(), e2.getKey()))
				 .forEachOrdered(e -> {
					 e.getValue().entrySet().stream().sorted((e1,e2) -> Integer.compare(e1.getKey(), e2.getKey()))
					 	.forEachOrdered(v -> {System.out.print(v.getValue() + "; ");});
					 System.out.print("\n");
				 });

	}
}
