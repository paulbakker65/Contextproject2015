package table.value;

/**
 * Case class for representing a numeric Value.
 *
 */
public class NumberValue extends Value {
  /**
   * Serial version.
   */
  private static final long serialVersionUID = 1L;
  private double value;

  /**
   * Constructs a new NumberValue.
   * 
   * @param value the stored number.
   */
  public NumberValue(final double value) {
    this.setValue(value);
  }

  /**
   * Compare this Number to another Number
   * 
   * @param other The other Number.
   * @return 1 if this > other, 0 if this == other, -1 if this < other.
   */
  public int compareToNumber(final NumberValue other) {
    final Double val = new Double(this.value);
    final Double anotherVal = new Double(other.value);

    return val.compareTo(anotherVal);
  }

  /**
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final NumberValue other = (NumberValue) obj;
    if (Double.doubleToLongBits(value) != Double.doubleToLongBits(other.value)) {
      return false;
    }
    return true;
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

  @Override
  public boolean isDate() {
    return false;
  }

  @Override
  public boolean isNull() {
    return false;
  }

  @Override
  public boolean isNumeric() {
    return true;
  }

  @Override
  public boolean isString() {
    return false;
  }

  @Override
  public boolean isTime() {
    return false;
  }

  /**
   * Stores a new number value.
   * 
   * @param value the new number value.
   */
  public void setValue(final double value) {
    this.value = value;
  }
  
  public void plusNumber(double value) {
    this.value += value;
  }

  @Override
  public String toString() {
    return Integer.toString((int) value);
  }
}
