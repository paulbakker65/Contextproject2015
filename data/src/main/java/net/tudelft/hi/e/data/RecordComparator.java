package net.tudelft.hi.e.data;

import java.util.Comparator;

/**
 * Compares records based on a column.
 */
public class RecordComparator implements Comparator<Record> {

  private final String col;

  /**
   * Makes a new Record comparator.
   *
   * @param col the column to compare on
   */
  public RecordComparator(final String col) {
    this.col = col;
  }

  /**
   * Compares the values using their own compareTo methods.
   */
  @Override
  public int compare(final Record o1, final Record o2) {
    final Value v1 = o1.get(col);
    final Value v2 = o2.get(col);
    return v1.compareTo(v2);
  }

}
