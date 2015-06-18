package net.tudelft.hi.e.common.exceptions;

import org.junit.After;
import org.junit.Test;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import static org.junit.Assert.*;

/**
 * Created by mawdegroot on 18/06/15.
 */
public class ExceptionHandlerTest {

  @After
  public void tearDown() {
    ExceptionHandler.getExceptionHandlerInstance().close();
  }

  @Test public void testGetExceptionHandlerInstance() throws Exception {
    assertEquals(ExceptionHandler.getExceptionHandlerInstance(),
        ExceptionHandler.getExceptionHandlerInstance());
    assertNotEquals(null, ExceptionHandler.getExceptionHandlerInstance());
    assertEquals(0, ExceptionHandler.getExceptionHandlerInstance().getLogRecords().size());
  }

  @Test public void testPublish() throws Exception {
    ExceptionHandler.getExceptionHandlerInstance().publish(new LogRecord(Level.SEVERE, "TRALALA"));
    assertEquals(1, ExceptionHandler.getExceptionHandlerInstance().getLogRecords().size());
  }

  @Test public void testFlush() throws Exception {
    assertEquals(ExceptionHandler.getExceptionHandlerInstance(),
        ExceptionHandler.getExceptionHandlerInstance());
    ExceptionHandler.getExceptionHandlerInstance().flush();
    assertEquals(ExceptionHandler.getExceptionHandlerInstance(),
        ExceptionHandler.getExceptionHandlerInstance());
  }

  @Test public void testClose() throws Exception {
    ExceptionHandler.getExceptionHandlerInstance().publish(new LogRecord(Level.SEVERE, "TRALALA"));
    assertEquals(1, ExceptionHandler.getExceptionHandlerInstance().getLogRecords().size());
    ExceptionHandler exceptionHandler = ExceptionHandler.getExceptionHandlerInstance();
    ExceptionHandler.getExceptionHandlerInstance().close();
    assertNotEquals(exceptionHandler, ExceptionHandler.getExceptionHandlerInstance());
  }

  @Test public void testClear() throws Exception {
    ExceptionHandler.getExceptionHandlerInstance().publish(new LogRecord(Level.SEVERE, "TRALALA"));
    ExceptionHandler.clear();
    assertEquals(0, ExceptionHandler.getExceptionHandlerInstance().getLogRecords().size());
  }

  @Test public void testReplaceHandler() throws Exception {
    Logger log = Logger.getLogger("LOGGER");
    Logger log2 = Logger.getLogger("LOGGER2");
    assertEquals(log.getHandlers(), log2.getHandlers());
    ExceptionHandler.replaceHandler(log);
    assertNotEquals(log.getHandlers(), log2.getHandlers());
    ExceptionHandler.replaceHandler(log2);
    assertEquals(log.getHandlers(), log2.getHandlers());
  }
}
