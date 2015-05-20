package operations.coding;

import java.util.ListIterator;

import table.Record;
import table.Table;

/**
 * End of Pattern.
 * @author paulbakker, robin
 */
public class NullPattern extends Pattern {

  public NullPattern() {
    super(null);
  }
  
  @Override
  public boolean findPattern(ListIterator<Record> i, Table records) {
     return true;
  }
 

}
