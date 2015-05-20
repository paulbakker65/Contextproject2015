package operations.coding;

import java.util.ListIterator;

import table.Record;
import table.Table;

/**
 * Single occurrence of an record from a specific file.
 * 
 * @author paulbakker
 *
 */
public class SingleOccurrencePattern extends Pattern {

  /**
   * The column name on which to look for the pattern.
   */
  private String colName;

  /**
   * Constructor which creates the pattern without a next pattern.
   * 
   * @param col
   */
  public SingleOccurrencePattern(String col) {
    super();
    this.colName = col;
  }

  /**
   * Constructor which creates the pattern with a next pattern.
   * 
   * @param col
   */
  public SingleOccurrencePattern(String col, Pattern p) {
    super(p);
    this.colName = col;
  }

  /**
   * Checks if the pattern is found.
   */
  @Override
  public boolean findPattern(ListIterator<Record> i, Table records) {
    if (!i.hasNext()) {
      return false;
    }
    Record record = i.next();
    if (!record.get(colName).isNull()) {
      records.add(record);
      return nextPattern.findPattern(i, records);
    } else {
      return false;
    }
  }
}
