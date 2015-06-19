package net.tudelft.hi.e.computation;

import net.tudelft.hi.e.data.NumberValue;
import net.tudelft.hi.e.data.Value;

import java.util.List;

/**
 * Case class of {@link Computation} for counting.
 */
public class CountComputation implements Computation {

  @Override
  public NumberValue compute(List<Value> values) {
    int count = 0;

    for (Value value : values) {
      count += (value.isNull() ? 0 : 1);
    }

    return new NumberValue(count);
  }

}
