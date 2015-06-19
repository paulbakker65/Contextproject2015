package net.tudelft.hi.e.computation;

import net.tudelft.hi.e.data.Table;

/**
 * End of Pattern.
 */
public class NullPattern extends Pattern {

  /**
   * Constructor only calls the super class with null.
   */
  public NullPattern() {
    super(null);
  }

  /**
   * This is always true, as no pattern has to be found.
   */
  @Override
  public boolean findPattern(final Table table, final int fromIndex, final Table records) {
    return true;
  }

  /**
   * Is this pattern is a next pattern, then it is the last.
   */
  @Override
  public boolean isNextLastPattern() {
    return true;
  }

}
