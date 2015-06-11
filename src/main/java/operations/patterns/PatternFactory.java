package operations.patterns;

import operations.patterns.condition.RecordCondition;

import java.util.ArrayList;
import java.util.List;

/**
 * Creating predetermined patterns.
 */
public class PatternFactory {

  /**
   * Creates a Pattern based on a list of descriptions.
   * 
   * @param patternDescriptions
   *          the list of descriptions.
   * @return the created pattern.
   */
  public static Pattern createPattern(List<PatternDescription> patternDescriptions) {
    if (patternDescriptions.isEmpty()) {
      return new NullPattern();
    }

    PatternDescription patternDescription = patternDescriptions.remove(0);
    Count count = patternDescription.getCount();
    RecordCondition condition = patternDescription.getCondition();

    Pattern nextPattern = createPattern(patternDescriptions);
    Pattern currentPattern = count.createPattern(condition);
    Pattern lastCurrentPattern = getLastNotNullPattern(currentPattern);
    lastCurrentPattern.setNextPattern(nextPattern);

    return currentPattern;
  }

  /**
   * Creates a Pattern using String representations. This is only used for testing purposes.
   * 
   * @param descriptions
   *          list of description strings.
   * @return the created Pattern.
   */
  public static Pattern createPattern(String... descriptions) {
    List<PatternDescription> list = new ArrayList<PatternDescription>();

    for (int i = 0; i < descriptions.length; i++) {
      list.add(PatternMatcher.getDescription(descriptions[i]));
    }

    return createPattern(list);
  }

  private static Pattern getLastNotNullPattern(Pattern pattern) {
    while (!(pattern.getNextPattern() instanceof NullPattern)) {
      pattern = pattern.getNextPattern();
    }

    return pattern;
  }

  private PatternFactory() {
  }
}
