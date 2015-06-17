package net.tudelft.hi.e.computation;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.tudelft.hi.e.data.DateValue;
import net.tudelft.hi.e.data.NumberValue;
import net.tudelft.hi.e.data.Table;
import net.tudelft.hi.e.data.Value;

import org.apache.commons.lang3.builder.EqualsBuilder;

/**
 * Generates a table with the lag between two events. The first event record contains the column
 * time_before_OTHEREVENT The value is the difference in seconds between the two events
 *
 * @author unset
 *
 */
public class BetweenOperation extends Operation {

  private static final Logger LOG = Logger.getLogger(BetweenOperation.class.getName());

  private final String datecol;
  private final String datecol2;
  private final String eventcol;

  private final Value ev1val;
  private final Value ev2val;

  /**
   * Creates a new lag operation that compares the time between each couple of event 1 and 2.
   *
   * @param inputDataset
   *          Table containing the input data.
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
   * @param inputDataset
   *          Table containing the input data.
   * @param eventcol
   *          Column where the type of the event is stored
   * @param datecol
   *          Column where the timing of the event is stored
   * @param ev1val
   *          Event 1 identifier
   * @param ev2val
   *          Event 2 identifiter
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
    resultData.get(index1).put("time_before_" + ev2val.toString(), new NumberValue(timeDif));
  }

  @Override
  public boolean execute() {
    executeFindForEveryEventIndex();

    return true;
  }

  private boolean executeFindForEveryEventIndex() {
    resultData = (Table) inputData.clone();

    int eventIndex = 0;

    while (eventIndex < inputData.size()) {
      if (isFirstEvent(eventIndex)) {
        executeFindTargetEventForEvent(eventIndex);
      }
      eventIndex++;
    }
    return true;
  }

  private boolean executeFindTargetEventForEvent(final int eventIndex) {
    int targetIndex = eventIndex + 1;
    while (targetIndex < inputData.size() && !isFirstEvent(targetIndex)) {
      if (isSecondEvent(targetIndex)) {
        addEvent(eventIndex, targetIndex);
        break;
      }
      targetIndex++;
    }
    return true;
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
      InputMismatchException ex = new InputMismatchException(date.toString() + " is not a date");
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

  @Override
  public int hashCode() {
    return Objects.hash(datecol, datecol2, ev1val, ev2val, eventcol);
  }

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
    return new EqualsBuilder().append(datecol, other.datecol)
        .append(datecol2, other.datecol2).append(eventcol, other.eventcol)
        .append(ev1val, other.ev1val).append(ev2val, other.ev2val).isEquals();
  }

}
