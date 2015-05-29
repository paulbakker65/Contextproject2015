package table;

import java.util.HashMap;
import java.util.List;

import table.value.Column;
import table.value.Value;

/**
 * A timed event that can contain various properties ("collumns").
 * Because it extends a HashMap new properties can be made on the fly.
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
   * @param list The list of column names.
   * @param val An array of values.
   */
  public Record(List<Column> list, Value[] val) {
    for (int i = 0; i < list.size(); i++) {
      this.put(list.get(i).getName(), val[i]);
    }
  }

  /**
   * Creates a string containing all the values in this record.
   */
  public String toString() {
    StringBuilder builder = new StringBuilder();
    for (Value val : values()) {
      builder.append(val + "\t");
    }
    return builder.toString();
  }
  
  /**
   * Renames a column name/key
   * @param oldname The key to replace.
   * @param newname The new name to use.
   */
  public void rename(String oldname, String newname) {
    Value value = get(oldname);
    remove(oldname);
    put(newname, value);

  }

}
