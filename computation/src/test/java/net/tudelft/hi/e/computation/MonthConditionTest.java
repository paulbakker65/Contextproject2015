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
  public void testMatches() throws ParseException {
    final Date date = new SimpleDateFormat("yyMMdd").parse("121110");
    final Date date2 = new SimpleDateFormat("yyMMdd").parse("121010");
    final Date date3 = new SimpleDateFormat("yyMMdd").parse("111110");

    assertTrue(new MonthCondition().matches(new DateValue(date), new DateValue(date)));
    assertFalse(new MonthCondition().matches(new DateValue(date), new DateValue(date2)));
    assertFalse(new MonthCondition().matches(new DateValue(date), new DateValue(date3)));
  }

}
