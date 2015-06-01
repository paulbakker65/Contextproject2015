package table.value;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 * Class for testing StringValue.
 */
public class StringValueTest {

  @Test
  public void testConstructor() {
    final StringValue value = new StringValue("testValue");
    assertNotNull(value);
    assertEquals("testValue", value.getValue());
  }

  @Test
  public void testEquals() {
    final StringValue value = new StringValue("testValue");
    final StringValue valueSame = new StringValue("testValue");
    final StringValue valueNotSame = new StringValue("testValue2");
    final StringValue valueNull1 = new StringValue(null);
    final StringValue valueNull2 = new StringValue(null);
    final NumberValue otherClass = new NumberValue(1.0);

    assertEquals(value, value);
    assertNotEquals(value, null);
    assertEquals(value, valueSame);
    assertNotEquals(value, valueNotSame);
    assertNotEquals(value, valueNull1);
    assertNotEquals(valueNull1, value);
    assertEquals(valueNull1, valueNull2);
    assertNotEquals(value, otherClass);
  }

  @Test
  public void testHashCode() {
    final StringValue value = new StringValue("testValue");

    assertEquals(value.hashCode(), "testValue".hashCode());
  }

  @Test
  public void testSetGetValue() {
    final StringValue value = new StringValue("testValue");
    assertEquals("testValue", value.getValue());

    value.setValue("newValue");
    assertEquals("newValue", value.getValue());
  }

  @Test
  public void testToString() {
    final StringValue value = new StringValue("testValue");
    assertEquals("testValue", value.toString());
  }

}
