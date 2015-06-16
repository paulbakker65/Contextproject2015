package net.tudelft.hi.e.data;

import net.tudelft.hi.e.data.NumberValue;
import net.tudelft.hi.e.data.StringValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 * Class for testing NumberValue.
 */
public class NumberValueTest {

  @Test
  public void testHashCode() {
    final NumberValue value = new NumberValue(10.0);

    assertEquals(value.hashCode(), 1076101151);
  }

  @Test
  public void testNumberValueConstructor() {
    final NumberValue value = new NumberValue(10.0);
    assertNotNull(value);
    assertEquals(10.0, value.getValue(), 0);
  }

  @Test
  public void testNumberValueEquals() {
    final NumberValue value = new NumberValue(10.0);
    final NumberValue valueSame = new NumberValue(10.0);
    final NumberValue valueNotSame = new NumberValue(20.0);
    final StringValue otherClass = new StringValue("text");

    assertEquals(value, value);
    assertNotEquals(value, null);
    assertEquals(value, valueSame);
    assertNotEquals(value, valueNotSame);
    assertNotEquals(value, otherClass);
  }

  @Test
  public void testNumberValueSetGetValue() {
    final NumberValue value = new NumberValue(10.0);
    assertEquals(10.0, value.getValue(), 0);

    value.setValue(20.0);
    assertEquals(20.0, value.getValue(), 0);
  }

  @Test
  public void testNumberValueToString() {
    final NumberValue value = new NumberValue(10.0);
    assertEquals("10", value.toString());
  }

}
