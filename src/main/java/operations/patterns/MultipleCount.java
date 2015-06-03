package operations.patterns;

import operations.patterns.condition.RecordCondition;

/**
 * Class for specifying a multiple pattern.
 */
public class MultipleCount implements Count {

  @Override
  public Pattern createPattern(RecordCondition condition) {
    return new MultipleConditionPattern(condition);
  } 
  
  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null) {
      return false;
    }
    return (other instanceof MultipleCount);
  }
}
