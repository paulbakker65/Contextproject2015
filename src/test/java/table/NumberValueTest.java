package table;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import parsers.NumberValue;
import parsers.StringValue;

public class NumberValueTest {

  @Test
  public void testNumberValueConstructor() {
    NumberValue value = new NumberValue(10.0);
    assertNotNull(value);
    assertEquals(10.0, value.getValue(), 0);
  }

  @Test
  public void testNumberValueSetGetValue() {
    NumberValue value = new NumberValue(10.0);
    assertEquals(10.0, value.getValue(), 0);

    value.setValue(20.0);
    assertEquals(20.0, value.getValue(), 0);
  }

  @Test
  public void testNumberValueToString() {
    NumberValue value = new NumberValue(10.0);
    assertEquals("10", value.toString());
  }

  @Test
  public void testNumberValueEquals() {
    NumberValue value = new NumberValue(10.0);
    NumberValue valueSame = new NumberValue(10.0);
    NumberValue valueNotSame = new NumberValue(20.0);
    StringValue otherClass = new StringValue("text");

    assertEquals(value, value);
    assertNotEquals(value, null);
    assertEquals(value, valueSame);
    assertNotEquals(value, valueNotSame);
    assertNotEquals(value, otherClass);
  }
  
  @Test 
  public void testHashCode() {
    NumberValue value = new NumberValue(10.0);
    
    assertEquals(value.hashCode(), 1076101151);
  }

}
