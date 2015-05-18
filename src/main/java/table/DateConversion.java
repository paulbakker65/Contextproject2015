package table;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * A class that contains Date utilities.
 */
public class DateConversion {

  /**
   * Converts Excel Serial Date to a Java Date object.
   * 
   * @param excelSerial
   *          Excel Style Date in SerialDate format. Double, date and time.
   * @return Date object in java.util.Date style
   */
  public static Date fromExcelSerialToDate(double excelSerial) {
    int nDays = (int) excelSerial;
    double partialDays = excelSerial - nDays;
    int nMillis = (int) (partialDays * 24 * 60 * 60 * 1000);

    GregorianCalendar c = new GregorianCalendar();
    c.set(1900, GregorianCalendar.JANUARY, 1, 0, 0, 0);

    c.add(GregorianCalendar.DATE, nDays);
    c.add(GregorianCalendar.MILLISECOND, nMillis);

    // Fix Excel's leap bugs
    int leapDays = 0;
    int year = c.get(GregorianCalendar.YEAR);
    if (excelSerial >= 60) {
      leapDays += 1;
    }
    if (year >= 2000) {
      leapDays += ((int) (year / 1000) - 1);
    }
    c.add(GregorianCalendar.DATE, -leapDays);

    c.set(GregorianCalendar.MILLISECOND, 0);
    return c.getTime();
  }

}
