package net.tudelft.hi.e.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import net.tudelft.hi.e.data.DateValue;
import net.tudelft.hi.e.data.StringValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;

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
    value.setDate(date);
    assertEquals(calendar, value.getValue());

    GregorianCalendar newCalendar = new GregorianCalendar();
    value.setValue(newCalendar);
    assertEquals(newCalendar, value.getValue());
  }

  @Test
  public void testDateValueToString() throws ParseException {
    Date date = new SimpleDateFormat("ddMMyy").parse("311214");
    DateValue dateValue = new DateValue(date);
    assertEquals("2014-12-31", dateValue.toString());

    date = new SimpleDateFormat("HHmm").parse("1314");
    DateValue timeValue = new DateValue(date);
    timeValue.setTarget("Date");
    assertEquals("13:14", timeValue.toString());

    dateValue.addTime(timeValue.getValue());

    assertEquals("2014-12-31T13:14", dateValue.toString());
    
    date = DateColumn.isoFormat.parse("2014-12-31T13:14");
    dateValue = new DateValue(date);
    dateValue.setFormat(DateColumn.isoFormatStr);
    
    assertEquals("2014-12-31T13:14", dateValue.toString());
  }

  @Test
  public void testHashCode() {
    final GregorianCalendar calendar = new GregorianCalendar();

    final DateValue value = new DateValue(calendar);
    final DateValue valueNull = new DateValue((Date) null);

    assertEquals(value.hashCode(), 31 + calendar.hashCode());
    assertEquals(valueNull.hashCode(), 31);
  }

  @Test
  public void testGetSetTarget() {
    DateValue value = new DateValue((Date) null, new DateColumn("name", "ddMMyy", "before"));

    assertEquals("before", value.getTarget());
    value.setTarget("after");

    assertEquals("after", value.getTarget());
  }

  @Test
  public void testGetSetFormat() {
    DateValue value = new DateValue((Date) null, new DateColumn("name", "ddMMyy", null));

    assertEquals("ddMMyy", value.getFormat());
    value.setFormat("yyMMdd");

    assertEquals("yyMMdd", value.getFormat());
  }
  
  @Test
  public void testCompareTo() {
	  GregorianCalendar first = new GregorianCalendar(2015, 6, 10, 10, 20);
	  GregorianCalendar second = new GregorianCalendar(2015, 6, 11, 0, 20);
	  GregorianCalendar third = new GregorianCalendar(2015, 6, 11);
	  
	  assertEquals(-1, new DateValue(first).compareTo(new DateValue(second)));
	  assertEquals(-1, new DateValue(first).compareTo(new DateValue(third)));
	  assertEquals(1, new DateValue(third).compareTo(new DateValue(second)));
	  assertEquals(0, new DateValue(third).compareTo(new DateValue(third)));
  }

}
