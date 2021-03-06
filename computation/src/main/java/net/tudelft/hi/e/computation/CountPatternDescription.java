package net.tudelft.hi.e.computation;


/**
 * Class for describing how a normal pattern looks like.
 */
public class CountPatternDescription implements PatternDescription {
  private Count count;
  private RecordCondition condition;

  /**
   * Constructs a CountPatternDescription.
   * @param count
   *        the number of occurrences of the pattern.
   * @param condition
   *        the {@link RecordCondition} for the pattern.
   */
  public CountPatternDescription(Count count, RecordCondition condition) {
    this.count = count;
    this.condition = condition;
  }
  
  @Override
  public Pattern createPattern() {
    return count.createPattern(condition);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + condition.hashCode();
    result = prime * result + count.hashCode();
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    CountPatternDescription other = (CountPatternDescription) obj;
    if (!condition.equals(other.condition)) {
      return false;
    }
    return count.equals(other.count);
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "CountPatternDescription [count=" + count + ", condition=" + condition
        + "]";
  }  
}
