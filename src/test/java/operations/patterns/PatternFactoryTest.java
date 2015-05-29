package operations.patterns;

import static org.junit.Assert.assertEquals;

import operations.patterns.PatternFactory.PatternEnum;

import org.junit.Test;

public class PatternFactoryTest {

  @Test
  public void testSimplePattern() {
    PatternFactory pf = new PatternFactory(PatternEnum.NORMAL_ENTRY);
    assertEquals(pf.getPattern(), pf.nePattern());
  }
  
  @Test
  public void testDoublePattern() {
    PatternFactory pf = new PatternFactory(PatternEnum.TWO_SENSOR_ONE_WEB);
    assertEquals(pf.getPattern(), new SingleOccurrencePattern("Useless", pf.nePattern()));
  }
  
  @Test
  public void testActionDonePattern() {
    PatternFactory pf = new PatternFactory(PatternEnum.ACTION_DONE);
    assertEquals(pf.getPattern(), pf.adPattern());
  }
  
  @Test
  public void testNull() {
    PatternFactory pf = new PatternFactory(PatternEnum.TEST);
    assertEquals(pf.getPattern(), null);
  }
}
