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
	
	public String toString() {
		return Integer.toString((int) value);
	}
}
