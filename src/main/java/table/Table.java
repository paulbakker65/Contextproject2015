package table;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import table.value.Column;
import table.value.DateColumn;
import table.value.NumberColumn;
import table.value.StringColumn;
import table.value.TimeColumn;
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
@SuppressFBWarnings(value = "SE_BAD_FIELD", justification = "We dont serialize")
public class Table extends ArrayList<Record> implements Serializable {
  private static final long serialVersionUID = 1L;
  private String name;
  private HashMap<String, Code> codes;
  private List<Chunk> chunks;

  /**
   * Call the ArrayList constructor and initialize chunks.
   */
  public Table() {
    super();
    chunks = new ArrayList<Chunk>();
    codes = new HashMap<String, Code>();
  }

  /**
   * Adding a chunk to the list of chunks for this table.
   *
   * @param c
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

  @SuppressWarnings("unchecked")
  @Override
  public Object clone() {
    final Table table = (Table) super.clone();
    table.chunks = new ArrayList<Chunk>(this.chunks);
    table.codes = (HashMap<String, Code>) codes.clone();
    return table;
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
   * Setter for the list of chunks.
   */
  public void setChunks(List<Chunk> set) {
    if (set == null) {
      set = new ArrayList<Chunk>();
    }
    this.chunks = set;
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
    return (value.isString() ? new StringColumn(name) : (value.isDate() ? new DateColumn(name)
        : (value.isNumeric() ? new NumberColumn(name) : (value.isTime() ? new TimeColumn(name)
            : null))));
  }

}
