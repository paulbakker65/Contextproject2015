package net.tudelft.hi.e.computation;

import java.util.Collections;

import net.tudelft.hi.e.data.Record;
import net.tudelft.hi.e.data.RecordComparator;
import net.tudelft.hi.e.data.Table;
import net.tudelft.hi.e.data.Value;

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
    if(!operationParametersSet) {
      return false;
    }
    
    // Sort on target column to speed up searching
    Collections.sort(this.inputData, new RecordComparator(this.columnName));
    Collections.sort(this.otherTable, new RecordComparator(this.otherColumnName));

    for (Record record : this.inputData) {
      Value value = record.get(columnName);
      /*
       * If value == null it makes no sense comparing anything because it will always be false.
       */
      if (value.isNull()) {
        continue;
      }
      for (Record otherRecord : this.otherTable) {
        Value otherValue = otherRecord.get(otherColumnName);
        /*
         * If otherValue == null it will never be equal to value, continue to save resources
         */
        if (otherValue.isNull()) {
          continue;
        }

        if (value.equals(otherValue)) {
          Record combinedRecord = new Record();
          combinedRecord = (Record) record.clone();
          for (String key : otherRecord.getKeysInOrder()) {
            combinedRecord.put(key + "_1", otherRecord.get(key));
          }

          this.resultData.add(combinedRecord);
        }
        // prevent searching beyond the value that we're looking for
        else {
          if (value.compareTo(otherValue) > 0) {
            continue;
          }
        }
      }
    }
    return true;
  }

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
