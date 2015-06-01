package exceptions;

/**
 * TableNotFoundException is thrown if a table name cannot be found.
 */
public class TableNotFoundException extends Exception {
  /**
   * serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Default constructor.
   * 
   * @param msg The error message.
   */
  public TableNotFoundException(final String msg) {
    super(msg);
  }
}
