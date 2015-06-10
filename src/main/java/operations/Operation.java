package operations;

import table.Table;

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
   * standard constructor setting inputDataset by parameter inputDataset and initialize resultData
   * to an empty dataset. All but the records from the input data are copied.
   * 
   * @param inputDataset the input dataset
   */
  public Operation(final Table inputDataset) {
    resetData(inputDataset);    
    this.operationParametersSet = false;
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
}
