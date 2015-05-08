package input;

import parsers.ColumnTypeMismatchException;
import parsers.NullValue;
import parsers.NumberValue;
import parsers.Value;

public class NumberColumn extends Column {

	public NumberColumn(String name) {
		super(name);
	}
	
	public String toString() {
		return super.toString() + ",\ttype: number";
	}

	@Override
	public Value convertToValue(String text) throws ColumnTypeMismatchException {
		try {
			if (text.toLowerCase().equals("null") || text.isEmpty())
				return new NullValue();
			
			return new NumberValue(Double.parseDouble(text));
		}
		catch (NumberFormatException e) {
			throw new ColumnTypeMismatchException();
		}
	}
}
