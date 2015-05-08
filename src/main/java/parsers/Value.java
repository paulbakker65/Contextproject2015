package parsers;

public abstract class Value {
	public abstract boolean isNumeric();
	public abstract boolean isDate();
	public abstract boolean isString();
	public abstract boolean isNull();
}
