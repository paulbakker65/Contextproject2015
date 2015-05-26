package table.value;

/**
 * Case class for representing a numeric Value.
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

  /**
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    long temp;
    temp = Double.doubleToLongBits(value);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    return result;
  }

  /**
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    NumberValue other = (NumberValue) obj;
    if (Double.doubleToLongBits(value) != Double.doubleToLongBits(other.value)) {
      return false;
    }
    return true;
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

  public int compareToNumber(NumberValue o) {
    Double val = new Double(this.value);
    Double anotherVal = new Double(o.value);

    return val.compareTo(anotherVal);
  }
}
