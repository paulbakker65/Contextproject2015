package operations.patterns;

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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((nextPattern == null) ? 0 : nextPattern.hashCode());
    return result;
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
    Pattern other = (Pattern) obj;
    if (nextPattern == null) {
      if (other.nextPattern != null) {
        return false;
      } 
    } else if (!nextPattern.equals(other.nextPattern)) {
      return false;
    }
    return true;
  }
  
  
  
}
