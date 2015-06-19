package net.tudelft.hi.e.computation;

import net.tudelft.hi.e.data.DateValue;
import net.tudelft.hi.e.data.Value;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Chunks on each month of the calendar.
 */
public class MonthCondition extends ChunkCondition {
  private int beginMonth;

  /**
   * Creates a MonthCondition.
   * 
   * @param maxNumberOfDifferences 
   *        the maximum number of differences in month.
   */
  public MonthCondition(int maxNumberOfDifferences) {
    super(maxNumberOfDifferences);
  }

  @Override
  public boolean matches(final Value recordValue) {
    GregorianCalendar recordDate = ((DateValue) recordValue).getValue();
    int curYear = recordDate.get(Calendar.YEAR);
    int curMonth = recordDate.get(Calendar.MONTH) + curYear * 12;

    if (beginMonth == 0) {
      beginMonth = curMonth;
      return true;
    }

    if (curMonth - beginMonth > maxNumberOfDifferences) {
      beginMonth = curMonth;
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + beginMonth;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (!super.equals(obj)) {
      return false;
    }
    MonthCondition other = (MonthCondition) obj;
    return beginMonth == other.beginMonth;
  }

}
