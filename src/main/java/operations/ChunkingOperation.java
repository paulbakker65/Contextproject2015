package operations;

import input.ChunkColumn;
import input.Column;
import input.Settings;

import java.util.ArrayList;
import java.util.Collections;

import operations.chunking.ChunkCondition;
import operations.chunking.DayCondition;
import operations.chunking.MonthCondition;
import operations.chunking.PatientCondition;
import operations.chunking.YearCondition;
import parsers.ChunkValue;
import parsers.StringValue;
import parsers.Value;
import table.Record;
import table.RecordComparator;
import table.Table;

public class ChunkingOperation extends Operation {

  /**
   * the column name relevant for this operation
   */
  String columnName;
  /**
   * condition
   */
  ChunkCondition cond;
  /**
   * result dataset, after operation
   */
  Table resultData;
  /**
   * The columns created after chunking.
   */
  ArrayList<Column> cols = new ArrayList<Column>();
  /**
   * The settings of the inputData. We will add an extra column for the Exporter.class
   */
  Settings settings;
  /**
   * The comparator needed for the chunking.
   */
  RecordComparator rc;

  /**
   * The enum on which we want to chunk.
   * 
   * @author paulbakker
   *
   */
  public enum ChunkComparatorEnum {
    DAY, MONTH, YEAR, WEEKEND, PATIENT, TEST
  }

  /**
   * Constructor that calls the Operation class to set the inputData and create an the colums for
   * the new table.
   * 
   * @param input
   */
  public ChunkingOperation(Table input) {
    super(input);
    this.resultData = new Table();
    cols.add(new ChunkColumn("Chunk"));
  }

  /**
   * Setting the parameters for the operation.
   * 
   * @param columnName
   * @param cce
   * @param settings
   * @return
   */
  public boolean setOperationParameters(String columnName, ChunkComparatorEnum cce,
      Settings settings) {
    this.columnName = columnName;
    this.cond = getCondition(cce);

    this.settings = settings;
    this.rc = new RecordComparator(columnName);
    this.operationParametersSet = true;
    return this.operationParametersSet;
  }

  /**
   * Returns the toString of the result data.
   */
  @Override
  public String toString() {
    return resultData.toString();
  }

  /**
   * Get the result data for the next calculation.
   */
  @Override
  public Table getResult() {
    return resultData;
  }

  /**
   * This method return the data in such a way that it can be used in the Exporter.class
   * 
   * @return
   */
  public Table getOutput() {
    Table output = new Table();
    for (Record r : resultData) {
      ChunkValue temp = (ChunkValue) r.get("Chunk");
      for (Record r2 : temp.getTable()) {
        r2.put("Chunk", new StringValue(temp.getLabel()));
      }
      output.addAll(temp.getTable());
    }
    settings.addColumn(new ChunkColumn("Chunk"));
    return output;
  }

  /**
   * We create the chunk with new index and label if the ChunkCondition returns false. We add the a
   * record to the chunk if chunkingOperation returns true.
   */
  @Override
  public boolean execute() {
    if (operationParametersSet) {

      Collections.sort(inputData, rc);
      int index = 0;
      String label = "Chunk " + Integer.toString(index);
      ChunkValue chunk = new ChunkValue(index, label, new Table());
      Value check = inputData.get(0).get(columnName);
      for (Record r : inputData) {
        if (cond.matches(r.get(columnName), check)) {
          chunk.addRecord(r);
        } else {
          Value[] values = { chunk };
          resultData.add(new Record(cols, values));
          index++;
          label = "Chunk " + Integer.toString(index);
          chunk = new ChunkValue(index, label, new Table());
          chunk.addRecord(r);
          check = r.get(columnName);

        }

      }
      Value[] values = { chunk };
      resultData.add(new Record(cols, values));

      return true;
    }
    return false;
  }

  /**
   * Returns a chunkcondition, which returns true if no new chunk is needed.
   * 
   * @param cce
   *          , on what to chunk
   */
  public ChunkCondition getCondition(ChunkComparatorEnum cce) {
    switch (cce) {
    case DAY: {
      return new DayCondition();
    }
    case MONTH: {
      return new MonthCondition();
    }
    case YEAR: {

      return new YearCondition();
    }
    case PATIENT: {

      return new PatientCondition();
    }
    default:
      return null;
    }

  }

}
