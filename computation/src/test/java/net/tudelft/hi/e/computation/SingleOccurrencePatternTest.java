package net.tudelft.hi.e.computation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import net.tudelft.hi.e.data.Column;
import net.tudelft.hi.e.data.NullValue;
import net.tudelft.hi.e.data.NumberColumn;
import net.tudelft.hi.e.data.NumberValue;
import net.tudelft.hi.e.data.Record;
import net.tudelft.hi.e.data.StringColumn;
import net.tudelft.hi.e.data.StringValue;
import net.tudelft.hi.e.data.Table;
import net.tudelft.hi.e.data.Value;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Class for testing SingleOccurrencePattern.
 */
public class SingleOccurrencePatternTest {
  private Table table;
  private ArrayList<Column> cols;
  private NullValue nullValue = new NullValue();

  /**
   * Creates empty table.
   */
  @Before
  public void setUp() {
    table = new Table();
    cols = new ArrayList<Column>();
    cols.add(new StringColumn("Measurement"));
    cols.add(new NumberColumn("Value"));
  }

  @Test
  public void testFindPatternEndOfTable() {
    Record record =
        new Record(cols, new Value[] {new StringValue("Crea"), nullValue}, "StatSensor");
    table.add(record);
    record =
        new Record(cols, new Value[] {new StringValue("Crea2"), nullValue}, "StatSensor");
    table.add(record);

    Pattern pattern = PatternFactory.createPattern("1 StatSensor");
    final Table event = new Table();
    assertFalse(pattern.findPattern(table, 2, event));
    assertFalse(pattern.findPattern(table, 3, event));
  }

  @Test
  public void testFindPatternSingleRecord() {
    final Record record =
        new Record(cols, new Value[] {new StringValue("Crea"), nullValue}, "StatSensor");
    table.add(record);

    Pattern pattern = PatternFactory.createPattern("1 StatSensor");
    final Table event = new Table();
    assertTrue(pattern.findPattern(table, 0, event));
    assertEquals(table, event);
  }

  @Test
  public void testFindPatternSingleRecordNotFound() {
    final Record record =
        new Record(cols, new Value[] {new StringValue("Crea"), nullValue}, "StatSensor");
    table.add(record);

    Pattern pattern = PatternFactory.createPattern("1 WebsiteValue");
    final Table event = new Table();
    assertFalse(pattern.findPattern(table, 0, event));
  }

  @Test
  public void testFindPatternThreeSameRecords() {
    Record record =
        new Record(cols, new Value[] {new StringValue("Crea"), nullValue}, "StatSensor");
    table.add(record);
    record =
        new Record(cols, new Value[] {new StringValue("Crea2"), nullValue}, "StatSensor");
    table.add(record);
    record =
        new Record(cols, new Value[] {new StringValue("Crea3"), nullValue}, "StatSensor");
    table.add(record);

    Pattern pattern = PatternFactory.createPattern("1 StatSensor");
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
        new Record(cols, new Value[] {new StringValue("Crea"), nullValue}, "StatSensor");
    table.add(record);
    record = new Record(cols, new Value[] {nullValue, new NumberValue(140)}, "WebsiteValue");
    table.add(record);

    Pattern pattern = PatternFactory.createPattern("1 StatSensor");
    Table event = new Table();
    assertTrue(pattern.findPattern(table, 0, event));
    assertEquals(1, event.size());

    event = new Table();
    assertFalse(pattern.findPattern(table, 1, event));
  }

  @Test
  public void testFindPatternTwoDifferentRecordsSecondPattern() {
    Record record =
        new Record(cols, new Value[] {new StringValue("Crea"), nullValue}, "StatSensor");
    table.add(record);
    record = new Record(cols, new Value[] {nullValue, new NumberValue(140)}, "WebsiteValue");
    table.add(record);

    Pattern pattern = PatternFactory.createPattern("1 WebsiteValue");
    final Table event = new Table();
    assertTrue(pattern.findPattern(table, 1, event));
    assertEquals(1, event.size());
  }

  @Test
  public void testFindPatternTwoPatterns() {
    Record record =
        new Record(cols, new Value[] {new StringValue("Crea"), nullValue}, "StatSensor");
    table.add(record);
    record =
        new Record(cols, new Value[] {new StringValue("Crea2"), nullValue}, "StatSensor");
    table.add(record);


    Pattern pattern = PatternFactory.createPattern("2 StatSensor");
    final Table event = new Table();
    assertTrue(pattern.findPattern(table, 0, event));
    assertEquals(table, event);
  }

  @Test
  public void testFindPatternTwoSameRecords() {
    Record record =
        new Record(cols, new Value[] {new StringValue("Crea"), nullValue}, "StatSensor");
    table.add(record);
    record =
        new Record(cols, new Value[] {new StringValue("Crea2"), nullValue}, "StatSensor");
    table.add(record);

    Pattern pattern = PatternFactory.createPattern("1 StatSensor");
    Table event = new Table();
    assertFalse(pattern.findPattern(table, 0, event));

    event = new Table();
    assertFalse(pattern.findPattern(table, 1, event));
  }

}
