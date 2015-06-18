package net.tudelft.hi.e.computation;

import java.util.ArrayList;
import java.util.Arrays;
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
    Count count = patternDescription.getCount();
    RecordCondition condition = patternDescription.getCondition();

    Pattern nextPattern = createPattern(patternDescriptions);
    Pattern currentPattern = count.createPattern(condition);
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
    for (int i = 0; i < descriptions.length; i++) {
      if (descriptions[i].startsWith("!(") && descriptions[i].endsWith(")")) {
    	Pattern firstPattern = 
    			createPattern(Arrays.asList(descriptions).subList(0, i).toArray(new String[0]));
    	Pattern lastPattern = new NullPattern();
    	if (i < descriptions.length - 1) {
    		lastPattern = 
    			createPattern(Arrays.asList(descriptions).subList(i + 1, descriptions.length).toArray(new String[0]));
    	}
    	Pattern toInvert = createPattern(descriptions[i].substring(2, descriptions[i].length() - 1));
    	Pattern notPattern = new NotPattern(toInvert, lastPattern);
    	firstPattern.getLastNotNullPattern().setNextPattern(notPattern);
    	return firstPattern;
      }
    }
	  
    List<PatternDescription> list = new ArrayList<PatternDescription>();

    for (int i = 0; i < descriptions.length; i++) {
      list.add(PatternMatcher.getDescription(descriptions[i]));
    }

    return createPattern(list);
  } 
}
