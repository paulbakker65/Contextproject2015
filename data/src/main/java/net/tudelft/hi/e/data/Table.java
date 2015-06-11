package net.tudelft.hi.e.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Special ArrayList that contains the records.
 */
public class Table extends ArrayList<Record> {

  private static final long serialVersionUID = 1L;
  private String name;
  private transient Map<String, Code> codes;
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
   * @param c chunk to add.
   */
  public void addChunk(final Chunk chunk) {
    chunks.add(chunk);
  }

  /**
   * Adding a code to the hashmap of codes for this table.
   *
   * @param c code to add.
   */
  public void addCode(final Code code) {
    codes.put(code.getName(), code);
  }

  @SuppressWarnings("unchecked")
  @Override
  public Object clone() {
    final Table table = (Table) super.clone();
    table.chunks = new ArrayList<Chunk>(this.chunks);
    table.codes = (HashMap<String, Code>) ((HashMap<String, Code>) codes).
            clone();
    return table;
  }

  /**
   * New equals method that also checks if the list of chunks is equal to that
   * of the other table. The same for the hashmap of codes.
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
  public Map<String, Code> getCodes() {
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

}
