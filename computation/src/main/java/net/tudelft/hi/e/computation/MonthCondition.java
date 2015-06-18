package net.tudelft.hi.e.computation;

import java.util.Calendar;
import java.util.GregorianCalendar;
import net.tudelft.hi.e.data.DateValue;
import net.tudelft.hi.e.data.Value;

/**
 * Chunks on each month of the calendar.
 */
public class MonthCondition extends ChunkCondition {
	private int beginMonth;

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

}
