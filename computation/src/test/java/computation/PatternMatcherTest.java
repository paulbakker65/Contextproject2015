package computation;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import net.tudelft.hi.e.common.enums.CompareOperator;
import net.tudelft.hi.e.computation.Condition;
import net.tudelft.hi.e.computation.Count;
import net.tudelft.hi.e.computation.MultipleCount;
import net.tudelft.hi.e.computation.PatternDescription;
import net.tudelft.hi.e.computation.PatternMatcher;
import net.tudelft.hi.e.computation.RecordCondition;
import net.tudelft.hi.e.computation.RecordMatchesConditionCondition;
import net.tudelft.hi.e.computation.RecordOccurrenceCondition;
import net.tudelft.hi.e.computation.SingleCount;
import net.tudelft.hi.e.data.NumberValue;
import net.tudelft.hi.e.data.StringValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

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

  @Test
  public void testConstructorIsPrivate() throws NoSuchMethodException, IllegalAccessException,
      InvocationTargetException, InstantiationException {
    Constructor<PatternMatcher> constructor = PatternMatcher.class.getDeclaredConstructor();
    assertTrue(Modifier.isPrivate(constructor.getModifiers()));
    constructor.setAccessible(true);
    constructor.newInstance();
  }
}
