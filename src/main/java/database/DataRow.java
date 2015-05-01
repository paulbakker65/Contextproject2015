package database;

import java.util.Date;
import java.util.HashMap;

public class DataRow {
	
	HashMap<String, String> columns;
	
	public DataRow() {
		columns = new HashMap<String, String>();
	}
	
	public int addColumn(String columnName, String columnElement) {
		columns.put(columnName, columnElement);
		
		return 0;
	}
	
	public Date getDate() {
		String res = columns.get("date");
		
		if(res == null) {
			throw new Exception("Date column not found");
		}
		
		return res;
	}

}
