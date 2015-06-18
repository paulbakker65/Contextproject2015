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
}
