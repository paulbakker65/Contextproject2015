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
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    return getClass() != obj.getClass();
  }

}
