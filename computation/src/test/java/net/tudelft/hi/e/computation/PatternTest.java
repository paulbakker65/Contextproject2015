package net.tudelft.hi.e.computation;

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
    final Pattern pattern = getSingleOccurrencePattern("Test");

    assertNotNull(pattern);
    assertEquals(new NullPattern(), pattern.getNextPattern());
  }

  @Test
  public void testConstructorNonEmpty() {
    final Pattern pattern = PatternFactory.createPattern("1 Test", "1 Test2");
    final Pattern nextPattern = PatternFactory.createPattern("1 Test2");

    assertNotNull(pattern);
    assertEquals(nextPattern, pattern.getNextPattern());
  }

  @Test
  public void testEquals() {
    final Pattern pattern = PatternFactory.createPattern("1 Test", "1 Test2");
    final Pattern patternSame = PatternFactory.createPattern("1 Test", "1 Test2");
    final Pattern patternNotSame = PatternFactory.createPattern("1 Test");
    final Pattern patternNextNull1 = PatternFactory.createPattern("1 Test");
    final Pattern patternNextNull2 = PatternFactory.createPattern("1 Test");
    patternNextNull1.setNextPattern(null);
    patternNextNull2.setNextPattern(null);
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
    final Pattern nextPattern = getSingleOccurrencePattern("Test2");
    final Pattern pattern = getSingleOccurrencePattern("Test");

    assertNotEquals(nextPattern, pattern.getNextPattern());

    pattern.setNextPattern(nextPattern);

    assertEquals(nextPattern, pattern.getNextPattern());
  }

  @Test
  public void testHashCode() {
    final Pattern pattern = new NullPattern();

    assertEquals(31, pattern.hashCode());
  }

  private Pattern getSingleOccurrencePattern(String name) {
    return new SingleConditionPattern(new RecordOccurrenceCondition(name));
  }
  
  @Test
  public void testGetLastNotNullPattern() {
    Pattern pattern = new NullPattern();
    assertEquals(pattern, pattern.getLastNotNullPattern());
    
    pattern = getSingleOccurrencePattern("Test");
    assertEquals(pattern, pattern.getLastNotNullPattern());
    
    Pattern pattern2 = getSingleOccurrencePattern("Test2");
    pattern.setNextPattern(pattern2);
    assertEquals(pattern2, pattern.getLastNotNullPattern());
  }
}
