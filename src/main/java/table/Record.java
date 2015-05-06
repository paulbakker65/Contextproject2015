package table;

import input.Column;

import java.util.ArrayList;
import java.util.HashMap;

public class Record extends HashMap<String, String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Record() {
		super();
	}
	
	public Record(ArrayList<Column> col, String[] val) {
		for(int i = 0; i < col.size(); i++) {
			this.put(col.get(i).getName(), val[i]);
		}
	}
	
	public String toString() {
		String res = "";
		
		for (String val : values()) {
			res += val + "\t";
		}
		
		return res;
	}
	
}
