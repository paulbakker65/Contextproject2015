package operations.patterns;

import operations.patterns.condition.RecordOccurrenceCondition;

/**
 * Pattern where a record came from a certain file.
 */
public class SingleOccurrencePattern extends SingleConditionPattern {
  /**
   * Constructor which creates the pattern without a next pattern. *
   * 
   * @param tableName
   *        the name of the original table.
   */
  public SingleOccurrencePattern(final String tableName) {
    super(new RecordOccurrenceCondition(tableName));
  }

  /**
   * Constructor which creates the pattern with a next pattern. *
   * 
   * @param tableName
   *        the name of the original table.
   * @param nextPattern 
   *        the next pattern to link to.
   */
  public SingleOccurrencePattern(final String tableName, final Pattern nextPattern) {
    super(new RecordOccurrenceCondition(tableName), nextPattern);
  }
}
