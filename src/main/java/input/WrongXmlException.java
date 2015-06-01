package input;

/**
 * WrongXmlException class providing an exception if there is an error with XML files.
 * 
 */
public class WrongXmlException extends Exception {
  private static final long serialVersionUID = 1L;

  public WrongXmlException(final String msg) {
    super(msg);
  }
}
