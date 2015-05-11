package input;

import parsers.ColumnTypeMismatchException;
import parsers.Value;

/**
 * Abstract class for specifying a column type in a table.
 * It specifies how all the values in a column must look like.
 * @author Robin
 *
 */
public abstract class Column {
	/**
	 * Each column must have a name.
	 */
	protected String name;

	/**
	 * Constructs a new column with a name.
	 * @param name the name of the column.
	 */
	public Column(String name) {
		this.setName(name);
	}
	
	/**
	 * Convert a string into a value if it satisfies the column's type.
	 * @param text the string to convert
	 * @return a Value that has the correct type.
	 * @throws ColumnTypeMismatchException if the string cannot be converted.
	 */
	public abstract Value convertToValue(String text) throws ColumnTypeMismatchException;

	/**
	 * Returns the name of the column.
	 * @return the name of the column.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gives the column a new name.
	 * @param name the new name of the column.
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "name: " + name;
	}	
}
