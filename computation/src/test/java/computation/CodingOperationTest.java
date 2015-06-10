package computation;

import java.util.ArrayList;
import net.tudelft.hi.e.computation.CodingOperation;
import net.tudelft.hi.e.computation.Pattern;
import net.tudelft.hi.e.computation.PatternFactory;
import net.tudelft.hi.e.data.Column;
import net.tudelft.hi.e.data.NullValue;
import net.tudelft.hi.e.data.NumberColumn;
import net.tudelft.hi.e.data.NumberValue;
import net.tudelft.hi.e.data.Record;
import net.tudelft.hi.e.data.StringColumn;
import net.tudelft.hi.e.data.StringValue;
import net.tudelft.hi.e.data.Table;
import net.tudelft.hi.e.data.Value;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;

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
    Pattern pattern = PatternFactory.createPattern("* WebsiteValue");

    final CodingOperation co = new CodingOperation(table, pattern, "?W");
    co.execute();

    assertEquals(co.getResult().getCode("?W").getFrequency(), 4);
  }

  @Test
  public void testMultiplePatternEndNoHasNext() {
    Pattern pattern = PatternFactory.createPattern("* StatSensor");

    final CodingOperation co = new CodingOperation(table, pattern, "?S");
    co.execute();

    assertEquals(co.getResult().getCode("?S").getFrequency(), 2);
  }

  @Test
  public void testMultiplePatternNoHasNext() {
    Pattern pattern = PatternFactory.createPattern("2 StatSensor", "* StatSensor");

    final CodingOperation co = new CodingOperation(table, pattern, "S?S");
    co.execute();

    assertEquals(co.getResult().getCode("S?S").getFrequency(), 0);
  }

  @Test
  public void testSimpleMultiplePattern() {
    Pattern pattern = PatternFactory.createPattern("1 StatSensor", "* WebsiteValue");

    final CodingOperation co = new CodingOperation(table, pattern, "1S?W");
    co.execute();

    assertEquals(co.getResult().getCode("1S?W").getFrequency(), 3);
  }

  @Test
  public void testSimplePattern() {
    Pattern pattern = PatternFactory.createPattern("1 StatSensor", "5 WebsiteValue");

    final CodingOperation co = new CodingOperation(table, pattern, "1S5W");
    co.execute();

    assertEquals(co.getResult().getCode("1S5W").getFrequency(), 2);
  }

  @Test
  public void testSimplePatternNotRecognized() {
    Pattern pattern = PatternFactory.createPattern("1 StatSensor", "4 WebsiteValue");

    final CodingOperation co = new CodingOperation(table, pattern, "1S4W");
    co.execute();

    assertEquals(co.getResult().getCode("1S4W").getFrequency(), 0);
  }
}
