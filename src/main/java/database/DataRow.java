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

	public String getDate() {
		String res = columns.get("date");

		if (res == null) {
			return "not found";
		}
		return res;
	}

}
