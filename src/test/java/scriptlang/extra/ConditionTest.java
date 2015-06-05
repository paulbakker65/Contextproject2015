package scriptlang.extra;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import enums.CompareOperator;

import org.junit.Before;
import org.junit.Test;

import table.value.NumberValue;

/**
 * JUnit tests for Condition class.
 *
 */
public class ConditionTest {

  Condition condition;

  @Before
  public void setUp() throws Exception {}

  @Test
  public void testCondition() {
    assertEquals(null, condition);
    condition = new Condition(CompareOperator.EQ, new NumberValue(10));
    assertEquals(new Condition(CompareOperator.EQ, new NumberValue(10)), condition);
    assertEquals(CompareOperator.EQ, condition.conditionOperator);
    assertEquals(new NumberValue(10), condition.conditionValue);
  }

  @Test
  public void testEqualsObject() {
    Condition anothercondition = null;
    condition = new Condition(CompareOperator.L, new NumberValue(10));
    
    assertEquals(condition, condition);
    assertNotEquals(condition, anothercondition);
    assertNotEquals(condition, new Object());

    anothercondition = new Condition(CompareOperator.LEQ, new NumberValue(10));
    assertNotEquals(condition, anothercondition);

    condition.conditionValue = null;
    anothercondition.conditionOperator = CompareOperator.L;
    assertNotEquals(condition, anothercondition);

    anothercondition.conditionValue = null;
    assertEquals(condition, anothercondition);

    anothercondition.conditionValue = new NumberValue(10);
    condition.conditionValue = new NumberValue(10);
    anothercondition.conditionOperator = CompareOperator.L;
    assertEquals(condition, anothercondition);

    condition.conditionValue = new NumberValue(15);
    assertNotEquals(condition, anothercondition);
  }

  @Test
  public void testHashCode() {
    condition = new Condition(CompareOperator.EQ, new NumberValue(10));

    final int prime = 31;
    int result = 1;
    result =
        prime * result
            + ((condition.conditionOperator == null) ? 0 : condition.conditionOperator.hashCode());
    result =
        prime * result
            + ((condition.conditionValue == null) ? 0 : condition.conditionValue.hashCode());
    assertEquals(result, condition.hashCode());

    //
    condition.conditionOperator = null;
    condition.conditionValue = null;
    result = 1;
    result =
        prime * result
            + ((condition.conditionOperator == null) ? 0 : condition.conditionOperator.hashCode());
    result =
        prime * result
            + ((condition.conditionValue == null) ? 0 : condition.conditionValue.hashCode());
    assertEquals(result, condition.hashCode());
  }

  @Test
  public void testToString() {
    condition = new Condition(CompareOperator.EQ, new NumberValue(10));
    assertEquals("== 10", condition.toString());
    condition = new Condition(CompareOperator.NEQ, new NumberValue(10));
    assertEquals("!= 10", condition.toString());
    condition = new Condition(CompareOperator.LEQ, new NumberValue(10));
    assertEquals("<= 10", condition.toString());
    condition = new Condition(CompareOperator.L, new NumberValue(10));
    assertEquals("< 10", condition.toString());
    condition = new Condition(CompareOperator.GEQ, new NumberValue(10));
    assertEquals(">= 10", condition.toString());
    condition = new Condition(CompareOperator.G, new NumberValue(10));
    assertEquals("> 10", condition.toString());
    condition = new Condition(CompareOperator.ND, new NumberValue(10));
    assertEquals("? 10", condition.toString());
  }

}
