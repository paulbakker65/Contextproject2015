package net.tudelft.hi.e.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * A timed event that can contain various properties ("columns"). Because it extends a HashMap new
 * properties can be made on the fly.
 */
public class Record extends LinkedHashMap<String, Value> implements Serializable {

  /**
   * Serial version.
   */
  private static final long serialVersionUID = 1L;
  private String tableName;

  /**
   * Creates a Record.
   *
   * @param tableName
   *          the original file name.
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
   *          The list of column names.
   * @param values
   *          An array of values.
   * @param tableName
   *          the original file name.
   */
  public Record(final List<Column> columns, final Value[] values) {
    this(columns, values, "");
  }

  /**
   * Creates a Record.
   *
   * @param columns
   *          The list of column names.
   * @param values
   *          An array of values.
   * @param tableName
   *          the original file name.
   */
  public Record(final List<Column> columns, final Value[] values, String tableName) {
    super();
    this.tableName = tableName;

    for (int i = 0; i < columns.size(); i++) {
      this.put(columns.get(i).getName(), values[i]);
    }
  }

  /**
   * Creates a record by copying the other record.
   *
   * @param otherRecord
   *          the record to copy.
   */
  public Record(Record otherRecord) {
    super(otherRecord);
    this.tableName = new String(otherRecord.tableName);
  }

  /**
   * Renames a column name/key
   *
   * @param oldName
   *          The key to replace.
   * @param newName
   *          The new name to use.
   */
  public void rename(final String oldName, final String newName) {
    Map<String, Value> entries = new LinkedHashMap<String, Value>(this);
    clear();

    for (String key : entries.keySet()) {
      if (key.equals(oldName)) {
        put(newName, entries.get(key));
      } else {
        put(key, entries.get(key));
      }
    }
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
   *
   * @return the original file name.
   */
  public String getTableName() {
    return tableName;
  }

  /**
   * Returns the list of keys in the order they are inserted.
   *
   * @return the list of keys in the order they are inserted.
   */
  public List<String> getKeysInOrder() {
    return new ArrayList<String>(keySet());
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 11 * hash + Objects.hashCode(this.tableName);
    hash = 11 * hash + Objects.hashCode(this.getKeysInOrder());
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
    return equalTableName(other) && equalSuper(other);
  }

  private boolean equalTableName(Record other) {
    return Objects.equals(this.tableName, other.tableName);
  }

  private boolean equalSuper(Record other) {
    return super.equals(other);
  }

}
