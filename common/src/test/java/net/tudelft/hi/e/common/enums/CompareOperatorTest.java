package net.tudelft.hi.e.common.enums;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

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
  }
  
  @Test
  public void testMatchesResultTrue() {
    assertTrue(CompareOperator.EQ.matchesCompareResult(0));
    assertTrue(CompareOperator.NEQ.matchesCompareResult(1));
    assertTrue(CompareOperator.GEQ.matchesCompareResult(0));
    assertTrue(CompareOperator.G.matchesCompareResult(1));
    assertTrue(CompareOperator.L.matchesCompareResult(-1));
    assertTrue(CompareOperator.LEQ.matchesCompareResult(0));
  }
  
  @Test
  public void testMatchesResultFalse() {
    assertFalse(CompareOperator.EQ.matchesCompareResult(1));
    assertFalse(CompareOperator.NEQ.matchesCompareResult(0));
    assertFalse(CompareOperator.GEQ.matchesCompareResult(-1));
    assertFalse(CompareOperator.G.matchesCompareResult(0));
    assertFalse(CompareOperator.L.matchesCompareResult(0));
    assertFalse(CompareOperator.LEQ.matchesCompareResult(1));
  }
}
