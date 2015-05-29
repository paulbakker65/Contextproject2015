package operations;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import operations.lsa.Grouper;
import operations.lsa.SingleGrouper;
import table.Record;
import table.Table;
import table.value.Column;
import table.value.NumberColumn;
import table.value.NumberValue;
import table.value.Value;

/**
 * Implements Lag Sequential Analysis according to the slides.
 * https://blackboard.tudelft.nl/bbcswebdav
 * /pid-2443413-dt-content-rid-8386462_2/courses/34575-141504
 * /Health%20Informatics%20project%20-%20SDA-%202015.pdf.
 * 
 *
 */
public class LSAOperation extends Operation {

  private String eventcol;
  private int from, to;
  private Value key, target;
  private LagTable lagtable;
  private Grouper grouper;

  private List<List<Record>> groups;

  /**
   * Creates a LSA. The grouper determines in what unit the lag is measured. If records are grouped
   * in days, the LSA will show lag in days between events.
   * 
   * @param inputDataset
   *          the table to extract lag from
   * @param eventcol
   *          the column where the eventtype of a record is stored
   * @param from
   *          the lowest lag to measure (inclusive)
   * @param to
   *          the highest lag to measure (exclusive)
   * @param key
   *          the eventtype to analyse
   * @param target
   *          the other eventtype where the lag is calculated between
   * @param grouper
   *          groups the records, used to calculate the lag of the LSA
   */
  public LSAOperation(Table inputDataset, String eventcol, int from, int to, Value key,
      Value target, Grouper grouper) {
    super(inputDataset);
    this.eventcol = eventcol;
    this.from = from;
    this.to = to;
    this.key = key;
    this.target = target;
    this.grouper = grouper;
  }

  /**
   * Creates a normal LSA with the default grouper. Lag is measured in amount of records.
   * 
   * @param inputDataset
   *          the table to extract lag from
   * @param eventcol
   *          the column where the eventtype of a record is stored
   * @param from
   *          the lowest lag to measure (inclusive)
   * @param to
   *          the highest lag to measure (exclusive)
   * @param key
   *          the eventtype to analyse
   * @param target
   *          the other eventtype where the lag is calculated between
   */
  public LSAOperation(Table inputDataset, String eventcol, int from, int to, Value key, Value target) {
    super(inputDataset);
    this.eventcol = eventcol;
    this.from = from;
    this.to = to;
    this.key = key;
    this.target = target;
    this.grouper = new SingleGrouper();
  }

  @Override
  public Table getResult() {
    return resultData;
  }

  @Override
  public boolean execute() {
    lagtable = new LagTable(from, to);
    groups = grouper.group(inputData);

    for (int keygroup = 0; keygroup < groups.size(); keygroup++) {
      for (Record r : groups.get(keygroup)) {
        if (isKey(r)) {
          calcLag(keygroup);
        }
      }
    }
    resultData = lagtable.toTable();
    return true;
  }

  private void calcLag(int keygroup) {
    for (Entry<Integer, Integer> lag : lagtable.entrySet()) {
      int targetgroup = keygroup + lag.getKey();
      if (targetgroup >= 0 && targetgroup < groups.size()) {
        for (Record tr : groups.get(targetgroup)) {
          if (isTarget(tr)) {
            lag.setValue(lag.getValue() + 1);
          }
        }
      }
    }
  }

  private boolean isKey(Record r) {
    return key.equals(r.get(eventcol));
  }

  private boolean isTarget(Record r) {
    return target.equals(r.get(eventcol));
  }

}

/**
 * A special HashMap that contains the lag. The keys are for example -4 until 5, and the values are
 * the occurrences of that lag.
 * 
 */
class LagTable extends HashMap<Integer, Integer> {

  /**
   * Collumns that will be used when exporting to Table.
   */
  public static List<Column> cols = Arrays.asList(new Column[] { new NumberColumn("lag"),
      new NumberColumn("occur") });

  private static final long serialVersionUID = 1L;

  /**
   * Creates a lag table with 0 occurence for lag FROM til lag TO.
   * 
   * @param from
   *          lowest lag to scan (inclusive)
   * @param to
   *          highest lag to scan (exclusive)
   */
  public LagTable(int from, int to) {
    super();
    for (int i = from; i < to; i++) {
      this.put(i, 0);
    }
  }

  public Table toTable() {
    Table t = new Table();
    for (Entry<Integer, Integer> lag : this.entrySet()) {
      t.add(new Record(cols, new Value[] { new NumberValue(lag.getKey()),
          new NumberValue(lag.getValue()) }));
    }
    Collections.sort(t, new RecordLagComparator());
    return t;
  }

}

/**
 * For internal use only. Sorts on lag.
 */
class RecordLagComparator implements Serializable, Comparator<Record> {

  private static final long serialVersionUID = 1L;

  @Override
  public int compare(Record o1, Record o2) {
    return o1.get("lag").compareTo(o2.get("lag"));
  }

}
