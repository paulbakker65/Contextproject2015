package net.tudelft.hi.e.computation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import net.tudelft.hi.e.common.enums.CompareOperator;
import net.tudelft.hi.e.data.NumberValue;
import net.tudelft.hi.e.data.StringValue;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

public class PatternMatcherTest {

  @Test
  public void testGetDescriptionOccur() {
    Count singleCount = new SingleCount(1);
    RecordCondition occurCondition = new RecordOccurrenceCondition("tableName");

    CountPatternDescription expected = new CountPatternDescription(singleCount, occurCondition);
    assertEquals(expected, PatternMatcher.getDescription("1 tableName"));
  }

  @Test
  public void testGetDescriptionEqualsString() {
    Count singleCount = new SingleCount(1);
    Condition condition = new Condition(CompareOperator.EQ, new StringValue("Crea"));
    RecordCondition matchesCondition = new RecordMatchesConditionCondition("colName", condition);

    CountPatternDescription expected = new CountPatternDescription(singleCount, matchesCondition);
    assertEquals(expected, PatternMatcher.getDescription("1 colName == 'Crea'"));
  }

  @Test
  public void testGetDescriptionEqualsNumber() {
    Count singleCount = new SingleCount(1);
    Condition condition = new Condition(CompareOperator.EQ, new NumberValue(140));
    RecordCondition matchesCondition = new RecordMatchesConditionCondition("colName", condition);

    CountPatternDescription expected = new CountPatternDescription(singleCount, matchesCondition);
    assertEquals(expected, PatternMatcher.getDescription("1 colName == 140"));
  }

  @Test
  public void testGetDescriptionOccurMultiple() {
    Count multiCount = new MultipleCount();
    RecordCondition occurCondition = new RecordOccurrenceCondition("tableName");

    CountPatternDescription expected = new CountPatternDescription(multiCount, occurCondition);
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

  @Test
  public void testConstructorIsPrivate() throws NoSuchMethodException, IllegalAccessException,
      InvocationTargetException, InstantiationException {
    Constructor<PatternMatcher> constructor = PatternMatcher.class.getDeclaredConstructor();
    assertTrue(Modifier.isPrivate(constructor.getModifiers()));
    constructor.setAccessible(true);
    constructor.newInstance();
  }
}
