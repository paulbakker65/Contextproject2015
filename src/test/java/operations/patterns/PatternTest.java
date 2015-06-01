package operations.patterns;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 * Class for testing the Pattern class.
 */
public class PatternTest {

  @Test
  public void testConstructorEmpty() {
    final Pattern pattern = new SingleOccurrencePattern("Test");

    assertNotNull(pattern);
    assertEquals(new NullPattern(), pattern.getNextPattern());
  }

  @Test
  public void testConstructorNonEmpty() {
    final Pattern nextPattern = new SingleOccurrencePattern("Test2");
    final Pattern pattern = new SingleOccurrencePattern("Test", nextPattern);

    assertNotNull(pattern);
    assertEquals(nextPattern, pattern.getNextPattern());
  }

  @Test
  public void testEquals() {
    final Pattern nextPattern = new SingleOccurrencePattern("Test2");
    final Pattern pattern = new SingleOccurrencePattern("Test", nextPattern);
    final Pattern patternSame = new SingleOccurrencePattern("Test", nextPattern);
    final Pattern patternNotSame = new SingleOccurrencePattern("Test", new NullPattern());
    final Pattern patternNextNull1 = new SingleOccurrencePattern("Test", null);
    final Pattern patternNextNull2 = new SingleOccurrencePattern("Test", null);
    final Pattern thisPattern = new NullPattern();
    final String otherClass = "";

    assertEquals(thisPattern, thisPattern);
    assertEquals(pattern, patternSame);
    assertNotEquals(pattern, null);
    assertNotEquals(pattern, patternNotSame);
    assertNotEquals(pattern, patternNextNull1);
    assertNotEquals(patternNextNull1, pattern);
    assertEquals(patternNextNull1, patternNextNull2);
    assertNotEquals(pattern, otherClass);
  }

  @Test
  public void testGetSetNextPattern() {
    final Pattern nextPattern = new SingleOccurrencePattern("Test2");
    final Pattern pattern = new SingleOccurrencePattern("Test");

    assertNotEquals(nextPattern, pattern.getNextPattern());

    pattern.setNextPattern(nextPattern);

    assertEquals(nextPattern, pattern.getNextPattern());
  }

  @Test
  public void testHashCode() {
    final Pattern pattern = new NullPattern();

    assertEquals(31, pattern.hashCode());
  }
}
