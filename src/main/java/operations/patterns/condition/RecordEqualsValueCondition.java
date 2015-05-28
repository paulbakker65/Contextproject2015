package operations.patterns.condition;

import table.Record;
import table.value.Value;

/**
 * Class for representing the condition that a record equals a certain Value.
 */
public class RecordEqualsValueCondition implements RecordCondition {
  private String column;
  private Value toCompare;

  public RecordEqualsValueCondition(String column, Value toCompare) {
    this.column = column;
    this.toCompare = toCompare;
  }
  
  @Override
  public boolean matches(Record record) {
    return record.get(column).equals(toCompare);
  }

}
