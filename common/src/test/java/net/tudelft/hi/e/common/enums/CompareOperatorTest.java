package net.tudelft.hi.e.common.enums;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by mawdegroot on 18/06/15.
 */
public class CompareOperatorTest {

  @Test
  public void testToString() throws Exception {
    assertEquals("==", CompareOperator.EQ.toString());
    assertEquals("!=", CompareOperator.NEQ.toString());
    assertEquals(">=", CompareOperator.GEQ.toString());
    assertEquals(">", CompareOperator.G.toString());
    assertEquals("<", CompareOperator.L.toString());
    assertEquals("<=", CompareOperator.LEQ.toString());
    assertEquals("?", CompareOperator.ND.toString());
  }
}
