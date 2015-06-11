package table;

import table.value.Column;
import table.value.Value;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Special ArrayList that contains the records.
 */
public class Table extends ArrayList<Record> implements Serializable {
  private static final long serialVersionUID = 1L;
  private String name;
  private HashMap<String, Code> codes;
  private List<Chunk> chunks;

  /**
   * Call the ArrayList constructor and initialize own fields.
   */
  public Table() {
    super();
    name = "";
    chunks = new ArrayList<Chunk>();
    codes = new HashMap<String, Code>();
  }

  /**
   * Constructs a Table by copying the other Table's fields.
   * 
   * @param otherTable
   *          the other Table.
   */
  public Table(Table otherTable) {
    this(otherTable, false);
  }

  /**
   * Constructs a Table by copying the other Table's fields.
   * 
   * @param otherTable
   *          the other Table.
   * @param copyRecords
   *          whether the Records must be copied.
   */
  public Table(Table otherTable, boolean copyRecords) {
    super(otherTable);
    if (copyRecords) {
      clear();
      for (Record record : otherTable) {
        add(new Record(record));
      }
    }
    name = new String(otherTable.name);
    chunks = new ArrayList<Chunk>(otherTable.chunks);
    codes = new HashMap<String, Code>(otherTable.codes);
  }

  /**
   * Adding a chunk to the list of chunks for this table.
   *
   * @param chunk
   *          chunk to add.
   */
  public void addChunk(final Chunk chunk) {
    chunks.add(chunk);
  }

  /**
   * Adding a code to the hashmap of codes for this table.
   *
   * @param c
   *          code to add.
   */
  public void addCode(final Code code) {
    codes.put(code.getName(), code);
  }

  /**
   * New equals method that also checks if the list of chunks is equal to that of the other table.
   * The same for the hashmap of codes.
   */
  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (!super.equals(obj)) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Table other = (Table) obj;
    if (!codes.equals(other.codes)) {
      return false;
    }
    return chunks.equals(other.chunks);
  }

  /**
   * Getter for the list of chunks.
   */
  public List<Chunk> getChunks() {
    return this.chunks;
  }

  /**
   * Getter for a code given a name.
   */
  public Code getCode(final String name) {
    return codes.get(name);
  }

  /**
   * Getter for the map of codes.
   */
  public HashMap<String, Code> getCodes() {
    return this.codes;
  }

  /**
   * Setter for the map of codes.
   * 
   * @param codes
   *          the new codes.
   */
  // public void setCodes(HashMap<String, Code> codes) {
  // this.codes = codes;
  // }

  /**
   * Getter for name.
   */
  public String getName() {
    return name;
  }

  /**
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + chunks.hashCode();
    result = prime * result + codes.hashCode();
    return result;
  }

  /**
   * Setter for name.
   */
  public void setName(final String name) {
    this.name = name;
  }

  /**
   * @return A string representation of the Table.
   */
  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    for (final Record record : this) {
      sb.append(record.toString());
      sb.append(System.getProperty("line.separator"));
    }

    return sb.toString();
  }

  @Override
  public boolean addAll(Collection<? extends Record> collection) {
    super.addAll(collection);

    if (collection instanceof Table) {
      this.chunks.addAll(((Table) collection).chunks);
      this.codes.putAll(((Table) collection).codes);
    }
    return true;
  }

  /**
   * Returns the list of present column types.
   * 
   * @return the list of present column types.
   */
  public List<Column> getColumns() {
    List<Column> res = new ArrayList<Column>();
    if (isEmpty()) {
      return res;
    }

    Record record = get(size() - 1);

    for (String name : record.getKeysInOrder()) {
      Column columnType = getColumnType(name);

      if (columnType != null) {
        res.add(columnType);
      }      
    }

    return res;
  }

  private Column getColumnType(String name) {
    Iterator<Record> iterator = iterator();
    Record finger = iterator.next();

    while (iterator.hasNext() && finger.get(name).isNull()) {
      finger = iterator.next();
    }

    return getColumnType(name, finger.get(name));
  }

  private Column getColumnType(String name, Value value) {
    return value.getType(name);
  }

}
