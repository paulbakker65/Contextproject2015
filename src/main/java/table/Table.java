package table;

import java.util.ArrayList;

public class Table extends ArrayList<Record> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Table getPatientByID(String id) {
		Table table = new Table();
		 for(int i = 0; i < this.size(); i++) {
				if(this.get(i).get("Login").equals(id)){
					table.add(this.get(i));
				}
	
		 }
		return table;
	}
	
	
	public String toString() {
		String res = "";
		
		for (Record tuple : this) {
			res += tuple.toString() + "\n";
		}
		
		return res;
	}
	
}
