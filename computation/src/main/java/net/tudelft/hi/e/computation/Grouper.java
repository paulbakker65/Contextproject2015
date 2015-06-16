package net.tudelft.hi.e.computation;

import java.util.List;
import net.tudelft.hi.e.data.Record;

/**
 * An object that groups records on a certain condition.
 */
public abstract class Grouper {

  public abstract List<List<Record>> group(List<Record> recs);

}
