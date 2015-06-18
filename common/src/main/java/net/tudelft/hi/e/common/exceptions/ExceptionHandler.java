package net.tudelft.hi.e.common.exceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogRecord;
import java.util.logging.Handler;
import java.util.logging.Logger;

/**
 * Created by mawdegroot on 18/06/15.
 */
public class ExceptionHandler extends Handler {

  private List<LogRecord> logRecordList;

  private static ExceptionHandler handler;

  private ExceptionHandler() {
    super();
    this.logRecordList = new ArrayList<>();
  }

  public static ExceptionHandler getExceptionHandlerInstance() {
    if (handler == null) {
      handler = new ExceptionHandler();
    }
    return handler;
  }

  @Override
  public void publish(LogRecord record) {
    logRecordList.add(record);
  }

  @Override public void flush() {
    return;
  }

  @Override public void close() {
    handler = null;
  }

  public List<LogRecord> getLogRecords() {
    return this.logRecordList;
  }

  public static void clear() {
    getExceptionHandlerInstance().getLogRecords().clear();
  }

  public static void replaceHandler(Logger logger) {
    for (Handler h : logger.getHandlers()) {
      logger.removeHandler(h);
    }
    logger.addHandler(ExceptionHandler.getExceptionHandlerInstance());
  }
}
