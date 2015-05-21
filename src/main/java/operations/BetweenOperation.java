package operations;

import parsers.DateValue;
import parsers.NumberValue;
import parsers.Value;
import table.Table;

/**
 * Generates a table with the lag between two events.
 * The first event record contains the column time_before_OTHEREVENT
 * The value is the difference in seconds between the two events
 * 
 * @author unset
 *
 */
public class BetweenOperation extends Operation {

  private String datecol, eventcol;

  private Value ev1val, ev2val;

  /**
   * Creates a new lag operation that compares the time between each couple of event 1 and 2.
   * 
   * @param inputDataset
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

    int i = 0;
    while (i < inputData.size()) {
      if (isFirstEvent(i)) {
        int j = i + 1;
        while (j < inputData.size() && !isFirstEvent(j)) {
          if (isSecondEvent(j)) {
            addEvent(i, j);
            break;
          }
          j++;
        }
      }
      i++;
    }

    return true;
  }

  private void addEvent(int i, int j) {
    int timeDif = (int) ((getTimeStamp(j) - getTimeStamp(i)) / 1000);
    resultData.get(i).put("time_before_" + ev2val.toString(), new NumberValue(timeDif));
  }

  private boolean isFirstEvent(int i) {
    return ev1val.equals(inputData.get(i).get(eventcol));
  }

  private boolean isSecondEvent(int i) {
    return ev2val.equals(inputData.get(i).get(eventcol));
  }

  private long getTimeStamp(int i) {
    Value date = inputData.get(i).get(datecol);
    if (date.isDate()) {
      return ((DateValue) date).getValue().getTime().getTime();
    }
    throw new Error("Not a date in the column");

  }

}


