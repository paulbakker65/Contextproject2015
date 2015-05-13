package input;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.w3c.dom.Element;

import parsers.ColumnTypeMismatchException;
import parsers.NullValue;
import parsers.TimeValue;
import parsers.Value;

public class TimeColumn extends Column {
	private String targetDate;
	/**
	 * For each column the format must be specified.
	 */
	private DateFormat format;
	private String formatStr;
	
	/**
	 * Constructs a new DateColumn using a default format.
	 * @param name the name of the column.
	 */
	public TimeColumn(String name) {
		this(name, "hhmm");
	}
	
	/**
	 * Constructs a new DateColumn.
	 * @param name the name of the column.
	 * @param format the date format of the column.
	 */
	public TimeColumn(String name, String format) {
		super(name);
		this.setFormat(format);
	}
	
	/**
	 * Returns the column's date format.
	 * @return the column's date format.
	 */
	public DateFormat getFormat() {
		return format;
	}
	
	/**
	 * Returns the column's date format as string.
	 * @return the column's date format as string.
	 */
	public String getFormatStr() {
		return formatStr;
	}

	/**
	 * Gives the column a new date format.
	 * @param format the new date format.
	 */
	public void setFormat(String format) {
		this.formatStr = format;		
		this.format = new SimpleDateFormat(format);
	}
	
	@Override
	public String toString() {
		return super.toString() + ",\ttype: time,\tformat: " + formatStr;
	}
	
	@Override
	public Value convertToValue(String text) throws ColumnTypeMismatchException {
		try {
			if (text.toLowerCase().equals("null") || text.isEmpty())
				return new NullValue();				
			return new TimeValue(format.parse(text), targetDate);
		}
		catch (ParseException e) {
			throw new ColumnTypeMismatchException("\"" + text + "\" does not satisfy the format \"" + formatStr + "\"");
		}
	}
	
	@Override
	public void read(Element element) throws WrongXMLException {
		String format = element.getAttribute("format");
		
		if (format.isEmpty())
			throw new WrongXMLException("Format not specified!");
		
		setFormat(format);		
		
		String target = element.getAttribute("target");
		
		if (target.isEmpty())
			throw new WrongXMLException("Target not specified!");
		
		setTargetDate(target);		
	}

	public String getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(String targetDate) {
		this.targetDate = targetDate;
	}
}
