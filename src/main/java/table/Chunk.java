package table;

/**
 * A chunk contains a table, index and a label.
 */
public class Chunk extends Table {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private String label;
  private int index;
  
  public Chunk(int index, String label) {
    this.index = index;
    this.label = label;
  }
  
  public int getIndex() {
    return index;
  }
  public void setIndex(int index) {
    this.index = index;
  }
  public String getLabel() {
    return label;
  }
  public void setLabel(String label) {
    this.label = label;
  }
  

}
