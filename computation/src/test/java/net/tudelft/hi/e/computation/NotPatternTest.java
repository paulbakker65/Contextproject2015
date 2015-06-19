package net.tudelft.hi.e.computation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
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

public class NotPatternTest {
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
    cols.add(new StringColumn("StatSensor"));
    cols.add(new NumberColumn("Website"));
    cols.add(new StringColumn("Hospital"));

    add("StatSensor", new StringValue("Crea"), nullValue, nullValue);
    add("Website", nullValue, new NumberValue(140), nullValue);
    add("Website", nullValue, new NumberValue(150), nullValue);
    add("Hospital", nullValue, nullValue, new StringValue("Erg ziek hoor"));
  }

  private void add(String tableName, Value... values) {
    table.add(new Record(cols, values, tableName));
  }

  @Test
  public void testInvertPatternNotNull() {
    NotPattern notPattern = new NotPattern(null);
    assertNotNull(notPattern.getInvertPattern());
  }

  @Test
  public void testFindPatternSinglePattern() {
    Pattern toInvert = PatternFactory.createPattern("2 Website");
    Pattern notPattern = new NotPattern(toInvert);
    Table result = new Table();

    assertTrue(notPattern.findPattern(table, 0, result));
    assertEquals(0, result.size());

    assertFalse(notPattern.findPattern(table, 1, new Table()));
  }

  @Test
  public void testFindPatternWrongIndex() {
    Pattern toInvert = PatternFactory.createPattern("2 Website");
    Pattern notPattern = new NotPattern(toInvert);
    assertFalse(notPattern.findPattern(table, 6, new Table()));
  }

  @Test
  public void testFindPatternBeforePatternFound() {
    Pattern toInvert = PatternFactory.createPattern("1 Hospital");
    Pattern notPattern = new NotPattern(toInvert);
    Pattern first = PatternFactory.createPattern("1 StatSensor");
    first.setNextPattern(notPattern);

    Table result = new Table();

    assertTrue(first.findPattern(table, 0, result));
    assertEquals(1, result.size());
  }

  @Test
  public void testFindPatternBeforePatternNotFound() {
    Pattern toInvert = PatternFactory.createPattern("2 Website");
    Pattern notPattern = new NotPattern(toInvert);
    Pattern first = PatternFactory.createPattern("1 StatSensor");
    first.setNextPattern(notPattern);

    Table result = new Table();

    assertFalse(first.findPattern(table, 0, result));
    assertEquals(0, result.size());
  }

  @Test
  public void testFindPatternAfterPatternFound() {
    Pattern second = PatternFactory.createPattern("2 Website");
    Pattern toInvert = PatternFactory.createPattern("1 StatSensor");
    Pattern notPattern = new NotPattern(toInvert, second);

    Table result = new Table();

    assertTrue(notPattern.findPattern(table, 1, result));
    assertEquals(2, result.size());
  }

  @Test
  public void testFindPatternAfterPatternNotFound() {
    Pattern second = PatternFactory.createPattern("2 Website");
    Pattern toInvert = PatternFactory.createPattern("1 StatSensor");
    Pattern notPattern = new NotPattern(toInvert, second);

    Table result = new Table();

    assertFalse(notPattern.findPattern(table, 0, result));
    assertEquals(0, result.size());
  }

  @Test
  public void testFindPatternBetweenPatternsFound() {
    Pattern pattern = PatternFactory.createPattern("2 Website", "!(* StatSensor)", "1 Hospital");

    Table result = new Table();

    assertTrue(pattern.findPattern(table, 1, result));
    assertEquals(3, result.size());
  }

  @Test
  public void testFindPatternMultiple() {
    Pattern pattern = PatternFactory.createPattern("1 StatSensor", "2 Website", "!(1 Hospital)");

    Table result = new Table();

    assertFalse(pattern.findPattern(table, 0, result));
    assertEquals(0, result.size());
  }
  
  @Test
  public void testIsNextLastPattern() {
    Pattern toInvert = PatternFactory.createPattern("2 Website");
    NotPattern notPattern = new NotPattern(toInvert, toInvert);
    
    assertFalse(notPattern.isNextLastPattern());
    
    assertTrue(new NotPattern(toInvert, new NullPattern()).isNextLastPattern());
  }

  @Test
  public void testEquals() {
    Pattern toInvert = PatternFactory.createPattern("2 Website");
    Pattern toInvert2 = PatternFactory.createPattern("1 Website");
    NotPattern notPattern = new NotPattern(toInvert);
    NotPattern notPatternSame = new NotPattern(toInvert);
    NotPattern notPatternNotSame = new NotPattern(toInvert2);
    final String otherClass = "";

    assertEquals(notPattern, notPattern);
    assertEquals(notPattern, notPatternSame);
    assertNotEquals(notPattern, notPatternNotSame);
    assertNotEquals(notPattern, null);
    assertNotEquals(notPattern, otherClass);
  }

  @Test
  public void testHashCode() {
    Pattern toInvert = PatternFactory.createPattern("2 Website");
    NotPattern notPattern = new NotPattern(toInvert);
    int superHashCode = 31 + 31;

    assertEquals(31 * superHashCode + toInvert.hashCode(), notPattern.hashCode());
  }
}
