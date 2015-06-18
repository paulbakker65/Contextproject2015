package net.tudelft.hi.e.common.exceptions;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by mawdegroot on 18/06/15.
 */
public class ColumnTypeMismatchExceptionTest {
  @Test
  public void testConstructor1() {
    ColumnTypeMismatchException ColumnTypeMismatchException1 = new ColumnTypeMismatchException();
    ColumnTypeMismatchException ColumnTypeMismatchException2 = new ColumnTypeMismatchException();
    assertEquals(ColumnTypeMismatchException1.getMessage(), ColumnTypeMismatchException2.getMessage());
    assertEquals(ColumnTypeMismatchException1.getCause(), ColumnTypeMismatchException2.getCause());
  }

  @Test
  public void testConstructor2() {
    ColumnTypeMismatchException ColumnTypeMismatchException1 = new ColumnTypeMismatchException("test");
    ColumnTypeMismatchException ColumnTypeMismatchException2 = new ColumnTypeMismatchException("test");
    assertEquals(ColumnTypeMismatchException1.getMessage(), ColumnTypeMismatchException2.getMessage());
    assertEquals(ColumnTypeMismatchException1.getCause(), ColumnTypeMismatchException2.getCause());  }

  @Test
  public void testConstructor3() {
    Exception ex = new Exception();
    ColumnTypeMismatchException exception1 = new ColumnTypeMismatchException(ex);
    ColumnTypeMismatchException exception2 = new ColumnTypeMismatchException(ex);
    assertEquals(exception1.getMessage(), exception1.getMessage());
    assertEquals(exception2.getCause(), exception2.getCause());
  }
}
