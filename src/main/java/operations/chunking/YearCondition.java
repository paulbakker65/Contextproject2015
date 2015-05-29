package operations.chunking;

import table.value.DateValue;
import table.value.Value;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Chunks on each year.
 */
public class YearCondition extends ChunkCondition {

  @Override
  public boolean matches(final Value recordValue, final Value check) {
    final DateValue current = (DateValue) check;
    final GregorianCalendar currentDate = current.getValue();
    final DateValue record = (DateValue) recordValue;
    final GregorianCalendar recordDate = record.getValue();
    if (recordDate.get(Calendar.YEAR) == currentDate.get(Calendar.YEAR)) {
      return true;
    }
    return false;
  }

}
