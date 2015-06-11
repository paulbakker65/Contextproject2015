package net.tudelft.hi.e.input;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.tudelft.hi.e.common.exceptions.ParseFailedException;
import net.tudelft.hi.e.data.Column;
import net.tudelft.hi.e.data.ColumnTypeMismatchException;
import net.tudelft.hi.e.data.DateValue;
import net.tudelft.hi.e.data.Record;
import net.tudelft.hi.e.data.Table;
import net.tudelft.hi.e.data.TimeValue;
import net.tudelft.hi.e.data.Value;

/**
 * Class for parsing a file into a Table object.
 *
 * @author Robin
 *
 */
public class Parser {
  protected Settings settings;
  protected List<Column> columns;
  protected int numColumns;

  /**
   * Constructs a new parser given the settings to describe the file.
   *
   * @param settings the settings that describe the file.
   */
  public Parser(final Settings settings) {
    super();
    this.settings = settings;
    this.columns = settings.getColumns();
    this.numColumns = columns.size();
  }

  private void connectLinks(final Map<String, String> links, final Record record) {
    for (final Entry<String, String> entry : links.entrySet()) {
      final TimeValue timeValue = (TimeValue) record.get(entry.getKey());
      final DateValue dateValue = (DateValue) record.get(entry.getValue());

      dateValue.addTime(timeValue.getValue());
      timeValue.setValue(dateValue.getValue());
    }
  }

  /**
   * Returns the parser's columns.
   *
   * @return the parser's columns.
   */
  public List<Column> getColumns() {
    return columns;
  }

  /**
   * Returns the parser's number of columns.
   *
   * @return the parser's number of columns.
   */
  public int getNumberOfColumns() {
    return numColumns;
  }

  /**
   * Returns the parser's Settings.
   *
   * @return the parser's Settings.
   */
  public Settings getSettings() {
    return settings;
  }

  /**
   * Parses the file given the Reader that reads the file.
   *
   * @param reader the reader that reads the file.
   * @return a Table object which represents the read file as a table.
   * @throws ParseFailedException IF the file fails to parse.
   */
  public Table parse(final Reader reader) throws ParseFailedException {
    final Table table = new Table();
    try {
      for (int i = 0; i < settings.getStartLine() - 1; i++) {
        reader.readRow();
      }

      table.setName(settings.getName());

      String[] row = reader.readRow();

      // Read a row, convert the values and store them in the Table.
      while (row != null && row.length == numColumns) {
        Record tuple = parseConvertValuesAndCreateRow(row);
        table.add(tuple);

        row = reader.readRow();
      }
    } catch (IOException | ColumnTypeMismatchException ex) {
      throw new ParseFailedException(ex);
    }

    return table;
  }

  private Record parseConvertValuesAndCreateRow(final String[] row)
          throws ColumnTypeMismatchException {
    final Value[] values = new Value[numColumns];
    final Map<String, String> timeDateLinks = new HashMap<String, String>();
    for (int i = 0; i < columns.size(); i++) {
      values[i] = columns.get(i).convertToValue(row[i]);

      if (values[i].isTime()) {
        timeDateLinks.put(columns.get(i).getName(), ((TimeValue) values[i]).
                getTargetDate());
      }
    }
    final Record tuple = new Record(columns, values, settings.getName());
    connectLinks(timeDateLinks, tuple);
    return tuple;
  }
}
