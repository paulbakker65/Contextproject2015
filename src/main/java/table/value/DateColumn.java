package table.value;

import input.WrongXMLException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
   * @param name
   *          the name of the column.
   */
  public DateColumn(String name) {
    this(name, "yyyy-MM-dd");
  }

  /**
   * Constructs a new DateColumn.
   * 
   * @param name
   *          the name of the column.
   * @param format
   *          the date format of the column.
   */
  public DateColumn(String name, String format) {
    super(name);
    this.setFormat(format);
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
  public void setFormat(String format) {
    this.formatStr = format;

    if (formatStr.toLowerCase().equals("excel")) {
      this.format = null;
    } else {
      this.format = new SimpleDateFormat(format);
    }
  }

  @Override
  public String toString() {
    return super.toString() + ",\ttype: date,\tformat: " + formatStr;
  }

  @Override
  public Value convertToValue(String text) throws ColumnTypeMismatchException {
    try {
      if (text.toLowerCase().equals("null") || text.isEmpty()) {
        return new NullValue();
      }
      if (formatStr.toLowerCase().equals("excel")) {
        return new DateValue(convertExcelDate(text));
      }

      return new DateValue(format.parse(text));
    } catch (ParseException e) {
      //Fall back on ISO
      try {
        return new DateValue(iso.parse(text));
      }
      catch (ParseException ex){
        throw getException(text);
      }
    }
  }
  
  private ColumnTypeMismatchException getException(String text) {
    return new ColumnTypeMismatchException("\"" + text + "\" does not satisfy the format \""
        + formatStr + "\"");
  }

  private Date convertExcelDate(String text) throws ColumnTypeMismatchException {
    try {
      return DateConversion.fromExcelSerialToDate(Double.parseDouble(text));
    } catch (NumberFormatException e) {
      throw new ColumnTypeMismatchException();
    }
  }

  @Override
  public void read(Element element) throws WrongXMLException {
    String format = element.getAttribute("format");

    if (format.isEmpty()) {
      throw new WrongXMLException("Format not specified!");
    }

    setFormat(format);
  }
}
