package input;

import parsers.ColumnTypeMismatchException;
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
		return new StringValue(text);
	}
}
