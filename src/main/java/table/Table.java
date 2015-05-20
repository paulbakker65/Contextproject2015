package table;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Special ArrayList that contains the records.
 */
public class Table extends ArrayList<Record> {

  private static final long serialVersionUID = 1L;

  private Set<Chunk> chunks;
  
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
  public Table(){
    super();
    chunks = new HashSet<Chunk>();
  }
  
  
  @Override
  public Object clone() {
    Table t = (Table) super.clone();
    t.chunks = new HashSet<Chunk>(this.chunks);
    return t;
  }
  
  
  /**
   * Adding a chunk to the set of chunk for this table.
   * @param c
   */
  public void addChunk(Chunk c) {
    chunks.add(c);
  }
  
  /**
   * Setter for the set of chunks.
   * @param set
   */
  public void setChunks(Set<Chunk> set) {
    this.chunks = set;
  }
  
  /**
   * Getter for the set of chunks.
   * @return
   */
  public Set<Chunk> getChunks() {
    return this.chunks;
  }

  /**
   * New equals method that also checks if the set of chunks is equal to that of the 
   * other table.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!super.equals(obj)) {
      return false;
    }
    if (!(obj instanceof Table)){
      return false;
    }
    Table other = (Table) obj;
    return chunks.equals(other.chunks);
  }
  


}
