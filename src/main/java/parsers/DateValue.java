package parsers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Case class for representing a date Value.
 *
 */
public class DateValue extends Value {
  private GregorianCalendar value;

  /**
   * Constructs a new DateValue.
   * 
   * @param value
   *          the stored date.
   */
  public DateValue(Date value) {
    this.value = new GregorianCalendar();
    this.setValue(value);
  }

  /**
   * Constructs a new DateValue.
   * 
   * @param value
   *          the stored date.
   */
  public DateValue(GregorianCalendar value) {
    this.value = value;
  }

  /**
   * Returns the stored date.
   * 
   * @return the stored date.
   */
  public GregorianCalendar getValue() {
    return value;
  }

  /**
   * Stores a new date.
   * 
   * @param value
   *          the new date.
   */
  public void setValue(Date value) {
    this.value.setTime(value);
  }

  /**
   * Adds the time (hours, minutes and milliseconds) to the date value.
   * 
   * @param time
   *          a calendar representing the time.
   */
  public void addTime(GregorianCalendar time) {
    value.add(Calendar.HOUR, time.get(Calendar.HOUR));
    value.add(Calendar.MINUTE, time.get(Calendar.MINUTE));
    value.add(Calendar.MILLISECOND, time.get(Calendar.MILLISECOND));
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
    DateValue other = (DateValue) obj;
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
    return new SimpleDateFormat("yyyy-MM-dd").format(getValue().getTime());
  }

  @Override
  public boolean isNumeric() {
    return false;
  }

  @Override
  public boolean isDate() {
    return true;
  }

  @Override
  public boolean isString() {
    return false;
  }

  @Override
  public boolean isNull() {
    return false;
  }

  @Override
  public boolean isTime() {
    return false;
  }

  public int compareToDate(Value o) {
    return this.value.compareTo(((DateValue) o).value);
  }
}
