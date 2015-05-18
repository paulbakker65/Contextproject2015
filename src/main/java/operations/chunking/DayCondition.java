package operations.chunking;

import java.util.Calendar;
import java.util.GregorianCalendar;

import parsers.DateValue;
import parsers.Value;

public class DayCondition extends ChunkCondition {

	@Override
	public boolean matches(Value recordValue, Value check) {
		DateValue current = (DateValue) check;
		GregorianCalendar currentDate = current.getValue();
		DateValue record = (DateValue) recordValue;
		GregorianCalendar recordDate = record.getValue();
		if (recordDate.get(Calendar.DAY_OF_YEAR) == currentDate
				.get(Calendar.DAY_OF_YEAR)
				&& recordDate.get(Calendar.YEAR) == currentDate
						.get(Calendar.YEAR)) {

			return true;
		}
		return false;
	}

}
