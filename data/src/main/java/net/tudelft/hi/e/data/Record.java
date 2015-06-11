package net.tudelft.hi.e.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * A timed event that can contain various properties ("columns"). Because it extends a HashMap new
 * properties can be made on the fly.
 */
public class Record extends HashMap<String, Value> {
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
    List<String> newKeysInOrder = new ArrayList<String>(keysInOrder);
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

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 11 * hash + Objects.hashCode(this.tableName);
    hash = 11 * hash + Objects.hashCode(this.keysInOrder);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Record other = (Record) obj;
    return deepEquals(other);
  }

  private boolean deepEquals(Record other) {
    return equalTableName(other) && equalKeysInOrder(other) && equalSuper(other);
  }

  private boolean equalTableName(Record other) {
    return Objects.equals(this.tableName, other.tableName);
  }

  private boolean equalKeysInOrder(Record other) {
    return Objects.equals(this.keysInOrder, other.keysInOrder);
  }

  private boolean equalSuper(Record other) {
    return super.equals(other);
  }

}
