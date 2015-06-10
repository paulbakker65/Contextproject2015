package net.tudelft.hi.e.computation;

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

  @Override
  public int hashCode() {
    return 89 * super.hashCode();
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "MultipleCount []";
  }
}
