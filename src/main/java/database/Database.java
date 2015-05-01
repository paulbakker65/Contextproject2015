package database;

import java.util.ArrayList;
import java.util.Date;

public class Database {
	
	ArrayList<DataRow> rows;
	
	public Database() {
		rows = new ArrayList<DataRow>();
	}
	
	public int addRow(DataRow dr) {
		rows.add(dr);
		
		return 0;
	}
	
	public ArrayList<DataRow> getRowsByDate(Date dt) {
		ArrayList<DataRow> res = new ArrayList<DataRow>();
		
		for(int i = 0; i < this.rows.size(); i++) {
			if(rows.get(i).getDate().equals(dt)) {
				res.add(rows.get(i));
			}
		}
		
		return res;
	}

}
