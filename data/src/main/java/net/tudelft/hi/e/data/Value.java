package net.tudelft.hi.e.data;

import java.io.Serializable;

/**
 * An abstract class to represent a String Date Time... etc.
 *
 * @author unset
 *
 */
public abstract class Value implements Comparable<Value>, Serializable {

  /**
   * Serial version.
   */
  private static final long serialVersionUID = 1L;

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
  public int compareTo(final Value other) {
    if (this instanceof StringValue) {
      if (other instanceof StringValue) {
        return ((StringValue) this).compareToString(((StringValue) other));
      } else {
        return Integer.MAX_VALUE;
      }
    } else if (this instanceof NumberValue) {
      if (other instanceof NumberValue) {
        return ((NumberValue) this).compareToNumber(((NumberValue) other));
      } else {
        return Integer.MAX_VALUE;
      }
    } else if (this instanceof DateValue) {
      if (other instanceof DateValue) {
        return ((DateValue) this).compareToDate(((DateValue) other));
      } else {
        return Integer.MAX_VALUE;
      }
    } else {
      if (other instanceof NullValue) {
        return 0;
      } else {
        return Integer.MAX_VALUE;
      }
    }
  }

  /**
   * Returns whether a Value is a date.
   *
   * @return whether a Value is a date.
   */
  public boolean isDate() {
    return false;
  }

  /**
   * Returns whether a Value is null, which means that no value is present.
   *
   * @return whether a Value is null.
   */
  public boolean isNull() {
    return false;
  }

  /**
   * Returns whether a Value is numeric.
   *
   * @return whether a Value is numeric.
   */
  public boolean isNumeric() {
    return false;
  }

  /**
   * Returns whether a Value is a string.
   *
   * @return whether a Value is a string.
   */
  public boolean isString() {
    return false;
  }

  /**
   * Returns whether a Value is a string.
   *
   * @return whether a Value is a string.
   */
  public boolean isTime() {
    return false;
  }

  /**
   * Returns the type of the value.
   *
   * @param name
   *          the column name;
   * @return the type of the value.
   */
  public abstract Column getType(String name);
}
