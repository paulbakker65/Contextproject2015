package net.tudelft.hi.e.computation;

import net.tudelft.hi.e.data.NumberValue;
import net.tudelft.hi.e.data.Value;

import java.util.List;

/**
 * Case class of {@link Computation} for computing minimum.
 */
public class MinComputation implements Computation {

  @Override
  public NumberValue compute(List<Value> values) {
    NumberValue minValue = null;
    double min = Double.MAX_VALUE;

    for (Value value : values) {
      if (value.isNumeric()) {
        double curValue = ((NumberValue) value).getValue();

        if (curValue < min) {
          min = curValue;
          minValue = ((NumberValue) value);
        }
      }
    }

    return minValue;
  }

}
