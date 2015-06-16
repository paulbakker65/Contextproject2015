package net.tudelft.hi.e.script;

import java.util.ArrayList;
import java.util.List;
import net.tudelft.hi.e.common.enums.ChunkType;
import net.tudelft.hi.e.common.enums.CompareOperator;
import net.tudelft.hi.e.common.exceptions.TableNotFoundException;
import net.tudelft.hi.e.computation.BetweenOperation;
import net.tudelft.hi.e.computation.ChunkingOperation;
import net.tudelft.hi.e.computation.CodingOperation;
import net.tudelft.hi.e.computation.ConnectionOperation;
import net.tudelft.hi.e.computation.ConstraintOperation;
import net.tudelft.hi.e.computation.Grouper;
import net.tudelft.hi.e.computation.LsaOperation;
import net.tudelft.hi.e.computation.Operation;
import net.tudelft.hi.e.computation.PatternDescription;
import net.tudelft.hi.e.computation.PatternFactory;
import net.tudelft.hi.e.data.NumberValue;
import net.tudelft.hi.e.data.StringValue;
import net.tudelft.hi.e.data.Table;
import net.tudelft.hi.e.data.Value;

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
    CONSTRAINT, CONNECT, CHUNK, COMPUTE, COMPARE, CODE, CONVERT, LSA, BETWEEN
  }

  private List<Table> tables;
  private List<Object> operandList;
  private OperationType operationType;

  /**
   * OperationSpec constructor creates an OperationSpec object that defines the specifics of an
   * Operation.
   *
   * @param tables The tables argument is an ArrayList of Table objects containing all the tables
   *        that can be used for a operation.
   */
  public OperationSpec(final List<Table> tables) {
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
   * Warning cannot be solved without solving the universe.
   */
  @SuppressWarnings("unchecked")
  /**
   * The getOperationForThisSpec method takes the operands that are in the operand list and parses
   * them to a Operation object.
   *
   * @return The Operation object defined by the operand list.
   * @throws TableNotFoundException If the operand list specifies an Table that is not available a
   *         TableNotFoundException is thrown.
   */
  public Operation getOperationForThisSpec() throws TableNotFoundException {
    switch (this.operationType) {
      case CONSTRAINT:
        return this.getConstraintOperationForThisSpec();

      case CHUNK:
        return this.getChunkingOperationForThisSpec();

      case COMPUTE:
        return null;

      case COMPARE:
        return null;

      case CODE:
        return this.getCodingOperationForThisSpec();

      case CONVERT:
        return null;

      case CONNECT:
        return this.getConnectionOperationForThisSpec();

      case BETWEEN:
        return this.getBetweenOperationForThisSpec();

      case LSA:
        return this.getLsaOperationForThisSpec();

      default:
        return null;
    }
  }
  
  public ConstraintOperation getConstraintOperationForThisSpec() throws TableNotFoundException {
    return new ConstraintOperation(this.getTableForTableName((String) operandList.get(0)),
        (String) operandList.get(1), (CompareOperator) operandList.get(2),
        (Value) operandList.get(3));
  }
  
  public ChunkingOperation getChunkingOperationForThisSpec() throws TableNotFoundException {
    return new ChunkingOperation(this.getTableForTableName((String) operandList.get(0)),
        (String) operandList.get(1), (ChunkType) operandList.get(2));
  }
  
  public CodingOperation getCodingOperationForThisSpec() throws TableNotFoundException {
    return new CodingOperation(this.getTableForTableName((String) operandList.get(0)),
        PatternFactory.createPattern((ArrayList<PatternDescription>) operandList.get(1)),
        ((StringValue) operandList.get(2)).getValue());
  }
  
  public ConnectionOperation getConnectionOperationForThisSpec() throws TableNotFoundException {
    return new ConnectionOperation(this.getTableForTableName((String) operandList.get(0)),
        this.getTableForTableName((String) operandList.get(2)), (String) operandList.get(1),
        (String) operandList.get(3));
  }

  /**
   * Create a BetweenOperation object according to the operation specification that this object
   * represents.
   *
   * @return A BetweenOperation.
   * @throws TableNotFoundException If one of the requested tables is not found by name a
   *         TableNotFoundException is thrown.
   */
  private BetweenOperation getBetweenOperationForThisSpec() throws TableNotFoundException {
    if (this.operandList.size() == 6) {
      return new BetweenOperation(this.getTableForTableName((String) operandList.get(0)),
          (String) operandList.get(1), (String) operandList.get(3), (Value) operandList.get(4),
          (Value) operandList.get(5));
    } else {
      return new BetweenOperation(this.getTableForTableName((String) operandList.get(0)),
          (String) operandList.get(1), (String) operandList.get(3), (String) operandList.get(5),
          (Value) operandList.get(6), (Value) operandList.get(7));
    }
  }

  /**
   * Create a LsaOperation object according to the operation specification that this object
   * represents.
   *
   * @return A LsaOperation.
   * @throws TableNotFoundException If one of the requested tables is not found by name a
   *         TableNotFoundException is thrown.
   */
  private LsaOperation getLsaOperationForThisSpec() throws TableNotFoundException {
    if (this.operandList.size() == 6) {
      return new LsaOperation(this.getTableForTableName((String) operandList.get(0)),
          (String) operandList.get(1), (int) ((NumberValue) operandList.get(2)).getValue(),
          (int) ((NumberValue) operandList.get(3)).getValue(), (Value) operandList.get(4),
          (Value) operandList.get(5));
    } else {
      return new LsaOperation(this.getTableForTableName((String) operandList.get(0)),
          (String) operandList.get(1), (int) ((NumberValue) operandList.get(2)).getValue(),
          (int) ((NumberValue) operandList.get(3)).getValue(), (Value) operandList.get(4),
          (Value) operandList.get(5), (Grouper) operandList.get(6));
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



  /**
   * @return the tables
   */
  public final List<Table> getTables() {
    return tables;
  }



  /**
   * @param tables the tables to set
   */
  public final void setTables(List<Table> tables) {
    this.tables = tables;
  }



  /**
   * @return the operandList
   */
  public final List<Object> getOperandList() {
    return operandList;
  }



  /**
   * @param operandList the operandList to set
   */
  public final void setOperandList(List<Object> operandList) {
    this.operandList = operandList;
  }



  /**
   * @return the operationType
   */
  public final OperationType getOperationType() {
    return operationType;
  }
}
