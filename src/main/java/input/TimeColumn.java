package input;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.w3c.dom.Element;

import parsers.ColumnTypeMismatchException;
import parsers.NullValue;
import parsers.TimeValue;
import parsers.Value;

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
   * @param name
   *          the name of the column.
   */
  public TimeColumn(String name) {
    this(name, "hh:mm", null);
  }

  /**
   * Constructs a new TimeColumn.
   * 
   * @param name
   *          the name of the column.
   * @param format
   *          the time format of the column.
   */
  public TimeColumn(String name, String format) {
    this(name, format, null);
  }

  /**
   * Constructs a new TimeColumn.
   * 
   * @param name
   *          the name of the column.
   * @param format
   *          the time format of the column.
   * @param targetDate
   *          the date column to link to.
   */
  public TimeColumn(String name, String format, String targetDate) {
    super(name);
    this.setFormat(format);
    this.setTargetDate(targetDate);
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
   * Gives the column a new time format.
   * 
   * @param format
   *          the new time format.
   */
  public void setFormat(String format) {
    this.formatStr = format;
    this.format = new SimpleDateFormat(format);
  }

  /**
   * Returns the column's target date.
   * 
   * @return the column's target date.
   */
  public String getTargetDate() {
    return targetDate;
  }

  /**
   * Gives the column a new target date.
   * 
   * @param targetDate
   *          the new target date.
   */
  public void setTargetDate(String targetDate) {
    this.targetDate = targetDate;
  }

  @Override
  public String toString() {
    return super.toString() + ",\ttype: time,\tformat: " + formatStr + ",\ttarget: " + targetDate;
  }

  @Override
  public Value convertToValue(String text) throws ColumnTypeMismatchException {
    try {
      if (text.toLowerCase().equals("null") || text.isEmpty()) {
        return new NullValue();
      }
      return new TimeValue(format.parse(text), targetDate);
    } catch (ParseException e) {
      throw new ColumnTypeMismatchException("\"" + text + "\" does not satisfy the format \""
          + formatStr + "\"");
    }
  }

  @Override
  public void read(Element element) throws WrongXMLException {
    String format = element.getAttribute("format");

    if (format.isEmpty()) {
      throw new WrongXMLException("Format not specified!");
    }

    setFormat(format);

    String target = element.getAttribute("target");

    if (target.isEmpty()) {
      throw new WrongXMLException("Target not specified!");
    }

    setTargetDate(target);
  }
}
