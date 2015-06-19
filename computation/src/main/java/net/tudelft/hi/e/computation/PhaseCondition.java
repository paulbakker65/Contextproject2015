package net.tudelft.hi.e.computation;

import net.tudelft.hi.e.data.DateValue;
import net.tudelft.hi.e.data.Value;

/**
 * Chunk condition which specifically chunks on admire phases.
 */
public class PhaseCondition extends ChunkCondition {
  /**
   * Array for specifying the first weeks of new phases.
   */
  private static final int[] newPhaseWeeks = new int[] {1, 5, 10, 16};
  private long beginTime = 0;
  int chunkIndex = 0;

  /**
   * Creates a PhaseCondition.
   */
  public PhaseCondition() {
    super(0);
  }

  @Override
  public boolean matches(Value recordValue) {
    DateValue currentValue = (DateValue) recordValue;
    if (noNewPhaseComing() || beginDateSet(currentValue)) {
      return true;
    }

    int currentWeek = getCurrentWeekFromBegin(currentValue);
    return !changedChunkIndex(currentWeek);
  }

  private boolean changedChunkIndex(int currentWeek) {
    int currentIndex = 0;

    while (currentIndex < newPhaseWeeks.length && currentWeek >= newPhaseWeeks[currentIndex]) {
      currentIndex++;
    }
    currentIndex--;


    boolean changed = chunkIndex != currentIndex;
    chunkIndex = currentIndex;

    return changed;
  }

  private boolean beginDateSet(DateValue currentValue) {
    if (beginTime == 0) {
      beginTime = currentValue.getValue().getTimeInMillis();
      return true;
    }
    return false;
  }

  private boolean noNewPhaseComing() {
    return chunkIndex >= newPhaseWeeks.length - 1;
  }

  private int getCurrentWeekFromBegin(DateValue currentValue) {
    int daysFromBegin = getDaysFromBegin(currentValue);
    return daysFromBegin / 7 + 1;
  }

  private int getDaysFromBegin(DateValue currentValue) {
    long currentTime = currentValue.getValue().getTimeInMillis();
    long difference = currentTime - beginTime;
    return (int) (difference / (1000 * 60 * 60 * 24));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + (int) (beginTime ^ (beginTime >>> 32));
    result = prime * result + chunkIndex;
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
    PhaseCondition other = (PhaseCondition) obj;
    if (beginTime != other.beginTime) {
      return false;
    }
    return chunkIndex == other.chunkIndex;
  }
}
