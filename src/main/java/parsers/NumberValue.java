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
}
