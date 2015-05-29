package table;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import table.value.Column;
import table.value.StringColumn;
import table.value.StringValue;
import table.value.Value;

import java.util.ArrayList;
import java.util.HashMap;
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
  public void testToString() {
    final Record record =
        new Record(cols, new StringValue[] {new StringValue("banana"), new StringValue("milk")});
    final String result = record.toString();
    if (result.indexOf("banana") == -1 || result.indexOf("milk") == -1) {
      fail(result);
    }
  }

}
