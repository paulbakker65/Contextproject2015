package table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Special ArrayList that contains the records.
 */
@SuppressFBWarnings(value = "SE_BAD_FIELD", justification = "We dont serialize")
public class Table extends ArrayList<Record> {

  private static final long serialVersionUID = 1L;
  private String name;
  private HashMap<String, Code> codes;
  private List<Chunk> chunks;

  /**
   * @return A string representation of the Table.
   */
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Record record : this) {
      sb.append(record.toString());
      sb.append(System.getProperty("line.separator"));
    }

    return sb.toString();
  }

  /**
   * Call the ArrayList constructor and initialize chunks.
   */
  public Table() {
    super();
    chunks = new ArrayList<Chunk>();
    codes = new HashMap<String, Code>();
  }

  @SuppressWarnings("unchecked")
  @Override
  public Object clone() {
    Table table = (Table) super.clone();
    table.chunks = new ArrayList<Chunk>(this.chunks);
    table.codes = (HashMap<String, Code>) codes.clone();
    return table;
  }

  /**
   * Getter for name.
   */
  public String getName() {
    return name;
  }

  /**
   * Setter for name.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Adding a chunk to the list of chunks for this table.
   * 
   * @param c
   *          chunk to add.
   */
  public void addChunk(Chunk chunk) {
    chunks.add(chunk);
  }

  /**
   * Adding a code to the hashmap of codes for this table.
   * 
   * @param c
   *          code to add.
   */
  public void addCode(Code code) {
    codes.put(code.getName(), code);
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
   * Getter for the list of chunks.
   */
  public List<Chunk> getChunks() {
    return this.chunks;
  }

  /**
   * Getter for the map of codes.
   */
  public HashMap<String, Code> getCodes() {
    return this.codes;
  }

  /**
   * Getter for a code given a name.
   */
  public Code getCode(String name) {
    return codes.get(name);
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
   * New equals method that also checks if the list of chunks is equal to that of the other table.
   * The same for the hashmap of codes.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!super.equals(obj)) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Table other = (Table) obj;
    if (!codes.equals(other.codes)) {
      return false;
    }
    return chunks.equals(other.chunks);
  }

}
