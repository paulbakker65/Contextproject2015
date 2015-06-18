package net.tudelft.hi.e.computation;

import java.util.Calendar;
import java.util.GregorianCalendar;

import net.tudelft.hi.e.data.DateValue;
import net.tudelft.hi.e.data.Value;

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

}
