package net.tudelft.hi.e.computation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class ChunkConditionTest {
  @Test
  public void testEquals() {
    final ChunkCondition condition = new DayCondition(1);
    final ChunkCondition conditionSame = new DayCondition(1);
    final ChunkCondition conditionNotSame = new DayCondition(2);
    final String otherClass = "";
    
    assertEquals(condition, condition);
    assertEquals(condition, conditionSame);
    assertNotEquals(condition, conditionNotSame);
    assertNotEquals(condition, null);
    assertNotEquals(condition, otherClass);
  }
  
  @Test
  public void testHashCode() {
    final ChunkCondition condition = new DayCondition(1);
    assertEquals(31 + 1, condition.hashCode());
  }
}
