package exceptions;

public class TableNotFoundException extends Exception {
  public TableNotFoundException(String msg) {
    super(msg);
  }
}
