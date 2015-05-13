package input;

import org.w3c.dom.Element;

import parsers.ColumnTypeMismatchException;
import parsers.NullValue;
import parsers.NumberValue;
import parsers.Value;

/**
 * Case class for specifying a column with numbers.
 * @author Robin
 *
 */
public class NumberColumn extends Column {

	/**
	 * Constructs a new NumberColumn.
	 * @param name the name of the column.
	 */
	public NumberColumn(String name) {
		super(name);
	}
	
	@Override
	public String toString() {
		return super.toString() + ",\ttype: number";
	}

	@Override
	public Value convertToValue(String text) throws ColumnTypeMismatchException {
		try {
			if (text.toLowerCase().equals("null") || text.isEmpty()) {
        return new NullValue();
      }
			
			return new NumberValue(Double.parseDouble(text));
		}
		catch (NumberFormatException e) {
			throw new ColumnTypeMismatchException("\"" + text + "\" is not a numeric value");
		}
	}

	@Override
	public void read(Element element) throws WrongXMLException {}
}
