package input;

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
}
