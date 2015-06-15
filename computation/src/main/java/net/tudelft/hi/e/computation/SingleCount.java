package net.tudelft.hi.e.computation;

/**
 * Class for specifying the number of single patterns.
 */
public class SingleCount implements Count {
  private int count;

  /**
   * Constructs a new SingleCount object.
   * @param count
   *        the number of patterns.
   */
  public SingleCount(int count) {
    this.count = count;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + count;
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
    SingleCount other = (SingleCount) obj;
    if (count != other.count) {
      return false;
    }
    return true;
  }

  @Override
  public Pattern createPattern(RecordCondition condition) {
    return createPattern(condition, count);
  }

  private Pattern createPattern(RecordCondition condition, int counter) {
    if (counter == 0) {
      return new NullPattern();
    }
    return new SingleConditionPattern(condition, createPattern(condition, --counter));
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "SingleCount [count=" + count + "]";
  }
}
