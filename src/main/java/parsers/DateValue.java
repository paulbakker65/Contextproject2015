package parsers;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateValue extends Value {
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
}
