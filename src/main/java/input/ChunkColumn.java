package input;

import org.w3c.dom.Element;

import parsers.ChunkValue;
import parsers.ColumnTypeMismatchException;
import parsers.Value;
import table.Table;

public class ChunkColumn extends Column{

	public ChunkColumn(String name) {
		super(name);
	}

	@Override
	public Value convertToValue(String text) throws ColumnTypeMismatchException {
		return new ChunkValue(0, text, new Table());
	}

	@Override
	public void read(Element element) throws WrongXMLException {
		
	}
	
}
