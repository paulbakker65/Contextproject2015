package operations.patterns;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import operations.patterns.condition.RecordCondition;
import operations.patterns.condition.RecordOccurrenceCondition;

import org.junit.Test;

/**
 * Class for testing PatternDescription.
 */
public class PatternDescriptionTest {
  
  @Test
  public void testEquals() {
    Count singleCount = new SingleCount(1);
    Count otherCount = new SingleCount(2);
    RecordCondition condition = new RecordOccurrenceCondition("tableName");
    RecordCondition otherCondition = new RecordOccurrenceCondition("tableName2");
    
    final PatternDescription patDes = new PatternDescription(singleCount, condition);
    final PatternDescription patDesSame = new PatternDescription(singleCount, condition);
    final PatternDescription patDesNotSameCount = 
        new PatternDescription(otherCount, condition);
    final PatternDescription patDesNotSameCond = 
        new PatternDescription(singleCount, otherCondition);
    final String otherClass = "";
    
    assertEquals(patDes, patDes);
    assertEquals(patDes, patDesSame);
    assertNotEquals(patDes, null);
    assertNotEquals(patDes, patDesNotSameCount);
    assertNotEquals(patDes, patDesNotSameCond);
    assertNotEquals(patDes, otherClass);
  }
  
  @Test
  public void testHashCode() {
    Count singleCount = new SingleCount(1);
    RecordCondition condition = new RecordOccurrenceCondition("tableName");
    
    PatternDescription patDes = new PatternDescription(singleCount, condition);
    
    assertEquals((31 + condition.hashCode()) * 31 + singleCount.hashCode(), patDes.hashCode());
  }
}
