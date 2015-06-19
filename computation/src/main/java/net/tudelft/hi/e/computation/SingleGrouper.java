package net.tudelft.hi.e.computation;

import net.tudelft.hi.e.data.Record;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
  public int hashCode() {
    int hash = 5;
    hash = 13 * hash + this.getClass().hashCode();
    return hash;
     
  }
  
  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    return getClass() == obj.getClass();
  }

}
