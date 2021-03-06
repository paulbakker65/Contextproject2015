package net.tudelft.hi.e.data;

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
   * compareTo function using the Java-standard < 0 for less, > 0 for more and 0 for equal checks
   * the instance of the Value to pick a specific compareTo overload.
   *
   * @param other
   *          Value object to compare to
   * @return 0 if equal to o <br>
   *         -1 if less than o <br>
   *         1 if more than o, or if the values cannot be compared <br>
   */
  @Override
  public int compareTo(Value other) {
    if (other instanceof NumberValue) {
      return this.compareToNumber((NumberValue) other);
    } else {
      return Integer.MAX_VALUE;
    }
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
    if (!Double.valueOf(value).equals(other.value)) {
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
  public boolean isNumeric() {
    return true;
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

  @Override
  public Column getType(String name) {
    return new NumberColumn(name);
  }
}
