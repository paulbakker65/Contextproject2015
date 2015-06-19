package net.tudelft.hi.e.computation;

import net.tudelft.hi.e.data.NumberValue;
import net.tudelft.hi.e.data.Value;

import java.util.List;

/**
 * Case class of {@link Computation} for computing sum.
 */
public class SumComputation implements Computation {

  @Override
  public NumberValue compute(List<Value> values) {
    double sum = 0.0;

    for (Value value : values) {
      if (value.isNumeric()) {
        sum += ((NumberValue) value).getValue();
      }
    }

    return new NumberValue(sum);
  }

}
