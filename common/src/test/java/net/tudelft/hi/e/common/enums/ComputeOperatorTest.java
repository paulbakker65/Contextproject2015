package net.tudelft.hi.e.common.enums;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by mawdegroot on 18/06/15.
 */
public class ComputeOperatorTest {

  @Test
  public void testAvg() {
    assertEquals(ComputeOperator.AVG, ComputeOperator.valueOf("AVG"));
  }

  @Test
  public void testCount() {
    assertEquals(ComputeOperator.COUNT, ComputeOperator.valueOf("COUNT"));
  }

  @Test
  public void testMax() {
    assertEquals(ComputeOperator.MAX, ComputeOperator.valueOf("MAX"));
  }

  @Test
  public void testMin() {
    assertEquals(ComputeOperator.MIN, ComputeOperator.valueOf("MIN"));
  }

  @Test
  public void testStdev() {
    assertEquals(ComputeOperator.STDEV, ComputeOperator.valueOf("STDEV"));
  }

  @Test
  public void testSum() {
    assertEquals(ComputeOperator.SUM, ComputeOperator.valueOf("SUM"));
  }
}
