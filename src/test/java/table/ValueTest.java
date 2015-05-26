package table;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

import parsers.DateValue;
import parsers.NullValue;
import parsers.NumberValue;
import parsers.StringValue;
import parsers.TimeValue;
import parsers.Value;

/**
 * ValueTest class testing table.Value class.
 * 
 */
public class ValueTest {

  @Test
  public void testValueIsNull() {
    Value nullValue = new NullValue();
    Value numberValue = new NumberValue(1.0);
    Value dateValue = new DateValue(new Date());
    Value stringValue = new StringValue("text");
    Value timeValue = new TimeValue(new Date(), "target");

    assertTrue(nullValue.isNull());
    assertFalse(numberValue.isNull());
    assertFalse(dateValue.isNull());
    assertFalse(stringValue.isNull());
    assertFalse(timeValue.isNull());
  }

  @Test
  public void testValueIsNumeric() {
    Value nullValue = new NullValue();
    Value numberValue = new NumberValue(1.0);
    Value dateValue = new DateValue(new Date());
    Value stringValue = new StringValue("text");
    Value timeValue = new TimeValue(new Date(), "target");

    assertFalse(nullValue.isNumeric());
    assertTrue(numberValue.isNumeric());
    assertFalse(dateValue.isNumeric());
    assertFalse(stringValue.isNumeric());
    assertFalse(timeValue.isNumeric());
  }

  @Test
  public void testValueIsDate() {
    Value nullValue = new NullValue();
    Value numberValue = new NumberValue(1.0);
    Value dateValue = new DateValue(new Date());
    Value stringValue = new StringValue("text");
    Value timeValue = new TimeValue(new Date(), "target");

    assertFalse(nullValue.isDate());
    assertFalse(numberValue.isDate());
    assertTrue(dateValue.isDate());
    assertFalse(stringValue.isDate());
    assertFalse(timeValue.isDate());
  }

  @Test
  public void testValueIsString() {
    Value nullValue = new NullValue();
    Value numberValue = new NumberValue(1.0);
    Value dateValue = new DateValue(new Date());
    Value stringValue = new StringValue("text");
    Value timeValue = new TimeValue(new Date(), "target");

    assertFalse(nullValue.isString());
    assertFalse(numberValue.isString());
    assertFalse(dateValue.isString());
    assertTrue(stringValue.isString());
    assertFalse(timeValue.isString());
  }

  @Test
  public void testValueIsTime() {
    Value nullValue = new NullValue();
    Value numberValue = new NumberValue(1.0);
    Value dateValue = new DateValue(new Date());
    Value stringValue = new StringValue("text");
    Value timeValue = new TimeValue(new Date(), "target");

    assertFalse(nullValue.isTime());
    assertFalse(numberValue.isTime());
    assertFalse(dateValue.isTime());
    assertFalse(stringValue.isTime());
    assertTrue(timeValue.isTime());
  }

  @Test
  public void testNullValueConstructor() {
    NullValue value = new NullValue();
    assertNotNull(value);
  }

  @Test
  public void testNullValueToString() {
    NullValue value = new NullValue();
    assertEquals("", value.toString());
  }

  @Test
  public void testNullValueEquals() {
    NullValue value = new NullValue();
    assertEquals(value, new NullValue());
    assertEquals(value, value);
    assertNotEquals(value, null);
  }

  @Test
  public void testStringValueConstructor() {
    StringValue value = new StringValue("testValue");
    assertNotNull(value);
    assertEquals("testValue", value.getValue());
  }

  @Test
  public void testStringValueSetGetValue() {
    StringValue value = new StringValue("testValue");
    assertEquals("testValue", value.getValue());

    value.setValue("newValue");
    assertEquals("newValue", value.getValue());
  }

  @Test
  public void testStringValueToString() {
    StringValue value = new StringValue("testValue");
    assertEquals("testValue", value.toString());
  }

  @Test
  public void testStringValueEquals() {
    StringValue value = new StringValue("testValue");
    StringValue valueSame = new StringValue("testValue");
    StringValue valueNotSame = new StringValue("testValue2");

    assertEquals(value, value);
    assertNotEquals(value, null);
    assertEquals(value, valueSame);
    assertNotEquals(value, valueNotSame);
  }

  @Test
  public void testNumberValueConstructor() {
    NumberValue value = new NumberValue(10.0);
    assertNotNull(value);
    assertEquals(10.0, value.getValue(), 0);
  }

  @Test
  public void testNumberValueSetGetValue() {
    NumberValue value = new NumberValue(10.0);
    assertEquals(10.0, value.getValue(), 0);

    value.setValue(20.0);
    assertEquals(20.0, value.getValue(), 0);
  }

  @Test
  public void testNumberValueToString() {
    NumberValue value = new NumberValue(10.0);
    assertEquals("10", value.toString());
  }

  @Test
  public void testNumberValueEquals() {
    NumberValue value = new NumberValue(10.0);
    NumberValue valueSame = new NumberValue(10.0);
    NumberValue valueNotSame = new NumberValue(20.0);

    assertEquals(value, value);
    assertNotEquals(value, null);
    assertEquals(value, valueSame);
    assertNotEquals(value, valueNotSame);
  }

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
    assertEquals("2014-12-31T00:00", value.toString());
  }

  @Test
  public void testDateValueEquals() throws ParseException {
    Date date = new SimpleDateFormat("ddMMyy").parse("311214");
    Date date2 = new SimpleDateFormat("ddMMyy").parse("311213");
    DateValue value = new DateValue(date);
    DateValue valueSame = new DateValue(date);
    DateValue valueNotSame = new DateValue(date2);

    assertEquals(value, value);
    assertNotEquals(value, null);
    assertEquals(value, valueSame);
    assertNotEquals(value, valueNotSame);
  }

  @Test
  public void testTimeValueConstructorDate() throws ParseException {
    GregorianCalendar calendar = new GregorianCalendar();
    Date date = new SimpleDateFormat("ddMMyy").parse("311214");
    calendar.setTime(date);
    TimeValue value = new TimeValue(date, "lorem");
    assertNotNull(value);
    assertEquals(calendar, value.getValue());
  }

  @Test
  public void testTimeValueConstructorCalendar() throws ParseException {
    GregorianCalendar calendar = new GregorianCalendar();
    Date date = new SimpleDateFormat("ddMMyy").parse("311214");
    calendar.setTime(date);
    TimeValue value = new TimeValue(calendar, "lorem");
    assertNotNull(value);
    assertEquals(calendar, value.getValue());
  }

  @Test
  public void testTimeValueSetGetValue() throws ParseException {
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
  public void testTimeValueSetGetTargetDate() throws ParseException {
    Date date = new SimpleDateFormat("hh:mm").parse("10:20");
    String target = "lorem";
    TimeValue value = new TimeValue(date, target);

    assertEquals(target, value.getTargetDate());

    target = "ipsum";
    value.setTargetDate(target);
    ;
    assertEquals(target, value.getTargetDate());
  }

  @Test
  public void testTimeValueToString() throws ParseException {
    Date date = new SimpleDateFormat("hh:mm").parse("10:20");
    TimeValue value = new TimeValue(date, "ipsum");
    assertEquals("10:20", value.toString());
  }

  @Test
  public void testTimeValueEquals() throws ParseException {
    Date date = new SimpleDateFormat("hh:mm").parse("10:20");
    Date date2 = new SimpleDateFormat("hh:mm").parse("10:21");
    TimeValue value = new TimeValue(date, "Target");
    TimeValue valueSame = new TimeValue(date, "Target");
    TimeValue valueNotSameDate = new TimeValue(date2, "Target");
    TimeValue valueNotSameTarget = new TimeValue(date, "Other target");

    assertEquals(value, value);
    assertNotEquals(value, null);
    assertEquals(value, valueSame);
    assertNotEquals(value, valueNotSameDate);
    assertNotEquals(value, valueNotSameTarget);
  }

}
