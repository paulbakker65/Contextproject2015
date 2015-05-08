package parsers;

/**
 * Abstract class for representing a value in a record.
 * @author Robin
 *
 */
public abstract class Value {
	
	/**
	 * Returns whether a Value is numeric.
	 * @return whether a Value is numeric.
	 */
	public abstract boolean isNumeric();
	
	/**
	 * Returns whether a Value is a date.
	 * @return whether a Value is a date.
	 */
	public abstract boolean isDate();
	
	/**
	 * Returns whether a Value is a string.
	 * @return whether a Value is a string.
	 */
	public abstract boolean isString();
	
	/**
	 * Returns whether a Value is null, which means
	 * that no value is present.
	 * @return whether a Value is null.
	 */
	public abstract boolean isNull();
}
