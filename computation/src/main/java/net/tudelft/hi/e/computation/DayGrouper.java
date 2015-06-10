package net.tudelft.hi.e.computation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import net.tudelft.hi.e.data.DateValue;
import net.tudelft.hi.e.data.Record;
import net.tudelft.hi.e.data.RecordComparator;

/**
 * Groups days on basis of the day in a date column.
 */
public class DayGrouper extends Grouper {

  /**
   * Test if two calendars are on the same day.
   *
   * @param cal1 The first calendar.
   * @param cal2 The second calendar.
   * @return True if the two calendars are on the same day. Else False.
   */
  public static boolean sameDay(final Calendar cal1, final Calendar cal2) {
    return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
        && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)
        && cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
  }

  private final String datecol;
  private GregorianCalendar curdate;
  private List<List<Record>> days;

  private List<Record> buffer;

  public DayGrouper(final String datecol) {
    this.datecol = datecol;
  }

  public GregorianCalendar getDate(final Record row) {
    return ((DateValue) row.get(datecol)).getValue();

  }

  @Override
  public List<List<Record>> group(final List<Record> recs) {
    Collections.sort(recs, new RecordComparator(datecol));

    days = new ArrayList<List<Record>>();
    curdate = getDate(recs.get(0));
    buffer = new ArrayList<Record>();
    for (final Record r : recs) {
      // Sync day with the record
      while (!sameDay(curdate, getDate(r))) {
        nextDay();
      }
      buffer.add(r);
    }
    nextDay();
    return days;
  }

  /**
   * Go to the next day.
   */
  public void nextDay() {
    days.add(buffer);
    buffer = new ArrayList<Record>();
    curdate.add(Calendar.DAY_OF_MONTH, 1);
  }

}
