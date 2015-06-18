package net.tudelft.hi.e.common.exceptions;

/**
 * Exception class for the case that a value does not match the column type.
 * 
 * @author Robin
 *
 */
public class ColumnTypeMismatchException extends Exception {
  private static final long serialVersionUID = 1L;

  /**
   * Constructs a new ColumnTypeMismatchException.
   */
  public ColumnTypeMismatchException() {
    super();
  }

  /**
   * Constructs a new ColumnTypeMismatchException.
   * 
   * @param msg
   *          the message of the exception.
   */
  public ColumnTypeMismatchException(final String msg) {
    super(msg);
  }

  /**
   * Constructs a new ColumnTypeMismatchException.
   *
   * @param exception
   *          the inner exception to use.
   */
  public ColumnTypeMismatchException(final Exception ex) {
    super(ex);
  }
}
