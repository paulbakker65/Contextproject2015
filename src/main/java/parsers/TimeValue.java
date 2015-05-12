package parsers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeValue extends Value {
	private GregorianCalendar value;
	private String targetDate;
	
	/**
	 * Constructs a new NumberValue
	 * @param value the stored date.
	 */
	public TimeValue(Date value, String targetDate) {
		this.targetDate = targetDate;
		this.value = new GregorianCalendar();
		this.value.setTime(value);
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
	public void setValue(GregorianCalendar value) {
		this.value = value;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof TimeValue) {
			TimeValue that = (TimeValue) other;
			
			return (this.value.equals(that.value));
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		return new SimpleDateFormat("hh:mm").format(getValue().getTime());
	}
	
	@Override
	public boolean isNumeric() {
		return false;
	}

	@Override
	public boolean isDate() {
		return false;
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
		return true;
	}

	public String getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(String targetDate) {
		this.targetDate = targetDate;
	}

}
