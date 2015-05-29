package operations.patterns;

import table.Record;
import table.Table;

/**
 * Multiple occurrences of a record from the same file.
 * 
 * @author paulbakker
 *
 */
public class MultipleOccurrencePattern extends Pattern {

  /**
   * Column name on which to detect the pattern.
   */
  private final String colName;

  /**
   * Constructor which creates the pattern without a next pattern.
   * 
   * @param col The column name on which to create a pattern.
   */
  public MultipleOccurrencePattern(final String col) {
    super();
    this.colName = col;
  }

  /**
   * Constructor which creates the pattern with a next pattern.
   * 
   * @param col The column name on which to create a pattern.
   * @param pattern The pattern to use as next pattern.
   */
  public MultipleOccurrencePattern(final String col, final Pattern pattern) {
    super(pattern);
    this.colName = col;
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
    if (records.isEmpty() && fromIndex > 0 && !table.get(fromIndex - 1).get(colName).isNull()) {
      return false;
    }

    final Record current = table.get(fromIndex++);
    Record next = table.get(fromIndex++);

    // Check if the current and next have the same column with values.
    if (!current.get(colName).isNull() && !next.get(colName).isNull()) {
      records.add(current);

      while (fromIndex < table.size() && !next.get(colName).isNull()) {
        records.add(next);
        next = table.get(fromIndex++);
      }
      // If a record from another file is found, decrease the index.
      if (next.get(colName).isNull()) {
        fromIndex--;
      }
      // Call the next pattern to continue the search.
      return nextPattern.findPattern(table, fromIndex, records);
    }
    return false;
  }
}
