package table;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import parsers.NullValue;

public class NullValueTest {

  @Test
  public void testConstructor() {
    NullValue value = new NullValue();
    assertNotNull(value);
  }

  @Test
  public void testToString() {
    NullValue value = new NullValue();
    assertEquals("", value.toString());
  }

  @Test
  public void testEquals() {
    NullValue value = new NullValue();
    assertEquals(value, new NullValue());
    assertEquals(value, value);
    assertNotEquals(value, null);
  }
  
  @Test
  public void testHashCode() {
    NullValue value = new NullValue();
    assertEquals(value.hashCode(), 0);
  }

}
