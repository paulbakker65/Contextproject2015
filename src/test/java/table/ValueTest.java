package table;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import parsers.DateValue;
import parsers.NullValue;
import parsers.NumberValue;
import parsers.StringValue;
import parsers.Value;

public class ValueTest {

	@Test
	public void testValueIsNull() {
		Value nullValue = new NullValue();
		Value numberValue = new NumberValue(1.0);
		Value dateValue = new DateValue(new Date());
		Value stringValue = new StringValue("text");
		
		assertTrue(nullValue.isNull());
		assertFalse(numberValue.isNull());
		assertFalse(dateValue.isNull());
		assertFalse(stringValue.isNull());
	}
	
	@Test
	public void testValueIsNumeric() {
		Value nullValue = new NullValue();
		Value numberValue = new NumberValue(1.0);
		Value dateValue = new DateValue(new Date());
		Value stringValue = new StringValue("text");
		
		assertFalse(nullValue.isNumeric());
		assertTrue(numberValue.isNumeric());
		assertFalse(dateValue.isNumeric());
		assertFalse(stringValue.isNumeric());
	}
	
	@Test
	public void testValueIsDate() {
		Value nullValue = new NullValue();
		Value numberValue = new NumberValue(1.0);
		Value dateValue = new DateValue(new Date());
		Value stringValue = new StringValue("text");
		
		assertFalse(nullValue.isDate());
		assertFalse(numberValue.isDate());
		assertTrue(dateValue.isDate());
		assertFalse(stringValue.isDate());
	}
	
	@Test
	public void testValueIsString() {
		Value nullValue = new NullValue();
		Value numberValue = new NumberValue(1.0);
		Value dateValue = new DateValue(new Date());
		Value stringValue = new StringValue("text");
		
		assertFalse(nullValue.isString());
		assertFalse(numberValue.isString());
		assertFalse(dateValue.isString());
		assertTrue(stringValue.isString());
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
	public void testDateValueConstructor() throws ParseException {
		Date date = new SimpleDateFormat("ddMMyy").parse("311214");
		DateValue value = new DateValue(date);
		assertNotNull(value);
		assertEquals(date, value.getValue());
	}
	
	@Test
	public void testDateValueSetGetValue() throws ParseException {
		Date date = new SimpleDateFormat("ddMMyy").parse("311214");
		DateValue value = new DateValue(date);
		assertEquals(date, value.getValue());
		
		date = new SimpleDateFormat("ddMMyy").parse("311213");
		value.setValue(date);
		assertEquals(date, value.getValue());
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
				
		assertEquals(value, value);
		assertNotEquals(value, null);
		assertEquals(value, valueSame);
		assertNotEquals(value, valueNotSame);
	}

}
