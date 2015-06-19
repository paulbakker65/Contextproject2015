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

public class YearConditionTest {

  @Test
  public void testMatchesOneYear() throws ParseException {
    final Date date = new SimpleDateFormat("yyMMdd").parse("121109");
    final Date date2 = new SimpleDateFormat("yyMMdd").parse("121109");
    final Date date3 = new SimpleDateFormat("yyMMdd").parse("131110");
    final Date date4 = new SimpleDateFormat("yyMMdd").parse("131110");
    final Date date5 = new SimpleDateFormat("yyMMdd").parse("141111");

    YearCondition cond = new YearCondition(0);

    assertTrue(cond.matches(new DateValue(date)));
    assertTrue(cond.matches(new DateValue(date2)));
    assertFalse(cond.matches(new DateValue(date3)));
    assertTrue(cond.matches(new DateValue(date4)));
    assertFalse(cond.matches(new DateValue(date5)));
  }

  @Test
  public void testMatchesFiveYears() throws ParseException {
    final Date date = new SimpleDateFormat("yyMMdd").parse("121030");
    final Date date2 = new SimpleDateFormat("yyMMdd").parse("131101");
    final Date date3 = new SimpleDateFormat("yyMMdd").parse("171106");

    YearCondition cond = new YearCondition(4);

    assertTrue(cond.matches(new DateValue(date)));
    assertTrue(cond.matches(new DateValue(date2)));
    assertFalse(cond.matches(new DateValue(date3)));
  }
  
  @Test
  public void testEquals() {
    final YearCondition yearCondition = new YearCondition(1);
    final YearCondition yearConditionSame = new YearCondition(1);
    final YearCondition yearConditionNotSame = new YearCondition(2);
    final YearCondition yearConditionNotSameTime = new YearCondition(1);
    yearConditionNotSameTime.matches(new DateValue(new GregorianCalendar(2015, 6, 19)));
    final String otherClass = "";
    
    assertEquals(yearCondition, yearCondition);
    assertEquals(yearCondition, yearConditionSame);
    assertNotEquals(yearCondition, yearConditionNotSame);
    assertNotEquals(yearCondition, yearConditionNotSameTime);
    assertNotEquals(yearCondition, null);
    assertNotEquals(yearCondition, otherClass);
  }
  
  @Test
  public void testHashCode() {
    final YearCondition condition = new YearCondition(1);    
    assertEquals((31 + 1) * 31, condition.hashCode());
  }
}
