package input;

import org.w3c.dom.Element;

import parsers.NullValue;
import parsers.StringValue;
import parsers.Value;

/**
 * Case class for specifying a column with just text.
 * @author Robin
 *
 */
public class StringColumn extends Column {

	/**
	 * Constructs a new StringColumn.
	 * @param name the name of the column.
	 */
	public StringColumn(String name) {
		super(name);
	}
	
	@Override
	public String toString() {
		return super.toString() + ",\ttype: text";
	}

	@Override
	public Value convertToValue(String text) {
		if (text.toLowerCase().equals("null") || text.isEmpty())
			return new NullValue();
		
		return new StringValue(text);
	}

	@Override
	public void read(Element element) throws WrongXMLException {}
}
