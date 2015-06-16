package net.tudelft.hi.e.computation;

import java.util.List;
import net.tudelft.hi.e.data.NumberValue;
import net.tudelft.hi.e.data.Value;

/**
 * Case class of {@link Computation} for computing maximum.
 */
public class MaxComputation implements Computation {

  @Override
  public NumberValue compute(List<Value> values) {
    NumberValue maxValue = null;
    double max = Double.MIN_VALUE;

    for (Value value : values) {
      if (value.isNumeric()) {
        double curValue = ((NumberValue) value).getValue();

        if (curValue > max) {
          max = curValue;
          maxValue = ((NumberValue) value);
        }
      }
    }

    return maxValue;
  }

}
