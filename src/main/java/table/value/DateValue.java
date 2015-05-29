package table.value;

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
   * @param value the stored date.
   */
  public DateValue(final Date value) {
    if (value != null) {
      this.value = new GregorianCalendar();
      this.setValue(value);
    }
  }

  /**
   * Constructs a new DateValue.
   * 
   * @param value the stored date.
   */
  public DateValue(final GregorianCalendar value) {
    this.value = value;
  }

  /**
   * Adds the time (hours, minutes and milliseconds) to the date value.
   * 
   * @param time a calendar representing the time.
   */
  public void addTime(final GregorianCalendar time) {
    value.add(Calendar.HOUR, time.get(Calendar.HOUR));
    value.add(Calendar.MINUTE, time.get(Calendar.MINUTE));
    value.add(Calendar.SECOND, time.get(Calendar.SECOND));
    value.add(Calendar.MILLISECOND, time.get(Calendar.MILLISECOND));
  }

  public int compareToDate(final DateValue other) {
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
    final DateValue other = (DateValue) obj;
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
   * Returns the stored date.
   * 
   * @return the stored date.
   */
  public GregorianCalendar getValue() {
    return value;
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

  @Override
  public boolean isDate() {
    return true;
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
    return false;
  }

  @Override
  public boolean isTime() {
    return false;
  }

  /**
   * Stores a new date.
   * 
   * @param value the new date.
   */
  public void setValue(final Date value) {
    this.value.setTime(value);
  }

  @Override
  public String toString() {
    return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").format(getValue().getTime());
  }
}
