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
    final SingleConditionPattern nextPattern = new SingleOccurrencePattern("Test2");
    final SingleConditionPattern pattern = new SingleOccurrencePattern("Test", nextPattern);
    final SingleConditionPattern patternSame = new SingleOccurrencePattern("Test", nextPattern);
    final SingleConditionPattern patternNotSame =
        new SingleOccurrencePattern("Test", new NullPattern());
    final String otherClass = "";

    assertEquals(pattern, pattern);
    assertEquals(pattern, patternSame);
    assertNotEquals(pattern, null);
    assertNotEquals(pattern, patternNotSame);
    assertNotEquals(pattern, otherClass);
  }

  @Test
  public void testHashCode() {
    final SingleConditionPattern nextPattern = new SingleOccurrencePattern("Test");
    final SingleConditionPattern nonNullNextPattern =
        new SingleOccurrencePattern("Test", nextPattern);

    int expectedHashCode = 31 + nextPattern.hashCode();
    expectedHashCode = expectedHashCode * 31 + nonNullNextPattern.getCondition().hashCode();

    assertEquals(expectedHashCode, nonNullNextPattern.hashCode());
  }
}
