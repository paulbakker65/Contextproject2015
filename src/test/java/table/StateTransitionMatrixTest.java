package table;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import table.value.Column;
import table.value.NumberColumn;
import table.value.NumberValue;
import table.value.StringColumn;
import table.value.StringValue;
import table.value.Value;

import java.util.ArrayList;
import java.util.Set;

public class StateTransitionMatrixTest {

  Table table;

  /**
   * Setup for the table on which to make the state transition matrix.
   */
  @Before
  public void setUp() {
    table = new Table();
    ArrayList<Column> col = new ArrayList<Column>();
    col.add(new StringColumn("Code"));
    table.add(new Record(col, new Value[] { new StringValue("1S5W") }));
    table.add(new Record(col, new Value[] { new StringValue("1S5W") }));
    table.add(new Record(col, new Value[] { new StringValue("2S5W") }));
    table.add(new Record(col, new Value[] { new StringValue("1S5W") }));
    table.add(new Record(col, new Value[] { new StringValue("3S5W") }));
    table.add(new Record(col, new Value[] { new StringValue("3S5W") }));
    table.add(new Record(col, new Value[] { new StringValue("3S5W") }));
    table.add(new Record(col, new Value[] { new StringValue("2S5W") }));
    table.add(new Record(col, new Value[] { new StringValue("1S5W") }));
    table.add(new Record(col, new Value[] { new StringValue("3S5W") }));
    table.add(new Record(col, new Value[] { new StringValue("3S5W") }));
    table.add(new Record(col, new Value[] { new StringValue("4S5W") }));
    table.add(new Record(col, new Value[] { new StringValue("1S5W") }));
    table.add(new Record(col, new Value[] { new StringValue("4S5W") }));
    table.add(new Record(col, new Value[] { new StringValue("5S5W") }));
    table.addCode(new Code("1S5W"));
    table.addCode(new Code("2S5W"));
    table.addCode(new Code("3S5W"));
    table.addCode(new Code("4S5W"));
    table.addCode(new Code("5S5W"));
  }

  private Record createRecord(String id, int i1, int i2, int i3, int i4, int i5) {
    Set<String> keyset = table.getCodes().keySet();
    ArrayList<Column> col = new ArrayList<Column>();

    col.add(new StringColumn("id"));
    for (String code : keyset) {
      col.add(new NumberColumn(code));
    }
    return new Record(col, new Value[] {new StringValue(id), new NumberValue(i1),
        new NumberValue(i2),  new NumberValue(i4), new NumberValue(i5), new NumberValue(i3)});
  }

  @Test
  public void testMatrix() {

    StateTransitionMatrix stMatrix = new StateTransitionMatrix(table);
    for (Record record : stMatrix) {
      switch (record.get("id").toString()) {
        case "1S5W":
          assertEquals(record, createRecord("1S5W", 0, 1, 2, 1, 0));
          break;
        case "2S5W":
          assertEquals(record, createRecord("2S5W", 2, 0, 0, 0, 0));
          break;
        case "3S5W": 
          assertEquals(record, createRecord("3S5W", 0, 1, 0, 1, 0));
          break;
        case "4S5W":
          assertEquals(record, createRecord("4S5W", 1, 0, 0, 0, 1));
          break;
        case "5S5W" :
          assertEquals(record, createRecord("5S5W", 0, 0, 0, 0, 0));
          break;
        default :
          assertTrue(false);
      }
    }
  }

}
