package computation;

import net.tudelft.hi.e.common.enums.CompareOperator;
import net.tudelft.hi.e.computation.Condition;
import net.tudelft.hi.e.data.NumberValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Before;
import org.junit.Test;

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
  public void testCondition() {
    assertEquals(null, condition);
    condition = new Condition(CompareOperator.EQ, new NumberValue(10));
    assertEquals(new Condition(CompareOperator.EQ, new NumberValue(10)),
        condition);
    assertEquals(CompareOperator.EQ, condition.getConditionOperator());
    assertEquals(new NumberValue(10), condition.getConditionValue());
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

    condition.setConditionValue(null);
    anothercondition.setConditionOperator(CompareOperator.L);
    assertNotEquals(condition, anothercondition);

    anothercondition.setConditionValue(null);
    assertEquals(condition, anothercondition);

    anothercondition.setConditionValue(new NumberValue(10));
    condition.setConditionValue(new NumberValue(10));
    anothercondition.setConditionOperator(CompareOperator.L);
    assertEquals(condition, anothercondition);

    condition.setConditionValue(new NumberValue(15));
    assertNotEquals(condition, anothercondition);
  }

  @Test
  public void testHashCode() {
    condition = new Condition(CompareOperator.EQ, new NumberValue(10));

    final int prime = 31;
    int result = 1;
    result = prime * result
        + ((condition.getConditionOperator() == null) ? 0 : condition.
            getConditionOperator().hashCode());
    result = prime * result
        + ((condition.getConditionValue() == null) ? 0 : condition.
            getConditionValue().hashCode());
    assertEquals(result, condition.hashCode());

    //
    condition.setConditionOperator(null);
    condition.setConditionValue(null);
    result = 1;
    result = prime * result
        + ((condition.getConditionOperator() == null) ? 0 : condition.
            getConditionOperator().hashCode());
    result = prime * result
        + ((condition.getConditionValue() == null) ? 0 : condition.
            getConditionValue().hashCode());
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