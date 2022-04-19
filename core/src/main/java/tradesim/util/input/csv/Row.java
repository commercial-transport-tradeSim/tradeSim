package tradesim.util.input.csv;

/**
 * The Class Row is a view on a {@link CsvFile} for a specific row index.
 */
public class Row {
	private final CsvFile container;
	private final int index;
	
	/**
	 * Instantiates a new row view for the given  {@link CsvFile} and row index.
	 *
	 * @param container the container
	 * @param index the index
	 */
	public Row(CsvFile container, int index) {
		this.container = container;
		this.index = index;
		verifyIndex();
	}
	
	/**
	 * Verifies the given row index.
	 */
	private void verifyIndex() {
		if (index < 0 || index >= container.length()) {
			throw new IllegalArgumentException("The rows index " + index + " does not exist in the container of length " + container.length());
		}
	}
	
	/**
	 * Checks whether the given column exists.
	 *
	 * @param column the column
	 * @return true, if the column exists
	 */
	public boolean hasColumn(String column) {
		return container.hasColumn(column);
	}
	
	/**
	 * Gets the string value of the given column.
	 *
	 * @param column the column
	 * @return the string value
	 */
	public String getStringValue(int column) {
		return this.container.getStringValue(index, column);
	}
	
	/**
	 * Gets the string value of the given column.
	 *
	 * @param column the column
	 * @return the string value
	 */
	public String getStringValue(String column) {
		return this.container.getStringValue(index, column);
	}
	
	/**
	 * Gets the int value of the given column.
	 *
	 * @param column the column
	 * @return the int value
	 */
	public int getIntValue(int column) {
		return this.container.getIntValue(index, column);
	}
	
	/**
	 * Gets the int value of the given column.
	 *
	 * @param column the column
	 * @return the int value
	 */
	public int getIntValue(String column) {
		return this.container.getIntValue(index, column);
	}
	
	/**
	 * Gets the long value of the given column.
	 *
	 * @param column the column
	 * @return the long value
	 */
	public long getLongValue(int column) {
		return this.container.getLongValue(index, column);
	}
	
	/**
	 * Gets the long value of the given column.
	 *
	 * @param column the column
	 * @return the long value
	 */
	public long getLongValue(String column) {
		return this.container.getLongValue(index, column);
	}
	
	/**
	 * Gets the float value of the given column.
	 *
	 * @param column the column
	 * @return the float value
	 */
	public float getFloatValue(int column) {
		return this.container.getFloatValue(index, column);
	}
	
	/**
	 * Gets the float value of the given column.
	 *
	 * @param column the column
	 * @return the float value
	 */
	public float getFloatValue(String column) {
		return this.container.getFloatValue(index, column);
	}
	
	/**
	 * Gets the double value of the given column.
	 *
	 * @param column the column
	 * @return the double value
	 */
	public double getDoubleValue(int column) {
		return this.container.getDoubleValue(index, column);
	}
	
	/**
	 * Gets the double value of the given column.
	 *
	 * @param column the column
	 * @return the double value
	 */
	public double getDoubleValue(String column) {
		return this.container.getDoubleValue(index, column);
	}
	
	/**
	 * Gets the boolean value of the given column.
	 *
	 * @param column the column
	 * @return the bool value
	 */
	public boolean getBoolValue(int column) {
		return this.container.getBoolValue(index, column);
	}
	
	/**
	 * Gets the boolean value of the given column.
	 *
	 * @param column the column
	 * @return the bool value
	 */
	public boolean getBoolValue(String column) {
		return this.container.getBoolValue(index, column);
	}

}
