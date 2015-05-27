package operations;

import java.util.Collections;

import operations.chunking.ChunkCondition;
import operations.chunking.DayCondition;
import operations.chunking.MonthCondition;
import operations.chunking.PatientCondition;
import operations.chunking.YearCondition;
import table.Chunk;
import table.Record;
import table.RecordComparator;
import table.Table;
import table.value.Value;

/**
 * An operation that outputs a Table of ChunkValues. Chunks on the basis of a ChunkCondition
 */
public class ChunkingOperation extends Operation {

  /**
   * the column name relevant for this operation.
   */
  String columnName;
  /**
   * Condition for chunking.
   */
  ChunkCondition cond;
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
  public ChunkingOperation(Table input, String columnName, ChunkComparatorEnum cce) {
    super(input);
    this.resultData = (Table) input.clone();
    setOperationParameters(columnName, cce);
  }

  /**
   * Setting the parameters for the operation.
   * 
   * @param columnName
   * @param cce
   * @param settings
   * @return
   */
  public boolean setOperationParameters(String columnName, ChunkComparatorEnum cce) {
    if (columnName != null && cce != null) {
      this.columnName = columnName;
      this.cond = getCondition(cce);

      this.rc = new RecordComparator(columnName);
      this.operationParametersSet = true;
    }
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
   * We create the chunk with new index and label if the ChunkCondition returns false. We add the a
   * record to the chunk if chunkingOperation returns true.
   */
  @Override
  public boolean execute() {
    if (operationParametersSet) {

      Collections.sort(inputData, rc);
      int index = 0;
      String label = "Chunk " + Integer.toString(index);
      Chunk chunk = new Chunk(index, label);
      Value check = inputData.get(0).get(columnName);
      for (Record r : inputData) {
        if (cond.matches(r.get(columnName), check)) {
          chunk.add(r);
        } else {
          resultData.addChunk(chunk);
          label = "Chunk " + Integer.toString(++index);
          chunk = new Chunk(index, label);
          chunk.add(r);
          check = r.get(columnName);

        }

      }
      resultData.addChunk(chunk);

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
