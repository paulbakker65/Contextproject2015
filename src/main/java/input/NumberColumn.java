package input;

public class NumberColumn extends Column {

	public NumberColumn(String name) {
		super(name);
	}
	
	public String toString() {
		return super.toString() + ",\ttype: number";
	}
}
