package scriptlang.scriptlangObjects;

import java.util.ArrayList;

import operations.*;
import operations.ChunkingOperation.ChunkComparatorEnum;
import parsers.*;

public class OperationSpec {
  
  public enum OperationType {
    CONSTRAINT,
    CONNECT,
    CHUNK,
    COMPUTE,
    COMPARE,
    CODE,
    CONVERT
  }
  
  public ArrayList<Object> operandList;
  public OperationType operationType;
  
  public OperationSpec() {
    this.operandList = new ArrayList<Object>();
  }
  
  public void setOperationType(OperationType ot) {
    this.operationType = ot;
  }
  
  public void addOperationOperand(Object operand) {
    this.operandList.add(operand);
  }
  
  public void setOperationParametersBySpec(Operation o) {
    switch (this.operationType) {
    case CONSTRAINT:
      ((FilterOperation) o).setOperationParameters((String)this.operandList.get(0)
          , (FilterOperation.ConstraintComparatorEnum)this.operandList.get(1)
          , (Value)this.operandList.get(2));
    case CHUNK:
      ((ChunkingOperation) o).setOperationParameters((String)this.operandList.get(0),
          (ChunkComparatorEnum)this.operandList.get(1));
    case COMPUTE:
      
    case COMPARE:
      
    case CODE:
      
    case CONVERT:
      
    
    }
  }

}
