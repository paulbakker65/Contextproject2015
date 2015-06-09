package table;

import table.value.Column;
import table.value.Value;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A timed event that can contain various properties ("columns"). Because it extends a HashMap new
 * properties can be made on the fly.
 */
public class Record extends HashMap<String, Value> implements Serializable {

  /**
   * Serial version.
   */
  private static final long serialVersionUID = 1L;
  private String tableName;
  private List<String> keysInOrder;

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
    this.keysInOrder = new ArrayList<String>();
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
    this.tableName = tableName;
    this.keysInOrder = new ArrayList<String>();
    
    for (int i = 0; i < columns.size(); i++) {
      this.put(columns.get(i).getName(), values[i]);
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
    ArrayList<String> newKeysInOrder = new ArrayList<String>(keysInOrder);
    newKeysInOrder.set(newKeysInOrder.indexOf(oldname), newname);
    remove(oldname);
    put(newname, value);
    keysInOrder = newKeysInOrder;
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
  
  @Override
  public Value put(String key, Value value) {
    Value result = super.put(key, value);
    
    if (result == null) {
      keysInOrder.add(key);
    }
    
    return result;
  }
  
  @Override
  public Value remove(Object key) {
    Value result = super.remove(key);
    
    if (result != null) {
      keysInOrder.remove(key);
    }
    
    return result;
  }

  public List<String> getKeysInOrder() {
    return keysInOrder;
  }
}
