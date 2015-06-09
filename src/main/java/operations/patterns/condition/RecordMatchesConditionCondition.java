package operations.patterns.condition;

import enums.CompareOperator;
import scriptlang.extra.Condition;
import table.Record;
import table.value.Value;

/**
 * Class for representing the condition that a record equals a certain Value.
 */
public class RecordMatchesConditionCondition implements RecordCondition {
  private final String column;
  private final Condition condition;

  public RecordMatchesConditionCondition(String column, Condition condition) {
    this.column = column;
    this.condition = condition;
  }

  public RecordMatchesConditionCondition(final String column, final Value toCompare) {
    this.column = column;
    this.condition = new Condition(CompareOperator.EQ, toCompare);
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
    final RecordMatchesConditionCondition other = (RecordMatchesConditionCondition) obj;
    if (!column.equals(other.column)) {
      return false;
    }
    return condition.equals(other.condition);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + column.hashCode();
    result = prime * result + condition.hashCode();
    return result;
  }

  @Override
  public boolean matches(final Record record) {
    return condition.matches(record.get(column));
  }

  @Override
  public String toString() {
    return "\"" + column + "\" " + condition.toString();
  }
}
