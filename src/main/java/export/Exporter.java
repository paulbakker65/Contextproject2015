package export;

import input.Column;
import input.Settings;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.opencsv.CSVWriter;

import table.*;

public class Exporter {

	public static void export(Table db, Writer writer, Settings settings) throws IOException {
		List<Column> temp = settings.getColumns(); //Get all the columns
		List<String> columns = new ArrayList<String>();
		for(Column col: temp) {
			columns.add(col.getName());
		}

		CSVWriter csvwriter = new CSVWriter(writer);
		csvwriter.writeNext(columns.toArray(new String[columns.size()])); //write column names
		for (Record dr : db){
			csvwriter.writeNext(generateRow(dr,columns));
		}
		csvwriter.close();
	}
	
	public static Set<String> getAllColumns(Table db){
		Set<String> col = new HashSet<String>();
		for (Record dr : db) {
			col.addAll(dr.keySet());
		}
		return col;
	}
	
	public static String[] generateRow(Record dr, List<String> columns){
		List<String> items = new ArrayList<String>();
		for (String column : columns){
			String value = dr.get(column);
			if (value==null)
				items.add("");
			else
				items.add(value);
		}
		return items.toArray(new String[items.size()]);
	}
	

}
