package parsers;
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
}
