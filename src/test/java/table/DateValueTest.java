package table;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

import table.value.DateValue;
import table.value.StringValue;

public class DateValueTest {

  @Test
  public void testDateValueConstructorDate() throws ParseException {
    GregorianCalendar calendar = new GregorianCalendar();
    Date date = new SimpleDateFormat("ddMMyy").parse("311214");
    calendar.setTime(date);
    DateValue value = new DateValue(date);

    assertNotNull(value);
    assertEquals(calendar, value.getValue());
  }

  @Test
  public void testDateValueConstructorCalendar() throws ParseException {
    GregorianCalendar calendar = new GregorianCalendar();
    Date date = new SimpleDateFormat("ddMMyy").parse("311214");
    calendar.setTime(date);
    DateValue value = new DateValue(calendar);

    assertNotNull(value);
    assertEquals(calendar, value.getValue());
  }
  
  @Test
  public void testDateValueConstructorDateNull() {
    DateValue value = new DateValue((Date) null);

    assertNotNull(value);
    assertNull(value.getValue());
  }

  @Test
  public void testDateValueSetGetValue() throws ParseException {
    GregorianCalendar calendar = new GregorianCalendar();
    Date date = new SimpleDateFormat("ddMMyy").parse("311214");
    calendar.setTime(date);
    DateValue value = new DateValue(date);

    assertEquals(calendar, value.getValue());

    date = new SimpleDateFormat("ddMMyy").parse("311213");
    calendar.setTime(date);
    value.setValue(date);
    assertEquals(calendar, value.getValue());
  }

  @Test
  public void testDateValueAddTime() throws ParseException {
    Date date = new SimpleDateFormat("ddMMyy").parse("311214");
    DateValue value = new DateValue(date);

    GregorianCalendar calendar = new GregorianCalendar();
    Date time = new SimpleDateFormat("hh:mm").parse("10:20");
    calendar.setTime(time);

    assertNotEquals(calendar, value.getValue());

    value.addTime(calendar);

    GregorianCalendar resultCalendar = new GregorianCalendar();
    Date resultDate = new SimpleDateFormat("ddMMyy hh:mm").parse("311214 10:20");
    resultCalendar.setTime(resultDate);
    assertEquals(resultCalendar, value.getValue());
  }

  @Test
  public void testDateValueToString() throws ParseException {
    Date date = new SimpleDateFormat("ddMMyy").parse("311214");
    DateValue value = new DateValue(date);
    assertEquals("2014-12-31", value.toString());
  }

  @Test
  public void testDateValueEquals() throws ParseException {
    Date date = new SimpleDateFormat("ddMMyy").parse("311214");
    Date date2 = new SimpleDateFormat("ddMMyy").parse("311213");
    DateValue value = new DateValue(date);
    DateValue valueSame = new DateValue(date);
    DateValue valueNotSame = new DateValue(date2);
    DateValue valueNull1 = new DateValue((Date) null);
    DateValue valueNull2 = new DateValue((Date) null);
    StringValue otherClass = new StringValue("text");

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
  public void testHashCode() {
    GregorianCalendar calendar = new GregorianCalendar();
    
    DateValue value = new DateValue(calendar);
    DateValue valueNull = new DateValue((Date) null);
    
    assertEquals(value.hashCode(), 31 + calendar.hashCode());
    assertEquals(valueNull.hashCode(), 31);
  }

}
