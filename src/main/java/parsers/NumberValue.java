package parsers;

/**
 * Case class for representing a numeric Value.
 * 
 * @author Robin
 *
 */
public class NumberValue extends Value {
  private double value;

  /**
   * Constructs a new NumberValue.
   * 
   * @param value
   *          the stored number.
   */
  public NumberValue(double value) {
    this.setValue(value);
  }

  /**
   * Returns the stored number.
   * 
   * @return the stored number.
   */
  public double getValue() {
    return value;
  }

  /**
   * Stores a new number value.
   * 
   * @param value
   *          the new number value.
   */
  public void setValue(double value) {
    this.value = value;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof NumberValue) {
      NumberValue that = (NumberValue) other;

      return (this.value == that.value);
    }

    return false;
  }

  @Override
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

  @Override
  public boolean isTime() {
    return false;
  }

  public int compareTo(NumberValue o) {
    Double val = new Double(this.value);
    Double anotherVal = new Double(o.value);

    return val.compareTo(anotherVal);
  }
}
