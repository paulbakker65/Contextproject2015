package operations.patterns;

import table.Record;
import table.Table;
import table.value.Value;

/**
 * Single occurrence of an record from a specific file.
 * 
 * @author paulbakker
 *
 */
public class SingleOccurrenceValuePattern extends Pattern {

  /**
   * The column name on which to look for the pattern.
   */
  private String colName;
  /**
   * The value to check.
   */
  private Value value;

  /**
   * Constructor which creates the pattern without a next pattern.
   * 
   * @param col
   */
  public SingleOccurrenceValuePattern(String col, Value v) {
    super();
    this.colName = col;
    this.value = v;
  }

  /**
   * Constructor which creates the pattern with a next pattern.
   * 
   * @param col
   */
  public SingleOccurrenceValuePattern(String col, Value v, Pattern p) {
    super(p);
    this.colName = col;
    this.value = v;
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
        !table.get(fromIndex - 1).get(colName).equals(value)) {
      return false;
    }
    
    Record record = table.get(fromIndex++);
    if (record.get(colName).equals(value)) {
      records.add(record);
      
      if (nextPattern instanceof NullPattern && 
          fromIndex < table.size() && 
          !table.get(fromIndex).get(colName).equals(value)) {
        return false;
      }
      
      return nextPattern.findPattern(table, fromIndex, records);
    } else {
      return false;
    }
  }
}
