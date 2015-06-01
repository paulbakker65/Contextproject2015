package table.value;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 * Class for testing NullValue.
 */
public class NullValueTest {

  @Test
  public void testConstructor() {
    final NullValue value = new NullValue();
    assertNotNull(value);
  }

  @Test
  public void testEquals() {
    final NullValue value = new NullValue();
    assertEquals(value, new NullValue());
    assertEquals(value, value);
    assertNotEquals(value, null);
  }

  @Test
  public void testHashCode() {
    final NullValue value = new NullValue();
    assertEquals(value.hashCode(), 0);
  }

  @Test
  public void testToString() {
    final NullValue value = new NullValue();
    assertEquals("", value.toString());
  }

}
