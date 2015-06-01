package operations.patterns.condition;

import table.Record;
import table.value.Value;

/**
 * Class for representing the condition that a record equals a certain Value.
 */
public class RecordEqualsValueCondition implements RecordCondition {
  private final String column;
  private final Value toCompare;

  public RecordEqualsValueCondition(final String column, final Value toCompare) {
    this.column = column;
    this.toCompare = toCompare;
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
    final RecordEqualsValueCondition other = (RecordEqualsValueCondition) obj;
    if (!column.equals(other.column)) {
      return false;
    }
    return toCompare.equals(other.toCompare);
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
  public boolean matches(final Record record) {
    return record.get(column).equals(toCompare);
  }

}
