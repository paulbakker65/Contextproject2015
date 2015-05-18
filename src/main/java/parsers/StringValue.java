package parsers;

/**
 * Case class for representing a string Value.
 * 
 * @author Robin
 *
 */
public class StringValue extends Value {
  private String value;

  /**
   * Constructs a new NumberValue.
   * 
   * @param value
   *          the stored string.
   */
  public StringValue(String value) {
    this.setValue(value);
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
   * Stores a new number string.
   * 
   * @param value
   *          the new number string.
   */
  public void setValue(String value) {
    this.value = value;
  }

  /**
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((value == null) ? 0 : value.hashCode());
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
    StringValue other = (StringValue) obj;
    if (value == null) {
      if (other.value != null) {
        return false;
      }
    } else if (!value.equals(other.value)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return value;
  }

  @Override
  public boolean isNumeric() {
    return false;
  }

  @Override
  public boolean isDate() {
    return false;
  }

  @Override
  public boolean isString() {
    return true;
  }

  @Override
  public boolean isNull() {
    return false;
  }

  @Override
  public boolean isTime() {
    return false;
  }

  public int compareTo(Value o) {
    return this.value.compareTo(((StringValue) o).value);
  }
}
