package net.tudelft.hi.e.data;

import java.io.Serializable;
import java.util.Objects;

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
   * @param index
   *          Index of the chunk.
   * @param label
   *          Label of the chunk.
   */
  public Chunk(final int index, final String label) {
    super();
    this.index = index;
    this.label = label;
  }

  /**
   * Constructs a new Chunk copying the old chunk's field and using the new data Table.
   *
   * @param oldChunk
   *          the old chunk to use.
   * @param newData
   *          the new data to use.
   */
  public Chunk(Chunk oldChunk, Table newData) {
    super(newData);
    this.index = oldChunk.index;
    this.label = new String(oldChunk.label);
  }

  /**
   * New equals method which also checks index and label.
   */
  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Chunk other = (Chunk) obj;
    return deepEquals(other);
  }

  private boolean deepEquals(Chunk other) {
    return equalLabel(other) && equalIndex(other);
  }

  private boolean equalLabel(Chunk other) {
    return Objects.equals(this.label, other.getLabel());
  }

  private boolean equalIndex(Chunk other) {
    return Objects.equals(this.index, other.getIndex());
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
   * @param index
   *          of the chunk in the table which to set.
   */
  public void setIndex(final int index) {
    this.index = index;
  }

  /**
   * @param label
   *          of the chunk which to set.
   */
  public void setLabel(final String label) {
    this.label = label;
  }

}
