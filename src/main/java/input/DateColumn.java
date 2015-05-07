package input;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
		this.formatStr = format;
		this.format = new SimpleDateFormat(format);
	}
	
//	public DateColumn(String format, Locale language) {
//		this.format = new SimpleDateFormat(format, language);
//	}
	
	public String toString() {
		return super.toString() + ",\ttype: date,\tformat: " + formatStr;
	}
}
