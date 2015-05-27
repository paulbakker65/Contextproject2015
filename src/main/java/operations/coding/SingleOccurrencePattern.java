package operations.coding;

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
  public boolean findPattern(Table table, int fromIndex, Table records) {
    // If the end of the table has been reached, return.
    if  (fromIndex >= table.size()) {         
      return false;
    }
    // Check whether the previous record differs.
    if (records.isEmpty() && 
        fromIndex > 0 && 
        !table.get(fromIndex - 1).get(colName).isNull()) {
      return false;
    }
    
    Record record = table.get(fromIndex++);
    if (!record.get(colName).isNull()) {
      records.add(record);
      
      if (nextPattern instanceof NullPattern && 
          fromIndex < table.size() && 
          !table.get(fromIndex).get(colName).isNull()) {
        return false;
      }
      
      return nextPattern.findPattern(table, fromIndex, records);
    } else {
      return false;
    }
  }
}
