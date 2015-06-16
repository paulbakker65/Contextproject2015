package net.tudelft.hi.e.computation;

import java.util.ArrayList;
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
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Class for testing SingleOccurrenceValuePattern.
 */
public class SingleOccurrenceValuePatternTest {
  private Table table;
  private ArrayList<Column> cols;

  /**
   * Creates empty table.
   */
  @Before
  public void setUp() {
    table = new Table();
    cols = new ArrayList<Column>();
    cols.add(new StringColumn("StatSensor"));
    cols.add(new NumberColumn("WebsiteValue"));
    cols.add(new StringColumn("HospitalVisit"));
  }

  @Test
  public void testFindPatternEndOfTable() {
    Record record =
        new Record(cols, new Value[] {new StringValue("Crea"), new NullValue(), new NullValue()});
    table.add(record);
    record =
        new Record(cols, new Value[] {new StringValue("Crea"), new NullValue(), new NullValue()});
    table.add(record);

    final Pattern pattern = PatternFactory.createPattern("1 StatSensor == 'Crea'");
    final Table event = new Table();
    assertFalse(pattern.findPattern(table, 2, event));
    assertFalse(pattern.findPattern(table, 3, event));
  }

  @Test
  public void testFindPatternSingleRecord() {
    final Record record =
        new Record(cols, new Value[] {new StringValue("Crea"), new NullValue(), new NullValue()});
    table.add(record);

    final Pattern pattern = PatternFactory.createPattern("1 StatSensor == 'Crea'");
    System.out.println(pattern);
    final Table event = new Table();
    assertTrue(pattern.findPattern(table, 0, event));
    assertEquals(table, event);
  }

  @Test
  public void testFindPatternSingleRecordNotFound() {
    final Record record =
        new Record(cols, new Value[] {new StringValue("Crea"), new NullValue(), new NullValue()});
    table.add(record);

    final Pattern pattern = PatternFactory.createPattern("1 StatSensor == 'Crea2'");
    final Table event = new Table();
    assertFalse(pattern.findPattern(table, 0, event));
  }

  @Test
  public void testFindPatternThreeSameRecords() {
    Record record =
        new Record(cols, new Value[] {new StringValue("Crea"), new NullValue(), new NullValue()});
    table.add(record);
    record =
        new Record(cols, new Value[] {new StringValue("Crea"), new NullValue(), new NullValue()});
    table.add(record);
    record =
        new Record(cols, new Value[] {new StringValue("Crea"), new NullValue(), new NullValue()});
    table.add(record);

    final Pattern pattern = PatternFactory.createPattern("1 StatSensor == 'Crea'");
    Table event = new Table();
    assertFalse(pattern.findPattern(table, 0, event));

    event = new Table();
    assertFalse(pattern.findPattern(table, 1, event));

    event = new Table();
    assertFalse(pattern.findPattern(table, 2, event));
  }

  @Test
  public void testFindPatternTwoDifferentRecords() {
    Record record =
        new Record(cols, new Value[] {new StringValue("Crea"), new NullValue(), new NullValue()});
    table.add(record);
    record = new Record(cols, new Value[] {new NullValue(), new NumberValue(140), new NullValue()});
    table.add(record);

    final Pattern pattern = PatternFactory.createPattern("1 StatSensor == 'Crea'");
    Table event = new Table();
    assertTrue(pattern.findPattern(table, 0, event));
    assertEquals(1, event.size());

    event = new Table();
    assertFalse(pattern.findPattern(table, 1, event));
  }

  @Test
  public void testFindPatternTwoDifferentRecordsSecondPattern() {
    Record record =
        new Record(cols, new Value[] {new StringValue("Crea"), new NullValue(), new NullValue()});
    table.add(record);
    record = new Record(cols, new Value[] {new NullValue(), new NumberValue(140), new NullValue()});
    table.add(record);

    final Pattern pattern = PatternFactory.createPattern("1 WebsiteValue == 140");
    final Table event = new Table();
    assertTrue(pattern.findPattern(table, 1, event));
    assertEquals(1, event.size());
  }

  @Test
  public void testFindPatternTwoPatterns() {
    Record record =
        new Record(cols, new Value[] {new StringValue("Crea"), new NullValue(), new NullValue()});
    table.add(record);
    record =
        new Record(cols, new Value[] {new StringValue("Crea2"), new NullValue(), new NullValue()});
    table.add(record);

    final Pattern pattern = PatternFactory.createPattern("1 StatSensor == 'Crea'",
        "1 StatSensor == 'Crea2'");
    final Table event = new Table();
    assertTrue(pattern.findPattern(table, 0, event));
    assertEquals(table, event);
  }

  @Test
  public void testFindPatternTwoSameRecords() {
    Record record =
        new Record(cols, new Value[] {new StringValue("Crea"), new NullValue(), new NullValue()});
    table.add(record);
    record =
        new Record(cols, new Value[] {new StringValue("Crea"), new NullValue(), new NullValue()});
    table.add(record);

    final Pattern pattern = PatternFactory.createPattern("1 StatSensor == 'Crea'");
    Table event = new Table();
    assertFalse(pattern.findPattern(table, 0, event));

    event = new Table();
    assertFalse(pattern.findPattern(table, 1, event));
  }
}
