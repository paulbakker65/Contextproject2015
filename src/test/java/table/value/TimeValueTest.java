package table.value;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

/**
 * Class for testing TimeValue.
 */
public class TimeValueTest {

  @Test
  public void testConstructorDate() throws ParseException {
    GregorianCalendar calendar = new GregorianCalendar();
    Date date = new SimpleDateFormat("ddMMyy").parse("311214");
    calendar.setTime(date);
    TimeValue value = new TimeValue(date, "lorem");
    assertNotNull(value);
    assertEquals(calendar, value.getValue());
  }

  @Test
  public void testConstructorCalendar() throws ParseException {
    GregorianCalendar calendar = new GregorianCalendar();
    Date date = new SimpleDateFormat("ddMMyy").parse("311214");
    calendar.setTime(date);
    TimeValue value = new TimeValue(calendar, "lorem");
    assertNotNull(value);
    assertEquals(calendar, value.getValue());
  }
  
  @Test
  public void testConstructorDateNull() throws ParseException {
    TimeValue value = new TimeValue((Date) null, "lorem");
    assertNotNull(value);
    assertNull(value.getValue());
  }

  @Test
  public void testSetGetValue() throws ParseException {
    GregorianCalendar calendar = new GregorianCalendar();
    Date date = new SimpleDateFormat("ddMMyy").parse("311214");
    calendar.setTime(date);
    TimeValue value = new TimeValue(date, "ipsum");

    assertEquals(calendar, value.getValue());

    date = new SimpleDateFormat("ddMMyy").parse("311213");
    calendar.setTime(date);
    value.setValue(calendar);
    assertEquals(calendar, value.getValue());
  }

  @Test
  public void testSetGetTargetDate() throws ParseException {
    Date date = new SimpleDateFormat("HH:mm").parse("10:20");
    String target = "lorem";
    TimeValue value = new TimeValue(date, target);

    assertEquals(target, value.getTargetDate());

    target = "ipsum";
    value.setTargetDate(target);
    ;
    assertEquals(target, value.getTargetDate());
  }

  @Test
  public void testToString() throws ParseException {
    Date date = new SimpleDateFormat("HH:mm").parse("10:20");
    TimeValue value = new TimeValue(date, "ipsum");
    assertEquals("10:20", value.toString());
  }

  @Test
  public void testEquals() throws ParseException {
    final Date date = new SimpleDateFormat("HH:mm").parse("10:20");
    final Date date2 = new SimpleDateFormat("HH:mm").parse("10:21");
    final TimeValue value = new TimeValue(date, "Target");
    final TimeValue valueSame = new TimeValue(date, "Target");
    final TimeValue valueNotSameDate = new TimeValue(date2, "Target");
    final TimeValue valueNotSameTarget = new TimeValue(date, "Other target");
    final TimeValue valueNullTarget1 = new TimeValue(date, null);
    final TimeValue valueNullTarget2 = new TimeValue(date, null);
    final TimeValue valueNullCalendar1 = new TimeValue((GregorianCalendar) null, "Target");
    final TimeValue valueNullCalendar2 = new TimeValue((GregorianCalendar) null, "Target");
    final StringValue otherClass = new StringValue("text");

    assertEquals(value, value);
    assertNotEquals(value, null);
    assertEquals(value, valueSame);
    assertNotEquals(value, valueNotSameDate);
    assertNotEquals(value, valueNotSameTarget);
    assertNotEquals(valueNullTarget1, value);
    assertNotEquals(valueNullCalendar1, value);
    assertEquals(valueNullTarget1, valueNullTarget2);
    assertEquals(valueNullCalendar1, valueNullCalendar2);
    assertNotEquals(value, otherClass);
  }
  
  @Test
  public void testHashCode() {
    GregorianCalendar calendar = new GregorianCalendar();
    
    final TimeValue value = new TimeValue(calendar, "Target");
    final TimeValue valueNullDate = new TimeValue((Date) null, "Target");
    final TimeValue valueNullTarget = new TimeValue(calendar, null);
    final TimeValue valueNullNull = new TimeValue((Date) null, null);
    
    assertEquals(value.hashCode(), (31 + "Target".hashCode()) * 31 + calendar.hashCode());
    assertEquals(valueNullDate.hashCode(), (31 + "Target".hashCode()) * 31);
    assertEquals(valueNullTarget.hashCode(), 31 * 31 + calendar.hashCode());
    assertEquals(valueNullNull.hashCode(), 31 * 31);
  }

}
