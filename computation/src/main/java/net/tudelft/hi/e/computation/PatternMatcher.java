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
   * @param expr the string representation of the description.
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
    Count resCount = checkForCountRegex(count);
    if (resCount == null) {
      return null;
    }
    return checkRegexes(resCount, condition);
  }
  
  private static PatternDescription checkRegexes(Count resCount, String condition) {
    PatternDescription numberDesc = checkNumberRegex(resCount, condition);
    if (numberDesc != null) {
      return numberDesc;
    }
    PatternDescription stringDesc = checkStringRegex(resCount, condition);
    if (stringDesc != null) {
      return stringDesc;
    }
    PatternDescription occurDesc = checkOccurRegex(resCount, condition);
    if (occurDesc != null) {
      return occurDesc;
    } 
    return null;
  }
  
  private static PatternDescription checkNumberRegex(Count count, String condition) {
    Matcher matcher = condNumberRegex.matcher(condition);
    if (matcher.find()) {
      Value numberValue = new NumberValue(Integer.parseInt(matcher.group(2)));
      Condition eqCondition = new Condition(CompareOperator.EQ, numberValue);
      RecordCondition resCondition =
          new RecordMatchesConditionCondition(matcher.group(1), eqCondition);
      return new CountPatternDescription(count, resCondition);
    }
    return null;
  }
  
  private static PatternDescription checkStringRegex(Count count, String condition) {
    Matcher matcher = condStringRegex.matcher(condition);
    if (matcher.find()) {
      Condition eqCondition = new Condition(CompareOperator.EQ, new StringValue(matcher.group(2)));
      RecordCondition resCondition =
          new RecordMatchesConditionCondition(matcher.group(1), eqCondition);
      return new CountPatternDescription(count, resCondition);
    }
    return null;
  }
  
  private static PatternDescription checkOccurRegex(Count count, String condition) {
    Matcher matcher = occurRegex.matcher(condition);
    if (matcher.find()) {
      RecordCondition resCondition = new RecordOccurrenceCondition(condition);
      return new CountPatternDescription(count, resCondition);
    }
    return null;
  }

  private static Count checkForCountRegex(String expr) {
    Matcher matcher = singleRegex.matcher(expr);
    if (matcher.find() && matcher.group(1).equals(expr)) {
      return new SingleCount(Integer.parseInt(expr));
    }
    if ("*".equals(expr)) {
      return new MultipleCount();
    }
    return null;
  }
}
