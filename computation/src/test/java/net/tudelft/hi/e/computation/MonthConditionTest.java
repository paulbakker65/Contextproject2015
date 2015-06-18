package net.tudelft.hi.e.computation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.tudelft.hi.e.computation.MonthCondition;
import net.tudelft.hi.e.data.DateValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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

}
