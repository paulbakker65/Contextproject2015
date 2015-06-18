package net.tudelft.hi.e.common.exceptions;

import java.io.IOException;

/**
 * WrongXmlException class providing an exception if there is an error with XML files.
 *
 */
public class WrongXmlException extends IOException {
  /**
   * serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Default constructor.
   */
  public WrongXmlException() {
    super();
  }

  /**
   * Default constructor with a message.
   * @param msg the exception message.
   */
  public WrongXmlException(final String msg) {
    super(msg);
  }

  /**
   * Default constructor with an internal exception.
   * @param ex the exception.
   */
  public WrongXmlException(final Exception ex) {
    super(ex);
  }
}
