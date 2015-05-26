package scriptlang.extra;

import static org.junit.Assert.*;
import operations.FilterOperation;

import org.junit.Before;
import org.junit.Test;

import table.value.NumberValue;
import scriptlang.extra.OperationSpec.OperationType;
import table.Table;

public class OperationSpecTest {

  OperationSpec operationSpec;
  
  @Before
  public void setUp() throws Exception {
    operationSpec = new OperationSpec();
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
  public void testSetOperationParametersBySpec() {
    FilterOperation fo = new FilterOperation(new Table());
    operationSpec.setOperationType(OperationType.CONSTRAINT);
    operationSpec.addOperationOperand("field");
    operationSpec.addOperationOperand(FilterOperation.ConstraintComparatorEnum.EQ);
    operationSpec.addOperationOperand(new NumberValue(10));
    
    FilterOperation other = new FilterOperation(new Table());
    assertEquals(other, fo);
    
    operationSpec.setOperationParametersBySpec(fo);
    
    other.setOperationParameters("field", FilterOperation.ConstraintComparatorEnum.EQ, 
        new NumberValue(10));
    
    assertEquals(other, fo);
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
