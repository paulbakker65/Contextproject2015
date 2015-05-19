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
   * @return A string representation of the Table
   */
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Record record : this) {
      sb.append(record.toString());
      sb.append(System.getProperty("line.separator"));
    }

    return sb.toString();
  }
  
  public void addChunk(Chunk c) {
    if (chunks == null) {
      chunks = new HashSet<Chunk>();
    }
    chunks.add(c);
  }
  
  public void setChunks(Set<Chunk> set) {
    this.chunks = set;
  }
  
  public Set<Chunk> getChunks() {
    return this.chunks;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((chunks == null) ? 0 : chunks.hashCode());
    return result;
  }

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
    if (chunks == null) {
      if (other.chunks != null) {
        return false;
      }
    } else if (!chunks.equals(other.chunks)) {
      return false;
    }
    return true;
  }
  


}
