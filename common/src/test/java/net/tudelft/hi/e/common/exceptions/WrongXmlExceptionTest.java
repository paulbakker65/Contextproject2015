package net.tudelft.hi.e.common.exceptions;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by mawdegroot on 18/06/15.
 */
public class WrongXmlExceptionTest {
  @Test
  public void testConstructor1() {
    WrongXmlException WrongXmlException1 = new WrongXmlException();
    WrongXmlException WrongXmlException2 = new WrongXmlException();
    assertEquals(WrongXmlException1.getMessage(), WrongXmlException2.getMessage());
    assertEquals(WrongXmlException1.getCause(), WrongXmlException2.getCause());
  }

  @Test
  public void testConstructor2() {
    WrongXmlException WrongXmlException1 = new WrongXmlException("test");
    WrongXmlException WrongXmlException2 = new WrongXmlException("test");
    assertEquals(WrongXmlException1.getMessage(), WrongXmlException2.getMessage());
    assertEquals(WrongXmlException1.getCause(), WrongXmlException2.getCause());
  }

  @Test
  public void testConstructor3() {
    Exception ex = new Exception();
    WrongXmlException exception1 = new WrongXmlException(ex);
    WrongXmlException exception2  = new WrongXmlException(ex);
    assertEquals(exception1.getMessage(), exception2.getMessage());
    assertEquals(exception1.getCause(), exception2.getCause());
  }
}
