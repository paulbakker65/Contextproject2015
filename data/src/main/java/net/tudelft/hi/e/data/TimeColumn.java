package net.tudelft.hi.e.data;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import net.tudelft.hi.e.common.exceptions.WrongXmlException;
import org.w3c.dom.Element;

/**
 * Case class for specifying a column with time values.
 *
 */
public class TimeColumn extends Column {
  /**
   * For each column the format must be specified.
   */
  private DateFormat format;
  private String formatStr;
  /**
   * For each column the target date must be specified.
   */
  private String targetDate;

  /**
   * Constructs a new DateColumn using a default format.
   *
   * @param name the name of the column.
   */
  public TimeColumn(final String name) {
    this(name, "hh:mm", null);
  }

  /**
   * Constructs a new TimeColumn.
   *
   * @param name the name of the column.
   * @param format the time format of the column.
   */
  public TimeColumn(final String name, final String format) {
    this(name, format, null);
  }

  /**
   * Constructs a new TimeColumn.
   *
   * @param name the name of the column.
   * @param format the time format of the column.
   * @param targetDate the date column to link to.
   */
  public TimeColumn(final String name, final String format, final String targetDate) {
    super(name);
    this.setFormat(format);
    this.setTargetDate(targetDate);
  }

  @Override
  public Value convertToValue(final String text) throws ColumnTypeMismatchException {
    try {
      if (text.toLowerCase().equals("null") || text.isEmpty()) {
        return new NullValue();
      }
      return new TimeValue(format.parse(text), targetDate);
    } catch (final ParseException e) {
      throw new ColumnTypeMismatchException("\"" + text + "\" does not satisfy the format \""
          + formatStr + "\"");
    }
  }

  /**
   * Returns the column's time format.
   *
   * @return the column's time format.
   */
  public DateFormat getFormat() {
    return format;
  }

  /**
   * Returns the column's time format as string.
   *
   * @return the column's time format as string.
   */
  public String getFormatStr() {
    return formatStr;
  }

  /**
   * Returns the column's target date.
   *
   * @return the column's target date.
   */
  public String getTargetDate() {
    return targetDate;
  }

  @Override
  public void read(final Element element) throws WrongXmlException {
    final String format = element.getAttribute("format");

    if (format.isEmpty()) {
      throw new WrongXmlException("Format not specified!");
    }

    setFormat(format);

    final String target = element.getAttribute("target");

    if (target.isEmpty()) {
      throw new WrongXmlException("Target not specified!");
    }

    setTargetDate(target);
  }

  /**
   * Gives the column a new time format.
   *
   * @param format the new time format.
   */
  public void setFormat(final String format) {
    this.formatStr = format;
    this.format = new SimpleDateFormat(format);
  }

  /**
   * Gives the column a new target date.
   *
   * @param targetDate the new target date.
   */
  public void setTargetDate(final String targetDate) {
    this.targetDate = targetDate;
  }

  @Override
  public String toString() {
    return super.toString() + ",\ttype: time,\tformat: " + formatStr + ",\ttarget: " + targetDate;
  }
}
