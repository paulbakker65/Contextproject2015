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
    RecordOccurrenceCondition condition = new RecordOccurrenceCondition("Column");
    RecordOccurrenceCondition conditionSame = new RecordOccurrenceCondition("Column");
    RecordOccurrenceCondition conditionNotSame = new RecordOccurrenceCondition("Column2");
    String otherClass = "";
    
    assertEquals(condition, condition);
    assertEquals(condition, conditionSame);
    assertNotEquals(condition, null);
    assertNotEquals(condition, conditionNotSame);
    assertNotEquals(condition, otherClass);
  }
  
  @Test
  public void testHashCode() {
    String column = "Column";
    RecordOccurrenceCondition condition = new RecordOccurrenceCondition(column);
    
    assertEquals(31 + column.hashCode(), condition.hashCode());
  }

}
