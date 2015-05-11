package parsers;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateValue extends Value implements Comparable<DateValue> {
	private Date value;
	
	public DateValue(Date value) {
		this.setValue(value);
	}

	public Date getValue() {
		return value;
	}

	public void setValue(Date value) {
		this.value = value;
	}
	
	public boolean equals(Object other) {
		if (other instanceof DateValue) {
			DateValue that = (DateValue) other;
			
			return (this.value.equals(that.value));
		}
		
		return false;
	}
	
	public String toString() {
		return new SimpleDateFormat("yyyy-MM-dd").format(value);
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

	public int compareTo(DateValue o) {
		return this.value.compareTo(o.value);
	}
}
