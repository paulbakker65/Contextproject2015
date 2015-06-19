package net.tudelft.hi.e.common.enums;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by mawdegroot on 18/06/15.
 */
public class CalcOperatorTest {

  @Test
  public void testMultiplyOperator() {
    assertEquals(CalcOperator.MULTIPLY, CalcOperator.valueOf("MULTIPLY"));
  }

  @Test
  public void testDivideOperator() {
    assertEquals(CalcOperator.DIVIDE, CalcOperator.valueOf("DIVIDE"));
  }

  @Test
  public void testPlusOperator() {
    assertEquals(CalcOperator.PLUS, CalcOperator.valueOf("PLUS"));
  }

  @Test
  public void testMinusOperator() {
    assertEquals(CalcOperator.MINUS, CalcOperator.valueOf("MINUS"));
  }

  @Test
  public void testModuloOperator() {
    assertEquals(CalcOperator.MODULO, CalcOperator.valueOf("MODULO"));
  }
}
