package operations.patterns;

import operations.patterns.condition.RecordOccurrenceCondition;

/**
 * Pattern where a record came from a certain file.
 */
public class SingleOccurrencePattern extends SingleConditionPattern {
  /**
   * Constructor which creates the pattern without a next pattern.   * 
   * @param column
   *        the column name that always exists.
   */
  public SingleOccurrencePattern(String column) {
    super(new RecordOccurrenceCondition(column));
  }

  /**
   * Constructor which creates the pattern with a next pattern.   * 
   * @param column
   *        the column name that always exists.
   * @param nextPattern
   *        the next pattern to link to.
   */
  public SingleOccurrencePattern(String column, Pattern nextPattern) {
    super(new RecordOccurrenceCondition(column), nextPattern);
  }
}
