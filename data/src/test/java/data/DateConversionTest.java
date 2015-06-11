package data;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import net.tudelft.hi.e.data.DateConversion;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * DateConversionTest class testing the table.DateConversion class.
 *
 */
public class DateConversionTest {

  @Test
  public void test_date0() {
    final GregorianCalendar calendar = new GregorianCalendar();
    calendar.set(1900, Calendar.JANUARY, 1, 0, 0, 0);
    calendar.set(Calendar.MILLISECOND, 0);

    final Date expected = calendar.getTime();
    final Date actual = DateConversion.fromExcelSerialToDate(0);

    assertEquals(expected, actual);
  }

  @Test
  public void test_date1() {
    final GregorianCalendar calendar = new GregorianCalendar();
    calendar.set(1900, Calendar.FEBRUARY, 1, 0, 0, 0);
    calendar.set(Calendar.MILLISECOND, 0);

    final Date expected = calendar.getTime();
    final Date actual = DateConversion.fromExcelSerialToDate(31);

    assertEquals(expected, actual);
  }

  @Test
  public void test_date2() {
    final GregorianCalendar calendar = new GregorianCalendar();
    calendar.set(1900, Calendar.FEBRUARY, 29, 0, 0, 0);
    calendar.set(Calendar.MILLISECOND, 0);

    final Date expected = calendar.getTime();
    final Date actual = DateConversion.fromExcelSerialToDate(60);

    assertEquals(expected, actual);
  }

  @Test
  public void test_date3() {
    final GregorianCalendar calendar = new GregorianCalendar();
    calendar.set(2000, Calendar.JUNE, 14, 0, 0, 0);
    calendar.set(Calendar.MILLISECOND, 0);

    final Date expected = calendar.getTime();
    final Date actual = DateConversion.fromExcelSerialToDate(36691);

    assertEquals(expected, actual);
  }

  @Test
  public void test_date4() {
    final GregorianCalendar calendar = new GregorianCalendar();
    calendar.set(2009, Calendar.JULY, 6, 18, 0, 0);
    calendar.set(Calendar.MILLISECOND, 0);

    final Date expected = calendar.getTime();
    final Date actual = DateConversion.fromExcelSerialToDate(40000.75);

    assertEquals(expected, actual);
  }

  @Test
  public void testConstructorIsPrivate() throws NoSuchMethodException, IllegalAccessException,
      InvocationTargetException, InstantiationException {
    Constructor<DateConversion> constructor = DateConversion.class.getDeclaredConstructor();
    assertTrue(Modifier.isPrivate(constructor.getModifiers()));
    constructor.setAccessible(true);
    constructor.newInstance();
  }

}
