package operations;

import table.Table;
import table.value.DateValue;
import table.value.NullValue;
import table.value.NumberValue;
import table.value.Value;

/**
 * Generates a table with the lag between two events. The first event record contains the column
 * time_before_OTHEREVENT The value is the difference in seconds between the two events
 * 
 * @author unset
 *
 */
public class BetweenOperation extends Operation {

  private String datecol;
  private String datecol2;
  private String eventcol;

  private Value ev1val;
  private Value ev2val;

  /**
   * Creates a new lag operation that compares the time between each couple of event 1 and 2.
   * 
   * @param inputDataset Table containing the input data.
   * @param eventcol
   *          Column where the type of the event is stored
   * @param datecol
   *          Column where the timing of the first event is stored
   * @param datecol2
   *          Column where the timing of the second event is stored
   * @param ev1val
   *          Event 1 identifier
   * @param ev2val
   *          Event 2 identifiter
   */
  public BetweenOperation(Table inputDataset, String eventcol, String datecol, String datecol2,
      Value ev1val, Value ev2val) {
    super(inputDataset);
    this.eventcol = eventcol;
    this.datecol = datecol;
    this.datecol2 = datecol2;
    this.ev1val = ev1val;
    this.ev2val = ev2val;
  }

  /**
   * Creates a new lag operation that compares the time between each couple of event 1 and 2.
   * 
   * @param inputDataset Table containing the input data.
   * @param eventcol
   *          Column where the type of the event is stored
   * @param datecol
   *          Column where the timing of the event is stored
   * @param ev1val
   *          Event 1 identifier
   * @param ev2val
   *          Event 2 identifiter
   */
  public BetweenOperation(Table inputDataset, String eventcol, String datecol, Value ev1val,
      Value ev2val) {
    super(inputDataset);
    this.eventcol = eventcol;
    this.datecol = datecol;
    this.datecol2 = datecol;
    this.ev1val = ev1val;
    this.ev2val = ev2val;
  }

  @Override
  public Table getResult() {
    return resultData;
  }

  @Override
  public boolean execute() {
    resultData = (Table) inputData.clone();

    int index1 = 0;
    while (index1 < inputData.size()) {
      if (isFirstEvent(index1)) {
        int index2 = index1 + 1;
        while (index2 < inputData.size() && !isFirstEvent(index2)) {
          if (isSecondEvent(index2)) {
            addEvent(index1, index2);
            break;
          }
          index2++;
        }
      }
      index1++;
    }

    return true;
  }

  private void addEvent(int index1, int index2) {
    int timeDif = (int) ((getTimeStamp(index2) - getTimeStamp(index1)) / (1000 * 60 * 60));
    resultData.get(index1).put("time_before_" + ev2val.toString(), new NumberValue(timeDif));
  }

  private boolean isFirstEvent(int index) {
    return ev1val.equals(inputData.get(index).get(eventcol));
  }

  private boolean isSecondEvent(int index) {
    return ev2val.equals(inputData.get(index).get(eventcol));
  }

  private long getTimeStamp(int index) {
    Value date = new NullValue();
    if (isFirstEvent(index)) {
      date = inputData.get(index).get(datecol);
    } else {
      date = inputData.get(index).get(datecol2);
    }
    if (date.isDate()) {
      return ((DateValue) date).getValue().getTime().getTime();
    }
    throw new Error("Not a date in the column");

  }

}
