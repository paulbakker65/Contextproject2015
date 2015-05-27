package operations.coding;

import table.Table;

/**
 * End of Pattern.
 * 
 * @author paulbakker, robin
 */
public class NullPattern extends Pattern {

  /**
   * Constroctor only calls the super class with null.
   */
  public NullPattern() {
    super(null);
  }

  /**
   * This is always true, as no pattern has to be found.
   */
  @Override
  public boolean findPattern(Table table, int fromIndex, Table records) {
    return true;
  }

}
