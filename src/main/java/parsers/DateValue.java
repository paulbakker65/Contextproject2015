package parsers;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Case class for representing a date Value.
 * @author Robin
 *
 */
public class DateValue extends Value {
	private Date value;
	
	/**
	 * Constructs a new NumberValue
	 * @param value the stored date.
	 */
	public DateValue(Date value) {
		this.setValue(value);
	}

	/**
	 * Returns the stored date.
	 * @return the stored date.
	 */
	public Date getValue() {
		return value;
	}

	/**
	 * Stores a new number date.
	 * @param value the new number date.
	 */
	public void setValue(Date value) {
		this.value = value;
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
