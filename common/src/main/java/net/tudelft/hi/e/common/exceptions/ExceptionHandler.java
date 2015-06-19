package net.tudelft.hi.e.common.exceptions;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.StreamHandler;

/**
 * ExceptionHandler to handle exceptions and print them in a user-friendly way.
 */
public class ExceptionHandler extends StreamHandler {

  private List<LogRecord> logRecordList;

  private OutputStream out;

  private static ExceptionHandler handlerInstance = new ExceptionHandler();

  private ExceptionHandler() {
    super();
    this.logRecordList = new ArrayList<>();
    this.out = new BufferedOutputStream(System.err);
    this.setFormatter(new Formatter() {
      @Override
      public String format(LogRecord record) {
        StringBuilder b = new StringBuilder();
        b.append(record.getLevel());
        b.append(": ");
        b.append(record.getMessage());
        b.append('\n');
        return b.toString();
      }
    });
    this.setOutputStream(this.out);
  }

  public static ExceptionHandler getExceptionHandlerInstance() {
    return handlerInstance;
  }

  @Override
  public void publish(LogRecord record) {
    logRecordList.add(record);
    try {
      out.write(this.getFormatter().format(record).getBytes());
      out.flush();
    } catch (IOException ex) {
      System.exit(1000);
    }
  }

  @Override
  public void flush() {
    return;
  }

  @Override
  public void close() throws SecurityException {
    return;
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
