package operations.patterns;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import enums.CompareOperator;

import operations.patterns.condition.RecordCondition;
import operations.patterns.condition.RecordMatchesConditionCondition;
import operations.patterns.condition.RecordOccurrenceCondition;

import org.junit.Test;

import scriptlang.extra.Condition;

import table.value.NumberValue;
import table.value.StringValue;

public class PatternMatcherTest {
  
  @Test
  public void testGetDescriptionOccur() {
    Count singleCount = new SingleCount(1);
    RecordCondition occurCondition = new RecordOccurrenceCondition("tableName");
    
    PatternDescription expected = new PatternDescription(singleCount, occurCondition);
    assertEquals(expected, PatternMatcher.getDescription("1 tableName"));
  }
  
  @Test
  public void testGetDescriptionEqualsString() {
    Count singleCount = new SingleCount(1);
    Condition condition = new Condition(CompareOperator.EQ, new StringValue("Crea"));
    RecordCondition matchesCondition = new RecordMatchesConditionCondition("colName", condition);
    
    PatternDescription expected = new PatternDescription(singleCount, matchesCondition);
    assertEquals(expected, PatternMatcher.getDescription("1 colName == 'Crea'"));
  }
  
  @Test
  public void testGetDescriptionEqualsNumber() {
    Count singleCount = new SingleCount(1);
    Condition condition = new Condition(CompareOperator.EQ, new NumberValue(140));
    RecordCondition matchesCondition = new RecordMatchesConditionCondition("colName", condition);
    
    PatternDescription expected = new PatternDescription(singleCount, matchesCondition);
    assertEquals(expected, PatternMatcher.getDescription("1 colName == 140"));
  }
  
  @Test
  public void testGetDescriptionOccurMultiple() {
    Count multiCount = new MultipleCount();
    RecordCondition occurCondition = new RecordOccurrenceCondition("tableName");
    
    PatternDescription expected = new PatternDescription(multiCount, occurCondition);
    assertEquals(expected, PatternMatcher.getDescription("* tableName"));
  }
  
  @Test
  public void testGetDescriptionOccurUnknown() {
    assertNull(PatternMatcher.getDescription("x tableName"));
  }
  
  @Test
  public void testGetDescriptionOccurUnknownCondition() {
    assertNull(PatternMatcher.getDescription("1  "));
  }
}
