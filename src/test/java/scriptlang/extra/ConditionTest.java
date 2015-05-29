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
        + ((condition.condOperator == null) ? 0 : condition.condOperator.hashCode());
    result = prime * result + ((condition.condValue == null) ? 0 : condition.condValue.hashCode());
    assertEquals(result, condition.hashCode());

    //
    condition.condOperator = null;
    condition.condValue = null;
    result = 1;
    result = prime * result
        + ((condition.condOperator == null) ? 0 : condition.condOperator.hashCode());
    result = prime * result + ((condition.condValue == null) ? 0 : condition.condValue.hashCode());
    assertEquals(result, condition.hashCode());
  }

  @Test
  public void testCondition() {
    assertEquals(null, condition);
    condition = new Condition(CompareOperator.EQ, new NumberValue(10));
    assertEquals(new Condition(CompareOperator.EQ, new NumberValue(10)), condition);
    assertEquals(CompareOperator.EQ, condition.condOperator);
    assertEquals(new NumberValue(10), condition.condValue);
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

    condition.condValue = null;
    assertEquals(false, condition.equals(anothercondition));

    anothercondition.condValue = null;
    assertEquals(false, condition.equals(anothercondition));

    anothercondition.condValue = new NumberValue(10);
    condition.condValue = new NumberValue(10);
    anothercondition.condOperator = CompareOperator.L;
    assertEquals(true, condition.equals(anothercondition));

    condition.condValue = new NumberValue(15);
    assertEquals(false, condition.equals(anothercondition));
  }

}
