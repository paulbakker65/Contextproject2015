package operations.patterns.condition;

import table.Record;

/**
 * Class for representing the condition that a colName exists.
 */
public class RecordOccurrenceCondition implements RecordCondition {
  private String colName;  

  public RecordOccurrenceCondition(String colName) {
    this.colName = colName;
  }
  
  @Override
  public boolean matches(Record record) {
    return !record.get(colName).isNull();
  }

}
