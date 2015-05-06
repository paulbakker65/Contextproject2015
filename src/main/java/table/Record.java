package table;

import java.util.HashMap;

public class Record extends HashMap<String, String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Record() {
		super();
	}
	
	public Record(String[] col, String[] val) {
		super();
		for(int i = 0; i < col.length; i++) {
			this.put(col[i], val[i]);
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
