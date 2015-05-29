package table;

import table.value.Column;
import table.value.Value;

import java.util.HashMap;
import java.util.List;

/**
 * A timed event that can contain various properties ("collumns"). Because it extends a HashMap new
 * properties can be made on the fly.
 */
public class Record extends HashMap<String, Value> {

  private static final long serialVersionUID = 1L;

  /**
   * Creates a Record.
   */
  public Record() {
    super();
  }

  /**
   * Creates a Record.
   * 
   * @param list The list of column names.
   * @param val An array of values.
   */
  public Record(final List<Column> list, final Value[] val) {
    for (int i = 0; i < list.size(); i++) {
      this.put(list.get(i).getName(), val[i]);
    }
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

}
