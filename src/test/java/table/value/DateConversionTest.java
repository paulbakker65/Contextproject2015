package table.value;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

/**
 * DateConversionTest class testing the table.DateConversion class.
 * 
 */
public class DateConversionTest {

  @Test
  public void test_date0() {
    GregorianCalendar calendar = new GregorianCalendar();
    calendar.set(1900, GregorianCalendar.JANUARY, 1, 0, 0, 0);
    calendar.set(Calendar.MILLISECOND, 0);

    Date expected = calendar.getTime();
    Date actual = DateConversion.fromExcelSerialToDate(0);

    assertEquals(expected, actual);
  }

  @Test
  public void test_date1() {
    GregorianCalendar calendar = new GregorianCalendar();
    calendar.set(1900, GregorianCalendar.FEBRUARY, 1, 0, 0, 0);
    calendar.set(Calendar.MILLISECOND, 0);

    Date expected = calendar.getTime();
    Date actual = DateConversion.fromExcelSerialToDate(31);

    assertEquals(expected, actual);
  }

  @Test
  public void test_date2() {
    GregorianCalendar calendar = new GregorianCalendar();
    calendar.set(1900, GregorianCalendar.FEBRUARY, 29, 0, 0, 0);
    calendar.set(Calendar.MILLISECOND, 0);

    Date expected = calendar.getTime();
    Date actual = DateConversion.fromExcelSerialToDate(60);

    assertEquals(expected, actual);
  }

  @Test
  public void test_date3() {
    GregorianCalendar calendar = new GregorianCalendar();
    calendar.set(2000, GregorianCalendar.JUNE, 14, 0, 0, 0);
    calendar.set(Calendar.MILLISECOND, 0);

    Date expected = calendar.getTime();
    Date actual = DateConversion.fromExcelSerialToDate(36691);

    assertEquals(expected, actual);
  }

  @Test
  public void test_date4() {
    GregorianCalendar calendar = new GregorianCalendar();
    calendar.set(2009, GregorianCalendar.JULY, 6, 18, 0, 0);
    calendar.set(Calendar.MILLISECOND, 0);

    Date expected = calendar.getTime();
    Date actual = DateConversion.fromExcelSerialToDate(40000.75);

    assertEquals(expected, actual);
  }

}
