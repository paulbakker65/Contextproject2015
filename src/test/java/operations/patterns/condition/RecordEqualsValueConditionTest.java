package operations.patterns.condition;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import enums.CompareOperator;

import org.junit.Test;

import scriptlang.extra.Condition;

import table.value.StringValue;
import table.value.Value;

/**
 * Class for testing the RecordEqualsValueCondition.
 */
public class RecordEqualsValueConditionTest {

  @Test
  public void testEquals() {
    final RecordMatchesConditionCondition condition =
        new RecordMatchesConditionCondition("Column", new StringValue("Text"));
    final RecordMatchesConditionCondition conditionSame =
        new RecordMatchesConditionCondition("Column", new StringValue("Text"));
    final RecordMatchesConditionCondition conditionNotSameColumn =
        new RecordMatchesConditionCondition("Column2", new StringValue("Text"));
    final RecordMatchesConditionCondition conditionNotSameValue =
        new RecordMatchesConditionCondition("Column", new StringValue("Text2"));
    final String otherClass = "";

    assertEquals(condition, condition);
    assertEquals(condition, conditionSame);
    assertNotEquals(condition, null);
    assertNotEquals(condition, conditionNotSameColumn);
    assertNotEquals(condition, conditionNotSameValue);
    assertNotEquals(condition, otherClass);
  }

  @Test
  public void testHashCode() {
    final String column = "Column";    
    final Value value = new StringValue("Text");
    final Condition valueCondition = new Condition(CompareOperator.EQ, value);
    final RecordMatchesConditionCondition condition = new RecordMatchesConditionCondition(column, value);

    assertEquals((31 + column.hashCode()) * 31 + valueCondition.hashCode(), condition.hashCode());
  }

}
