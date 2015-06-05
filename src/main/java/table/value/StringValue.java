package table.value;

/**
 * Case class for representing a string Value.
 * 
 * @author Robin
 *
 */
public class StringValue extends Value {
  /**
   * Serial version.
   */
  private static final long serialVersionUID = 1L;
  private String value;

  /**
   * Constructs a new NumberValue.
   * 
   * @param value the stored string.
   */
  public StringValue(final String value) {
    this.setValue(value);
  }

  public int compareToString(final StringValue other) {
    return this.value.compareTo(other.value);
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
    final StringValue other = (StringValue) obj;
    if (value == null) {
      if (other.value != null) {
        return false;
      }
    } else if (!value.equals(other.value)) {
      return false;
    }
    return true;
  }

  /**
   * Returns the stored string.
   * 
   * @return the stored string.
   */
  public String getValue() {
    return value;
  }

  /**
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    return value.hashCode();
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
    return false;
  }

  @Override
  public boolean isString() {
    return true;
  }

  @Override
  public boolean isTime() {
    return false;
  }

  /**
   * Stores a new number string.
   * 
   * @param value the new number string.
   */
  public void setValue(final String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }
}
