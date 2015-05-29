package operations.patterns.condition;

import table.Record;

/**
 * Class for representing the condition that a colName exists.
 */
public class RecordOccurrenceCondition implements RecordCondition {
  private final String colName;

  public RecordOccurrenceCondition(final String colName) {
    this.colName = colName;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final RecordOccurrenceCondition other = (RecordOccurrenceCondition) obj;
    return colName.equals(other.colName);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + colName.hashCode();
    return result;
  }

  @Override
  public boolean matches(final Record record) {
    return !record.get(colName).isNull();
  }

}
