package net.tudelft.hi.e.computation;

import java.util.Calendar;
import java.util.GregorianCalendar;

import net.tudelft.hi.e.data.DateValue;
import net.tudelft.hi.e.data.Value;

/**
 * Chunks on each day of the calendar.
 */
public class DayCondition extends ChunkCondition {
  private long beginTime;

  /**
   * Creates a DayCondition.
   * 
   * @param maxNumberOfDifferences 
   *        the maximum number of differences in days.
   */
  public DayCondition(int maxNumberOfDifferences) {
    super(maxNumberOfDifferences);
    this.beginTime = 0;
  }

  @Override
  public boolean matches(final Value recordValue) {
    GregorianCalendar checkCalendar =
        (GregorianCalendar) ((DateValue) recordValue).getValue().clone();
    checkCalendar.set(Calendar.HOUR_OF_DAY, 0);
    checkCalendar.set(Calendar.MINUTE, 0);
    checkCalendar.set(Calendar.MILLISECOND, 0);

    long curTime = checkCalendar.getTimeInMillis();

    if (beginTime == 0) {
      beginTime = curTime;
      return true;
    }

    long diffTime = curTime - beginTime;
    double diffDays = diffTime / (1000 * 60 * 60 * 24);

    if (Math.floor(diffDays) > maxNumberOfDifferences) {
      beginTime = curTime;
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + (int) (beginTime ^ (beginTime >>> 32));
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (!super.equals(obj)) {
      return false;
    }
    DayCondition other = (DayCondition) obj;
    return beginTime == other.beginTime;
  }
}
