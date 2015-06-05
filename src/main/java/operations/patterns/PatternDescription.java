package operations.patterns;

import operations.patterns.condition.RecordCondition;

/**
 * Class for describing how a pattern looks like.
 */
public class PatternDescription {
  private Count count;
  private RecordCondition condition;
  
  /**
   * Constructs a PatternDescription. 
   * @param count 
   *        the number of occurrences of the pattern.
   * @param condition 
   *        the {@link RecordCondition} for the pattern.
   */
  public PatternDescription(Count count, RecordCondition condition) {
    this.count = count;
    this.condition = condition;
  }
  
  /**
   * Returns the number of occurrences of the pattern.
   * @return the number of occurrences of the pattern.
   */
  public Count getCount() {
    return count;
  }
  
  /**
   * Returns the {@link RecordCondition} for the pattern.
   * @return the {@link RecordCondition} for the pattern.
   */
  public RecordCondition getCondition() {
    return condition;
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
    PatternDescription other = (PatternDescription) obj;
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
    return "PatternDescription [count=" + count + ", condition=" + condition
        + "]";
  }
}
