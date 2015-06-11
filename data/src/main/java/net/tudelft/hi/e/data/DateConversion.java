package net.tudelft.hi.e.data;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * A class that contains Date utilities.
 */
public class DateConversion {

  /**
   * Default hidden constructor because you cannot instantiate this object.
   */
  private DateConversion() {
  }

  /**
   * Converts Excel Serial Date to a Java Date object.
   *
   * @param excelSerial Excel Style Date in SerialDate format. Double, date and time.
   * @return Date object in java.util.Date style
   */
  public static Date fromExcelSerialToDate(final double excelSerial) {
    final int nDays = (int) excelSerial;
    final double partialDays = excelSerial - nDays;
    final int nMillis = (int) (partialDays * 24 * 60 * 60 * 1000);

    final GregorianCalendar c = new GregorianCalendar();
    c.set(1900, Calendar.JANUARY, 1, 0, 0, 0);

    c.add(Calendar.DATE, nDays);
    c.add(Calendar.MILLISECOND, nMillis);

    // Fix Excel's leap bugs
    int leapDays = 0;
    final int year = c.get(Calendar.YEAR);
    if (excelSerial >= 60) {
      leapDays += 1;
    }
    if (year >= 2000) {
      leapDays += year / 1000 - 1;
    }
    c.add(Calendar.DATE, -leapDays);

    c.set(Calendar.MILLISECOND, 0);
    return c.getTime();
  }

}
