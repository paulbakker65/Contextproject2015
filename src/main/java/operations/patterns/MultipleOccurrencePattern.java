package operations.patterns;

import operations.patterns.condition.RecordOccurrenceCondition;

import table.Record;
import table.Table;

/**
 * Multiple occurrences of a record from the same file.
 */
public class MultipleOccurrencePattern extends Pattern {
  private final RecordOccurrenceCondition condition;

  /**
   * Constructor which creates the pattern without a next pattern.
   * 
   * @param tableName
   *        the name of the original table.
   */
  public MultipleOccurrencePattern(final String tableName) {
    super();
    this.condition = new RecordOccurrenceCondition(tableName);
  }

  /**
   * Constructor which creates the pattern with a next pattern.
   * 
   * @param tableName
   *        the name of the original table.
   * @param pattern
   *        the pattern to use as next pattern.
   */
  public MultipleOccurrencePattern(final String tableName, final Pattern pattern) {
    super(pattern);
    this.condition = new RecordOccurrenceCondition(tableName);
  }

  /**
   * The method checks if the pattern is recognized.
   */
  @Override
  public boolean findPattern(final Table table, int fromIndex, final Table records) {
    // The table must contain at least 2 records.
    if (fromIndex >= table.size() - 1) {
      return false;
    }
    // Check whether the previous record differs.
    if (records.isEmpty() && fromIndex > 0 && matches(table.get(fromIndex - 1))) {
      return false;
    }

    final Record current = table.get(fromIndex++);
    Record next = table.get(fromIndex++);

    // Check if the current and next have the same column with values.
    if (matches(current) && matches(next)) {
      records.add(current);

      while (fromIndex < table.size() && matches(next)) {
        records.add(next);
        next = table.get(fromIndex++);
      }
      // If a record from another file is found, decrease the index.
      if (!matches(next)) {
        fromIndex--;
      }
      // Call the next pattern to continue the search.
      return nextPattern.findPattern(table, fromIndex, records);
    }
    return false;
  }
  
  private boolean matches(Record record) {
    return condition.matches(record);
  }
}
