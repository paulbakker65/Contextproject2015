package net.tudelft.hi.e.computation;

import java.util.ArrayList;
import java.util.List;

/**
 * Creating predetermined patterns.
 */
public class PatternFactory {

  /**
   * Default hidden constructor because this class cannot be instantiated.
   */
  private PatternFactory() {
  }

  /**
   * Creates a Pattern based on a list of descriptions.
   *
   * @param patternDescriptions
   *          the list of descriptions.
   * @return the created pattern.
   */
  public static Pattern createPattern(List<PatternDescription> patternDescriptions) {
    if (patternDescriptions.isEmpty() || patternDescriptions.get(0) == null) {
      return new NullPattern();
    }

    PatternDescription patternDescription = patternDescriptions.remove(0);

    Pattern nextPattern = createPattern(patternDescriptions);
    Pattern currentPattern = patternDescription.createPattern();
    Pattern lastCurrentPattern = currentPattern.getLastNotNullPattern();
    lastCurrentPattern.setNextPattern(nextPattern);

    return currentPattern;
  }

  /**
   * Creates a Pattern using String representations.
   * This is only used for testing purposes.
   *
   * @param descriptions
   *        list of description strings.
   * @return
   *        the created Pattern.
   */
  public static Pattern createPattern(String... descriptions) {
    List<PatternDescription> list = new ArrayList<PatternDescription>();

    for (int i = 0; i < descriptions.length; i++) {
      list.add(PatternMatcher.getDescription(descriptions[i]));
    }

    return createPattern(list);
  } 
}
