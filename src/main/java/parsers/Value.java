package parsers;

public abstract class Value implements Comparable<Value> {
	public abstract boolean isNumeric();
	public abstract boolean isDate();
	public abstract boolean isString();
	public abstract boolean isNull();
	
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
				return ((StringValue)this).compareTo(((StringValue)o));
			}
			else {
				return Integer.MAX_VALUE;
			}
		}
		return Integer.MAX_VALUE;
	}
}
