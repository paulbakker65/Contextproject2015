package table;

import java.util.HashMap;

public class Tuple extends HashMap<String, String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Tuple(String[] col, String[] val) {
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
