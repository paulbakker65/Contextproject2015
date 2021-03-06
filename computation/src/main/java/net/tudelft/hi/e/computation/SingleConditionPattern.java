package net.tudelft.hi.e.computation;

import net.tudelft.hi.e.data.Record;
import net.tudelft.hi.e.data.Table;

/**
 * Abstract class for a pattern where a record should satisfy a certain
 * condition.
 */
public class SingleConditionPattern extends Pattern {

  /**
   * The condition that should hold.
   */
  private final RecordCondition condition;

  /**
   * Constructor with only the condition.
   *
   * @param condition the condition a record should satisfy.
   */
  public SingleConditionPattern(final RecordCondition condition) {
    super();
    this.condition = condition;
  }

  /**
   * Constructor with the condition and the next pattern.
   *
   * @param condition the condition a record should satisfy.
   * @param nextPattern the next pattern to link to.
   */
  public SingleConditionPattern(final RecordCondition condition,
      final Pattern nextPattern) {
    super(nextPattern);
    this.condition = condition;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (!super.equals(obj)) {
      return false;
    }
    final SingleConditionPattern other = (SingleConditionPattern) obj;
    return condition.equals(other.condition);
  }

  /**
   * Checks if the pattern is found.
   */
  @Override
  public boolean findPattern(final Table table, int fromIndex,
      final Table records) {
    if (!findPatternPreConditions(table, fromIndex, records)) {
      return false;
    }
    int count = fromIndex;
    final Record record = table.get(count++);
    if (matches(record)) {
      records.add(record);

      return findPatternFindNextPattern(table, count, records);
    } else {
      return false;
    }
  }

  private boolean findPatternFindNextPattern(final Table table,
          final int fromIndex, final Table records) {
    if (nextPattern.isNextLastPattern() && fromIndex < table.size()
            && matches(table.get(fromIndex))) {
      return false;
    }
    return nextPattern.findPattern(table, fromIndex, records);
  }

  private boolean findPatternPreConditions(final Table table,
          final int fromIndex, final Table records) {
    if (fromIndex >= table.size()) {
      return false;
    }
    if (records.isEmpty() && fromIndex > 0 && matches(table.get(fromIndex - 1))) {
      return false;
    }
    return true;
  }

  /**
   * Returns the condition.
   *
   * @return the condition.
   */
  public RecordCondition getCondition() {
    return condition;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + condition.hashCode();
    return result;
  }

  private boolean matches(final Record record) {
    return condition.matches(record);
  }

  @Override
  public boolean isNextLastPattern() {
    return false;
  }

}
