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
  private GregorianCalendar value;
  private String targetDate;

  /**
   * Constructs a new TimeValue.
   * 
   * @param value
   *          the stored time.
   * @param targetDate
   *          the stored target date.
   */
  public TimeValue(Date value, String targetDate) {
    this.targetDate = targetDate;
    this.value = new GregorianCalendar();
    this.value.setTime(value);
  }

  /**
   * Constructs a new TimeValue.
   * 
   * @param value
   *          the stored time.
   * @param targetDate
   *          the stored target date.
   */
  public TimeValue(GregorianCalendar value, String targetDate) {
    this.targetDate = targetDate;
    this.value = value;
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
   * Stores a new time.
   * 
   * @param value
   *          the new time.
   */
  public void setValue(GregorianCalendar value) {
    this.value = value;
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
    TimeValue other = (TimeValue) obj;
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

  @Override
  public String toString() {
    return new SimpleDateFormat("hh:mm").format(getValue().getTime());
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
    return false;
  }

  @Override
  public boolean isNull() {
    return false;
  }

  @Override
  public boolean isTime() {
    return true;
  }

  public String getTargetDate() {
    return targetDate;
  }

  public void setTargetDate(String targetDate) {
    this.targetDate = targetDate;
  }

}
