package operations.patterns.condition;

import table.Record;

/**
 * Class for representing the condition that a record is from a certain file.
 */
public class RecordOccurrenceCondition implements RecordCondition {
  private final String tableName;

  /**
   * Creates a RecordOccurrenceCondition.
   * @param tableName
   *        the name of the original table.
   */
  public RecordOccurrenceCondition(final String tableName) {
    this.tableName = tableName;
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
    return tableName.equals(other.tableName);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + tableName.hashCode();
    return result;
  }

  @Override
  public boolean matches(final Record record) {
    return record.getTableName().equals(tableName);
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "RecordOccurrenceCondition [tableName=" + tableName + "]";
  }
}
