package operations.patterns.condition;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import table.value.StringValue;
import table.value.Value;

/**
 * Class for testing the RecordEqualsValueCondition.
 */
public class RecordEqualsValueConditionTest {

  @Test
  public void testEquals() {
    RecordEqualsValueCondition condition = new RecordEqualsValueCondition("Column", new StringValue("Text"));
    RecordEqualsValueCondition conditionSame = new RecordEqualsValueCondition("Column", new StringValue("Text"));
    RecordEqualsValueCondition conditionNotSameColumn = new RecordEqualsValueCondition("Column2", 
        new StringValue("Text"));
    RecordEqualsValueCondition conditionNotSameValue = new RecordEqualsValueCondition("Column", 
        new StringValue("Text2"));
    String otherClass = "";
    
    assertEquals(condition, condition);
    assertEquals(condition, conditionSame);
    assertNotEquals(condition, null);
    assertNotEquals(condition, conditionNotSameColumn);
    assertNotEquals(condition, conditionNotSameValue);
    assertNotEquals(condition, otherClass);
  }
  
  @Test
  public void testHashCode() {
    String column = "Column";
    Value value = new StringValue("Text");
    RecordEqualsValueCondition condition = new RecordEqualsValueCondition(column, value);
    
    assertEquals((31 + column.hashCode()) * 31 + value.hashCode(), condition.hashCode());
  }

}
