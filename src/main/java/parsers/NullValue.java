package parsers;

/**
 * Case class for representing that no value is present.
 * @author Robin
 *
 */
public class NullValue extends Value {
	
	/**
	 * Constructs a new NullValue.
	 */
	public NullValue() {}
	
	@Override
	public String toString() {
		return "";
	}
	
	@Override
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
	
	@Override
	public boolean isTime() {
		return false;
	}

	public int compareTo(NullValue o) {
		return 0;
	}	
}
