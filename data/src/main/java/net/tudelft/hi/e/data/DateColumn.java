package net.tudelft.hi.e.data;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import net.tudelft.hi.e.common.exceptions.WrongXmlException;
import org.w3c.dom.Element;

/**
 * Case class for specifying a column with dates.
 *
 */
public class DateColumn extends Column {
  /**
   * For each column the format must be specified.
   */
  private DateFormat format;
  private String formatStr;
  private String targetDate;

  static final String isoFormatStr = "yyyy-MM-dd'T'HH:mm";
  static final DateFormat isoFormat = new SimpleDateFormat(isoFormatStr);

  /**
   * Constructs a new DateColumn using a default format.
   *
   * @param name the name of the column.
   */
  public DateColumn(final String name) {
    this(name, isoFormatStr);
  }

  /**
   * Constructs a new DateColumn.
   *
   * @param name the name of the column.
   * @param format the date format of the column.
   */
  public DateColumn(final String name, final String format) {
    this(name, format, null);
  }

  /**
   * Constructs a new DateColumn.
   *
   * @param name
   *          the name of the column.
   * @param format
   *          the format of the column.
   * @param targetDate
   *          the date column to link to if it is not null.
   */
  public DateColumn(final String name, final String format, final String targetDate) {
    super(name);
    this.setFormat(format);
    this.setTargetDate(targetDate);
  }

  @Override
  public Value convertToValue(final String text) throws ColumnTypeMismatchException {
    if ("null".equalsIgnoreCase(text) || text.isEmpty()) {
      return new NullValue();
    }
    if ("excel".equalsIgnoreCase(formatStr)) {
      return new DateValue(convertExcelDate(text), this);
    }

    try {
      return new DateValue(format.parse(text), this);
    } catch (final ParseException e) {
      return convertIsoFormat(text);
    }
  }

  private Value convertIsoFormat(String text) throws ColumnTypeMismatchException {
    try {
      return new DateValue(isoFormat.parse(text), this);
    } catch (final ParseException ex) {
      throw getException(text);
    }
  }

  private Date convertExcelDate(final String text) throws ColumnTypeMismatchException {
    try {
      return DateConversion.fromExcelSerialToDate(Double.parseDouble(text));
    } catch (final NumberFormatException e) {
      throw new ColumnTypeMismatchException(e.getMessage());
    }
  }

  private ColumnTypeMismatchException getException(final String text) {
    return new ColumnTypeMismatchException("\"" + text + "\" does not satisfy the format \""
        + formatStr + "\"");
  }

  @Override
  public void read(final Element element) throws WrongXmlException {
    readFormat(element);
    readTarget(element);
    checkFormatExcelIsNotTime();
  }

  private void checkFormatExcelIsNotTime() throws WrongXmlException {
    if ("excel".equals(formatStr) && isTime()) {
      throw new WrongXmlException("Excel format cannot also be a time!");
    }
  }

  private void readFormat(final Element element) throws WrongXmlException {
    final String elementFormat = element.getAttribute("format");

    if (elementFormat.isEmpty()) {
      throw new WrongXmlException("Format not specified!");
    }

    setFormat(elementFormat);
  }

  private void readTarget(final Element element) throws WrongXmlException {
    final String target = element.getAttribute("target");
    setTargetDate(target.isEmpty() ? null : target);
  }

  public boolean isTime() {
    return targetDate != null;
  }

  @Override
  public String toString() {
    return super.toString() + ",\ttype: date,\tformat: " + formatStr
        + (!isTime() ? "" : ",\ttarget: " + targetDate);
  }

  /**
   * Returns the column's date format.
   *
   * @return the column's date format.
   */
  public DateFormat getFormat() {
    return format;
  }

  /**
   * Returns the column's date format as string.
   *
   * @return the column's date format as string.
   */
  public String getFormatStr() {
    return formatStr;
  }

  /**
   * Gives the column a new date format.
   *
   * @param format
   *          the new date format.
   */
  public void setFormat(final String dateFormat) {
    formatStr = dateFormat;

    if ("excel".equalsIgnoreCase(formatStr)) {
      this.format = null;
    } else {
      this.format = new SimpleDateFormat(dateFormat);
    }
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 67 * hash + Objects.hashCode(this.format);
    hash = 67 * hash + Objects.hashCode(this.formatStr);
    hash = 67 * hash + Objects.hashCode(this.isoFormat);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final DateColumn other = (DateColumn) obj;
    return Objects.equals(getName(), other.getName());
  }

  public String getTargetDate() {
    return targetDate;
  }

  public void setTargetDate(String targetDate) {
    this.targetDate = targetDate;
  }

  @Override
  public String getType() {
    return "date";
  }

}
