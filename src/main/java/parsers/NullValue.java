package parsers;

public class NullValue extends Value {
	public String toString() {
		return "";
	}
	
	public boolean equals(Object other) {
		return (other instanceof NullValue);
	}
}
