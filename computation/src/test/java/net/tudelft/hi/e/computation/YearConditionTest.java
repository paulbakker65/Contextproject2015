package net.tudelft.hi.e.computation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

}
