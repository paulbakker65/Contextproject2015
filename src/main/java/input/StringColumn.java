package input;

import parsers.ColumnTypeMismatchException;
import parsers.NullValue;
import parsers.StringValue;
import parsers.Value;

public class StringColumn extends Column {

	public StringColumn(String name) {
		super(name);
	}
	
	public String toString() {
		return super.toString() + ",\ttype: text";
	}

	@Override
	public Value convertToValue(String text) throws ColumnTypeMismatchException {
		if (text.toLowerCase().equals("null") || text.isEmpty())
			return new NullValue();
		
		return new StringValue(text);
	}
}
