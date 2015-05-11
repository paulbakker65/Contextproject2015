package parsers;

public class NullValue extends Value implements Comparable<NullValue> {
	public String toString() {
		return "";
	}
	
	public boolean equals(Object other) {
		return (other instanceof NullValue);
	}
	
	@Override
	public boolean isNumeric() {
		return false;
	}

	@Override
	public boolean isDate() {
		return false;
	}

	@Override
	public boolean isString() {
		return false;
	}

	@Override
	public boolean isNull() {
		return true;
	}

	public int compareTo(NullValue o) {
		return 0;
	}
}
