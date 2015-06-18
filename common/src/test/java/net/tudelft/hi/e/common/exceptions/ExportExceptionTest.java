package net.tudelft.hi.e.common.exceptions;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by mawdegroot on 18/06/15.
 */
public class ExportExceptionTest {

  @Test
  public void testConstructor1() {
    ExportException exportException1 = new ExportException();
    ExportException exportException2 = new ExportException();
    assertEquals(exportException1.getMessage(), exportException2.getMessage());
    assertEquals(exportException1.getCause(), exportException2.getCause());
  }

  @Test
  public void testConstructor2() {
    ExportException exportException1 = new ExportException("test");
    ExportException exportException2 = new ExportException("test");
    assertEquals(exportException1.getMessage(), exportException2.getMessage());
    assertEquals(exportException1.getCause(), exportException2.getCause());  }

  @Test
  public void testConstructor3() {
    Exception ex = new Exception();
    ExportException exportException1 = new ExportException(ex);
    ExportException exportException2 = new ExportException(ex);
    assertEquals(exportException1.getMessage(), exportException2.getMessage());
    assertEquals(exportException1.getCause(), exportException2.getCause());
  }
}
