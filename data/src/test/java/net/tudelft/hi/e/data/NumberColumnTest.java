package net.tudelft.hi.e.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by mawdegroot on 18/06/15.
 */
public class NumberColumnTest {

  NumberColumn someColumn;

  @Before
  public void setUo() {
    someColumn = new NumberColumn("someColumn");
  }
  
  @Test
  public void testGetType() throws Exception {
    assertEquals("number", someColumn.getType());
    assertNotEquals("null", someColumn.getType());
  }
}
