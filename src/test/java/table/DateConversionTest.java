package table;

import table.DateConversion;
import static org.junit.Assert.*;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

public class DateConversionTest {
	
	@Test
	public void test_date0() {
		GregorianCalendar c = new GregorianCalendar();
		c.set(1900, GregorianCalendar.JANUARY, 1, 0, 0, 0);
		c.set(Calendar.MILLISECOND, 0);
		
		Date expected = c.getTime();
		Date actual = DateConversion.fromExcelSerialToDate(0);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_date1() {
		GregorianCalendar c = new GregorianCalendar();
		c.set(1900, GregorianCalendar.FEBRUARY, 1, 0, 0, 0);
		c.set(Calendar.MILLISECOND, 0);
		
		Date expected = c.getTime();
		Date actual = DateConversion.fromExcelSerialToDate(31);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_date2() {
		GregorianCalendar c = new GregorianCalendar();
		c.set(1900, GregorianCalendar.FEBRUARY, 29, 0, 0, 0);
		c.set(Calendar.MILLISECOND, 0);
		
		Date expected = c.getTime();
		Date actual = DateConversion.fromExcelSerialToDate(60);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_date3() {
		GregorianCalendar c = new GregorianCalendar();
		c.set(2000, GregorianCalendar.JUNE, 14, 0, 0, 0);
		c.set(Calendar.MILLISECOND, 0);
		
		Date expected = c.getTime();
		Date actual = DateConversion.fromExcelSerialToDate(36691);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_date4() {
		GregorianCalendar c = new GregorianCalendar();
		c.set(2009, GregorianCalendar.JULY, 6, 18, 0, 0);
		c.set(Calendar.MILLISECOND, 0);
		
		Date expected = c.getTime();
		Date actual = DateConversion.fromExcelSerialToDate(40000.75);
		
		assertEquals(expected, actual);
	}

}
