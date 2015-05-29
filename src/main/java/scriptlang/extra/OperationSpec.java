package scriptlang.extra;

import enums.ChunkType;
import enums.CompareOperator;

import exceptions.TableNotFoundException;

import operations.ChunkingOperation;
import operations.CodingOperation;
import operations.ConnectionOperation;
import operations.FilterOperation;
import operations.Operation;
import operations.patterns.Pattern;

import table.Table;
import table.value.Value;

import java.util.ArrayList;

/**
 * OperationSpec is an object to specify the parameters of a parsed script input.
 * 
 */
public class OperationSpec {

  /**
   * OperationType enum holds the different types of operations this class can specify the
   * parameters for.
   */
  public enum OperationType {
    CONSTRAINT, CONNECT, CHUNK, COMPUTE, COMPARE, CODE, CONVERT
  }

  public ArrayList<Table> tables;
  public ArrayList<Object> operandList;
  public OperationType operationType;

  /**
   * OperationSpec constructor creates an OperationSpec object that defines the specifics of an
   * Operation.
   * 
   * @param tables The tables argument is an ArrayList of Table objects containing all the tables
   *        that can be used for a operation.
   */
  public OperationSpec(final ArrayList<Table> tables) {
    this.tables = tables;
    this.operandList = new ArrayList<Object>();
    this.operationType = null;
  }

  public void addOperationOperand(final Object operand) {
    this.operandList.add(operand);
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final OperationSpec other = (OperationSpec) obj;
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

  /**
   * The getOperationBySpec method takes the operands that are in the operand list and parses them
   * to a Operation object.
   *
   * @return The Operation object defined by the operand list.
   * @throws TableNotFoundException If the operand list specifies an Table that is not available a
   *         TableNotFoundException is thrown.
   */
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

  /**
   * The getTableForTableName method retrieves the Table with the specified table name from the list
   * of Tables in the tables field.
   * 
   * @param tableName The table name that is searched for.
   * @return The Table object retrieved from tables.
   * @throws TableNotFoundException If the the table cannot be found in the tables field a
   *         TableNotFoundException is thrown.
   */
  public Table getTableForTableName(final String tableName) throws TableNotFoundException {
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

  public void setOperationType(final OperationType ot) {
    this.operationType = ot;
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
}
