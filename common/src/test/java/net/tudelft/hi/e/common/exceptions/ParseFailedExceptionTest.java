package net.tudelft.hi.e.common.exceptions;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by mawdegroot on 18/06/15.
 */
public class ParseFailedExceptionTest {
  @Test
  public void testConstructor1() {
    ParseFailedException ParseFailedException1 = new ParseFailedException();
    ParseFailedException ParseFailedException2 = new ParseFailedException();
    assertEquals(ParseFailedException1.getMessage(), ParseFailedException2.getMessage());
    assertEquals(ParseFailedException1.getCause(), ParseFailedException2.getCause());
  }

  @Test
  public void testConstructor2() {
    ParseFailedException ParseFailedException1 = new ParseFailedException("test");
    ParseFailedException ParseFailedException2 = new ParseFailedException("test");
    assertEquals(ParseFailedException1.getMessage(), ParseFailedException2.getMessage());
    assertEquals(ParseFailedException1.getCause(), ParseFailedException2.getCause());
  }

  @Test
  public void testConstructor3() {
    Exception ex = new Exception();
    ParseFailedException exception1 = new ParseFailedException(ex);
    ParseFailedException exception2  = new ParseFailedException(ex);
    assertEquals(exception1.getMessage(), exception2.getMessage());
    assertEquals(exception1.getCause(), exception2.getCause());
  }
}
