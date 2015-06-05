package operations.patterns;

import operations.patterns.condition.RecordCondition;

/**
 * Interface for specifying the number of patterns to build in 
 * the {@link PatternFactory}.
 */
public interface Count { 
  /**
   * Creates a pattern based on how many patterns are wanted.
   * @param condition 
   *        the {@link RecordCondition} for the pattern.
   * @return the created Pattern.
   */
  Pattern createPattern(RecordCondition condition);
}
