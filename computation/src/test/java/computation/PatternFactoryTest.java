package computation;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import net.tudelft.hi.e.common.enums.CompareOperator;
import net.tudelft.hi.e.computation.Condition;
import net.tudelft.hi.e.computation.Count;
import net.tudelft.hi.e.computation.MultipleConditionPattern;
import net.tudelft.hi.e.computation.MultipleCount;
import net.tudelft.hi.e.computation.Pattern;
import net.tudelft.hi.e.computation.PatternDescription;
import net.tudelft.hi.e.computation.PatternFactory;
import net.tudelft.hi.e.computation.RecordCondition;
import net.tudelft.hi.e.computation.RecordMatchesConditionCondition;
import net.tudelft.hi.e.computation.RecordOccurrenceCondition;
import net.tudelft.hi.e.computation.SingleConditionPattern;
import net.tudelft.hi.e.computation.SingleCount;
import net.tudelft.hi.e.data.StringValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Test for pattern factory.
 */
public class PatternFactoryTest {
  private PatternDescription oneOccur;
  private PatternDescription twoOccur;
  private PatternDescription multiOccur;
  private PatternDescription oneCond;
  private PatternDescription twoCond;
  private PatternDescription multiCond;

  private RecordCondition occurCondition;
  private RecordCondition condCondition;

  /**
   * Setup for the test.
   */
  @Before
  public void setUp() {
    final Count oneCount = new SingleCount(1);
    final Count twoCount = new SingleCount(2);
    final Count multiCount = new MultipleCount();

    Condition condition = new Condition(CompareOperator.EQ, new StringValue("toCompare"));

    occurCondition = new RecordOccurrenceCondition("tableName");
    condCondition = new RecordMatchesConditionCondition("columnName", condition);

    oneOccur = new PatternDescription(oneCount, occurCondition);
    twoOccur = new PatternDescription(twoCount, occurCondition);
    multiOccur = new PatternDescription(multiCount, occurCondition);
    oneCond = new PatternDescription(oneCount, condCondition);
    twoCond = new PatternDescription(twoCount, condCondition);
    multiCond = new PatternDescription(multiCount, condCondition);
  }

  @Test
  public void testOneSingleOccurrencePattern() {
    List<PatternDescription> list = new ArrayList<PatternDescription>();
    list.add(oneOccur);

    Pattern expected = new SingleConditionPattern(occurCondition);

    assertEquals(expected, PatternFactory.createPattern(list));
  }

  @Test
  public void testOneSingleConditionPattern() {
    List<PatternDescription> list = new ArrayList<PatternDescription>();
    list.add(oneCond);

    Pattern expected = new SingleConditionPattern(condCondition);

    assertEquals(expected, PatternFactory.createPattern(list));
  }

  @Test
  public void testTwoSingleOccurrencePattern() {
    List<PatternDescription> list = new ArrayList<PatternDescription>();
    list.add(twoOccur);

    Pattern expectedEnd = new SingleConditionPattern(occurCondition);
    Pattern expected = new SingleConditionPattern(occurCondition);
    expected.setNextPattern(expectedEnd);

    assertEquals(expected, PatternFactory.createPattern(list));
  }

  @Test
  public void testTwoSingleConditionPattern() {
    List<PatternDescription> list = new ArrayList<PatternDescription>();
    list.add(twoCond);

    Pattern expectedEnd = new SingleConditionPattern(condCondition);
    Pattern expected = new SingleConditionPattern(condCondition);
    expected.setNextPattern(expectedEnd);

    assertEquals(expected, PatternFactory.createPattern(list));
  }

  @Test
  public void testMultiOccurrencePattern() {
    List<PatternDescription> list = new ArrayList<PatternDescription>();
    list.add(multiOccur);

    Pattern expected = new MultipleConditionPattern(occurCondition);

    assertEquals(expected, PatternFactory.createPattern(list));
  }

  @Test
  public void testMultiConditionPattern() {
    List<PatternDescription> list = new ArrayList<PatternDescription>();
    list.add(multiCond);

    Pattern expected = new MultipleConditionPattern(condCondition);

    assertEquals(expected, PatternFactory.createPattern(list));
  }

  @Test
  public void testMultipleDescriptionsPattern() {
    List<PatternDescription> list = new ArrayList<PatternDescription>();
    list.add(oneOccur);
    list.add(multiCond);
    list.add(twoCond);
    list.add(multiOccur);
    list.add(twoOccur);
    list.add(oneCond);

    Pattern expectedOneCond = new SingleConditionPattern(condCondition);
    Pattern expectedTwoOccurEnd = new SingleConditionPattern(occurCondition, expectedOneCond);
    Pattern expectedTwoOccur = new SingleConditionPattern(occurCondition, expectedTwoOccurEnd);
    Pattern expectedMultiOccur = new MultipleConditionPattern(occurCondition, expectedTwoOccur);
    Pattern expectedTwoCondEnd = new SingleConditionPattern(condCondition, expectedMultiOccur);
    Pattern expectedTwoCond = new SingleConditionPattern(condCondition, expectedTwoCondEnd);
    Pattern expectedMultiCond = new MultipleConditionPattern(condCondition, expectedTwoCond);
    Pattern expected = new SingleConditionPattern(occurCondition, expectedMultiCond);

    assertEquals(expected, PatternFactory.createPattern(list));
  }

  @Test
  public void testConstructorIsPrivate() throws NoSuchMethodException, IllegalAccessException,
      InvocationTargetException, InstantiationException {
    Constructor<PatternFactory> constructor = PatternFactory.class.getDeclaredConstructor();
    assertTrue(Modifier.isPrivate(constructor.getModifiers()));
    constructor.setAccessible(true);
    constructor.newInstance();
  }
}
