package net.tudelft.hi.e.computation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

/**
 * Class for testing Count.
 */
public class CountTest {

  @Test
  public void testEqualsSingleCount() {
    final SingleCount singleCount = new SingleCount(1);
    final SingleCount singleCountSame = new SingleCount(1);
    final SingleCount singleCountNotSame = new SingleCount(2);
    final String otherClass = "";

    assertEquals(singleCount, singleCount);
    assertEquals(singleCount, singleCountSame);
    assertNotEquals(singleCount, null);
    assertNotEquals(singleCount, singleCountNotSame);
    assertNotEquals(singleCount, otherClass);
  }

  @Test
  public void testHashCodeSingleCount() {
    SingleCount singleCount = new SingleCount(1);
    assertEquals(31 + 1, singleCount.hashCode());
  }
  
  @Test
  public void testToStringSingleCount() {
    assertEquals("SingleCount [count=1]", new SingleCount(1).toString());
  }

  @Test
  public void testEqualsMultipleCount() {
    final MultipleCount multiCount = new MultipleCount();
    final MultipleCount multiCountSame = new MultipleCount();
    final String otherClass = "";

    assertEquals(multiCount, multiCount);
    assertEquals(multiCount, multiCountSame);
    assertNotEquals(multiCount, null);
    assertNotEquals(multiCount, otherClass);
  }
  
  @Test
  public void testHashCodeMultipleCount() {
    MultipleCount multiCount = new MultipleCount();
    assertEquals(89, multiCount.hashCode());
  }

  @Test
  public void testToStringMultipleCount() {
    assertEquals("MultipleCount []", new MultipleCount().toString());
  }
}
