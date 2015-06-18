package net.tudelft.hi.e.common.exceptions;

/**
 * ParseFailedException occurs when an exception is thrown during parsing.
 */
public class ParseFailedException extends Exception {
  /**
   * serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Default constructor.
   */
  public ParseFailedException() {
    super();
  }

  /**
   * Default constructor with a message.
   * @param msg the exception message.
   */
  public ParseFailedException(String msg) {
    super(msg);
  }

  /**
   * Default constructor using an exception.
   * @param ex
   */
  public ParseFailedException(Exception ex) {
    super(ex);
  }
}
