package net.tudelft.hi.e.computation;

import net.tudelft.hi.e.data.Table;

import java.util.Objects;

/**
 * An abstract class for respresenting an operation on a table that returns a table.
 */
public abstract class Operation {

  /**
   * input dataset.
   */
  Table inputData;
  /**
   * result dataset, after operation.
   */
  Table resultData;
  /**
   * determines whether operationParameters are already set or not.
   */
  boolean operationParametersSet;

  /**
   * table name where the result data should live.
   */
  private String resultTableName;

  /**
   * standard constructor setting inputDataset by parameter inputDataset and initialize resultData
   * to an empty dataset. The chunks from the input data are copied.
   *
   * @param inputDataset the input dataset
   */
  public Operation(final Table inputDataset) {
    resetData(inputDataset);
    this.operationParametersSet = false;
    if (inputDataset != null) {
      this.resultTableName = inputData.getName();
    }
  }

  /**
   * abstract for executing operation.
   *
   * @return true iff succeeded, else false
   */
  public abstract boolean execute();

  /**
   * abstract for retrieving operation result.
   *
   * @return result dataset
   */
  public abstract Table getResult();

  /**
   * abstract for resetting the input and result data.
   */
  public abstract void resetData(Table inputData);

  /**
   * Get the table name of the result data.
   *
   * @return the table name of the result data.
   */
  public final String getResultTableName() {
    return this.resultTableName;
  }

  /**
   * Set the table name for the result data.
   *
   * @param tableName the table name.
   */
  public final void setResultTableName(String tableName) {
    this.resultTableName = tableName;
  }

  @Override public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Operation operation = (Operation) o;
    return Objects.equals(operationParametersSet, operation.operationParametersSet) &&
        Objects.equals(inputData, operation.inputData) &&
        Objects.equals(resultData, operation.resultData) &&
        Objects.equals(resultTableName, operation.resultTableName);
  }

  @Override public int hashCode() {
    return Objects.hash(inputData, resultData, operationParametersSet, resultTableName);
  }
}
