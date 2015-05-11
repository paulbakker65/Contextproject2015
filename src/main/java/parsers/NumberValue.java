package parsers;

public class NumberValue extends Value {
	private double value;
	
	public NumberValue(double value) {
		this.setValue(value);
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
	
	public boolean equals(Object other) {
		if (other instanceof NumberValue) {
			NumberValue that = (NumberValue) other;
			
			return (this.value == that.value);
		}
		
		return false;
	}
	
	public String toString() {
		return Integer.toString((int) value);
	}
	
	@Override
	public boolean isNumeric() {
		return true;
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
		return false;
	}
	
	public int compareTo(NumberValue o) {
		Double val = new Double(this.value);
		Double anotherVal = new Double(o.value);
		
		return val.compareTo(anotherVal);
	}
}
