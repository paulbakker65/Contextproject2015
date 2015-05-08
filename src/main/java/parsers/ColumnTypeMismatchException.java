package parsers;

public class ColumnTypeMismatchException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public ColumnTypeMismatchException() {
		super();
	}
	
	public ColumnTypeMismatchException(String msg) {
		super(msg);
	}
}
