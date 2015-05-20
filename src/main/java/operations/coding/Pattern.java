package operations.coding;

import java.util.ListIterator;

import table.Record;
import table.Table;

/**
 * Pattern used to determine behaviour.
 * @author paulbakker
 *
 */
public abstract class Pattern {

  protected Pattern nextPattern;
  
  public Pattern() {
    this.nextPattern = new NullPattern();
  }
  
  public Pattern(Pattern p) {
    this.nextPattern = p;
  }
  
  public abstract boolean findPattern(ListIterator<Record> i, Table records);
  
  public Pattern getNextPattern() {
    return nextPattern;
  }
  
  public void setPattern(Pattern p) {
    this.nextPattern = p;
  }
  
}
