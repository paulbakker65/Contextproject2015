package net.tudelft.hi.e.computation;

import java.util.Objects;
import net.tudelft.hi.e.data.Record;
import net.tudelft.hi.e.data.Table;

/**
 * Multiple occurrences of a record from the same file.
 */
public class MultipleConditionPattern extends Pattern {
  private final RecordCondition condition;

  /**
   * Constructor which creates the pattern without a next pattern.
   *
   * @param condition
   *        the condition for the pattern.
   */
  public MultipleConditionPattern(final RecordCondition condition) {
    super();
    this.condition = condition;
  }

  /**
   * Constructor which creates the pattern without a next pattern.
   *
   * @param tableName
   *        the name of the original table.
   */
  public MultipleConditionPattern(final String tableName) {
    super();
    this.condition = new RecordOccurrenceCondition(tableName);
  }

  /**
   * Constructor which creates the pattern without a next pattern.
   *
   * @param condition
   *        the condition for the pattern.
   * @param pattern
   *        the pattern to use as next pattern.
   */
  public MultipleConditionPattern(final RecordCondition condition, final Pattern pattern) {
    super(pattern);
    this.condition = condition;
  }

  /**
   * Constructor which creates the pattern with a next pattern.
   *
   * @param tableName
   *        the name of the original table.
   * @param pattern
   *        the pattern to use as next pattern.
   */
  public MultipleConditionPattern(final String tableName, final Pattern pattern) {
    super(pattern);
    this.condition = new RecordOccurrenceCondition(tableName);
  }

  /**
   * The method checks if the pattern is recognized.
   * @return true if the pattern is found, false if it isn't.
   */
  @Override
  public boolean findPattern(final Table table, final int fromIndex,
          final Table records) {
    if (!findPatternPreConditions(table, fromIndex, records)) {
      return false;
    }

    int index = fromIndex;

    final Record current = table.get(index);
    index++;
    Record next = table.get(index);
    index++;

    // Check if the current and next have the same column with values.
    if (matches(current) && matches(next)) {
      records.add(current);

      while (index < table.size() && matches(next)) {
        records.add(next);
        next = table.get(index);
        index++;
      }
      if (!matches(next)) {
        index--;
      }
      // Call the next pattern to continue the search.
      return nextPattern.findPattern(table, index, records);
    }
    return false;
  }

  private boolean findPatternPreConditions(final Table table,
          final int fromIndex, final Table records) {
    if (fromIndex >= table.size() - 1) {
      return false;
    }
    return !(records.isEmpty() && fromIndex > 0 && matches(table.get(fromIndex
            - 1)));
  }

  private boolean matches(Record record) {
    return condition.matches(record);
  }

  private RecordCondition getCondition() {
    return condition;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 47 * hash + Objects.hashCode(this.condition);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final MultipleConditionPattern other = (MultipleConditionPattern) obj;
    return Objects.equals(this.getCondition(), other.getCondition());
  }

}
