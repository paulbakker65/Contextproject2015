package operations.patterns;

import enums.CompareOperator;

import operations.patterns.condition.RecordCondition;
import operations.patterns.condition.RecordMatchesConditionCondition;
import operations.patterns.condition.RecordOccurrenceCondition;

import scriptlang.extra.Condition;

import table.value.NumberValue;
import table.value.StringValue;
import table.value.Value;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class for recognizing {@link PatternDescription}s using strings.
 */
public class PatternMatcher {
  private static Pattern singleRegex = Pattern.compile("([1-9]+[0-9]*)");
  private static Pattern occurRegex = Pattern.compile("([^\\s]+)");
  private static Pattern condStringRegex = Pattern.compile("([^\\s]+)\\s*==\\s*'(.+)'");
  private static Pattern condNumberRegex = Pattern.compile("([^\\s]+)\\s*==\\s*([0-9]+)");
  
  /**
   * Creates a {@link PatternDescription} using a string.
   * @param expr
   *        the string representation of the description.
   * @return
   *        the created {@link PatternDescription}.
   */
  public static PatternDescription getDescription(String expr) {
    String[] description = expr.split(" ", 2);
    String count = description[0];
    String condition = description[1];
    Count resCount = null;
    RecordCondition resCondition = null;
    
    Matcher matcher = singleRegex.matcher(count);
    if (matcher.find()) {
      resCount = new SingleCount(Integer.parseInt(count));
    } else if (count.equals("*")) {
      resCount = new MultipleCount();
    } else {
      return null;
    }
    
    matcher = condNumberRegex.matcher(condition);
    if (matcher.find()) {
      Value numberValue = new NumberValue(Integer.parseInt(matcher.group(2)));
      Condition eqCondition = new Condition(CompareOperator.EQ, numberValue);
      resCondition = new RecordMatchesConditionCondition(matcher.group(1), eqCondition);
      return new PatternDescription(resCount, resCondition);
    }    
    matcher = condStringRegex.matcher(condition);
    if (matcher.find()) {
      Condition eqCondition = new Condition(CompareOperator.EQ, new StringValue(matcher.group(2)));
      resCondition = new RecordMatchesConditionCondition(matcher.group(1), eqCondition);
      return new PatternDescription(resCount, resCondition);
    }
    matcher = occurRegex.matcher(condition);
    if (matcher.find()) {
      resCondition = new RecordOccurrenceCondition(condition);  
      return new PatternDescription(resCount, resCondition);
    }
    
    return null;
  }
}
