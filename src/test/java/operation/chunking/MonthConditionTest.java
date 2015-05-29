package operation.chunking;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import operations.chunking.MonthCondition;

import org.junit.Test;

import table.value.DateValue;

public class MonthConditionTest {

  @Test
  public void testMatches() throws ParseException {
    Date date = new SimpleDateFormat("yyMMdd").parse("121110");  
    Date date2 = new SimpleDateFormat("yyMMdd").parse("121010");
    Date date3 = new SimpleDateFormat("yyMMdd").parse("111110");
    
    assertTrue(new MonthCondition().matches(new DateValue(date), new DateValue(date)));
    assertFalse(new MonthCondition().matches(new DateValue(date), new DateValue(date2)));
    assertFalse(new MonthCondition().matches(new DateValue(date), new DateValue(date3)));
  }

}
