package net.tudelft.hi.e.computation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.tudelft.hi.e.data.Record;

/**
 * Gives each record it's own group.
 */
public class SingleGrouper extends Grouper {

  @Override
  public List<List<Record>> group(final List<Record> recs) {
    final List<List<Record>> res = new ArrayList<List<Record>>();
    for (final Record r : recs) {
      res.add(Arrays.asList(new Record[] {r}));
    }
    return res;
  }

}
