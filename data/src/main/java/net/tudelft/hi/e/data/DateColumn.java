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

  private final DateFormat iso = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

  /**
   * Constructs a new DateColumn using a default format.
   *
   * @param name the name of the column.
   */
  public DateColumn(final String name) {
    this(name, "yyyy-MM-dd");
  }

  /**
   * Constructs a new DateColumn.
   *
   * @param name the name of the column.
   * @param format the date format of the column.
   */
  public DateColumn(final String name, final String format) {
    super(name);
    this.setFormat(format);
  }

  private Date convertExcelDate(final String text) throws ColumnTypeMismatchException {
    try {
      return DateConversion.fromExcelSerialToDate(Double.parseDouble(text));
    } catch (final NumberFormatException e) {
      throw new ColumnTypeMismatchException();
    }
  }

  @Override
  public Value convertToValue(final String text) throws
          ColumnTypeMismatchException {
    Value val = convertToValueIsNullOrEmpty(text);
    if (val instanceof NullValue) {
      return val;
    } else {
    try {
      if ("excel".equalsIgnoreCase(formatStr)) {
        return new DateValue(convertExcelDate(text));
      }
      return new DateValue(format.parse(text));
    } catch (final ParseException e) {
      // Fall back on ISO
      try {
        return new DateValue(iso.parse(text));
      } catch (final ParseException ex) {
        throw getException(text);
      }
      }
    }
  }

  private Value convertToValueIsNullOrEmpty(final String text) {
    if ("null".equalsIgnoreCase(text) || text.isEmpty()) {
      return new NullValue();
    } else {
      return null;
    }
  }

  private ColumnTypeMismatchException getException(final String text) {
    return new ColumnTypeMismatchException("\"" + text + "\" does not satisfy the format \""
        + formatStr + "\"");
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

  @Override
  public void read(final Element element) throws WrongXmlException {
    final String elementFormat = element.getAttribute("format");

    if (elementFormat.isEmpty()) {
      throw new WrongXmlException("Format not specified!");
    }

    setFormat(elementFormat);
  }

  /**
   * Gives the column a new date format.
   *
   * @param format the new date format.
   */
  public void setFormat(final String format) {
    this.formatStr = format;

    if ("excel".equalsIgnoreCase(formatStr)) {
      this.format = null;
    } else {
      this.format = new SimpleDateFormat(format);
    }
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 67 * hash + Objects.hashCode(this.format);
    hash = 67 * hash + Objects.hashCode(this.formatStr);
    hash = 67 * hash + Objects.hashCode(this.iso);
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
    if (!Objects.equals(this.format, other.format)) {
      return false;
    }
    return Objects.equals(this.formatStr, other.formatStr);
  }

  @Override
  public String toString() {
    return super.toString() + ",\ttype: date,\tformat: " + formatStr;
  }
}
