package operations.coding;

import java.util.ListIterator;

import table.Record;
import table.Table;

/**
 * Multiple occurrences of a record from the same file.
 * @author paulbakker
 *
 */
public class MultipleOccurrencePattern extends Pattern {

  /**
   * Column name on which to detect the pattern.
   */
  private String colName;
  
  /**
   * Constructor which creates the pattern without a next pattern.
   * 
   * @param col
   */
  public MultipleOccurrencePattern(String col) {
    super();
    this.colName = col;
  }
  
  /**
   * Constructor which creates the pattern with a next pattern.
   * @param col
   * @param p
   */
  public MultipleOccurrencePattern(String col, Pattern p) {
    super(p);
    this.colName = col;
  }
  
  /**
   * The method checks if the pattern is recognized.
   */
  @Override
  public boolean findPattern(ListIterator<Record> i, Table records) {
    //Check if the iterator has a next.
    if (!i.hasNext()) {
      return false;
    }
    
    Record current = i.next();
    
    if (!i.hasNext()) {
      return false;
    }
    
    Record next = i.next();
    
    //Check if the current and next have the same column with values.
    if (!current.get(colName).isNull() && !next.get(colName).isNull()) {
      records.add(current);
   
      while (i.hasNext() && !next.get(colName).isNull()) {
        records.add(next);
        next = i.next();
      }
      //If a record from another file is found remove it from the records and call previos on the iterator.
      if (next.get(colName).isNull()) {
        records.remove(next);
        i.previous();
      }
      //Call the next pattern to continue the search.
      return nextPattern.findPattern(i, records);
    }
    return false;   
  }
}
