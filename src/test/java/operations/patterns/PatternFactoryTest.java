package operations.patterns;

import static org.junit.Assert.assertEquals;

import operations.patterns.PatternFactory.PatternEnum;

import org.junit.Test;

/**
 * Test for pattern factory.
 * 
 * @author paulbakker
 *
 */
public class PatternFactoryTest {

  @Test
  public void testActionDonePattern() {
    final PatternFactory pf = new PatternFactory(PatternEnum.ACTION_DONE);
    assertEquals(pf.getPattern(), pf.adPattern());
  }

  @Test
  public void testDoublePattern() {
    final PatternFactory pf = new PatternFactory(PatternEnum.TWO_SENSOR_ONE_WEB);
    assertEquals(pf.getPattern(), new SingleOccurrencePattern("Useless", pf.nePattern()));
  }

  @Test
  public void testNull() {
    final PatternFactory pf = new PatternFactory(PatternEnum.TEST);
    assertEquals(pf.getPattern(), null);
  }

  @Test
  public void testSimplePattern() {
    final PatternFactory pf = new PatternFactory(PatternEnum.NORMAL_ENTRY);
    assertEquals(pf.getPattern(), pf.nePattern());
  }
}
