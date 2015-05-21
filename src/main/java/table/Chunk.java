package table;

/**
 * A chunk contains a table, index and a label.
 */
public class Chunk extends Table {

  /**
   * Serial version.
   */
  private static final long serialVersionUID = 1L;
  /**
   * Label of the chunk.
   */
  private String label;
  /**
   * The index of the chunk in the table.
   */
  private int index;
  
  /**
   * Constructor sets the label and index of the chunk.
   * @param index
   * @param label
   */
  public Chunk(int index, String label) {
    this.index = index;
    this.label = label;
  }
  
  /**
   * @return index of the chunk in the table.
   */
  public int getIndex() {
    return index;
  }
  
  /**
   * 
   * @param index of the chunk in the table which to set.
   */
  public void setIndex(int index) {
    this.index = index;
  }
  
  /**
   * @return label of the chunk.
   */
  public String getLabel() {
    return label;
  }
  
  /**
   * @param label of the chunk which to set.
   */
  public void setLabel(String label) {
    this.label = label;
  }
  
  
  /**
   * New equals method which also checks index and label.
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
    Chunk other = (Chunk) obj;
    if (index != other.index) {
      return false;
    }  
    if (label == null) {
      if (other.label != null) {
        return false;
      }  
    } else if (!label.equals(other.label)) {
      return false;
    } 
    return true;
  }

}
