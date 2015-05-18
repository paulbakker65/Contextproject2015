package parsers;

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
   * Constructs a new TimeValue
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
   * Constructs a new TimeValue
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

  @Override
  public boolean equals(Object other) {
    if (other instanceof TimeValue) {
      TimeValue that = (TimeValue) other;

      return (this.value.equals(that.value) && this.targetDate.equals(that.targetDate));
    }

    return false;
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
