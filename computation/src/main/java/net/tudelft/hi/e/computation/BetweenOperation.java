package net.tudelft.hi.e.computation;

import java.util.InputMismatchException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.tudelft.hi.e.data.DateValue;
import net.tudelft.hi.e.data.NumberValue;
import net.tudelft.hi.e.data.Table;
import net.tudelft.hi.e.data.Value;

/**
 * Generates a table with the lag between two events. The first event record contains the column
 * time_before_OTHEREVENT The value is the difference in seconds between the two events
 *
 * @author unset
 *
 */
public class BetweenOperation extends Operation {

  private static final Logger LOG
      = Logger.getLogger(BetweenOperation.class.getName());

  private final String datecol;
  private final String datecol2;
  private final String eventcol;

  private final Value ev1val;
  private final Value ev2val;

  /**
   * Creates a new lag operation that compares the time between each couple of event 1 and 2.
   *
   * @param inputDataset Table containing the input data.
   * @param eventcol Column where the type of the event is stored
   * @param datecol Column where the timing of the first event is stored
   * @param datecol2 Column where the timing of the second event is stored
   * @param ev1val Event 1 identifier
   * @param ev2val Event 2 identifiter
   */
  public BetweenOperation(final Table inputDataset, final String eventcol, final String datecol,
      final String datecol2, final Value ev1val, final Value ev2val) {
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
   * @param eventcol Column where the type of the event is stored
   * @param datecol Column where the timing of the event is stored
   * @param ev1val Event 1 identifier
   * @param ev2val Event 2 identifier
   */
  public BetweenOperation(final Table inputDataset, final String eventcol, final String datecol,
      final Value ev1val, final Value ev2val) {
    this(inputDataset, eventcol, datecol, datecol, ev1val, ev2val);
  }

  @Override
  public void resetData(Table inputData) {
    this.inputData = inputData;
    this.resultData = new Table(inputData);
  }

  private void addEvent(final int index1, final int index2) {
    final int timeDif = (int) ((getTimeStamp(index2) - getTimeStamp(index1)) / (1000 * 60 * 60));
    resultData.get(index1).put("time_between", new NumberValue(timeDif));
  }

  @Override
  public boolean execute() {
    executeFindForEveryEventIndex();

    return true;
  }

  private boolean executeFindForEveryEventIndex() {
    int eventIndex = 0;

    while (eventIndex < inputData.size()) {
      if (isFirstEvent(eventIndex)) {
        executeFindTargetEventForEvent(eventIndex);
      }
      eventIndex++;
    }
    return true;
  }

  private void executeFindTargetEventForEvent(final int eventIndex) {
    int targetIndex = eventIndex + 1;
    while (targetIndex < inputData.size()) {
      if (isSecondEvent(targetIndex)) {
        addEvent(eventIndex, targetIndex);
        break;
      }
      else if (isFirstEvent(targetIndex)) {
    	  break;
      }
      targetIndex++;
    }
  }

  @Override
  public Table getResult() {
    return resultData;
  }

  private long getTimeStamp(final int index) {
    Value date = null;
    if (isFirstEvent(index)) {
      date = inputData.get(index).get(datecol);
    } else {
      date = inputData.get(index).get(datecol2);
    }
    if (date.isDate()) {
      return ((DateValue) date).getValue().getTime().getTime();
    } else {
      InputMismatchException ex = new InputMismatchException(date.toString()
          + " is not a date");
      LOG.log(Level.SEVERE, ex.getMessage(), ex);
      throw ex;
    }
  }

  private boolean isFirstEvent(final int index) {
    return ev1val.equals(inputData.get(index).get(eventcol));
  }

  private boolean isSecondEvent(final int index) {
    return ev2val.equals(inputData.get(index).get(eventcol));
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "BetweenOperation [datecol=" + datecol + ", datecol2=" + datecol2 + ", eventcol="
        + eventcol + ", ev1val=" + ev1val + ", ev2val=" + ev2val + "]";
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((datecol == null) ? 0 : datecol.hashCode());
    result = prime * result + ((datecol2 == null) ? 0 : datecol2.hashCode());
    result = prime * result + ((ev1val == null) ? 0 : ev1val.hashCode());
    result = prime * result + ((ev2val == null) ? 0 : ev2val.hashCode());
    result = prime * result + ((eventcol == null) ? 0 : eventcol.hashCode());
    return result;
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    BetweenOperation other = (BetweenOperation) obj;
    return this.equalDates(other) && this.equalValues(other) && this.equalEvent(
        other);
  }

  private boolean equalDates(BetweenOperation other) {
    if (datecol == null) {
      return false;
    } else if (!datecol.equals(other.datecol)) {
      return false;
    }
    if (datecol2 == null) {
      return false;
    } else if (!datecol2.equals(other.datecol2)) {
      return false;
    }
    return true;
  }

  private boolean equalValues(BetweenOperation other) {
    if (ev1val == null) {
      return false;
    } else if (!ev1val.equals(other.ev1val)) {
      return false;
    }
    if (ev2val == null) {
      return false;
    } else if (!ev2val.equals(other.ev2val)) {
      return false;
    }
    return true;
  }


  private boolean equalEvent(BetweenOperation other) {
    if (eventcol == null) {
      return false;
    } else {
      return eventcol.equals(other.eventcol);
    }
  }
}
