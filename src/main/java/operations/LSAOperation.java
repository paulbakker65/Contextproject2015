package operations;

import input.Column;
import input.NumberColumn;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import parsers.NumberValue;
import parsers.Value;
import table.Record;
import table.Table;

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


  /**
   * Generates a new lag sequential analysis.
   * 
   * @param inputDataset
   *          the data to do the analysis on
   * @param eventcol
   *          Column where the type of the event is stored
   * @param from
   *          the lowest lag (inclusive)
   * @param to
   *          the highest lag (exclusive)
   * @param key
   *          the key event
   * @param target
   *          the target event
   */
  public LSAOperation(Table inputDataset, String eventcol, int from, int to, Value key, Value target) {
    super(inputDataset);
    this.eventcol = eventcol;
    this.from = from;
    this.to = to;
    this.key = key;
    this.target = target;
  }

  @Override
  public Table getResult() {
    return resultData;
  }

  @Override
  public boolean execute() {
    lagtable = new LagTable(from, to);

    //Iterate over the table
    for (int ikey = 0; ikey < inputData.size(); ikey++) {
      //Check if the record is a key event
      if (key.equals(eventOfRecord(ikey))) {
        //Do analysis
        calcLag(ikey);
      }
    }

    resultData = lagtable.toTable();
    return true;
  }


  private void calcLag(int ikey) {
    //Iterates over each possible lag
    for (Entry<Integer, Integer> lag : lagtable.entrySet()) {
      //Checks if the lagged record a is target event
      if (isTargetRecord(ikey + lag.getKey())) {
        //Increases the occurrence by 1
        lag.setValue(lag.getValue() + 1);
      }
    }
  }

  private boolean isTargetRecord(int itarget) {
    return itarget >= 0 && itarget < inputData.size() && target.equals(eventOfRecord(itarget));
  }

  private Value eventOfRecord(int i) {
    return inputData.get(i).get(eventcol);
  }

}


/**
 * A special HashMap that contains the lag.
 * The keys are for example -4 until 5,
 * and the values are the occurrences of that lag.
 * @author unset
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
   * @param from lowest lag to scan (inclusive)
   * @param to highest lag to scan (exclusive)
   */
  public LagTable(int from, int to){
    super();
    for (int i = from; i < to; i++) {
      this.put(i, 0);
    }
  }
  
  public Table toTable(){
    Table t = new Table();
    for (Entry<Integer, Integer> lag : this.entrySet()) {
      t.add(new Record(cols, new Value[] { new NumberValue(lag.getKey()),
          new NumberValue(lag.getValue()) }));
    }
    t.sort(new RecordLagComparator());
    return t;
  }
  
}

/**
 * For internal use only. Sorts on lag.
 */
class RecordLagComparator implements Serializable, Comparator<Record>{

  private static final long serialVersionUID = 1L;

  @Override
  public int compare(Record o1, Record o2) {
    return o1.get("lag").compareTo(o2.get("lag"));
  }
  
}
