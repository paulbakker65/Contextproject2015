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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + column.hashCode();
    result = prime * result + toCompare.hashCode();
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    RecordEqualsValueCondition other = (RecordEqualsValueCondition) obj;
    if (!column.equals(other.column)) {
      return false;
    }
    return toCompare.equals(other.toCompare);
  }
  
  

}
