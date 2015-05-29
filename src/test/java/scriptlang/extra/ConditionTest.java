package scriptlang.extra;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import enums.CompareOperator;
import table.value.NumberValue;

/**
 * JUnit tests for Condition class.
 *
 */
public class ConditionTest {

  Condition condition;

  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void testHashCode() {
    condition = new Condition(CompareOperator.EQ, new NumberValue(10));

    final int prime = 31;
    int result = 1;
    result = prime * result
        + ((condition.conditionOperator == null) ? 0 : condition.conditionOperator.hashCode());
    result = prime * result
        + ((condition.conditionValue == null) ? 0 : condition.conditionValue.hashCode());
    assertEquals(result, condition.hashCode());

    //
    condition.conditionOperator = null;
    condition.conditionValue = null;
    result = 1;
    result = prime * result
        + ((condition.conditionOperator == null) ? 0 : condition.conditionOperator.hashCode());
    result = prime * result
        + ((condition.conditionValue == null) ? 0 : condition.conditionValue.hashCode());
    assertEquals(result, condition.hashCode());
  }

  @Test
  public void testCondition() {
    assertEquals(null, condition);
    condition = new Condition(CompareOperator.EQ, new NumberValue(10));
    assertEquals(new Condition(CompareOperator.EQ, new NumberValue(10)), condition);
    assertEquals(CompareOperator.EQ, condition.conditionOperator);
    assertEquals(new NumberValue(10), condition.conditionValue);
  }

  @Test
  public void testToString() {
    condition = new Condition(CompareOperator.G, new NumberValue(10));

    assertEquals("Condition [condOperator=G, condValue=10]", condition.toString());
  }

  @Test
  public void testEqualsObject() {
    Condition anothercondition = null;

    condition = new Condition(CompareOperator.L, new NumberValue(10));
    assertEquals(true, condition.equals(condition));

    assertEquals(false, condition.equals(anothercondition));

    assertEquals(false, condition.equals(new Object()));

    anothercondition = new Condition(CompareOperator.LEQ, new NumberValue(10));
    assertEquals(false, condition.equals(anothercondition));

    condition.conditionValue = null;
    assertEquals(false, condition.equals(anothercondition));

    anothercondition.conditionValue = null;
    assertEquals(false, condition.equals(anothercondition));

    anothercondition.conditionValue = new NumberValue(10);
    condition.conditionValue = new NumberValue(10);
    anothercondition.conditionOperator = CompareOperator.L;
    assertEquals(true, condition.equals(anothercondition));

    condition.conditionValue = new NumberValue(15);
    assertEquals(false, condition.equals(anothercondition));
  }

}
