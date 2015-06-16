package net.tudelft.hi.e.computation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.tudelft.hi.e.common.enums.ComputeOperator;
import net.tudelft.hi.e.data.Column;
import net.tudelft.hi.e.data.NullValue;
import net.tudelft.hi.e.data.NumberColumn;
import net.tudelft.hi.e.data.NumberValue;
import net.tudelft.hi.e.data.Record;
import net.tudelft.hi.e.data.StringValue;
import net.tudelft.hi.e.data.Table;
import net.tudelft.hi.e.data.Value;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class ComputeOperationTest {
  private Table table;
  private List<Column> columns;

  /**
   * Create input table.
   */
  @Before
  public void setUp() {
    table = new Table();
    columns = new ArrayList<Column>(Arrays.asList(new NumberColumn("Value")));
    addNullValue();
    addValues(2, 6, 9, 35, 100, 10, 50);
    addNullValue();
    addValues(40, 30, 28, 60, 0, 7);
    addNullValue();
    table.add(new Record());
  }

  private void addValues(double...values) {
    for (int i = 0; i < values.length; i++) {
      table.add(new Record(columns, new Value[]{ new NumberValue(values[i]) }));
    }
  }

  private void addNullValue() {
    table.add(new Record(columns, new Value[]{ new NullValue() }));
  }

  @Test
  public void testConstructor() {
    ComputeOperation operation = new ComputeOperation(table, ComputeOperator.AVG, "Value");

    assertNotNull(operation);
    assertEquals(ComputeOperator.AVG, operation.operator);
    assertEquals("Value", operation.column);
    assertTrue(operation.operationParametersSet);
  }

  @Test
  public void testConstructorWrongArgs() {
    ComputeOperation operation = new ComputeOperation(null, ComputeOperator.AVG, "Value");
    assertFalse(operation.operationParametersSet);

    operation = new ComputeOperation(new Table(), ComputeOperator.AVG, "Value");
    assertFalse(operation.operationParametersSet);

    operation = new ComputeOperation(table, ComputeOperator.AVG, null);
    assertFalse(operation.operationParametersSet);

    operation = new ComputeOperation(table, ComputeOperator.AVG, "NotExists");
    assertFalse(operation.operationParametersSet);
  }

  @Test
  public void testExecute() throws ClassNotFoundException, IOException {
    Table resultTable = new Table();
    resultTable.setName("Computation");
    Record record = new Record("Computation");
    record.put("Computation", new StringValue("AVG(Value)"));
    record.put("Result", new NumberValue(29));
    resultTable.add(record);

    ComputeOperation operation = new ComputeOperation(table, ComputeOperator.AVG, "Value");
    assertTrue(operation.execute());
    assertEquals(resultTable, operation.getResult());
  }

  @Test
  public void testExecuteWrongArgs() throws ClassNotFoundException, IOException {
    ComputeOperation operation = new ComputeOperation(null, ComputeOperator.AVG, "Value");
    assertFalse(operation.execute());
  }
}
