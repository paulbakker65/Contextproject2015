package net.tudelft.hi.e.computation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.tudelft.hi.e.computation.DayCondition;
import net.tudelft.hi.e.data.DateValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class DayConditionTest {

  @Test
  public void testMatchesOneDay() throws ParseException {
    final Date date = new SimpleDateFormat("yyMMdd").parse("121109");
    final Date date2 = new SimpleDateFormat("yyMMdd").parse("121109");
    final Date date3 = new SimpleDateFormat("yyMMdd").parse("121110");
    final Date date4 = new SimpleDateFormat("yyMMdd").parse("121110");
    final Date date5 = new SimpleDateFormat("yyMMdd").parse("121111");
    
    DayCondition cond = new DayCondition(0);

    assertTrue(cond.matches(new DateValue(date)));
    assertTrue(cond.matches(new DateValue(date2)));
    assertFalse(cond.matches(new DateValue(date3)));
    assertTrue(cond.matches(new DateValue(date4)));
    assertFalse(cond.matches(new DateValue(date5)));
  }
  
  @Test
  public void testMatchesTwoDays() throws ParseException {
    final Date date = new SimpleDateFormat("yyMMdd").parse("121101");
    final Date date2 = new SimpleDateFormat("yyMMdd").parse("121102");
    final Date date3 = new SimpleDateFormat("yyMMdd").parse("121103");
    
    DayCondition cond = new DayCondition(1);

    assertTrue(cond.matches(new DateValue(date)));
    assertTrue(cond.matches(new DateValue(date2)));
    assertFalse(cond.matches(new DateValue(date3)));
  }
  
  @Test
  public void testMatchesFiveDays() throws ParseException {
    final Date date = new SimpleDateFormat("yyMMdd").parse("121030");
    final Date date2 = new SimpleDateFormat("yyMMdd").parse("121101");
    final Date date3 = new SimpleDateFormat("yyMMdd").parse("121106");
    
    DayCondition cond = new DayCondition(4);

    assertTrue(cond.matches(new DateValue(date)));
    assertTrue(cond.matches(new DateValue(date2)));
    assertFalse(cond.matches(new DateValue(date3)));
  }

}
