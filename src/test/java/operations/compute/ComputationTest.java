package operations.compute;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import table.value.NullValue;
import table.value.NumberValue;
import table.value.Value;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for testing all Computations.
 */
public class ComputationTest {
  private List<Value> values;

  /**
   * Create value list.
   */
  @Before
  public void setUp() {
    values = new ArrayList<Value>();
    values.add(new NullValue());
    addValues(2, 6, 9, 35, 100, 10, 50);
    values.add(new NullValue());
    addValues(40, 30, 28, 60, 0, 7);
    values.add(new NullValue());
  }

  private void addValues(double... ds) {
    for (int i = 0; i < ds.length; i++) {
      values.add(new NumberValue(ds[i]));
    }
  }

  @Test
  public void testAvgComputation() {
    assertEquals(new NumberValue(29), new AvgComputation().compute(values));
  }

  @Test
  public void testCountComputation() {
    assertEquals(new NumberValue(13), new CountComputation().compute(values));
  }

  @Test
  public void testMinComputation() {
    assertEquals(new NumberValue(0), new MinComputation().compute(values));
  }

  @Test
  public void testMaxComputation() {
    assertEquals(new NumberValue(100), new MaxComputation().compute(values));
  }

  @Test
  public void testSumComputation() {
    assertEquals(new NumberValue(377), new SumComputation().compute(values));
  }

  @Test
  public void testStdevComputation() {
    assertEquals(new NumberValue(Math.sqrt((double) 9946 / 13)),
        new StdevComputation().compute(values));
  }
}
