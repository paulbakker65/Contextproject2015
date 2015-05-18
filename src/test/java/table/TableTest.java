package table;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import input.Column;
import input.NumberColumn;
import input.StringColumn;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import parsers.NumberValue;
import parsers.StringValue;
import parsers.Value;

public class TableTest {

  private Table t;

  @Before
  public void setUp() {
    t = new Table();

    // Create table with 100 user id's and a random number as second field.
    for (int i = 0; i < 100; i++) {
      ArrayList<Column> cols = new ArrayList<Column>();
      cols.add(new NumberColumn("userid"));
      cols.add(new StringColumn("random"));
      Record r = new Record(cols, new Value[] { new NumberValue(i), new StringValue(i * 10 + "") });
      t.add(r);
    }
    // Add 10 more records with the same user id's
    for (int i = 0; i < 10; i++) {
      ArrayList<Column> cols = new ArrayList<Column>();
      cols.add(new NumberColumn("userid"));
      cols.add(new StringColumn("random"));
      Record r = new Record(cols,
          new Value[] { new NumberValue(i), new StringValue(i * 1000 + "") });
      t.add(r);
    }
  }

  @Test
  public void test_getPatientById_size0() {
    int actual = t.getPatientByID(new NumberValue(0), "userid").size();

    assertEquals(2, actual);
  }

  @Test
  public void test_getPatientById_size1() {
    int actual = t.getPatientByID(new NumberValue(20), "userid").size();

    assertEquals(1, actual);
  }

  @Test
  public void test_getPatientById_size_empty() {
    int actual = t.getPatientByID(new NumberValue(-5), "userid").size();

    assertEquals(0, actual);
  }

  @Test
  public void test_getPatientById_equal() {
    Table actual = t.getPatientByID(new NumberValue(1), "userid");

    Table expected = new Table();
    ArrayList<Column> cols = new ArrayList<Column>();
    cols.add(new NumberColumn("userid"));
    cols.add(new StringColumn("random"));
    Record r1 = new Record(cols, new Value[] { new NumberValue(1), new StringValue("10") });
    Record r2 = new Record(cols, new Value[] { new NumberValue(1), new StringValue("1000") });

    expected.add(r1);
    expected.add(r2);

    assertEquals(expected, actual);
  }

  @Test
  public void test_getPatientById_notEqual() {
    Table actual = t.getPatientByID(new NumberValue(-5), "userid");

    Table expected = new Table();
    ArrayList<Column> cols = new ArrayList<Column>();
    cols.add(new StringColumn("userid"));
    cols.add(new StringColumn("random"));
    Record r1 = new Record(cols, new StringValue[] { new StringValue("1"), new StringValue("10") });
    Record r2 = new Record(cols,
        new StringValue[] { new StringValue("1"), new StringValue("1000") });

    expected.add(r1);
    expected.add(r2);

    assertNotEquals(expected, actual);
  }

  @Test
  public void test_getPatientById_partlyEqual() {
    Table actual = t.getPatientByID(new NumberValue(1), "userid");

    Table expected = new Table();
    ArrayList<Column> cols = new ArrayList<Column>();
    cols.add(new StringColumn("userid"));
    cols.add(new StringColumn("random"));
    Record r1 = new Record(cols, new StringValue[] { new StringValue("1"), new StringValue("10") });

    expected.add(r1);

    assertNotEquals(expected, actual);
  }

  @Test
  public void test_getPatientById_partlyEqualpartlyWrong() {
    Table actual = t.getPatientByID(new NumberValue(1), "userid");

    Table expected = new Table();
    ArrayList<Column> cols = new ArrayList<Column>();
    cols.add(new StringColumn("userid"));
    cols.add(new StringColumn("random"));
    Record r1 = new Record(cols, new StringValue[] { new StringValue("1"), new StringValue("10") });
    Record r2 = new Record(cols, new StringValue[] { new StringValue("1"), new StringValue("5") });

    expected.add(r1);
    expected.add(r2);

    assertNotEquals(expected, actual);
  }

  @Test
  public void test_getPatientById_competelyWrong() {
    Table actual = t.getPatientByID(new NumberValue(1), "userid");

    Table expected = new Table();
    ArrayList<Column> cols = new ArrayList<Column>();
    cols.add(new StringColumn("userid"));
    cols.add(new StringColumn("random"));
    Record r1 = new Record(cols, new StringValue[] { new StringValue("1"), new StringValue("5") });
    Record r2 = new Record(cols, new StringValue[] { new StringValue("1"), new StringValue("5") });

    expected.add(r1);
    expected.add(r2);

    assertNotEquals(expected, actual);
  }

  @Test
  public void test_getPatientById_testAlot() {
    for (int i = 0; i < t.size(); i++) {
      Table actual = t.getPatientByID(new StringValue(i + ""), "userid");

      assertEquals(actual, actual.getPatientByID(new StringValue(i + ""), "userid"));
    }

  }
}
