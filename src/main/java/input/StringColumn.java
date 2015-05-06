package input;

public class StringColumn extends Column {

	public StringColumn(String name) {
		super(name);
	}
	
	public String toString() {
		return super.toString() + ",\ttype: text";
	}
}
