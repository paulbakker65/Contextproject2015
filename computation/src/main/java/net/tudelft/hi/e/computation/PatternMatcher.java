package net.tudelft.hi.e.computation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.tudelft.hi.e.common.enums.CompareOperator;
import net.tudelft.hi.e.data.NumberValue;
import net.tudelft.hi.e.data.StringValue;
import net.tudelft.hi.e.data.Value;

/**
 * Class for recognizing {@link CountPatternDescription}s using strings.
 */
public class PatternMatcher {
  private static Pattern singleRegex = Pattern.compile("([1-9]+[0-9]*)");
  private static Pattern occurRegex = Pattern.compile("([^\\s]+)");
  private static Pattern condStringRegex = Pattern.compile("([^\\s]+)\\s*==\\s*'(.+)'");
  private static Pattern condNumberRegex = Pattern.compile("([^\\s]+)\\s*==\\s*([0-9]+)");
  private static Pattern notRegex = Pattern.compile("!\\((.*)\\)");

  /**
   * Default hidden constructor because this class cannot be instantiated.
   */
  private PatternMatcher() {
  }

  /**
   * Creates a {@link PatternDescription} using a string.
   *
   * @param expr
   *          the string representation of the description.
   * @return the created {@link PatternDescription}.
   */
  public static PatternDescription getDescription(String expr) {
    Matcher matcher = notRegex.matcher(expr);
    
    if (matcher.find()) {
      return new NotPatternDescription(getDescription(matcher.group(1)));
    }
    
    String[] description = expr.split(" ", 2);
    String count = description[0];
    String condition = description[1];
    Count resCount = null;
    RecordCondition resCondition = null;

    matcher = singleRegex.matcher(count);
    if (matcher.find() && matcher.group(1).equals(count)) {
      resCount = new SingleCount(Integer.parseInt(count));
    } else if ("*".equals(count)) {
      resCount = new MultipleCount();
    } else {
      return null;
    }

    matcher = condNumberRegex.matcher(condition);
    if (matcher.find()) {
      Value numberValue = new NumberValue(Integer.parseInt(matcher.group(2)));
      Condition eqCondition = new Condition(CompareOperator.EQ, numberValue);
      resCondition = new RecordMatchesConditionCondition(matcher.group(1), eqCondition);
      return new CountPatternDescription(resCount, resCondition);
    }
    matcher = condStringRegex.matcher(condition);
    if (matcher.find()) {
      Condition eqCondition = new Condition(CompareOperator.EQ, new StringValue(matcher.group(2)));
      resCondition = new RecordMatchesConditionCondition(matcher.group(1), eqCondition);
      return new CountPatternDescription(resCount, resCondition);
    }
    matcher = occurRegex.matcher(condition);
    if (matcher.find()) {
      resCondition = new RecordOccurrenceCondition(condition);
      return new CountPatternDescription(resCount, resCondition);
    }

    return null;
  }
}
