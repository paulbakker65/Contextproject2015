package operations.coding;

import table.Table;

/**
 * Pattern used to determine behaviour.
 * @author paulbakker
 *
 */
public abstract class Pattern {

  protected Pattern nextPattern;
  
  /**
   * Constructor which sets the next pattern to a null pattern.
   */
  public Pattern() {
    this.nextPattern = new NullPattern();
  }
  
  /**
   * Constructor which sets the pattern p as the next pattern.
   * @param p
   */
  public Pattern(Pattern p) {
    this.nextPattern = p;
  }
  
  /**
   * Every pattern has to be able to find its pattern.
   * @param i
   * @param records
   * @return
   */
  public abstract boolean findPattern(Table table, int fromIndex, Table records);
  
//  /**
//   * Getter for next pattern.
//   * @return
//   */
//  public Pattern getNextPattern() {
//    return nextPattern;
//  }
//  
//  /**
//   * Setter for next pattern.
//   * @param p
//   */
//  public void setPattern(Pattern p) {
//    this.nextPattern = p;
//  }
  
}
