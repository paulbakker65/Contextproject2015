package net.tudelft.hi.e.export;

import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import net.tudelft.hi.e.data.Chunk;
import net.tudelft.hi.e.data.Code;
import net.tudelft.hi.e.data.NullValue;
import net.tudelft.hi.e.data.Record;
import net.tudelft.hi.e.data.StringValue;
import net.tudelft.hi.e.data.Table;
import net.tudelft.hi.e.data.TableFile;
import net.tudelft.hi.e.data.Value;

/**
 * Exporter class that outputs internal datastructures into output files.
 */
public final class Exporter {
  public static char delimiter = ';';
  private static boolean ADD_CODE_FREQUENCY = true;

  /**
   * Default private constructor.
   */
  private Exporter() {
  }

  public static void setAddCodeFrequency(boolean value) {
    ADD_CODE_FREQUENCY = value;
  }

  /**
   * The export method writes a Table to a writer instance.
   *
   * @param table The Table to be written.
   * @param path The path where the Table has to been written to.
   * @param extension The extension the output file will have.
   * @throws IOException If the writing fails an IOException is thrown.
   */
  public static void export(Table table, String path, String extension) throws
          IOException {
    FileWriter writer = new FileWriter(path + extension);
    export(table, writer);
    TableFile.writeTable(table, path);
  }

  /**
   * The export method writes a Table to a writer instance.
   *
   * @param table
   *          The Table to be written.
   * @param writer
   *          The writer where the Table has to been written to.
   * @throws IOException
   *           If the writing fails an IOException is thrown.
   */
  public static void export(final Table table, final Writer writer) throws IOException {
    checkForEmptyKeys(table);
    checkForChunksColumn(table);
    checkForCodesColumn(table);
    checkForEmptyKeys(table);

    writeToFile(table, writer);
  }

  /**
   * The generateRow method generates String[] containing all fields of a record.
   *
   * @param dr
   *          The record that we want to convert.
   * @param columns
   *          The columns that the dr record contains.
   * @return String[] containing all the values of the record dr.
   */
  public static String[] generateRow(final Record dr, final List<String> columns) {
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

  private static void checkForChunksColumn(Table table) {
    checkForChunksColumn(table.getChunks(), 0);
  }

  private static void checkForChunksColumn(List<Chunk> chunks, int depth) {
    if (!chunks.isEmpty()) {
      for (Chunk chunk : chunks) {
        for (Record record : chunk) {
          record.put("Chunks " + depth, new StringValue(chunk.getLabel()));
        }
        checkForChunksColumn(chunk.getChunks(), depth + 1);
      }
    }
  }

  private static void checkForCodesColumn(Table table) {
    if (!table.getCodes().isEmpty()) {
      for (Record record : table) {
        record.put("Code", new NullValue());
      }
      for (Code code : table.getCodes().values()) {
        checkForCodeRecord(table, code);
        addCodeColumnsToRecord(code);
      }
    }
  }

  private static void addCodeColumnsToRecord(Code code) {
    for (Table events : code.getEvents()) {
      for (Record record : events) {
        record.put("Code", new StringValue(code.getName()));
      }
    }
  }

  private static void checkForCodeRecord(Table table, Code code) {
    if (ADD_CODE_FREQUENCY) {
      Record codeRecord = new Record();
      codeRecord.put("Code", new StringValue(code.getName() + "=" + code.getFrequency()));
      table.add(0, codeRecord);
    }
  }

  private static void checkForEmptyKeys(Table table) {
    Set<String> keySet = new TreeSet<String>();
    for (final Record record : table) {
      keySet.addAll(record.keySet());
    }

    for (final Record record : table) {
      for (String key : keySet) {
        if (record.get(key) == null) {
          record.put(key, new NullValue());
        }
      }
    }
  }

  private static void writeToFile(Table table, Writer writer) throws IOException {
    CSVWriter csvWriter = new CSVWriter(writer, delimiter);
    List<String> keys = table.get(table.size() - 1).getKeysInOrder();

    // Write column names.
    csvWriter.writeNext(keys.toArray(new String[keys.size()]));

    // Write records.
    for (final Record record : table) {
      csvWriter.writeNext(generateRow(record, keys));
    }
    csvWriter.close();
  }
}
