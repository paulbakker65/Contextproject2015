package table.value;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Case class for representing a time Value.
 * 
 * @author Robin
 *
 */
public class TimeValue extends Value {
  /**
   * Serial version.
   */
  private static final long serialVersionUID = 1L;
  private GregorianCalendar value;
  private String targetDate;

  /**
   * Constructs a new TimeValue.
   * 
   * @param value the stored time.
   * @param targetDate the stored target date.
   */
  public TimeValue(final Date value, final String targetDate) {
    this.targetDate = targetDate;

    if (value != null) {
      this.value = new GregorianCalendar();
      this.value.setTime(value);
    }
  }

  /**
   * Constructs a new TimeValue.
   * 
   * @param value the stored time.
   * @param targetDate the stored target date.
   */
  public TimeValue(final GregorianCalendar value, final String targetDate) {
    this.targetDate = targetDate;
    this.value = value;
  }

  public int compareToTime(final TimeValue other) {
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
    final TimeValue other = (TimeValue) obj;
    if (targetDate == null) {
      if (other.targetDate != null) {
        return false;
      }
    } else if (!targetDate.equals(other.targetDate)) {
      return false;
    }
    if (value == null) {
      if (other.value != null) {
        return false;
      }
    } else if (!value.equals(other.value)) {
      return false;
    }
    return true;
  }

  public String getTargetDate() {
    return targetDate;
  }

  /**
   * Returns the stored time.
   * 
   * @return the stored time.
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
    result = prime * result + ((targetDate == null) ? 0 : targetDate.hashCode());
    result = prime * result + ((value == null) ? 0 : value.hashCode());
    return result;
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
    return false;
  }

  @Override
  public boolean isTime() {
    return true;
  }

  public void setTargetDate(final String targetDate) {
    this.targetDate = targetDate;
  }

  /**
   * Stores a new time.
   * 
   * @param value the new time.
   */
  public void setValue(final GregorianCalendar value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return new SimpleDateFormat("HH:mm").format(getValue().getTime());
  }

}
