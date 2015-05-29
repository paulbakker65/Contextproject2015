package operations.lsa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import table.Record;

/**
 * Gives each record it's own group.
 */
public class SingleGrouper extends Grouper {

  @Override
  public List<List<Record>> group(List<Record> recs) {
    List<List<Record>> res =  new ArrayList<List<Record>>();
    for (Record r : recs){
      res.add(Arrays.asList(new Record[] { r }));
    }
    return res;
  }

}
