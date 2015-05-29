package scriptlang.extra;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;

import operations.FilterOperation;

import org.junit.Before;
import org.junit.Test;

import enums.CompareOperator;
import exceptions.TableNotFoundException;
import table.value.NumberValue;
import scriptlang.extra.OperationSpec.OperationType;
import table.Table;

/**
 * JUnit test for OperationSpec class.
 *
 */
public class OperationSpecTest {

  OperationSpec operationSpec;

  /**
   * Setup the operationSpec with an empty table.
   * 
   */
  @Before
  public void setUp() {
    operationSpec = new OperationSpec();
    operationSpec.tables.add(new Table());
    operationSpec.tables.get(0).setName("table");
  }

  @Test
  public void testOperationSpec() {
    assertNotEquals(null, operationSpec);
    assertEquals(OperationSpec.class, operationSpec.getClass());
    assertEquals(null, operationSpec.operationType);
    assertEquals(0, operationSpec.operandList.size());
  }

  @Test
  public void testSetOperationType() {
    operationSpec.setOperationType(OperationType.COMPARE);
    assertEquals(OperationType.COMPARE, operationSpec.operationType);

    operationSpec.setOperationType(OperationType.CHUNK);
    assertEquals(OperationType.CHUNK, operationSpec.operationType);

    operationSpec.setOperationType(OperationType.CODE);
    assertEquals(OperationType.CODE, operationSpec.operationType);

    operationSpec.setOperationType(OperationType.COMPUTE);
    assertEquals(OperationType.COMPUTE, operationSpec.operationType);

    operationSpec.setOperationType(OperationType.CONSTRAINT);
    assertEquals(OperationType.CONSTRAINT, operationSpec.operationType);

    operationSpec.setOperationType(OperationType.CONVERT);
    assertEquals(OperationType.CONVERT, operationSpec.operationType);
  }

  @Test
  public void testAddOperationOperand() {
    operationSpec.setOperationType(OperationType.CHUNK);
    assertEquals(0, operationSpec.operandList.size());
    operationSpec.addOperationOperand("field");
    assertEquals(1, operationSpec.operandList.size());
    assertEquals("field", operationSpec.operandList.get(0));

    OperationSpec compare = new OperationSpec();
    compare.setOperationType(OperationType.CHUNK);
    compare.operandList.add("field");

    assertEquals(compare, operationSpec);
  }

  @Test
  public void testGetOperationBySpec() throws TableNotFoundException {
    final FilterOperation op = new FilterOperation(new Table(), "field", CompareOperator.EQ,
        new NumberValue(10));
    
    operationSpec.setOperationType(OperationType.CONSTRAINT);
    operationSpec.addOperationOperand("table");
    operationSpec.addOperationOperand("field");
    operationSpec.addOperationOperand(CompareOperator.EQ);
    operationSpec.addOperationOperand(new NumberValue(10));
    
    FilterOperation other = new FilterOperation(new Table(), null, null, null);
    other = (FilterOperation) operationSpec.getOperationBySpec();
    other.setOperationParameters("field", CompareOperator.EQ, new NumberValue(10));

    assertEquals(op, other);
  }

  @Test
  public void testToString() {
    operationSpec.setOperationType(OperationType.CONSTRAINT);
    String expected = "OperationSpec [operationType=CONSTRAINT, operandList=[]]";
    assertEquals(expected, operationSpec.toString());

    operationSpec.addOperationOperand("field");
    expected = "OperationSpec [operationType=CONSTRAINT, operandList=[field]]";
    assertEquals(expected, operationSpec.toString());
  }

  @Test
  public void testHashCode() {
    operationSpec = new OperationSpec();
    operationSpec.setOperationType(OperationType.CHUNK);
    final int prime = 31;
    int result = 1;
    result = prime * result
        + ((operationSpec.operandList == null) ? 0 : operationSpec.operandList.hashCode());
    result = prime * result
        + ((operationSpec.operationType == null) ? 0 : operationSpec.operationType.hashCode());
    assertEquals(result, operationSpec.hashCode());

    //
    operationSpec = new OperationSpec();
    operationSpec.setOperationType(OperationType.CHUNK);
    operationSpec.operandList = null;
    result = 1;
    result = prime * result
        + ((operationSpec.operandList == null) ? 0 : operationSpec.operandList.hashCode());
    result = prime * result
        + ((operationSpec.operationType == null) ? 0 : operationSpec.operationType.hashCode());
    assertEquals(result, operationSpec.hashCode());

    //
    operationSpec = new OperationSpec();
    operationSpec.setOperationType(OperationType.CHUNK);
    operationSpec.operationType = null;
    result = 1;
    result = prime * result
        + ((operationSpec.operandList == null) ? 0 : operationSpec.operandList.hashCode());
    result = prime * result
        + ((operationSpec.operationType == null) ? 0 : operationSpec.operationType.hashCode());
    assertEquals(result, operationSpec.hashCode());

    //
    operationSpec = new OperationSpec();
    operationSpec.setOperationType(OperationType.CHUNK);
    operationSpec.operandList = null;
    operationSpec.operationType = null;
    result = 1;
    result = prime * result
        + ((operationSpec.operandList == null) ? 0 : operationSpec.operandList.hashCode());
    result = prime * result
        + ((operationSpec.operationType == null) ? 0 : operationSpec.operationType.hashCode());
    assertEquals(result, operationSpec.hashCode());
  }

  @Test
  public void testEquals() {
    OperationSpec operationSpecOther;

    assertEquals(true, operationSpec.equals(operationSpec));
    assertEquals(false, operationSpec.equals(null));
    assertEquals(false, operationSpec.equals(new Object()));

    //
    operationSpec = new OperationSpec();
    operationSpecOther = new OperationSpec();
    operationSpec.operandList = null;
    assertEquals(false, operationSpec.equals(operationSpecOther));

    //
    operationSpec = new OperationSpec();
    operationSpecOther = new OperationSpec();
    operationSpecOther.operandList = null;
    assertEquals(false, operationSpec.equals(operationSpecOther));

    //
    operationSpec = new OperationSpec();
    operationSpecOther = new OperationSpec();
    operationSpecOther.operandList = null;
    operationSpec.operandList = null;
    assertEquals(true, operationSpec.equals(operationSpecOther));

    //
    operationSpec = new OperationSpec();
    operationSpecOther = new OperationSpec();
    operationSpec.addOperationOperand("field");
    assertEquals(false, operationSpec.equals(operationSpecOther));

    //
    operationSpec = new OperationSpec();
    operationSpecOther = new OperationSpec();
    operationSpecOther.addOperationOperand("field_2");
    assertEquals(false, operationSpec.equals(operationSpecOther));

    //
    operationSpec = new OperationSpec();
    operationSpecOther = new OperationSpec();
    operationSpec.addOperationOperand("field");
    operationSpecOther.addOperationOperand("field");
    assertEquals(true, operationSpec.equals(operationSpecOther));

    operationSpec = new OperationSpec();
    operationSpecOther = new OperationSpec();
    operationSpec.setOperationType(OperationType.CHUNK);
    assertEquals(false, operationSpec.equals(operationSpecOther));

    operationSpec = new OperationSpec();
    operationSpecOther = new OperationSpec();
    operationSpec.setOperationType(OperationType.CONSTRAINT);
    operationSpecOther.setOperationType(OperationType.CONSTRAINT);
    assertEquals(true, operationSpec.equals(operationSpecOther));

    operationSpec = new OperationSpec();
    operationSpecOther = new OperationSpec();
    operationSpecOther.setOperationType(OperationType.CODE);
    operationSpecOther.addOperationOperand("field");
    operationSpec.setOperationType(OperationType.CODE);
    operationSpec.addOperationOperand("field_diff");
    assertEquals(false, operationSpec.equals(operationSpecOther));
  }
}
