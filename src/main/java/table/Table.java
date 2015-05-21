package table;

import java.util.ArrayList;
import java.util.List;

/**
 * Special ArrayList that contains the records.
 */
public class Table extends ArrayList<Record> {

  private static final long serialVersionUID = 1L;

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
  public Table(){
    super();
    chunks = new ArrayList<Chunk>();
  }
  
  
  @Override
  public Object clone() {
    Table t = (Table) super.clone();
    t.chunks = new ArrayList<Chunk>(this.chunks);
    return t;
  }
  
  
  /**
   * Adding a chunk to the list of chunk for this table.
   * @param c
   */
  public void addChunk(Chunk c) {
    chunks.add(c);
  }
  
  /**
   * Setter for the list of chunks.
   * @param set
   */
  public void setChunks(List<Chunk> set) {
    this.chunks = set;
  }
  
  /**
   * Getter for the list of chunks.
   * @return
   */
  public List<Chunk> getChunks() {
    return this.chunks;
  }

  /**
   * New equals method that also checks if the list of chunks is equal to that of the 
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
