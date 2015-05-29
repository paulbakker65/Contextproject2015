package table.value;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Class for testing DateValue.
 * 
 * @author Robin
 *
 */
public class DateValueTest {

  @Test
  public void testDateValueAddTime() throws ParseException {
    final Date date = new SimpleDateFormat("ddMMyy").parse("311214");
    final DateValue value = new DateValue(date);

    final GregorianCalendar calendar = new GregorianCalendar();
    final Date time = new SimpleDateFormat("hh:mm").parse("10:20");
    calendar.setTime(time);

    assertNotEquals(calendar, value.getValue());

    value.addTime(calendar);

    final GregorianCalendar resultCalendar = new GregorianCalendar();
    final Date resultDate = new SimpleDateFormat("ddMMyy hh:mm").parse("311214 10:20");
    resultCalendar.setTime(resultDate);
    assertEquals(resultCalendar, value.getValue());
  }

  @Test
  public void testDateValueConstructorCalendar() throws ParseException {
    final GregorianCalendar calendar = new GregorianCalendar();
    final Date date = new SimpleDateFormat("ddMMyy").parse("311214");
    calendar.setTime(date);
    final DateValue value = new DateValue(calendar);

    assertNotNull(value);
    assertEquals(calendar, value.getValue());
  }

  @Test
  public void testDateValueConstructorDate() throws ParseException {
    final GregorianCalendar calendar = new GregorianCalendar();
    final Date date = new SimpleDateFormat("ddMMyy").parse("311214");
    calendar.setTime(date);
    final DateValue value = new DateValue(date);

    assertNotNull(value);
    assertEquals(calendar, value.getValue());
  }

  @Test
  public void testDateValueConstructorDateNull() {
    final DateValue value = new DateValue((Date) null);

    assertNotNull(value);
    assertNull(value.getValue());
  }

  @Test
  public void testDateValueEquals() throws ParseException {
    final Date date = new SimpleDateFormat("ddMMyy").parse("311214");
    final Date date2 = new SimpleDateFormat("ddMMyy").parse("311213");
    final DateValue value = new DateValue(date);
    final DateValue valueSame = new DateValue(date);
    final DateValue valueNotSame = new DateValue(date2);
    final DateValue valueNull1 = new DateValue((Date) null);
    final DateValue valueNull2 = new DateValue((Date) null);
    final StringValue otherClass = new StringValue("text");

    assertEquals(value, value);
    assertNotEquals(value, null);
    assertEquals(value, valueSame);
    assertNotEquals(value, valueNotSame);
    assertNotEquals(value, otherClass);
    assertNotEquals(value, valueNull1);
    assertNotEquals(valueNull1, value);
    assertEquals(valueNull1, valueNull2);
  }

  @Test
  public void testDateValueSetGetValue() throws ParseException {
    final GregorianCalendar calendar = new GregorianCalendar();
    Date date = new SimpleDateFormat("ddMMyy").parse("311214");
    calendar.setTime(date);
    final DateValue value = new DateValue(date);

    assertEquals(calendar, value.getValue());

    date = new SimpleDateFormat("ddMMyy").parse("311213");
    calendar.setTime(date);
    value.setValue(date);
    assertEquals(calendar, value.getValue());
  }

  @Test
  public void testDateValueToString() throws ParseException {
    final Date date = new SimpleDateFormat("ddMMyy").parse("311214");
    final DateValue value = new DateValue(date);
    assertEquals("2014-12-31T00:00", value.toString());
  }

  @Test
  public void testHashCode() {
    final GregorianCalendar calendar = new GregorianCalendar();

    final DateValue value = new DateValue(calendar);
    final DateValue valueNull = new DateValue((Date) null);

    assertEquals(value.hashCode(), 31 + calendar.hashCode());
    assertEquals(valueNull.hashCode(), 31);
  }

}
