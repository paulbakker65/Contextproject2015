package net.tudelft.hi.e.data;

import java.io.IOException;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class StateTransitionMatrixTest {

  Table table;

  /**
   * Setup for the table on which to make the state transition matrix.
   *
   * @throws IOException
   *           exception
   * @throws ClassNotFoundException
   *           exception
   */
  @Before
  public void setUp() throws ClassNotFoundException, IOException {
    table = TableFile.readTable("src/test/resources/output_website");
  }

  private Record createRecord(String id, int i1, int i2, int i3, int i4) {
    ArrayList<Column> col = new ArrayList<Column>();
    col.add(new StringColumn("id"));
    col.add(new NumberColumn("1Stat"));
    col.add(new NumberColumn("2Stat"));
    col.add(new NumberColumn("5Web"));
    col.add(new NumberColumn("2ndMeas"));

    return new Record(col, new Value[] { new StringValue(id), new NumberValue(i1),
        new NumberValue(i2), new NumberValue(i3), new NumberValue(i4) });
  }

  @Test
  public void testMatrix() {
    StateTransitionMatrix stMatrix = new StateTransitionMatrix(table, "Date");

    for (Record record : stMatrix) {
      switch (record.get("id").toString()) {
        case "1Stat":
          assertEquals(createRecord("1Stat", 0, 1, 72, 5), record);
          break;
        case "2Stat":
          assertEquals(createRecord("2Stat", 0, 0, 14, 2), record);
          break;
        case "5Web":
          assertEquals(createRecord("5Web", 79, 10, 2, 0), record);
          break;
        case "2ndMeas":
          assertEquals(createRecord("2ndMeas", 0, 1, 0, 3), record);
          break;
        default:
          assertTrue(false);
      }
    }
  }

}
