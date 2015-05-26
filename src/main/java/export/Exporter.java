package export;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import table.Record;
import table.Table;
import table.value.Value;

import com.opencsv.CSVWriter;

/**
 * Exporter class that outputs internal datastructures into output files.
 * 
 */
public class Exporter {

  public static void export(Table db, Writer writer) throws IOException {

    Set<String> keys = new TreeSet<String>();
    for (Record r : db) {
      keys.addAll(r.keySet());
    }

    CSVWriter csvwriter = new CSVWriter(writer, ';');
    csvwriter.writeNext(keys.toArray(new String[keys.size()])); // write
    // column
    // names

    for (Record dr : db) {
      csvwriter.writeNext(generateRow(dr, keys));
    }
    csvwriter.close();
  }

  public static String[] generateRow(Record dr, Set<String> columns) {
    List<String> items = new ArrayList<String>();
    for (String column : columns) {
      Value value = dr.get(column);
      if (value == null) {
        items.add("");
      }  
      else {
        items.add(value.toString());
      }    
    }
    return items.toArray(new String[items.size()]);
  }
}


