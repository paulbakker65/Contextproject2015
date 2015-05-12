package operations;

import table.Table;

public abstract class Operation {

	/**
	 * input dataset
	 */
	Table inputData;
	/**
	 * result dataset, after operation
	 */
	Table resultData;
	/**
	 * determines whether operationParameters are already set or not
	 */
	boolean operationParametersSet;

	/**
	 * standard constructor setting inputDataset by parameter inputDataset and
	 * initialize resultData to an empty dataset
	 * 
	 * @param inputDataset
	 *            the input dataset
	 */
	public Operation(Table inputDataset) {
		this.inputData = inputDataset;
		this.operationParametersSet = false;
		this.resultData = new Table();
	}

	/**
	 * abstract for retrieving operation result
	 * 
	 * @return result dataset
	 */
	public abstract Table getResult();

	/**
	 * abstract for executing operation
	 * 
	 * @return true iff succeeded, else false
	 */
	public abstract boolean execute();
}
