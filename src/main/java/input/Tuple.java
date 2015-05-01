package input;

import java.util.HashMap;

public class Tuple extends HashMap<String, String> {

	public Tuple(String[] col, String[] val) {
		for(int i = 0; i < col.length; i++) {
			this.put(col[i], val[i]);
		}
	}
	
}
