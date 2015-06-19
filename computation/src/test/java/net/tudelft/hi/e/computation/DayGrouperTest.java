package net.tudelft.hi.e.computation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

/**
 * Class for testing DayGrouper.
 */
public class DayGrouperTest {

  @Test
  public void testEquals() {
    final DayGrouper grouper = new DayGrouper("colName");
    final DayGrouper grouperSame = new DayGrouper("colName");
    final DayGrouper grouperNotSame = new DayGrouper("colName2");
    final String otherClass = "";
    
    assertEquals(grouper, grouper);
    assertEquals(grouper, grouperSame);
    assertNotEquals(grouper, grouperNotSame);
    assertNotEquals(grouper, null);
    assertNotEquals(grouper, otherClass);
  }
  
  @Test
  public void testHashCode() {
    final DayGrouper grouper = new DayGrouper("colName");
    assertEquals(31 + 31 + "colName".hashCode(), grouper.hashCode());
  }
}
