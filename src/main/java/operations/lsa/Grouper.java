package operations.lsa;

import java.util.List;

import table.Record;

/**
 * An object that groups records on a certain condition.
*/
public abstract class Grouper {

  public abstract List<List<Record>> group(List<Record> recs); 
  
}
