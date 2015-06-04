package operations.patterns;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import operations.patterns.condition.RecordOccurrenceCondition;

import org.junit.Test;

/**
 * Class for testing the SingleConditionPattern class.
 */
public class SingleConditionPatternTest {

  @Test
  public void testEquals() {
    RecordOccurrenceCondition condition = new RecordOccurrenceCondition("Test");
    RecordOccurrenceCondition condition2 = new RecordOccurrenceCondition("Test2");
    
    final SingleConditionPattern nextPattern = new SingleConditionPattern(condition2);
    final SingleConditionPattern pattern = new SingleConditionPattern(condition, nextPattern);
    final SingleConditionPattern patternSame = new SingleConditionPattern(condition, nextPattern);
    final SingleConditionPattern patternNotSame = new SingleConditionPattern(condition);
    final String otherClass = "";

    assertEquals(pattern, pattern);
    assertEquals(pattern, patternSame);
    assertNotEquals(pattern, null);
    assertNotEquals(pattern, patternNotSame);
    assertNotEquals(pattern, otherClass);
  }

  @Test
  public void testHashCode() {
    RecordOccurrenceCondition condition = new RecordOccurrenceCondition("Test");
    final SingleConditionPattern nextPattern = new SingleConditionPattern(condition);
    final SingleConditionPattern nonNullNextPattern = 
        new SingleConditionPattern(condition, nextPattern);

    int expectedHashCode = 31 + nextPattern.hashCode();
    expectedHashCode = expectedHashCode * 31 + nonNullNextPattern.getCondition().hashCode();

    assertEquals(expectedHashCode, nonNullNextPattern.hashCode());
  }
}
