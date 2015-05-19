package operations;

import parsers.Value;
import table.Record;
import table.RecordComparator;
import table.Table;

/**
 * ConnectionOperation class providing an Operation to merge tables using a user-defined connection
 * argument.
 * 
 */
public class ConnectionOperation extends Operation {

  Table otherTable;
  String columnName;
  String otherColumnName;

  public ConnectionOperation(Table inputDataset) {
    super(inputDataset);
  }

  @Override
  public Table getResult() {
    return this.resultData;
  }

  @Override
  public boolean execute() {
    if (!this.operationParametersSet) {
      return false;
    }

    // Sort on target column to speed up searching
    this.inputData.sort(new RecordComparator(this.columnName));
    this.otherTable.sort(new RecordComparator(this.otherColumnName));

    for (Record record : this.inputData) {
      Value value = record.get(columnName);
      /*
       * If value == null it makes no sense comparing anything because it will always be false.
       */
      if (value == null) {
        continue;
      }
      for (Record otherRecord : this.otherTable) {
        Value otherValue = otherRecord.get(otherColumnName);
        /*
         * If otherValue == null it will never be equal to value, continue to save resources
         */
        if (otherValue == null) {
          continue;
        }

        if (value.equals(otherValue)) {
          Record combinedRecord = new Record();
          combinedRecord.putAll(record);
          combinedRecord.putAll(otherRecord);
          
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

  public boolean setOperationParameters(Table otherTable, String columnName, String otherColumnName) {
    this.otherTable = otherTable;
    this.columnName = columnName;
    this.otherColumnName = otherColumnName;

    this.operationParametersSet = true;
    return true;
  }
}
