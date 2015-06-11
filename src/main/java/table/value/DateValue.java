package table.value;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Case class for representing a date Value.
 *
 */
public class DateValue extends Value {
  /**
   * Serial version.
   */
  private static final long serialVersionUID = 1L;
  private GregorianCalendar value;
  private String target;
  private String format;

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
    value.add(Calendar.HOUR, time.get(Calendar.HOUR));
    value.add(Calendar.MINUTE, time.get(Calendar.MINUTE));
    value.add(Calendar.SECOND, time.get(Calendar.SECOND));
    value.add(Calendar.MILLISECOND, time.get(Calendar.MILLISECOND));
  }

  public int compareToDate(final DateValue other) {
    return this.value.compareTo(other.value);
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
    return !isTime();
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
    return DateColumn.isoFormat.format(getValue().getTime());
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
