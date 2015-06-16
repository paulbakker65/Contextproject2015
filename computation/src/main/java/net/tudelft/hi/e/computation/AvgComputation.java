package net.tudelft.hi.e.computation;

import java.util.List;
import net.tudelft.hi.e.data.NumberValue;
import net.tudelft.hi.e.data.Value;

/**
 * Case class of {@link Computation} for computing average.
 */
public class AvgComputation implements Computation {

  @Override
  public NumberValue compute(List<Value> values) {
    double sum = 0.0;
    int count = 0;

    for (Value value : values) {
      if (value.isNumeric()) {
        sum += ((NumberValue) value).getValue();
        count++;
      }
    }

    return new NumberValue(sum / count);
  }

}
