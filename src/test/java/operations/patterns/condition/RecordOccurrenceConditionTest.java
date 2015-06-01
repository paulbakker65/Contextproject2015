package operations.patterns.condition;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

/**
 * Class for testing the RecordOccurrenceCondition.
 */
public class RecordOccurrenceConditionTest {

  @Test
  public void testEquals() {
    final RecordOccurrenceCondition condition = new RecordOccurrenceCondition("Column");
    final RecordOccurrenceCondition conditionSame = new RecordOccurrenceCondition("Column");
    final RecordOccurrenceCondition conditionNotSame = new RecordOccurrenceCondition("Column2");
    final String otherClass = "";

    assertEquals(condition, condition);
    assertEquals(condition, conditionSame);
    assertNotEquals(condition, null);
    assertNotEquals(condition, conditionNotSame);
    assertNotEquals(condition, otherClass);
  }

  @Test
  public void testHashCode() {
    final String column = "Column";
    final RecordOccurrenceCondition condition = new RecordOccurrenceCondition(column);

    assertEquals(31 + column.hashCode(), condition.hashCode());
  }

}
