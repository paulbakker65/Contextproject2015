package parsers;

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
	
	/**
	 * Returns whether a Value is a time.
	 * @return whether a Value is a time.
	 */
	public abstract boolean isTime();

	/**
	 * compareTo function using the Java-standard < 0 for less, > 0 for more and 0 for equal
	 * checks the instance of the Value to pick a specific compareTo overload.
	 * @param o Value object to compare to
	 * @return 0 if equal to o <br> 
	 * 		  -1 if less than o <br>
	 * 		   1 if more than o, or if the values cannot be compared <br>
	 */
	public int compareTo(Value o) {
		if (this instanceof StringValue) {
			if (o instanceof StringValue) {
				return ((StringValue)this).compareTo(((StringValue)o));
			}
			else {
				return Integer.MAX_VALUE;
			}
		}
		else if (this instanceof NumberValue) {
			if (o instanceof NumberValue) {
				return ((NumberValue)this).compareTo(((NumberValue)o));
			}
			else {
				return Integer.MAX_VALUE;
			}
		}
		else if (this instanceof DateValue) {
			if (o instanceof DateValue) {
				return ((DateValue)this).compareTo(((DateValue)o));
			}
			else {
				return Integer.MAX_VALUE;
			}
		}
		else if (this instanceof NullValue) {
			if (o instanceof NullValue) {
				return ((NullValue)this).compareTo(((NullValue)o));
			}
			else {
				return Integer.MAX_VALUE;
			}
		}
		return Integer.MAX_VALUE;
	}
}
