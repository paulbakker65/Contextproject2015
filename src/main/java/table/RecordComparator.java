package table;

import java.util.Comparator;
import parsers.Value;

/**
 * Compares records based on a column.
 * 
 * @author unset, paulbakker65
 */
public class RecordComparator implements Comparator<Record> {

  private String col;

  /**
   * Makes a new Record comparator.
   * 
   * @param col
   *          the column to compare on
   */
  public RecordComparator(String col) {
    this.col = col;
  }

  /**
   * Compares the values using their own compareTo methods.
   */
  @Override
  public int compare(Record o1, Record o2) {
    Value v1 = o1.get(col);
    Value v2 = o2.get(col);
    return v1.compareTo(v2);

  }

}
