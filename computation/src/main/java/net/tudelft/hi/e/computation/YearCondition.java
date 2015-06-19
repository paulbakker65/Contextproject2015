package net.tudelft.hi.e.computation;

import net.tudelft.hi.e.data.DateValue;
import net.tudelft.hi.e.data.Value;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Chunks on each year.
 */
public class YearCondition extends ChunkCondition {
  private int beginYear;

  /**
   * Creates a YearCondition.
   * 
   * @param maxNumberOfDifferences 
   *        the maximum number of differences in years.
   */
  public YearCondition(int maxNumberOfDifferences) {
    super(maxNumberOfDifferences);
  }

  @Override
  public boolean matches(final Value recordValue) {
    GregorianCalendar recordDate = ((DateValue) recordValue).getValue();
    int curYear = recordDate.get(Calendar.YEAR);

    if (beginYear == 0) {
      beginYear = curYear;
      return true;
    }

    if (curYear - beginYear > maxNumberOfDifferences) {
      beginYear = curYear;
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + beginYear;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (!super.equals(obj)) {
      return false;
    }
    YearCondition other = (YearCondition) obj;
    return beginYear == other.beginYear;
  }
}
