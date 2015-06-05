package table;

import java.io.Serializable;

/**
 * A chunk contains a table, index and a label.
 */
public class Chunk extends Table implements Serializable {

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
   * 
   * @param index Index of the chunk.
   * @param label Label of the chunk.
   */
  public Chunk(final int index, final String label) {
    this.index = index;
    this.label = label;
  }

  /**
   * New equals method which also checks index and label.
   */
  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (!super.equals(obj)) {
      return false;
    }
    final Chunk other = (Chunk) obj;
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

  /**
   * @return index of the chunk in the table.
   */
  public int getIndex() {
    return index;
  }

  /**
   * @return label of the chunk.
   */
  public String getLabel() {
    return label;
  }

  /**
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + index;
    result = prime * result + ((label == null) ? 0 : label.hashCode());
    return result;
  }

  /**
   * 
   * @param index of the chunk in the table which to set.
   */
  public void setIndex(final int index) {
    this.index = index;
  }

  /**
   * @param label of the chunk which to set.
   */
  public void setLabel(final String label) {
    this.label = label;
  }

}
