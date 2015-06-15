package net.tudelft.hi.e.script;

import java.util.ArrayList;
import net.tudelft.hi.e.common.enums.CompareOperator;
import net.tudelft.hi.e.common.exceptions.TableNotFoundException;
import net.tudelft.hi.e.computation.BetweenOperation;
import net.tudelft.hi.e.computation.ConstraintOperation;
import net.tudelft.hi.e.data.NumberValue;
import net.tudelft.hi.e.data.Table;
import net.tudelft.hi.e.script.OperationSpec;
import net.tudelft.hi.e.script.OperationSpec.OperationType;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Before;
import org.junit.Test;

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
    operationSpec = new OperationSpec(new ArrayList<Table>());
    operationSpec.getTables().add(new Table());
    operationSpec.getTables().get(0).setName("table");
  }

  @Test
  public void testAddOperationOperand() {
    operationSpec.setOperationType(OperationType.CHUNK);
    assertEquals(0, operationSpec.getOperandList().size());
    operationSpec.addOperationOperand("field");
    assertEquals(1, operationSpec.getOperandList().size());
    assertEquals("field", operationSpec.getOperandList().get(0));

    final OperationSpec compare = new OperationSpec(new ArrayList<Table>());
    compare.setOperationType(OperationType.CHUNK);
    compare.getOperandList().add("field");

    assertEquals(compare, operationSpec);
  }

  @Test
  public void testEquals() {

    assertEquals(true, operationSpec.equals(operationSpec));
    assertEquals(false, operationSpec.equals(null));
    assertEquals(false, operationSpec.equals(new Object()));

    //
    OperationSpec operationSpecOther;
    operationSpec = new OperationSpec(new ArrayList<Table>());
    operationSpecOther = new OperationSpec(new ArrayList<Table>());
    operationSpec.setOperandList(null);
    assertEquals(false, operationSpec.equals(operationSpecOther));

    //
    operationSpec = new OperationSpec(new ArrayList<Table>());
    operationSpecOther = new OperationSpec(new ArrayList<Table>());
    operationSpecOther.setOperandList(null);
    assertEquals(false, operationSpec.equals(operationSpecOther));

    //
    operationSpec = new OperationSpec(new ArrayList<Table>());
    operationSpecOther = new OperationSpec(new ArrayList<Table>());
    operationSpecOther.setOperandList(null);
    operationSpec.setOperandList(null);
    assertEquals(true, operationSpec.equals(operationSpecOther));

    //
    operationSpec = new OperationSpec(new ArrayList<Table>());
    operationSpecOther = new OperationSpec(new ArrayList<Table>());
    operationSpec.addOperationOperand("field");
    assertEquals(false, operationSpec.equals(operationSpecOther));

    //
    operationSpec = new OperationSpec(new ArrayList<Table>());
    operationSpecOther = new OperationSpec(new ArrayList<Table>());
    operationSpecOther.addOperationOperand("field_2");
    assertEquals(false, operationSpec.equals(operationSpecOther));

    //
    operationSpec = new OperationSpec(new ArrayList<Table>());
    operationSpecOther = new OperationSpec(new ArrayList<Table>());
    operationSpec.addOperationOperand("field");
    operationSpecOther.addOperationOperand("field");
    assertEquals(true, operationSpec.equals(operationSpecOther));

    operationSpec = new OperationSpec(new ArrayList<Table>());
    operationSpecOther = new OperationSpec(new ArrayList<Table>());
    operationSpec.setOperationType(OperationType.CHUNK);
    assertEquals(false, operationSpec.equals(operationSpecOther));

    operationSpec = new OperationSpec(new ArrayList<Table>());
    operationSpecOther = new OperationSpec(new ArrayList<Table>());
    operationSpec.setOperationType(OperationType.CONSTRAINT);
    operationSpecOther.setOperationType(OperationType.CONSTRAINT);
    assertEquals(true, operationSpec.equals(operationSpecOther));

    operationSpec = new OperationSpec(new ArrayList<Table>());
    operationSpecOther = new OperationSpec(new ArrayList<Table>());
    operationSpecOther.setOperationType(OperationType.CODE);
    operationSpecOther.addOperationOperand("field");
    operationSpec.setOperationType(OperationType.CODE);
    operationSpec.addOperationOperand("field_diff");
    assertEquals(false, operationSpec.equals(operationSpecOther));
  }

  @Test
  public void testgetOperationForThisSpec_Constraint() throws TableNotFoundException {
    final ConstraintOperation op =
        new ConstraintOperation(new Table(), "field", CompareOperator.EQ, new NumberValue(10));

    operationSpec.setOperationType(OperationType.CONSTRAINT);
    operationSpec.addOperationOperand("table");
    operationSpec.addOperationOperand("field");
    operationSpec.addOperationOperand(CompareOperator.EQ);
    operationSpec.addOperationOperand(new NumberValue(10));

    ConstraintOperation other = new ConstraintOperation(new Table(), null, null, null);
    other = (ConstraintOperation) operationSpec.getOperationForThisSpec();
    other.setOperationParameters("field", CompareOperator.EQ, new NumberValue(10));

    assertEquals(op, other);
  }

  @Test
  public void testgetOperationForThisSpec_Between_1() throws TableNotFoundException {
    final BetweenOperation op =
        new BetweenOperation(new Table(), "field", "date1", new NumberValue(10),
            new NumberValue(20));

    operationSpec.setOperationType(OperationType.BETWEEN);
    operationSpec.addOperationOperand("table");
    operationSpec.addOperationOperand("field");
    operationSpec.addOperationOperand("table");
    operationSpec.addOperationOperand("date1");
    operationSpec.addOperationOperand(new NumberValue(10));
    operationSpec.addOperationOperand(new NumberValue(20));

    BetweenOperation other = (BetweenOperation) operationSpec.getOperationForThisSpec();
    assertEquals(op, other);
  }

  @Test
  public void testgetOperationForThisSpec_Between_2() throws TableNotFoundException {
    final BetweenOperation op =
        new BetweenOperation(new Table(), "field", "date1", "date2", new NumberValue(10),
            new NumberValue(20));

    operationSpec.setOperationType(OperationType.BETWEEN);
    operationSpec.addOperationOperand("table");
    operationSpec.addOperationOperand("field");
    operationSpec.addOperationOperand("table");
    operationSpec.addOperationOperand("date1");
    operationSpec.addOperationOperand("table");
    operationSpec.addOperationOperand("date2");
    operationSpec.addOperationOperand(new NumberValue(10));
    operationSpec.addOperationOperand(new NumberValue(20));

    BetweenOperation other = (BetweenOperation) operationSpec.getOperationForThisSpec();
    assertEquals(op, other);
  }

  @Test
  public void testHashCode() {
    operationSpec = new OperationSpec(new ArrayList<Table>());
    operationSpec.setOperationType(OperationType.CHUNK);
    final int prime = 31;
    int result = 1;
    result =
        prime * result
            + ((operationSpec.getOperandList() == null) ? 0 : operationSpec.getOperandList().hashCode());
    result =
        prime * result
            + ((operationSpec.getOperationType() == null) ? 0 : operationSpec.getOperationType().hashCode());
    assertEquals(result, operationSpec.hashCode());

    //
    operationSpec = new OperationSpec(new ArrayList<Table>());
    operationSpec.setOperationType(OperationType.CHUNK);
    operationSpec.setOperandList(null);
    result = 1;
    result =
        prime * result
            + ((operationSpec.getOperandList() == null) ? 0 : operationSpec.getOperandList().hashCode());
    result =
        prime * result
            + ((operationSpec.getOperationType() == null) ? 0 : operationSpec.getOperationType().hashCode());
    assertEquals(result, operationSpec.hashCode());

    //
    operationSpec = new OperationSpec(new ArrayList<Table>());
    operationSpec.setOperationType(OperationType.CHUNK);
    operationSpec.setOperationType(null);
    result = 1;
    result =
        prime * result
            + ((operationSpec.getOperandList() == null) ? 0 : operationSpec.getOperandList().hashCode());
    result =
        prime * result
            + ((operationSpec.getOperationType() == null) ? 0 : operationSpec.getOperationType().hashCode());
    assertEquals(result, operationSpec.hashCode());

    //
    operationSpec = new OperationSpec(new ArrayList<Table>());
    operationSpec.setOperationType(OperationType.CHUNK);
    operationSpec.setOperandList(null);
    operationSpec.setOperationType(null);
    result = 1;
    result =
        prime * result
            + ((operationSpec.getOperandList() == null) ? 0 : operationSpec.getOperandList().hashCode());
    result =
        prime * result
            + ((operationSpec.getOperationType() == null) ? 0 : operationSpec.getOperationType().hashCode());
    assertEquals(result, operationSpec.hashCode());
  }

  @Test
  public void testOperationSpec() {
    assertNotEquals(null, operationSpec);
    assertEquals(OperationSpec.class, operationSpec.getClass());
    assertEquals(null, operationSpec.getOperationType());
    assertEquals(0, operationSpec.getOperandList().size());
  }

  @Test
  public void testSetOperationType() {
    operationSpec.setOperationType(OperationType.COMPARE);
    assertEquals(OperationType.COMPARE, operationSpec.getOperationType());

    operationSpec.setOperationType(OperationType.CHUNK);
    assertEquals(OperationType.CHUNK, operationSpec.getOperationType());

    operationSpec.setOperationType(OperationType.CODE);
    assertEquals(OperationType.CODE, operationSpec.getOperationType());

    operationSpec.setOperationType(OperationType.COMPUTE);
    assertEquals(OperationType.COMPUTE, operationSpec.getOperationType());

    operationSpec.setOperationType(OperationType.CONSTRAINT);
    assertEquals(OperationType.CONSTRAINT, operationSpec.getOperationType());

    operationSpec.setOperationType(OperationType.CONVERT);
    assertEquals(OperationType.CONVERT, operationSpec.getOperationType());
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
}