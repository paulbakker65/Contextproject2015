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

  private String colName;
  
  public MultipleOccurrencePattern(String col) {
    super();
    this.colName = col;
  }
  
  public MultipleOccurrencePattern(String col, Pattern p) {
    super(p);
    this.colName = col;
  }
  
  
  @Override
  public boolean findPattern(ListIterator<Record> i, Table records) {
    if (!i.hasNext()) {
      return false;
    }
    
    Record current = i.next();
    
    if (!i.hasNext()) {
      return false;
    }
    
    Record next = i.next();
    
    if (!current.get(colName).isNull() && !next.get(colName).isNull()) {
      records.add(current);
      
      while (i.hasNext() && !next.get(colName).isNull()) {
        records.add(next);
        next = i.next();
      }
      
      if (next.get(colName).isNull()) {
        records.remove(next);
        i.previous();
      }
      return nextPattern.findPattern(i, records);
    }
    
    return false;   
  }
}
