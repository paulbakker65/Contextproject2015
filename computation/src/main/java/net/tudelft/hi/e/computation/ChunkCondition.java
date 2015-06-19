package net.tudelft.hi.e.computation;

import net.tudelft.hi.e.data.Value;

/**
 * The abstract class which we use to determine if a new chunk has to be created. The matches method
 * returns true if no new chunk is needed. *
 */
public abstract class ChunkCondition {
  protected int maxNumberOfDifferences;

  /**
   * Creates a ChunkCondition.
   * 
   * @param maxNumberOfDifferences 
   *        the maximum number of differences in chunk type.
   */
  public ChunkCondition(int maxNumberOfDifferences) {
    this.maxNumberOfDifferences = maxNumberOfDifferences;
  }

  /**
   * Check whether the given values should be in the same chunk.
   * 
   * @param recordValue
   *        the given value.
   * @return whether the given values should be in the same chunk.
   * 
   */
  public abstract boolean matches(Value recordValue);
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + maxNumberOfDifferences;
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
    ChunkCondition other = (ChunkCondition) obj;
    return maxNumberOfDifferences == other.maxNumberOfDifferences;
  }

}
