package operation.chunking;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import operations.chunking.DayCondition;

import org.junit.Test;

import table.value.DateValue;

public class DayConditionTest {

  @Test
  public void testMatches() throws ParseException {
    Date date = new SimpleDateFormat("yyMMdd").parse("121110");    
    Date date2 = new SimpleDateFormat("yyMMdd").parse("121109");
    Date date3 = new SimpleDateFormat("yyMMdd").parse("121010");
    Date date4 = new SimpleDateFormat("yyMMdd").parse("111110");
    
    assertTrue(new DayCondition().matches(new DateValue(date), new DateValue(date)));
    assertFalse(new DayCondition().matches(new DateValue(date), new DateValue(date2)));
    assertFalse(new DayCondition().matches(new DateValue(date), new DateValue(date3)));
    assertFalse(new DayCondition().matches(new DateValue(date), new DateValue(date4)));
  }

}
