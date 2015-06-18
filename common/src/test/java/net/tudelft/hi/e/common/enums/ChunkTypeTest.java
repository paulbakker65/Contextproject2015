package net.tudelft.hi.e.common.enums;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by mawdegroot on 18/06/15.
 */
public class ChunkTypeTest {

  @Test
  public void testYear() {
    assertEquals(ChunkType.YEAR, ChunkType.valueOf("YEAR"));
  }

  @Test
  public void testMonth() {
    assertEquals(ChunkType.MONTH, ChunkType.valueOf("MONTH"));
  }

  @Test
  public void testDay() {
    assertEquals(ChunkType.DAY, ChunkType.valueOf("DAY"));
  }

  @Test
  public void testPhase() {
    assertEquals(ChunkType.PHASE, ChunkType.valueOf("PHASE"));
  }

  @Test
  public void testWeekend() {
    assertEquals(ChunkType.WEEKEND, ChunkType.valueOf("WEEKEND"));
  }

}
