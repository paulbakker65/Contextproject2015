package net.tudelft.hi.e.computation;

import static org.junit.Assert.assertEquals;
import net.tudelft.hi.e.common.enums.ComputeOperator;
import net.tudelft.hi.e.data.Table;

import org.junit.Test;

/**
 * Class for testing Operation.
 */
public class OperationTest {

  @Test
  public void testGetInputTableName() {
    Table table = new Table();
    table.setName("Name");
    Operation operation = new ComputeOperation(table, ComputeOperator.COUNT, "column");
    assertEquals("Name", operation.getInputTableName());
  }
  
  @Test
  public void testSetResultTableName() {
    Table table = new Table();
    table.setName("Name");
    Operation operation = new ComputeOperation(table, ComputeOperator.COUNT, "column");
    operation.setResultTableName("OtherName");
    assertEquals("OtherName", operation.getResultTableName());
  }
}
