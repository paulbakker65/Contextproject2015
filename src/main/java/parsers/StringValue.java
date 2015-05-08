package parsers;

/**
 * Case class for representing a string Value.
 * @author Robin
 *
 */
public class StringValue extends Value {
	private String value;
	
	/**
	 * Constructs a new NumberValue
	 * @param value the stored string.
	 */
	public StringValue(String value) {
		this.setValue(value);
	}

	/**
	 * Returns the stored string.
	 * @return the stored string.
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Stores a new number string.
	 * @param value the new number string.
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof StringValue) {
			StringValue that = (StringValue) other;
			
			return (this.value.equals(that.value));
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		return value;
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
		return true;
	}

	@Override
	public boolean isNull() {
		return false;
	}
}
