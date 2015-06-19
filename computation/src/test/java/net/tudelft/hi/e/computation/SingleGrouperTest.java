package net.tudelft.hi.e.computation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class SingleGrouperTest {

  @Test
  public void testEqualsHashCode() {
    SingleGrouper grouper = new SingleGrouper();
    SingleGrouper grouper2 = new SingleGrouper();
 
    assertEquals(grouper, grouper2);
    assertFalse(grouper.equals(null));
    assertFalse(grouper.equals(new DayGrouper("Test")));
    
    assertEquals(13 * 5 + grouper.getClass().hashCode(), grouper.hashCode());
  }
  
}
