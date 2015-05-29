package operations.lsa;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

import table.Record;
import table.RecordComparator;
import table.value.DateValue;

/**
 * Groups days on basis of the day in a date column.
 */
public class DayGrouper extends Grouper {

  private String datecol;

  private GregorianCalendar curdate;
  private List<List<Record>> days;
  private List<Record> buffer;

  public DayGrouper(String datecol) {
    this.datecol = datecol;
  }

  @Override
  public List<List<Record>> group(List<Record> recs) {
    Collections.sort(recs, new RecordComparator(datecol));

    days = new ArrayList<List<Record>>();
    curdate = getDate(recs.get(0));
    buffer = new ArrayList<Record>();
    for (Record r : recs) {
      // Sync day with the record
      while (!sameDay(curdate, getDate(r))) {
        nextDay();
      }
      buffer.add(r);
    }
    nextDay();
    return days;
  }

  public void nextDay() {
    days.add(buffer);
    buffer = new ArrayList<Record>();
    curdate.add(GregorianCalendar.DAY_OF_MONTH, 1);
  }

  public static boolean sameDay(Calendar cal1, Calendar cal2) {
    return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
        && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)
        && cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
  }

  public GregorianCalendar getDate(Record r) {
    return ((DateValue) r.get(datecol)).getValue();

  }

}
