package net.tudelft.hi.e.computation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

/**
 * Class for testing MultipleConditionPattern.
 */
public class MultipleConditionPatternTest {

  @Test
  public void testEquals() {
    final Pattern multiPattern = PatternFactory.createPattern("* StatSensor");
    final Pattern multiPatternSame = PatternFactory.createPattern("* StatSensor");
    final Pattern multiPatternNotSame = PatternFactory.createPattern("* Website");
    final String otherClass = "";
    
    assertEquals(multiPattern, multiPattern);
    assertEquals(multiPattern, multiPatternSame);
    assertNotEquals(multiPattern, multiPatternNotSame);
    assertNotEquals(multiPattern, null);
    assertNotEquals(multiPattern, otherClass);
  }
  
  @Test
  public void testHashCode() {
    RecordCondition condition = new RecordOccurrenceCondition("name");
    MultipleConditionPattern pattern = new MultipleConditionPattern(condition);
    
    assertEquals(47 * 7 + condition.hashCode(), pattern.hashCode());
  }
}
