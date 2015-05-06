package table;

import java.util.ArrayList;

public class Table extends ArrayList<Record> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Get a Table containing all tables entries for a given patient id
	 * 
	 * @param id The requested patient id
	 * @param col The header of the column containing the user id
	 * @return A Table object with all row's for a given user.
	 */
	public Table getPatientByID(String id, String col) {
		Table table = new Table();
		 for(int i = 0; i < this.size(); i++) {
				if(this.get(i).get(col).equals(id)){
					table.add(this.get(i));
				}
	
		 }
		return table;
	}
	
	/**
	 * @return A string representation of the Table
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Record record : this) {
			sb.append(record.toString());
			sb.append(System.getProperty("line.separator"));
		}

		return sb.toString();
	}
	
}
