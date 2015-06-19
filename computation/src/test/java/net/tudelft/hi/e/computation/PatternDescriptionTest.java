package net.tudelft.hi.e.computation;

import net.tudelft.hi.e.computation.Count;
import net.tudelft.hi.e.computation.CountPatternDescription;
import net.tudelft.hi.e.computation.RecordCondition;
import net.tudelft.hi.e.computation.RecordOccurrenceCondition;
import net.tudelft.hi.e.computation.SingleCount;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
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

    final CountPatternDescription patDes = new CountPatternDescription(singleCount, condition);
    final CountPatternDescription patDesSame = new CountPatternDescription(singleCount, condition);
    final CountPatternDescription patDesNotSameCount =
        new CountPatternDescription(otherCount, condition);
    final CountPatternDescription patDesNotSameCond =
        new CountPatternDescription(singleCount, otherCondition);
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

    CountPatternDescription patDes = new CountPatternDescription(singleCount, condition);

    assertEquals((31 + condition.hashCode()) * 31 + singleCount.hashCode(), patDes.hashCode());
  }
}
