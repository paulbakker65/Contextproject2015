package net.tudelft.hi.e.data;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by mawdegroot on 18/06/15.
 */
public class CodeTest {

  private Code someCode;

  @Before
  public void setUp() {
    someCode = new Code("someCode");
  }

  @Test
  public void testAddEventGetEventsGetFrequency() throws Exception {
    assertEquals(0, someCode.getEvents().size());
    assertEquals(0, someCode.getFrequency());
    someCode.addEvent(new Table());
    assertEquals(1, someCode.getEvents().size());
    someCode.addEvent(new Table());
    someCode.addEvent(new Table());
    assertEquals(3, someCode.getEvents().size());
    assertEquals(3, someCode.getFrequency());
  }

  @Test public void testGetNameSetName() throws Exception {
    assertEquals("someCode", someCode.getName());
    someCode.setName("someOtherCode");
    assertEquals("someOtherCode", someCode.getName());
  }
  
  @Test
  public void testEqualsHashCode() {
    Code otherCode = new Code("someCode");
    Code otherNameCode = new Code("otherCode");
    Code nullCode = null;
    Chunk otherClassObject = new Chunk(0, "Test");
    
    assertEquals(otherCode, someCode);
    assertNotEquals(otherNameCode, someCode);
    assertNotEquals(someCode, nullCode);
    assertNotEquals(someCode, otherClassObject);
    
    assertEquals(otherCode.hashCode(), someCode.hashCode());
    
    otherCode.addEvent(new Table());
    assertNotEquals(otherCode, someCode);
  }
}
