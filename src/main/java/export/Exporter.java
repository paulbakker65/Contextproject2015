package export;

import com.opencsv.CSVWriter;

import table.Record;
import table.Table;
import table.value.Value;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Exporter class that outputs internal datastructures into output files.
 */
public final class Exporter {

  /**
   * The export method writes a Table to a writer instance.
   *
   * @param db The Table to be written.
   * @param writer The writer where the Table has to been written to.
   * @throws IOException If the writing fails an IOException is thrown.
   */
  public static void export(final Table db, final Writer writer) throws IOException {

    final Set<String> keys = new TreeSet<String>();
    for (final Record r : db) {
      keys.addAll(r.keySet());
    }

    final CSVWriter csvwriter = new CSVWriter(writer, ';');
    csvwriter.writeNext(keys.toArray(new String[keys.size()])); // write
    // column
    // names

    for (final Record dr : db) {
      csvwriter.writeNext(generateRow(dr, keys));
    }
    csvwriter.close();
  }

  /**
   * The generateRow method generates String[] containing all fields of a record.
   *
   * @param dr The record that we want to convert.
   * @param columns The columns that the dr record contains.
   * @return String[] containing all the values of the record dr.
   */
  public static String[] generateRow(final Record dr, final Set<String> columns) {
    final List<String> items = new ArrayList<String>();
    for (final String column : columns) {
      final Value value = dr.get(column);
      if (value == null) {
        items.add("");
      } else {
        items.add(value.toString());
      }
    }
    return items.toArray(new String[items.size()]);
  }

  /**
   * Default private constructor.
   */
  private Exporter() {}
}
