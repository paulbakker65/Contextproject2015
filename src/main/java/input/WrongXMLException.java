package input;

public class WrongXMLException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public WrongXMLException() {
		super();
	}
	
	public WrongXMLException(String msg) {
		super(msg);
	}
}