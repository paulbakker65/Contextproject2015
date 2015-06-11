package net.tudelft.hi.e.computation;

import java.util.Collections;
import net.tudelft.hi.e.common.enums.ChunkType;
import static net.tudelft.hi.e.common.enums.ChunkType.MONTH;
import static net.tudelft.hi.e.common.enums.ChunkType.PATIENT;
import static net.tudelft.hi.e.common.enums.ChunkType.YEAR;
import net.tudelft.hi.e.data.Chunk;
import net.tudelft.hi.e.data.Record;
import net.tudelft.hi.e.data.RecordComparator;
import net.tudelft.hi.e.data.Table;
import net.tudelft.hi.e.data.Value;

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
   * Constructor that calls the Operation class to set the inputData and create an the colums for
   * the new table.
   *
   * @param input Table containing the input data.
   */
  public ChunkingOperation(final Table input, final String columnName, final ChunkType cce) {
    super(input);
    setOperationParameters(columnName, cce);
  }

  @Override
  public void resetData(Table inputData) {
    this.inputData = inputData;
    this.resultData = new Table(inputData);
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
      for (final Record r : inputData) {
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
   * @param cce , on what to chunk
   */
  public ChunkCondition getCondition(final ChunkType cce) {
    switch (cce) {
      case DAY: return new DayCondition();
      case MONTH: return new MonthCondition();
      case YEAR: return new YearCondition();
      case PATIENT: return new PatientCondition();
      default: return null;
    }
  }

  /**
   * Get the result data for the next calculation.
   */
  @Override
  public Table getResult() {
    return resultData;
  }

  /**
   * Setting the parameters for the operation.
   */
  public boolean setOperationParameters(final String columnName, final ChunkType cce) {
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

}
