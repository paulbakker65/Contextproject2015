package export;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import parsers.Value;

import com.opencsv.CSVWriter;

import table.*;

public class Exporter {

	public static void export(Table db, Writer writer, Set<String> colNames)
			throws IOException {
	

		CSVWriter csvwriter = new CSVWriter(writer, ";"
				.charAt(0));
		csvwriter.writeNext(colNames.toArray(new String[colNames.size()])); // write
																			// column
																			// names
		for (Record dr : db) {
			csvwriter.writeNext(generateRow(dr, colNames));
		}
		csvwriter.close();
	}

	public static String[] generateRow(Record dr, Set<String> columns) {
		List<String> items = new ArrayList<String>();
		for (String column : columns) {
			Value value = dr.get(column);
			if (value == null)
				items.add("");
			else
				items.add(value.toString());
		}
		return items.toArray(new String[items.size()]);
	}

}
