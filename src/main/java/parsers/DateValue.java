package parsers;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Case class for representing a date Value.
 * @author Robin
 *
 */
public class DateValue extends Value {
	private GregorianCalendar value;
	
	/**
	 * Constructs a new NumberValue
	 * @param value the stored date.
	 */
	public DateValue(Date value) {
		this.value = new GregorianCalendar();
		this.setValue(value);
	}

	/**
	 * Returns the stored date.
	 * @return the stored date.
	 */
	public GregorianCalendar getValue() {
		return value;
	}

	/**
	 * Stores a new number date.
	 * @param value the new number date.
	 */
	public void setValue(Date value) {
		this.value.setTime(value);;
	}
	
	public void addTime(GregorianCalendar time) {
		value.add(Calendar.HOUR, time.get(Calendar.HOUR));
		value.add(Calendar.MINUTE, time.get(Calendar.MINUTE));
		value.add(Calendar.MILLISECOND, time.get(Calendar.MILLISECOND));
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof DateValue) {
			DateValue that = (DateValue) other;
			
			return (this.value.equals(that.value));
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		return new SimpleDateFormat("yyyy-MM-dd").format(getValue().getTime());
	}

	@Override
	public boolean isNumeric() {
		return false;
	}

	@Override
	public boolean isDate() {
		return true;
	}

	@Override
	public boolean isString() {
		return false;
	}

	@Override
	public boolean isNull() {
		return false;
	}
	
	@Override
	public boolean isTime() {
		return false;
	}
}
