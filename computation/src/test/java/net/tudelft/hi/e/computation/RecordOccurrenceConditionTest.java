package net.tudelft.hi.e.computation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

/**
 * Class for testing the RecordOccurrenceCondition.
 */
public class RecordOccurrenceConditionTest {

  @Test
  public void testEquals() {
    final RecordOccurrenceCondition condition = new RecordOccurrenceCondition("TableName");
    final RecordOccurrenceCondition conditionSame = new RecordOccurrenceCondition("TableName");
    final RecordOccurrenceCondition conditionNotSame = new RecordOccurrenceCondition("TableName2");
    final String otherClass = "";

    assertEquals(condition, condition);
    assertEquals(condition, conditionSame);
    assertNotEquals(condition, null);
    assertNotEquals(condition, conditionNotSame);
    assertNotEquals(condition, otherClass);
  }

  @Test
  public void testHashCode() {
    final String tableName = "TableName";
    final RecordOccurrenceCondition condition = new RecordOccurrenceCondition(tableName);

    assertEquals(31 + tableName.hashCode(), condition.hashCode());
  }
  
  @Test
  public void testToString() {
    final RecordOccurrenceCondition condition = new RecordOccurrenceCondition("TableName");
    assertEquals("RecordOccurrenceCondition [tableName=TableName]", condition.toString());
  }

}
