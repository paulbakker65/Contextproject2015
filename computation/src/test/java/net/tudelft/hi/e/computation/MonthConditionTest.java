package net.tudelft.hi.e.computation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import net.tudelft.hi.e.data.DateValue;

import org.junit.Test;

public class MonthConditionTest {

  @Test
  public void testMatches1Month() throws ParseException {
    final Date date = new SimpleDateFormat("yyMMdd").parse("121010");
    final Date date2 = new SimpleDateFormat("yyMMdd").parse("121015");
    final Date date3 = new SimpleDateFormat("yyMMdd").parse("121110");
    final Date date4 = new SimpleDateFormat("yyMMdd").parse("121114");
    final Date date5 = new SimpleDateFormat("yyMMdd").parse("121210");
    
    MonthCondition cond = new MonthCondition(0);

    assertTrue(cond.matches(new DateValue(date)));
    assertTrue(cond.matches(new DateValue(date2)));
    assertFalse(cond.matches(new DateValue(date3)));
    assertTrue(cond.matches(new DateValue(date4)));
    assertFalse(cond.matches(new DateValue(date5)));
  }
  
  @Test
  public void testMatches2Months() throws ParseException {
    final Date date = new SimpleDateFormat("yyMMdd").parse("121010");
    final Date date2 = new SimpleDateFormat("yyMMdd").parse("121015");
    final Date date3 = new SimpleDateFormat("yyMMdd").parse("121110");
    final Date date4 = new SimpleDateFormat("yyMMdd").parse("121210");
    
    MonthCondition cond = new MonthCondition(1);

    assertTrue(cond.matches(new DateValue(date)));
    assertTrue(cond.matches(new DateValue(date2)));
    assertTrue(cond.matches(new DateValue(date3)));
    assertFalse(cond.matches(new DateValue(date4)));
  }
  
  @Test
  public void testEquals() {
    final MonthCondition monthCondition = new MonthCondition(1);
    final MonthCondition monthConditionSame = new MonthCondition(1);
    final MonthCondition monthConditionNotSame = new MonthCondition(2);
    final MonthCondition monthConditionNotSameTime = new MonthCondition(1);
    monthConditionNotSameTime.matches(new DateValue(new GregorianCalendar(2015, 6, 19)));
    final String otherClass = "";
    
    assertEquals(monthCondition, monthCondition);
    assertEquals(monthCondition, monthConditionSame);
    assertNotEquals(monthCondition, monthConditionNotSame);
    assertNotEquals(monthCondition, monthConditionNotSameTime);
    assertNotEquals(monthCondition, null);
    assertNotEquals(monthCondition, otherClass);
  }
  
  @Test
  public void testHashCode() {
    final MonthCondition condition = new MonthCondition(1);    
    assertEquals((31 + 1) * 31, condition.hashCode());
  }

}
