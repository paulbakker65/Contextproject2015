package net.tudelft.hi.e.common.exceptions;

/**
 * ParseFailedException occurs when an exception is thrown during parsing.
 */
public class ParseFailedException extends Exception {

  public ParseFailedException(String msg) {
    super(msg);
  }

  public ParseFailedException(Exception ex) {
    super(ex);
  }

  public ParseFailedException() {
    super();
  }
}
