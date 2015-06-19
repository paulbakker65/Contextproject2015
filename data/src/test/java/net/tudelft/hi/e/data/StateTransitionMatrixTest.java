package net.tudelft.hi.e.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import net.tudelft.hi.e.common.exceptions.TableNotFoundException;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

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
  public void setUp() throws TableNotFoundException {
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
          assertEquals(createRecord("1Stat", 0, 0, 77, 0), record);
          break;
        case "2Stat":
          assertEquals(createRecord("2Stat", 6, 0, 12, 0), record);
          break;
        case "5Web":
          assertEquals(createRecord("5Web", 71, 18, 1, 0), record);
          break;
        case "2ndMeas":
          assertEquals(createRecord("2ndMeas", 0, 0, 0, 0), record);
          break;
        default:
          assertTrue(false);
      }
    }
  }

  @Test
  public void testEqualsHashCode() {
    StateTransitionMatrix testMatrix = new StateTransitionMatrix(new Table(), "Date");
    testMatrix.add(createRecord("2Stat", 6, 0, 12, 0));
    testMatrix.add(createRecord("1Stat", 0, 0, 77, 0));
    testMatrix.add(createRecord("5Web", 71, 18, 1, 0));
    testMatrix.add(createRecord("2ndMeas", 0, 0, 0, 0));

    
    

    StateTransitionMatrix stMatrix = new StateTransitionMatrix(table, "Date");

    assertEquals(testMatrix, stMatrix);
    assertFalse(stMatrix.equals(null));
    assertFalse(stMatrix.equals(new Table()));

    assertEquals(37 * 7 + testMatrix.getName().hashCode(), testMatrix.hashCode());
    assertEquals(37 * 7 + stMatrix.getName().hashCode(), stMatrix.hashCode());
  }

}