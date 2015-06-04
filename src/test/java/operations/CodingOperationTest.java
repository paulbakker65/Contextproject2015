package operations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import operations.patterns.MultipleOccurrencePattern;
import operations.patterns.NullPattern;
import operations.patterns.Pattern;
import operations.patterns.SingleOccurrencePattern;

import org.junit.Before;
import org.junit.Test;

import table.Record;
import table.Table;
import table.value.Column;
import table.value.NullValue;
import table.value.NumberColumn;
import table.value.NumberValue;
import table.value.StringColumn;
import table.value.StringValue;
import table.value.Value;

import java.util.ArrayList;

/**
 * Test for the coding operation.
 * 
 * @author robin
 *
 */
public class CodingOperationTest {
  private Table table;
  private NullValue nullValue = new NullValue();
  private ArrayList<Column> cols;

  /**
   * Create dummy table.
   */
  @Before
  public void setUp() {
    table = new Table();
    cols = new ArrayList<Column>();
    cols.add(new StringColumn("Measurement"));
    cols.add(new NumberColumn("Value"));
    cols.add(new StringColumn("Hospital"));
    
    add("StatSensor", new StringValue("Crea"), nullValue, nullValue);
    add("WebsiteValue", nullValue, new NumberValue(140), nullValue);
    add("WebsiteValue", nullValue, new NumberValue(150), nullValue);
    add("WebsiteValue", nullValue, new NumberValue(160), nullValue);
    add("WebsiteValue", nullValue, new NumberValue(170), nullValue);
    add("WebsiteValue", nullValue, new NumberValue(180), nullValue);
    add("StatSensor", new StringValue("Crea2"), nullValue, nullValue);
    add("WebsiteValue", nullValue, new NumberValue(140), nullValue);
    add("WebsiteValue", nullValue, new NumberValue(350), nullValue);
    add("WebsiteValue", nullValue, new NumberValue(160), nullValue);
    add("WebsiteValue", nullValue, new NumberValue(470), nullValue);
    add("WebsiteValue", nullValue, new NumberValue(180), nullValue);
    add("StatSensor", new StringValue("Crea2"), nullValue, nullValue);
    add("StatSensor", new StringValue("Crea2"), nullValue, nullValue);
    add("StatSensor", new StringValue("Crea2"), nullValue, nullValue);
    add("HospitalVisit", nullValue, nullValue, new StringValue("Erg ziek hoor"));
    add("WebsiteValue", nullValue, new NumberValue(160), nullValue);
    add("WebsiteValue", nullValue, new NumberValue(170), nullValue);
    add("WebsiteValue", nullValue, new NumberValue(160), nullValue);
    add("WebsiteValue", nullValue, new NumberValue(170), nullValue);
    add("WebsiteValue", nullValue, new NumberValue(180), nullValue);
    add("StatSensor", new StringValue("Crea2"), nullValue, nullValue);
    add("WebsiteValue", nullValue, new NumberValue(140), nullValue);
    add("WebsiteValue", nullValue, new NumberValue(350), nullValue);
    add("WebsiteValue", nullValue, new NumberValue(160), nullValue);
    add("StatSensor", new StringValue("Crea2"), nullValue, nullValue);
    add("StatSensor", new StringValue("Crea2"), nullValue, nullValue);
  }
  
  private void add(String tableName, Value... values) {
    table.add(new Record(cols, values, tableName));
  }

  @Test
  public void testExecutePatternNull() {
    final CodingOperation co = new CodingOperation(table, null, "1S4W");
    assertFalse(co.execute());
  }

  @Test
  public void testMultiplePattern() {
    final Pattern pattern = new MultipleOccurrencePattern("WebsiteValue", new NullPattern());

    final CodingOperation co = new CodingOperation(table, pattern, "?W");
    co.execute();

    assertEquals(co.getResult().getCode("?W").getFrequency(), 4);
  }

  @Test
  public void testMultiplePatternEndNoHasNext() {
    final Pattern pattern = new MultipleOccurrencePattern("StatSensor", new NullPattern());

    final CodingOperation co = new CodingOperation(table, pattern, "?S");
    co.execute();

    assertEquals(co.getResult().getCode("?S").getFrequency(), 2);
  }

  @Test
  public void testMultiplePatternNoHasNext() {
    final Pattern endPattern = new MultipleOccurrencePattern("StatSensor");
    final Pattern middlePattern = new SingleOccurrencePattern("StatSensor", endPattern);
    final Pattern firstPattern = new SingleOccurrencePattern("StatSensor", middlePattern);

    final CodingOperation co = new CodingOperation(table, firstPattern, "S?S");
    co.execute();

    assertEquals(co.getResult().getCode("S?S").getFrequency(), 0);
  }

  @Test
  public void testSimpleMultiplePattern() {
    final Pattern endPattern = new MultipleOccurrencePattern("WebsiteValue");
    final Pattern firstPattern = new SingleOccurrencePattern("StatSensor", endPattern);

    final CodingOperation co = new CodingOperation(table, firstPattern, "1S?W");
    co.execute();

    assertEquals(co.getResult().getCode("1S?W").getFrequency(), 3);
  }

  @Test
  public void testSimplePattern() {
    final Pattern endPattern = new SingleOccurrencePattern("WebsiteValue");
    Pattern prevPattern = new SingleOccurrencePattern("WebsiteValue", endPattern);

    for (int i = 0; i < 3; i++) {
      prevPattern = new SingleOccurrencePattern("WebsiteValue", prevPattern);
    }

    final Pattern firstPattern = new SingleOccurrencePattern("StatSensor", prevPattern);

    final CodingOperation co = new CodingOperation(table, firstPattern, "1S5W");
    co.execute();

    assertEquals(co.getResult().getCode("1S5W").getFrequency(), 2);
  }

  @Test
  public void testSimplePatternNotRecognized() {
    final Pattern endPattern = new SingleOccurrencePattern("WebsiteValue");
    Pattern prevPattern = new SingleOccurrencePattern("WebsiteValue", endPattern);

    for (int i = 0; i < 2; i++) {
      prevPattern = new SingleOccurrencePattern("WebsiteValue", prevPattern);
    }

    final Pattern firstPattern = new SingleOccurrencePattern("StatSensor", prevPattern);

    final CodingOperation co = new CodingOperation(table, firstPattern, "1S4W");
    co.execute();

    assertEquals(co.getResult().getCode("1S4W").getFrequency(), 0);
  }
}
