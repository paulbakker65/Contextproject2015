package net.tudelft.hi.e.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

/**
 * Created by mawdegroot on 18/06/15.
 */
public class StringColumnTest {

  @Test
  public void testGetType() throws Exception {
    StringColumn someColumn = new StringColumn("someColumn");
    assertEquals("string", someColumn.getType());
    assertNotEquals("null", someColumn.getType());
  }
}
