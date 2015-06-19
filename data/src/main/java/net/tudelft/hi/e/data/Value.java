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
