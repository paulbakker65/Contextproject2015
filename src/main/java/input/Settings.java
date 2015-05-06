package input;
import java.util.ArrayList;


public class Settings {
	private int startLine;
	private String delimiter;
	private ArrayList<Column> columns;
	
	public Settings() {
		startLine = 1;
		delimiter = ",";
		setColumns(new ArrayList<Column>());		
	}
	
	public int getStartLine() {
		return startLine;
	}
	
	public void setStartLine(int startLine) {
		this.startLine = startLine;
	}
	
	public String getDelimiter() {
		return delimiter;
	}
	
	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	public ArrayList<Column> getColumns() {
		return columns;
	}

	public void setColumns(ArrayList<Column> columns) {
		this.columns = columns;
	}	
	
	public void addColumn(Column column) {
		columns.add(column);
	}
	
	public String toString() {
		String res = "startLine:\t" + startLine + "\n";
		res += "delimiter:\t\"" + delimiter + "\"\n";
		res += "columns:\t";
		
		for (Column column : columns) {
			res += column.toString() + "\n\t\t";
		}
		
		return res;
	}
}
