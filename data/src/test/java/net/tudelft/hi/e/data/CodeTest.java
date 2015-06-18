package net.tudelft.hi.e.data;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Objects;

import static org.junit.Assert.*;

/**
 * Created by mawdegroot on 18/06/15.
 */
public class CodeTest {

  private Code someCode;

  @Before public void setUp() {
    someCode = new Code("someCode");
  }

  @Test public void testAddEvent() {
    assertEquals(someCode.getEvents(), new ArrayList<Table>());
    someCode.addEvent(new Table());
    assertEquals(someCode.getEvents().size(), 1);
  }

  @Test public void testGetEvents() {
    assertEquals(someCode.getEvents(), new ArrayList<Table>());
  }

  @Test public void testGetFrequency() {
    assertEquals(someCode.getFrequency(), 0);
  }

  @Test public void testGetName() {
    assertEquals(someCode.getName(), "someCode");
  }

  @Test public void testSetName() {
    assertEquals(someCode.getName(), "someCode");
    someCode.setName("someOtherName");
    assertEquals(someCode.getName(), "someOtherName");
  }

  @Test public void testEqualsSame() {
    assertEquals(someCode, someCode);
  }

  @Test public void testEqualsNull() {
    Code someOtherCode = null;
    assertFalse(someCode.equals(someOtherCode));
  }

  @Test public void testEqualsOtherClass() {
    Table someOtherCode = new Table();
    assertFalse(someCode.equals(someOtherCode));
  }

  @Test public void testEquals() {
    Code someOtherCode = new Code("someCode");
    assertTrue(someCode.equals(someOtherCode));
  }

  @Test public void testEqualsFalse() {
    Code someOtherCode = new Code("tralala");
    assertFalse(someCode.equals(someOtherCode));
  }

  @Test public void testHashCode() {
    int otherHash = Objects.hash(new ArrayList<Table>(), "someCode");
    assertEquals(otherHash, someCode.hashCode());
  }
}
