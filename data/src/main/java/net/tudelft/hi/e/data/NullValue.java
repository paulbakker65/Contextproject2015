package net.tudelft.hi.e.data;

/**
 * Case class for representing that no value is present.
 *
 */
public class NullValue extends Value {
  /**
   * Serial version.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Constructs a new NullValue.
   */
  public NullValue() {
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
    if (other instanceof NullValue) {
      return 0;
    } else {
      return Integer.MAX_VALUE;
    }
  }

  @Override
  public boolean equals(final Object other) {
    return other instanceof NullValue;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public boolean isNull() {
    return true;
  }

  @Override
  public String toString() {
    return "";
  }

  @Override
  public Column getType(String name) {
    return null;
  }
}
