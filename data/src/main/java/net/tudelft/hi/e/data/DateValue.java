package net.tudelft.hi.e.data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

/**
 * Case class for representing a date Value.
 *
 */
public class DateValue extends Value {
  /**
   * Serial version.
   */
  private static final long serialVersionUID = 1L;
  private static final SimpleDateFormat defaultDateFormat = new SimpleDateFormat("yyyy-MM-dd");
  private static final SimpleDateFormat defaultTimeFormat = new SimpleDateFormat("HH:mm");
  private GregorianCalendar value;
  private String target;
  private String format;
  private boolean timeAdded;

  /**
   * Constructs a new DateValue.
   *
   * @param value the stored date.
   */
  public DateValue(final Date value) {
    this(value, null);
  }

  /**
   * Constructs a new DateValue.
   *
   * @param value the stored date.
   */
  public DateValue(final GregorianCalendar value) {
    this.value = value;
    this.timeAdded = false;
  }

  /**
   * Constructs a new DateValue.
   *
   * @param value the stored date.
   * @param columnType the column to get the target and format from.
   */
  public DateValue(final Date value, DateColumn columnType) {
    this(new GregorianCalendar(), columnType);
    if (value == null) {
      this.value = null;
    } else {
      setDate(value);
    }
  }

  /**
   * Constructs a new DateValue.
   *
   * @param value the stored date.
   * @param columnType the column to get the target and format from.
   */
  public DateValue(final GregorianCalendar value, DateColumn columnType) {
    this.value = value;
    this.timeAdded = false;

    if (columnType != null) {
      this.target = columnType.getTargetDate();
      this.format = columnType.getFormatStr();
    }
  }

  /**
   * Adds the time (hours, minutes and milliseconds) to the date value.
   *
   * @param time a calendar representing the time.
   */
  public void addTime(final GregorianCalendar time) {
    value.add(Calendar.HOUR_OF_DAY, time.get(Calendar.HOUR_OF_DAY));
    value.add(Calendar.MINUTE, time.get(Calendar.MINUTE));
    value.add(Calendar.SECOND, time.get(Calendar.SECOND));
    value.add(Calendar.MILLISECOND, time.get(Calendar.MILLISECOND));
    timeAdded = true;
  }

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
  public int compareTo(Value other) {
    if (other instanceof DateValue) {
      return ((DateValue) this).compareToDate(((DateValue) other));
    } else {
      return Integer.MAX_VALUE;
    }
  }

  public int compareToDate(final DateValue other) {
    return checkDate(this).compareTo(checkDate(other));
  }

  private GregorianCalendar checkDate(DateValue check) {
    if (check.value.get(Calendar.HOUR_OF_DAY) == 0 && check.value.get(Calendar.MINUTE) == 0) {
      GregorianCalendar checkCalendar = (GregorianCalendar) check.value.clone();
      checkCalendar.add(Calendar.HOUR_OF_DAY, 23);
      checkCalendar.add(Calendar.MINUTE, 59);
      return checkCalendar;
    }
    return check.value;
  }

  /**
   * @see java.lang.Object#equals()
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
    final DateValue other = (DateValue) obj;
    return equalValue(other);
  }

  private boolean equalValue(DateValue other) {
    if (value == null && other.value != null) {
      return false;
    }
    return Objects.equals(value, other.value);
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
  public boolean isTime() {
    return target != null;
  }

  /**
   * Stores a new date.
   *
   * @param value the new date.
   */
  public void setDate(final Date value) {
    this.value.setTime(value);
  }

  /**
   * Stores a new date.
   *
   * @param value the new date.
   */
  public void setValue(final GregorianCalendar value) {
    this.value = value;
  }

  @Override
  public String toString() {
    DateFormat dateFormat =
        isTime() ? defaultTimeFormat
            : (timeAdded || DateColumn.isoFormatStr.equals(format) ? DateColumn.isoFormat
                : defaultDateFormat);

    return dateFormat.format(getValue().getTime());
  }

  public String getTarget() {
    return target;
  }

  public void setTarget(String target) {
    this.target = target;
  }

  public String getFormat() {
    return format;
  }

  public void setFormat(String format) {
    this.format = format;
  }

  @Override
  public Column getType(String name) {
    return new DateColumn(name, format, target);
  }
}
