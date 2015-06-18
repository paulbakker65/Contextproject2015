package net.tudelft.hi.e.computation;

import net.tudelft.hi.e.data.Table;

/**
 * Pattern used to determine behaviour.
 *
 * @author paulbakker
 *
 */
public abstract class Pattern {
  /**
   * The next pattern to link to.
   */
  protected Pattern nextPattern;

  /**
   * Constructor which sets the next pattern to a null pattern.
   */
  public Pattern() {
    this.nextPattern = new NullPattern();
  }

  /**
   * Constructor which sets the pattern p as the next pattern.
   *
   * @param pattern Pattern to use as next pattern.
   */
  public Pattern(final Pattern pattern) {
    this.nextPattern = pattern;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Pattern other = (Pattern) obj;
    return equalNextPattern(other);
  }

  private boolean equalNextPattern(Pattern other) {
    if (nextPattern == null) {
      if (other.nextPattern != null) {
        return false;
      }
    } else if (!nextPattern.equals(other.nextPattern)) {
      return false;
    }
    return true;
  }

  /**
   * Find a pattern in a set of records.
   *
   * @param table Table in which to find the pattern.
   * @param fromIndex Index where to start searching.
   * @param records Records in which to search.
   * @return True if it finds a pattern, else false.
   */
  public abstract boolean findPattern(Table table, int fromIndex, Table records);

  /**
   * Returns the next pattern.
   *
   * @return the next pattern.
   */
  public Pattern getNextPattern() {
    return nextPattern;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((nextPattern == null) ? 0 : nextPattern.hashCode());
    return result;
  }

  /**
   * Set the next pattern.
   *
   * @param nextPattern the next pattern.
   */
  public void setNextPattern(final Pattern nextPattern) {
    this.nextPattern = nextPattern;
  }
  
  /**
   * Returns the last Pattern this Pattern links to.
   * 
   * @return the last Pattern this Pattern links to.
   */
  public Pattern getLastNotNullPattern() {
	  if (nextPattern == null) {
		  return new NullPattern();
	  }
	  if (nextPattern instanceof NullPattern) {
		  return this;
	  }
	  return nextPattern.getLastNotNullPattern();
  }

}
