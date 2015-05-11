package parsers;

public class StringValue extends Value implements Comparable<StringValue> {
	private String value;
	
	public StringValue(String value) {
		this.setValue(value);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public boolean equals(Object other) {
		if (other instanceof StringValue) {
			StringValue that = (StringValue) other;
			
			return (this.value.equals(that.value));
		}
		
		return false;
	}
	
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

	@Override
	public int compareTo(StringValue o) {
		return this.value.compareTo(o.value);
	}
}
