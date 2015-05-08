package input;

import parsers.ColumnTypeMismatchException;
import parsers.Value;

public abstract class Column {
	protected String name;
	
	public Column(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return "name: " + name;
	}
	
	public abstract Value convertToValue(String text) throws ColumnTypeMismatchException;
}
