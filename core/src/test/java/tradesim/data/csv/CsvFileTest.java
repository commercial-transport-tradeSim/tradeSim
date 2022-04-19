package tradesim.data.csv;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tradesim.util.input.csv.CsvFile;
import tradesim.util.input.csv.Row;

public class CsvFileTest {
		
	private CsvFile file;
	private static Collection<String> expectedHeader = Set.of("a_string", "b_int", "c_long", "d_float", "e_double", "f_bool");
	
	@BeforeEach
	public void setUp() {
		this.file = new CsvFile("src/test/resources/test.csv");
	}
	
	@Test
	public void length() {
		assertEquals(4, file.length());
	}
	
	@Test
	public void header() {
		assertEquals(6, file.header().size());
		expectedHeader.forEach(e -> assertTrue(file.header().contains(e)));
	}
	
	@Test
	public void stringValue() {
		stringValue(0, 0, "a_string", "alpha");
		stringValue(1, 0, "a_string", "beta");
		stringValue(2, 0, "a_string", "gamma");
		stringValue(3, 0, "a_string", "delta");
		
		stringValue(0, 1, "b_int", "42");
		stringValue(1, 1, "b_int", "13");
		stringValue(2, 1, "b_int", "17");
		stringValue(3, 1, "b_int", "420");

		stringValue(0, 2, "c_long", "1234");
		stringValue(1, 2, "c_long", "56789");
		stringValue(2, 2, "c_long", "8765");
		stringValue(3, 2, "c_long", "432");
		
		stringValue(0, 3, "d_float", "42.0");
		stringValue(1, 3, "d_float", "21.0");
		stringValue(2, 3, "d_float", "1.23");
		stringValue(3, 3, "d_float", "6.90");
		
		stringValue(0, 4, "e_double", "4.2136");
		stringValue(1, 4, "e_double", "3.1415");
		stringValue(2, 4, "e_double", "2.3487");
		stringValue(3, 4, "e_double", "1.8459");
		
		stringValue(0, 5, "f_bool", "true");
		stringValue(1, 5, "f_bool", "false");
		stringValue(2, 5, "f_bool", "false");
		stringValue(3, 5, "f_bool", "true");
	}
	
	private void stringValue(int row, int column, String col, String expected) {
		assertEquals(expected, file.getStringValue(row, column));
		assertEquals(expected, file.getStringValue(row, col));
		assertEquals(expected, file.getRow(row).getStringValue(column));
		assertEquals(expected, file.getRow(row).getStringValue(col));
	}
	
	@Test
	public void intValue() {	
		intValue(0, 1, "b_int", 42);
		intValue(1, 1, "b_int", 13);
		intValue(2, 1, "b_int", 17);
		intValue(3, 1, "b_int", 420);

		intValue(0, 2, "c_long", 1234);
		intValue(1, 2, "c_long", 56789);
		intValue(2, 2, "c_long", 8765);
		intValue(3, 2, "c_long", 432);
	}
	
	private void intValue(int row, int column, String col, int expected) {
		assertEquals(expected, file.getIntValue(row, column));
		assertEquals(expected, file.getIntValue(row, col));
		assertEquals(expected, file.getRow(row).getIntValue(column));
		assertEquals(expected, file.getRow(row).getIntValue(col));
	}
	
	@Test
	public void longValue() {	
		longValue(0, 1, "b_int", 42L);
		longValue(1, 1, "b_int", 13L);
		longValue(2, 1, "b_int", 17L);
		longValue(3, 1, "b_int", 420L);

		longValue(0, 2, "c_long", 1234L);
		longValue(1, 2, "c_long", 56789L);
		longValue(2, 2, "c_long", 8765L);
		longValue(3, 2, "c_long", 432L);
	}
	
	private void longValue(int row, int column, String col, long expected) {
		assertEquals(expected, file.getLongValue(row, column));
		assertEquals(expected, file.getLongValue(row, col));
		assertEquals(expected, file.getRow(row).getLongValue(column));
		assertEquals(expected, file.getRow(row).getLongValue(col));
	}
	

	@Test
	public void floatValue() {
		
		floatValue(0, 3, "d_float", 42.0f);
		floatValue(1, 3, "d_float", 21.0f);
		floatValue(2, 3, "d_float", 1.23f);
		floatValue(3, 3, "d_float", 6.90f);
		
		floatValue(0, 4, "e_double", 4.2136f);
		floatValue(1, 4, "e_double", 3.1415f);
		floatValue(2, 4, "e_double", 2.3487f);
		floatValue(3, 4, "e_double", 1.8459f);

	}
	
	private void floatValue(int row, int column, String col, float expected) {
		assertEquals(expected, file.getFloatValue(row, column));
		assertEquals(expected, file.getFloatValue(row, col));
		assertEquals(expected, file.getRow(row).getFloatValue(column));
		assertEquals(expected, file.getRow(row).getFloatValue(col));
	}
	
	@Test
	public void doubleValue() {
		
		doubleValue(0, 3, "d_float", 42.0d);
		doubleValue(1, 3, "d_float", 21.0d);
		doubleValue(2, 3, "d_float", 1.23d);
		doubleValue(3, 3, "d_float", 6.90d);
		
		doubleValue(0, 4, "e_double", 4.2136d);
		doubleValue(1, 4, "e_double", 3.1415d);
		doubleValue(2, 4, "e_double", 2.3487d);
		doubleValue(3, 4, "e_double", 1.8459d);

	}
	
	private void doubleValue(int row, int column, String col, double expected) {
		assertEquals(expected, file.getDoubleValue(row, column));
		assertEquals(expected, file.getDoubleValue(row, col));
		assertEquals(expected, file.getRow(row).getDoubleValue(column));
		assertEquals(expected, file.getRow(row).getDoubleValue(col));
	}
	
	
	@Test
	public void boolValue() {	
		boolValue(0, 5, "f_bool", true);
		boolValue(1, 5, "f_bool", false);
		boolValue(2, 5, "f_bool", false);
		boolValue(3, 5, "f_bool", true);
	}
	
	private void boolValue(int row, int column, String col, boolean expected) {
		assertEquals(expected, file.getBoolValue(row, column));
		assertEquals(expected, file.getBoolValue(row, col));
		assertEquals(expected, file.getRow(row).getBoolValue(column));
		assertEquals(expected, file.getRow(row).getBoolValue(col));
	}
	
	@Test
	public void missingRow() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Row(file, 42);
		});
	}
	
	@Test
	public void invalidRow() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Row(file, -1);
		});
	}
	
	@Test
	public void missingRowIndex() {
		assertThrows(IllegalArgumentException.class, () -> {
			file.getStringValue(42, 0);
		});
	}
	
	@Test
	public void missingColmun() {
		assertThrows(IllegalArgumentException.class, () -> {
			file.getStringValue(0, "foo");
		});
	}
	
	@Test
	public void missingColmunIndex() {
		assertThrows(IllegalArgumentException.class, () -> {
			file.getStringValue(0, 17);
		});
	}
	
	//@Test
	public void missingFile() {
		assertThrows(FileNotFoundException.class, () -> {
			new CsvFile("src/test/resources/missing.csv");
		});
	}
}
