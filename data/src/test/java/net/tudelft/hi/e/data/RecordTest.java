package net.tudelft.hi.e.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * RecordTest class testing the table.Record class.
 *
 */
public class RecordTest {

  ArrayList<Column> cols;

  /**
   * Creates list of column names.
   */
  @Before
  public void setUp() {
    cols = new ArrayList<Column>();
    cols.add(new StringColumn("fruit"));
    cols.add(new StringColumn("drink"));
  }

  @Test
  public void testContructor() {
    final Map<String, Value> expected = new HashMap<String, Value>();
    expected.put("fruit", new StringValue("banana"));
    expected.put("drink", new StringValue("milk"));

    assertEquals(expected, new Record(cols, new Value[] {new StringValue("banana"),
        new StringValue("milk")}));
  }

  @Test
  public void testConstructor2() {
    Record someRecord =
        new Record(cols, new Value[] {new StringValue("banana"), new StringValue("milk")});
    Record anotherRecord = new Record(someRecord);

    assertEquals(someRecord, anotherRecord);
  }

  @Test
  public void testToString() {
    final Record record =
        new Record(cols, new StringValue[] {new StringValue("banana"), new StringValue("milk")});
    final String result = record.toString();
    if (result.indexOf("banana") == -1 || result.indexOf("milk") == -1) {
      fail(result);
    }
  }

  @Test
  public void testKeysInOrderPut() {
    final Record record = new Record();
    List<String> columns = new ArrayList<String>();
    columns.add("fruit");
    columns.add("drink");
    columns.add("saus");
    record.put("fruit", new NullValue());
    record.put("drink", new NullValue());
    record.put("saus", new NullValue());

    assertEquals(columns, record.getKeysInOrder());
  }

  @Test
  public void testKeysInOrderRemove() {
    final Record record = new Record();
    List<String> columns = new ArrayList<String>();
    columns.add("saus");
    columns.add("fruit");
    columns.add("drink");
    record.put("saus", new NullValue());
    record.put("fruit", new NullValue());
    record.put("drink", new NullValue());

    assertEquals(columns, record.getKeysInOrder());

    record.remove("fruit");
    columns.remove("fruit");
    assertEquals(columns, record.getKeysInOrder());
  }

  @Test
  public void testKeysInOrderRename() {
    final Record record = new Record();
    List<String> columns = new ArrayList<String>();
    columns.add("fruit");
    columns.add("saus");
    columns.add("drink");
    record.put("fruit", new NullValue());
    record.put("saus", new NullValue());
    record.put("drink", new NullValue());

    assertEquals(columns, record.getKeysInOrder());

    record.rename("fruit", "groente");
    columns.set(0, "groente");
    assertEquals(columns, record.getKeysInOrder());
  }

  @Test
  public void testKeysInOrderPutExists() {
    final Record record = new Record();
    List<String> columns = new ArrayList<String>();
    columns.add("fruit");
    columns.add("saus");
    record.put("fruit", new NullValue());
    record.put("saus", new NullValue());
    record.put("saus", new NullValue());

    assertEquals(columns, record.getKeysInOrder());
  }

  @Test
  public void testKeysInOrderRemoveNotExists() {
    final Record record = new Record();
    List<String> columns = new ArrayList<String>();
    columns.add("fruit");
    columns.add("saus");
    record.put("fruit", new NullValue());
    record.put("saus", new NullValue());

    record.remove("groente");

    assertEquals(columns, record.getKeysInOrder());
  }

  @Test
  public void testEquals() {
    Record someRecord =
        new Record(cols, new Value[] {new StringValue("banana"), new StringValue("milk")});

    assertNotEquals(someRecord, null);
    assertEquals(someRecord, someRecord);
    assertEquals(someRecord, new Record(cols, new Value[] {new StringValue("banana"),
        new StringValue("milk")}));
    assertNotEquals(someRecord, new Table());
  }

  @Test
  public void testGetSetTableName() {
    Record someRecord =
        new Record(cols, new Value[] {new StringValue("banana"), new StringValue("milk")});
    assertEquals("", someRecord.getTableName());
    Record someOtherRecord =
        new Record(cols, new Value[] {new StringValue("banana"), new StringValue("milk")}, "table");
    assertEquals("table", someOtherRecord.getTableName());
  }

}
