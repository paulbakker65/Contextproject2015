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
