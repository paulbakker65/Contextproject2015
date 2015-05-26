package scriptlang.extra;

import java.util.ArrayList;

import operations.*;
import operations.ChunkingOperation.ChunkComparatorEnum;
import operations.coding.Pattern;
import parsers.*;

/**
 * OperationSpec is an object to specify the paramters of a parsed script input.
 * 
 */
public class OperationSpec {

  /**
   * OperationType Enum holds the different types of operations this class can specify the paramters
   * for.
   */
  public enum OperationType {
    CONSTRAINT, CONNECT, CHUNK, COMPUTE, COMPARE, CODE, CONVERT
  }

  public ArrayList<Object> operandList;
  public OperationType operationType;

  public OperationSpec() {
    this.operandList = new ArrayList<Object>();
    this.operationType = null;
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
      ((FilterOperation) o).setOperationParameters((String) this.operandList.get(0),
          (FilterOperation.ConstraintComparatorEnum) this.operandList.get(1),
          (Value) this.operandList.get(2));
      break;

    case CHUNK:
      ((ChunkingOperation) o).setOperationParameters((String) this.operandList.get(0),
          (ChunkComparatorEnum) this.operandList.get(1));
      break;

    case COMPUTE:
      break;

    case COMPARE:
      break;
      
    case CODE:
      ((CodingOperation) o).setOperationParameters((Pattern) this.operandList.get(0),
          (String) this.operandList.get(1));
      break;
      
    case CONVERT:
      break;

    default:
      break;
    }
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "OperationSpec [operationType=" + operationType + ", operandList=" + operandList + "]";
  }

  /* (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((operandList == null) ? 0 : operandList.hashCode());
    result = prime * result + ((operationType == null) ? 0 : operationType.hashCode());
    return result;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    OperationSpec other = (OperationSpec) obj;
    if (operandList == null) {
      if (other.operandList != null) {
        return false;
      }
    } else {
      for (int i = 0; i < this.operandList.size(); i++) {
        if (!this.operandList.get(i).toString().equals(other.operandList.get(i).toString())) {
          return false;
        }
      }
    }
    if (operationType != other.operationType) {
      return false;
    }
    return true;
  }
}
