package net.tudelft.hi.e.computation;

import net.tudelft.hi.e.common.enums.ChunkType;
import net.tudelft.hi.e.data.Chunk;
import net.tudelft.hi.e.data.Record;
import net.tudelft.hi.e.data.RecordComparator;
import net.tudelft.hi.e.data.Table;

import java.util.Collections;

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
   * Number of times the current type may occur.
   */
  int numberOfTypes;

  /**
   * Constructor that calls the Operation class to set the inputData and create an the colums for
   * the new table.
   *
   * @param input Table containing the input data.
   */
  public ChunkingOperation(final Table input, final String columnName, final ChunkType cce,
      int numberOfTypes) {
    super(input);
    setOperationParameters(columnName, cce, numberOfTypes);
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
    if (!operationParametersSet || inputData.isEmpty()) {
      return false;
    }

    Collections.sort(resultData, rc);
    int index = 0;
    Chunk chunk = new Chunk(index, "Chunk " + index);

    for (final Record record : resultData) {
      if (cond.matches(record.get(columnName))) {
        chunk.add(record);
      } else {
        resultData.addChunk(chunk);
        chunk = new Chunk(++index, "Chunk " + index);
        chunk.add(record);
      }
    }
    resultData.addChunk(chunk);

    return true;
  }

  /**
   * Returns a chunkcondition, which returns true if no new chunk is needed.
   *
   * @param cce , on what to chunk
   */
  public ChunkCondition getCondition(final ChunkType cce, int numberOfTypes) {
    switch (cce) {
      case DAY:
        return new DayCondition(numberOfTypes - 1);
      case MONTH:
        return new MonthCondition(numberOfTypes - 1);
      case YEAR:
        return new YearCondition(numberOfTypes - 1);
      case PHASE:
        return new PhaseCondition();
      default:
        return null;
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
  public boolean setOperationParameters(final String columnName, final ChunkType cce,
      int numberOfTypes) {
    if (columnName != null && cce != null && numberOfTypes > 0) {
      this.columnName = columnName;
      this.cond = getCondition(cce, numberOfTypes);
      this.rc = new RecordComparator(columnName);
      this.numberOfTypes = numberOfTypes;

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
