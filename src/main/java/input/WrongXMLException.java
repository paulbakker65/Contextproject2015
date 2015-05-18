package input;

/**
 * WrongXMLException class providing an exception if there is an error with XML files.
 * 
 */
public class WrongXMLException extends Exception {
  private static final long serialVersionUID = 1L;

  public WrongXMLException(String msg) {
    super(msg);
  }
}
