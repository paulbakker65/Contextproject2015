package net.tudelft.hi.e.common.exceptions;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by mawdegroot on 18/06/15.
 */
public class TableNotFoundExceptionTest {
  @Test
  public void testConstructor1() {
    TableNotFoundException TableNotFoundException1 = new TableNotFoundException();
    TableNotFoundException TableNotFoundException2 = new TableNotFoundException();
    assertEquals(TableNotFoundException1.getMessage(), TableNotFoundException2.getMessage());
    assertEquals(TableNotFoundException1.getCause(), TableNotFoundException2.getCause());
  }

  @Test
  public void testConstructor2() {
    TableNotFoundException TableNotFoundException1 = new TableNotFoundException("test");
    TableNotFoundException TableNotFoundException2 = new TableNotFoundException("test");
    assertEquals(TableNotFoundException1.getMessage(), TableNotFoundException2.getMessage());
    assertEquals(TableNotFoundException1.getCause(), TableNotFoundException2.getCause());
  }

  @Test
  public void testConstructor3() {
    Exception ex = new Exception();
    TableNotFoundException exception1 = new TableNotFoundException(ex);
    TableNotFoundException exception2  = new TableNotFoundException(ex);
    assertEquals(exception1.getMessage(), exception2.getMessage());
    assertEquals(exception1.getCause(), exception2.getCause());
  }
}
