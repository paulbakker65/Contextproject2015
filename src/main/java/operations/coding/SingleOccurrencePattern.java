package operations.coding;

import java.util.ListIterator;

import table.Record;
import table.Table;

/**
 * Single occurrence of an record from a specific file.
 * @author paulbakker
 *
 */
public class SingleOccurrencePattern extends Pattern {

  private String colName;
  
  public SingleOccurrencePattern(String col) {
    super();
    this.colName = col;
  }
  
  public SingleOccurrencePattern(String col, Pattern p) {
    super(p);
    this.colName = col;
  }
  
  @Override
  public boolean findPattern(ListIterator<Record> i, Table records) {
    if (!i.hasNext()) {
      return false;
    }
    Record record = i.next();
    if (!record.get(colName).isNull()) {
      records.add(record);
      return nextPattern.findPattern(i, records);
    }
    else {
      return false;
    }
    
  }

  
  
}
