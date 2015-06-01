package operations.lsa;

import table.Record;

import java.util.List;

/**
 * An object that groups records on a certain condition.
 */
public abstract class Grouper {

  public abstract List<List<Record>> group(List<Record> recs);

}
