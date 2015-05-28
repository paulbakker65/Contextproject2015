package operations.patterns;

import operations.patterns.condition.RecordEqualsValueCondition;
import table.value.Value;

/**
 * Pattern where a record column must be equal to a certain value.
 */
public class SingleOccurrenceValuePattern extends SingleConditionPattern {
  /**
   * Constructor which creates the pattern without a next pattern.   * 
   * @param column
   *        the column name.
   * @param value
   *        the value to compare with.
   */
  public SingleOccurrenceValuePattern(String column, Value value) {
    super(new RecordEqualsValueCondition(column, value));
  }

  /**
   * Constructor which creates the pattern with a next pattern.   * 
   * @param column
   *        the column name.
   * @param value
   *        the value to compare with.
   * @param nextPattern
   *        the next pattern to link to.
   */
  public SingleOccurrenceValuePattern(String column, Value value, Pattern nextPattern) {
    super(new RecordEqualsValueCondition(column, value), nextPattern);
  }  
}
