package table;

import table.value.Column;
import table.value.Value;

import java.util.HashMap;
import java.util.List;

/**
 * A timed event that can contain various properties ("columns"). Because it extends a HashMap new
 * properties can be made on the fly.
 */
public class Record extends HashMap<String, Value> {
  private static final long serialVersionUID = 1L;
  private String tableName;

  /**
   * Creates a Record.
   * 
   * @param tableName
   *        the original file name.
   */
  public Record() {
    this("");
  }
  
  /**
   * Creates a Record.
   */
  public Record(String tableName) {
    super();
    this.tableName = tableName;
  }

  /**
   * Creates a Record.
   * 
   * @param columns 
   *        The list of column names.
   * @param values 
   *        An array of values.
   * @param tableName
   *        the original file name.
   */
  public Record(final List<Column> columns, final Value[] values) {
    this(columns, values, "");
  }
  
  /**
   * Creates a Record.
   * 
   * @param columns 
   *        The list of column names.
   * @param values 
   *        An array of values.
   * @param tableName
   *        the original file name.
   */
  public Record(final List<Column> columns, final Value[] values, String tableName) {
    super();
    for (int i = 0; i < columns.size(); i++) {
      this.put(columns.get(i).getName(), values[i]);
    }
    this.tableName = tableName;
  }

  /**
   * Renames a column name/key
   * 
   * @param oldname The key to replace.
   * @param newname The new name to use.
   */
  public void rename(final String oldname, final String newname) {
    final Value value = get(oldname);
    remove(oldname);
    put(newname, value);

  }

  /**
   * Creates a string containing all the values in this record.
   */
  @Override
  public String toString() {
    final StringBuilder builder = new StringBuilder();
    for (final Value val : values()) {
      builder.append(val + "\t");
    }
    return builder.toString();
  }

  /**
   * Returns the original file name.
   * @return the original file name.
   */
  public String getTableName() {
    return tableName;
  }
}
