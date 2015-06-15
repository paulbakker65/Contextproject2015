package operations;

import table.Record;
import table.Table;

public class CombineOperation extends Operation {

  private String column;
  private String value;
  private String value2;
  private String equalsCol;

  /**
   * Creates the combine operations.
   * 
   * @param inputDataset
   *          table in which to search.
   * @param column
   *          Column in which to find the unique values.
   * @param value
   *          The first unique value we want to combine.
   * @param value2
   *          The second unique value we want to combine.
   * @param equalsCol
   *          The value in this column has to be equal.
   */
  public CombineOperation(Table inputDataset, String column, String value, String value2,
      String equalsCol) {
    super(inputDataset);
    this.column = column;
    this.value = value;
    this.value2 = value2;
    this.equalsCol = equalsCol;
    operationParametersSet = true;
  }

  /**
   * Executes the operation. Keeps track of which value is found by two booleans.
   */
  @Override
  public boolean execute() {
    boolean valueFound = false;
    boolean value2Found = false;

    Record newRecord = new Record();
    for (Record record : inputData) {
      boolean[] result = combineRecords(record, newRecord, value, valueFound, value2Found);
      result = combineRecords(record, newRecord, value2, result[1], result[0]);
      valueFound = result[1];
      value2Found = result[0];
    }

    return true;
  }

  /**
   * Checks if the record equals the value and adds it to the newRecord.
   * 
   * @param record
   *          to check
   * @param newRecord
   *          to add the record to if it matches the value.
   * @param value
   *          to check
   * @param valueFound
   *          keeps track if the first unique value is found.
   * @param value2Found
   *          keeps track if the second unique value is found.
   * @return the list of valueFound and value2Found to keep track if a value is found.
   */
  private boolean[] combineRecords(Record record, Record newRecord, String value,
      Boolean valueFound, Boolean value2Found) {
    if (record.get(column).toString().equals(value)) {
      if (value2Found && newRecord.get(equalsCol).equals(record.get(equalsCol))) {
        for (String key : record.getKeysInOrder()) {
          newRecord.put(key + "_1", record.get(key));
        }
        resultData.add((Record) newRecord.clone());
        newRecord = new Record();
        valueFound = false;
        value2Found = false;
      } else {
        newRecord.putAll(record);
        valueFound = true;
      }
    }
    return new boolean[] { valueFound, value2Found };
  }

  public Table getResult() {
    return resultData;
  }

  @Override
  public void resetData(Table inputData) {
    this.inputData = inputData;
    this.resultData = new Table();
  }

}
