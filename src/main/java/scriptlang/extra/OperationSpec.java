package scriptlang.extra;

import java.util.ArrayList;

import enums.ChunkType;
import enums.CompareOperator;
import exceptions.TableNotFoundException;
import operations.*;
import operations.coding.Pattern;
import table.Table;
import table.value.*;

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
    CONSTRAINT,
    CONNECT,
    CHUNK,
    COMPUTE,
    COMPARE,
    CODE,
    CONVERT
  }

  public ArrayList<Table> tables;
  public ArrayList<Object> operandList;
  public OperationType operationType;

  public OperationSpec() {
    this.tables = new ArrayList<Table>();
    this.operandList = new ArrayList<Object>();
    this.operationType = null;
  }
  
  public OperationSpec(ArrayList<Table> tables) {
    this.tables = tables;
    this.operandList = new ArrayList<Object>();
    this.operationType = null;
  }

  public void setOperationType(OperationType ot) {
    this.operationType = ot;
  }

  public void addOperationOperand(Object operand) {
    this.operandList.add(operand);
  }

  public Operation getOperationBySpec() throws TableNotFoundException {
    switch (this.operationType) {
    case CONSTRAINT:
      return new FilterOperation(this.getTableForTableName((String) operandList.get(0)),
          (String) operandList.get(1), (CompareOperator) operandList.get(2),
          (Value) operandList.get(3));

    case CHUNK:
      return new ChunkingOperation(this.getTableForTableName((String) operandList.get(0)),
          (String) operandList.get(1), (ChunkType) operandList.get(2));

    case COMPUTE:
      return null;

    case COMPARE:
      return null;

    case CODE:
      return new CodingOperation(this.getTableForTableName((String) operandList.get(0)),
          (Pattern) operandList.get(1), (String) operandList.get(2));

    case CONVERT:
      return null;

    case CONNECT:
      return new ConnectionOperation(this.getTableForTableName((String) operandList.get(0)),
          this.getTableForTableName((String) operandList.get(2)), (String) operandList.get(1),
          (String) operandList.get(3));

    default:
      return null;
    }
  }

  private Table getTableForTableName(String tableName) throws TableNotFoundException {
    for (int i = 0; i < tables.size(); i++) {
      if (tables.get(i).getName().equals(tableName)) {
        return tables.get(i);
      }
    }
    throw new TableNotFoundException(String.format("Table \"%s\" not found", tableName));
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "OperationSpec [operationType=" + operationType + ", operandList=" + operandList + "]";
  }

  /*
   * (non-Javadoc)
   * 
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

  /*
   * (non-Javadoc)
   * 
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
    if (operationType != other.operationType) {
      return false;
    }

    if (operandList == null) {
      if (other.operandList != null) {
        return false;
      }
    } else if (other.operandList == null) {
      return false;
    } else if (operandList.size() != other.operandList.size()) {
      return false;
    } else {
      for (int i = 0; i < this.operandList.size(); i++) {
        if (!this.operandList.get(i).toString().equals(other.operandList.get(i).toString())) {
          return false;
        }
      }
    }

    return true;
  }
}
