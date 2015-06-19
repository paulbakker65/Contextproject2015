package net.tudelft.hi.e.common.exceptions;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.Handler;
import java.util.logging.Logger;

/**
 * ExceptionHandler to handle and record exceptions to display in the GUI.
 */
public class ExceptionHandler extends Handler {

  private List<LogRecord> logRecordList;

  private static ExceptionHandler handler;

  private OutputStream out;

  private ExceptionHandler() {
    super();
    this.logRecordList = new ArrayList<>();
    out = new BufferedOutputStream(System.err);
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
    this.setFormatter(new Formatter() {
      @Override
      public String format(LogRecord record) {
        return record.getMessage();
      }
    });
    try {
      this.out.write(this.getFormatter().format(record).getBytes());
      this.out.write('\n');
      this.out.flush();
    } catch (IOException ex) {
      throw new SecurityException();
    }
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
    logger.setUseParentHandlers(false);
  }
}
