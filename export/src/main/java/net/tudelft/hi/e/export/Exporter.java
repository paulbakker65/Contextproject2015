package net.tudelft.hi.e.export;

import com.opencsv.CSVWriter;

import net.tudelft.hi.e.common.exceptions.ExportException;
import net.tudelft.hi.e.common.exceptions.TableNotFoundException;
import net.tudelft.hi.e.data.Chunk;
import net.tudelft.hi.e.data.Code;
import net.tudelft.hi.e.data.NullValue;
import net.tudelft.hi.e.data.Record;
import net.tudelft.hi.e.data.StringValue;
import net.tudelft.hi.e.data.Table;
import net.tudelft.hi.e.data.TableFile;
import net.tudelft.hi.e.data.Value;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Exporter class that outputs internal data structures into output files.
 */
public final class Exporter {
  /**
   * Code column check.
   */
  private static final String CODE_STRING = "Code";
  /**
   * Delimiter to use in exporter.
   */
  private static char delimiter = ';';
  /**
   * Add the code frequency to the table or not.
   */
  private static boolean addCodeFrequency = true;

  /**
   * Default private constructor.
   */
  private Exporter() {
  }

  /**
   * Get the delimiter.
   *
   * @return the delimiter.
   */
  public static char getDelimiter() {
    return delimiter;
  }

  /**
   * Set a new delimiter.
   *
   * @param newDelimiter
   *         the new delmiiter.
   */
  public static void setDelimiter(char newDelimiter) {
    Exporter.delimiter = newDelimiter;
  }

  /**
   * Set if code frequency is added by the exporter.
   *
   * @param value
   *         true if the frequency must be added, otherwise false.
   */
  public static void setAddCodeFrequency(final boolean value) {
    addCodeFrequency = value;
  }

  /**
   * The export method writes a Table to a writer instance.
   *
   * @param table
   *         The Table to be written.
   * @param path
   *         The path where the Table has to been written to.
   * @param extension
   *         The extension the output file will have.
   *
   * @throws ExportException
   *         if a output filepath or table object is not found.
   */
  public static void export(final Table table, final String path, final String extension) throws
          ExportException {
    try {
      final FileWriter writer = new FileWriter(path + extension);
      export(table, writer);
      TableFile.writeTable(table, path);
    } catch (IOException e) {
      throw new ExportException(e);
    } catch (TableNotFoundException e) {
      throw new ExportException(e);
    }

  }

  /**
   * The export method writes a Table to a writer instance.
   *
   * @param table
   *         The Table to be written.
   * @param writer
   *         The writer where the Table has to been written to.
   *
   * @throws IOException
   *         If the writing fails an IOException is thrown.
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
   *         The record that we want to convert.
   * @param columns
   *         The columns that the dr record contains.
   *
   * @return String[] containing all the values of the record dr.
   */
  public static String[] generateRow(final Record dr, final List<String> columns) {
    final List<String> items = new ArrayList<>();
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
   * Check for a chunk whether a column should be added.
   *
   * @param table
   *         table to check.
   */
  private static void checkForChunksColumn(final Table table) {
    checkForChunksColumn(table.getChunks(), 0);
  }

  /**
   * Check for each chunk whether a column should be added.
   *
   * @param chunks
   *         to check.
   * @param depth
   *         the depth of chunks to process.
   */
  private static void checkForChunksColumn(final List<Chunk> chunks, final int depth) {
    if (!chunks.isEmpty()) {
      for (final Chunk chunk : chunks) {
        for (final Record record : chunk) {
          record.put("Chunks " + depth, new StringValue(chunk.getLabel()));
        }
        checkForChunksColumn(chunk.getChunks(), depth + 1);
      }
    }
  }

  /**
   * Check if codes must be added to the columns.
   *
   * @param table
   *         the table to check.
   */
  private static void checkForCodesColumn(final Table table) {
    if (!table.getCodes().isEmpty()) {
      for (final Record record : table) {
        record.put(CODE_STRING, new NullValue());
      }
      for (final Code code : table.getCodes().values()) {
        checkForCodeRecord(table, code);
        addCodeColumnsToRecord(code);
      }
    }
  }

  /**
   * Add Code column to each record that the code belongs to.
   *
   * @param code
   *         code to add columns to.
   */
  private static void addCodeColumnsToRecord(final Code code) {
    for (final Table events : code.getEvents()) {
      for (final Record record : events) {
        record.put(CODE_STRING, new StringValue(code.getName()));
      }
    }
  }

  /**
   * Checks whether a code row must be added with the frequency of each code.
   *
   * @param table
   *         table to add the row to.
   * @param code
   *         code to add teh row for.
   */
  private static void checkForCodeRecord(final Table table, final Code code) {
    if (addCodeFrequency) {
      final Record codeRecord = new Record();
      codeRecord.put(CODE_STRING, new StringValue(code.getName() + "=" + code.getFrequency()));
      table.add(0, codeRecord);
    }
  }

  /**
   * Check if the Table has empty keys and replace missing values with NullValues. This operation
   * iterates through the keys of each record to find the union of their key set. Every record gets
   * the union of key sets and there are NullValues placed at empty spots.
   *
   * @param table
   *         the table to check for empty keys.
   */
  private static void checkForEmptyKeys(final Table table) {
    final Set<String> keySet = new LinkedHashSet<>();
    for (final Record record : table) {
      keySet.addAll(record.keySet());
    }

    for (final Record record : table) {
      for (final String key : keySet) {
        if (record.get(key) == null) {
          record.put(key, new NullValue());
        }
      }
    }
  }

  /**
   * Write a {@see Table} to a file using a writer.
   *
   * @param table
   *         the table to export.
   * @param writer
   *         the writer to use.
   *
   * @throws IOException
   *         if the write operation fails by an IOException.
   */
  private static void writeToFile(final Table table, final Writer writer) throws IOException {
    final CSVWriter csvWriter = new CSVWriter(writer, delimiter);
    final List<String> keys = table.get(table.size() - 1).getKeysInOrder();

    // Write column names.
    csvWriter.writeNext(keys.toArray(new String[keys.size()]));

    // Write records.
    for (final Record record : table) {
      csvWriter.writeNext(generateRow(record, keys));
    }
    csvWriter.close();
  }
}
