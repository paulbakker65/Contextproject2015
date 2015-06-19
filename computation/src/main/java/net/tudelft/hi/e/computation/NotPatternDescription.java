package net.tudelft.hi.e.computation;

/**
 * Class for specifying a PatternDescription for a NotPattern.
 */
public class NotPatternDescription implements PatternDescription {
  private PatternDescription otherPattern;
  
  /**
   * Creates a NotPatternDescription.
   * 
   * @param otherPattern the description of the Pattern to invert.
   */
  public NotPatternDescription(PatternDescription otherPattern) {
    this.otherPattern = otherPattern;
  }

  @Override
  public Pattern createPattern() {
    return new NotPattern(otherPattern.createPattern());
  }
}
