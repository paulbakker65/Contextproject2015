package operations.patterns;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

/**
 * Class for testing the SingleConditionPattern class.
 */
public class SingleConditionPatternTest {

  @Test
  public void testEquals() {
    SingleConditionPattern nextPattern = new SingleOccurrencePattern("Test2");
    SingleConditionPattern pattern = new SingleOccurrencePattern("Test", nextPattern);
    SingleConditionPattern patternSame = new SingleOccurrencePattern("Test", nextPattern);
    SingleConditionPattern patternNotSame = new SingleOccurrencePattern("Test", new NullPattern());
    String otherClass = "";
    
    assertEquals(pattern, pattern);
    assertEquals(pattern, patternSame);
    assertNotEquals(pattern, null);
    assertNotEquals(pattern, patternNotSame);
    assertNotEquals(pattern, otherClass);
  }
  
  @Test
  public void testHashCode() {
    SingleConditionPattern nextPattern = new SingleOccurrencePattern("Test");
    SingleConditionPattern nonNullNextPattern = new SingleOccurrencePattern("Test", nextPattern);  
    
    int expectedHashCode = 31 + nextPattern.hashCode();
    expectedHashCode = expectedHashCode * 31 + nonNullNextPattern.getCondition().hashCode();
    
    assertEquals(expectedHashCode, nonNullNextPattern.hashCode());
  }
}
