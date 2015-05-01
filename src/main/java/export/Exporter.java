package export;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.opencsv.CSVWriter;

import database.DataRow;
import database.Database;

public class Exporter {

	public static void export(Database db, Writer writer) {
		List<String> columns = new ArrayList(getAllColumns(db)); //Get all the columns

		CSVWriter csvwriter = new CSVWriter(writer);
		csvwriter.writeNext(columns.toArray(new String[columns.size()])); //write column names
		for (DataRow dr : db.getRows()){
			csvwriter.writeNext(generateRow(dr,columns));
		}
	}
	
	public static Set<String> getAllColumns(Database db){
		Set<String> col = new HashSet<String>();
		for (DataRow dr : db.getRows()) {
			col.addAll(dr.getHashMap().keySet());
		}
		return col;
	}
	
	public static String[] generateRow(DataRow dr, List<String> columns){
		List<String> items = new ArrayList<String>();
		for (String column : columns){
			String value = dr.getHashMap().get(column);
			if (value==null)
				items.add("");
			else
				items.add(value);
		}
		return items.toArray(new String[items.size()]);
	}
	

}
