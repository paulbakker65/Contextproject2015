package table;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import table.value.Column;
import table.value.StringColumn;
import table.value.StringValue;
import table.value.Value;

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
    Map<String, Value> expected = new HashMap<String, Value>();
    expected.put("fruit", new StringValue("banana"));
    expected.put("drink", new StringValue("milk"));

    assertEquals(expected, new Record(cols, new Value[] { new StringValue("banana"),
        new StringValue("milk") }));

  }

  @Test
  public void testToString() {
    Record record = new Record(cols, new StringValue[] { new StringValue("banana"),
        new StringValue("milk") });
    String result = record.toString();
    if (result.indexOf("banana") == -1 || result.indexOf("milk") == -1) {
      fail(result);
    }
  }

}
