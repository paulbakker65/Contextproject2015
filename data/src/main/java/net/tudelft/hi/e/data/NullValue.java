package net.tudelft.hi.e.data;

/**
 * Case class for representing that no value is present.
 *
 */
public class NullValue extends Value {

  /**
   * Constructs a new NullValue.
   */
  public NullValue() {
  }

  public int compareToNull(final NullValue other) {
    return 0;
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
}
