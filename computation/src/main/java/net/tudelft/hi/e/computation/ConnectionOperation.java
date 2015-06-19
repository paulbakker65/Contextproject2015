package net.tudelft.hi.e.computation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.tudelft.hi.e.data.NullValue;
import net.tudelft.hi.e.data.Record;
import net.tudelft.hi.e.data.RecordComparator;
import net.tudelft.hi.e.data.Table;

/**
 * ConnectionOperation class providing an Operation to merge tables using a user-defined connection
 * argument.
 */
public class ConnectionOperation extends Operation {

  Table otherTable;

  String columnName;

  String otherColumnName;

  /**
   * Connection will merge inputDataset and otherTable. The inputcolumnName and otherColumnName will
   * be merged into the same column in the result, with column name inputcolumnName. If inputDataset
   * and otherTable contain columns with the same name, they will be merged in the result. If one
   * table has columns the other table has not, the column will be added to the table with a
   * NullValue, before merging into the result.
   *
   * @param inputDataset The first table to merge.
   * @param otherTable The second table to merge.
   * @param inputcolumnName The column name in inputDataset to merge on.
   * @param otherColumnName The column name in otherTable to merge on.
   */
  public ConnectionOperation(final Table inputDataset, final Table otherTable,
      final String inputcolumnName, final String otherColumnName) {
    super(inputDataset);
    setOperationParameters(otherTable, inputcolumnName, otherColumnName);
  }

  @Override
  public void resetData(Table inputData) {
    this.inputData = inputData;
    this.resultData = new Table(inputData);
    this.resultData.clear();
  }

  @Override
  public boolean execute() {

    if (!executePreConditions()) {
      return false;
    }
    if (executeWithEmptyTable()) {
      return true;
    }


    // If one table has columns that the other table does not, they will be
    // added with a null value.
    final List<String> t1temp = new ArrayList<String>(inputData.get(0).keySet());
    final List<String> t1columns = new ArrayList<String>(inputData.get(0).keySet());
    final List<String> t2columns = new ArrayList<String>(otherTable.get(0).keySet());

    if (!t1temp.remove(columnName) || !t1columns.remove(columnName)
        || !t2columns.remove(otherColumnName)) {
      return false;
    }

    t1columns.removeAll(t2columns);
    t2columns.removeAll(t1temp);

    executeFillRecords(inputData, t2columns, false);
    executeFillRecords(otherTable, t1columns, true);

    // merge the 2 tables
    resultData.addAll(inputData);
    resultData.addAll(otherTable);

    // sort on the chosen column
    Collections.sort(resultData, new RecordComparator(this.columnName));

    return true;
  }

  private boolean executePreConditions() {
    return !(!this.operationParametersSet || inputData == null || otherTable == null);
  }

  private boolean executeWithEmptyTable() {
    if (inputData.isEmpty() || otherTable.isEmpty()) {
      resultData.addAll(otherTable);
      resultData.addAll(inputData);
      return true;
    } else {
      return false;
    }
  }

  private boolean executeFillRecords(List<Record> recordList, List<String> columnList,
      boolean rename) {
    for (final Record record : recordList) {
      if (rename) {
        record.rename(otherColumnName, columnName);
      }
      for (final String column : columnList) {
        record.put(column, new NullValue());
      }
    }
    return true;
  }

  @Override
  public Table getResult() {
    return this.resultData;
  }

  /**
   * Set operation parameters.
   *
   * @param otherTable The other table object to merge.
   * @param columnName The column name in the input table.
   * @param otherColumnName The column name in the other table.
   * @return Returns true if the all parameters are set correctly.
   */
  public boolean setOperationParameters(final Table otherTable, final String columnName,
      final String otherColumnName) {
    if (otherTable != null && columnName != null && otherColumnName != null) {
      this.otherTable = otherTable;
      this.columnName = columnName;
      this.otherColumnName = otherColumnName;
      this.operationParametersSet = true;
    }

    return operationParametersSet;
  }

  public boolean isOperationParametersSet() {
    return operationParametersSet;
  }

}
