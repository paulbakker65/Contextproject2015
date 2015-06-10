package table.value;

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
  public NullValue() {}

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
  public boolean isDate() {
    return false;
  }

  @Override
  public boolean isNull() {
    return true;
  }

  @Override
  public boolean isNumeric() {
    return false;
  }

  @Override
  public boolean isString() {
    return false;
  }

  @Override
  public boolean isTime() {
    return false;
  }

  @Override
  public String toString() {
    return "";
  }
}
