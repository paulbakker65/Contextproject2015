package input;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import parsers.ColumnTypeMismatchException;
import parsers.DateValue;
import parsers.NullValue;
import parsers.Value;
import table.DateConversion;


public class DateColumn extends Column {
	private DateFormat format;
	private String formatStr;
	
	public DateColumn(String name) {
		super(name);
	}
	
	public DateColumn(String name, String format) {
		super(name);
		this.setFormat(format);
	}

	public DateFormat getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.formatStr = format.toLowerCase();
		
		if (formatStr.equals("excel"))
			this.format = null;
		else
			this.format = new SimpleDateFormat(format);
	}
	
//	public DateColumn(String format, Locale language) {
//		this.format = new SimpleDateFormat(format, language);
//	}
	
	public String toString() {
		return super.toString() + ",\ttype: date,\tformat: " + formatStr;
	}

	@Override
	public Value convertToValue(String text) throws ColumnTypeMismatchException {
		try {
			if (text.toLowerCase().equals("null") || text.isEmpty())
				return new NullValue();
			if (formatStr.equals("excel"))
				return new DateValue(convertExcelDate(text));
				
			return new DateValue(format.parse(text));
		}
		catch (ParseException e) {
			throw new ColumnTypeMismatchException(e.getMessage());
		}
	}
	
	private Date convertExcelDate(String text) throws ColumnTypeMismatchException {
		try {
			return DateConversion.fromExcelSerialToDate(Double.parseDouble(text));
		}
		catch (NumberFormatException e) {
			throw new ColumnTypeMismatchException();
		}
	}
}
