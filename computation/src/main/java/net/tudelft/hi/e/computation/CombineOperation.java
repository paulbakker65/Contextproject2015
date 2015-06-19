package net.tudelft.hi.e.computation;

import net.tudelft.hi.e.data.Record;
import net.tudelft.hi.e.data.RecordComparator;
import net.tudelft.hi.e.data.Table;
import net.tudelft.hi.e.data.Value;

import java.util.Collections;

/**
 * Combine class providing an Operation to merge tables using a user-defined connection argument.
 * 
 */
public class CombineOperation extends Operation {

  Table otherTable;
  String columnName;
  String otherColumnName;

  /**
   * Sets all the necessary variables.
   * 
   * @param inputDataset the first table with records you want to combine.
   * @param otherTable the other table with records you want to combine.
   * @param columnName columnname in the first table on which you want to combine.
   * @param otherColumnName columnname in the other table on which you want to combine.
   */
  public CombineOperation(Table inputDataset, Table otherTable, String columnName,
      String otherColumnName) {
    super(inputDataset);
    setOperationParameters(otherTable, columnName, otherColumnName);
  }

  @Override
  public Table getResult() {
    return this.resultData;
  }

  @Override
  public boolean execute() {
    if (!operationParametersSet) {
      return false;
    }

    // Sort on target column to speed up searching
    Collections.sort(this.inputData, new RecordComparator(this.columnName));
    Collections.sort(this.otherTable, new RecordComparator(this.otherColumnName));

    for (Record record : this.inputData) {
      Value value = record.get(columnName);
      if (value.isNull()) {
        continue;
      }
      searchOtherRecord(record, value);
    }
    return true;
  }
  
  private void searchOtherRecord(Record thisRecord, Value thisValue) {
    for (Record otherRecord : this.otherTable) {
      Value otherValue = otherRecord.get(otherColumnName);
      if (otherValue.isNull()) {
        continue;
      }
      if (thisValue.equals(otherValue)) {
        combineRecords(thisRecord, otherRecord);
      } 
    }
  }
  
  private void combineRecords(Record thisRecord, Record otherRecord) {
    Record combinedRecord = new Record(thisRecord);
    
    for (String key : otherRecord.getKeysInOrder()) {
      combinedRecord.put(key + "_1", otherRecord.get(key));
    }

    this.resultData.add(combinedRecord);
  }

  /**
   * Sets the variables of the operation.
   * 
   * @param otherTable the other table
   * @param columnName column name of the first table
   * @param otherColumnName column name of the other table
   */
  public void setOperationParameters(Table otherTable, String columnName, String otherColumnName) {
    if (columnName != null && otherColumnName != null) {
      this.otherTable = otherTable;
      this.columnName = columnName;
      this.otherColumnName = otherColumnName;
      this.operationParametersSet = true;
    }
  }

  @Override
  public void resetData(Table inputData) {
    this.inputData = inputData;
    this.resultData = new Table();
    this.resultData.setName("Combine");
  }
}
