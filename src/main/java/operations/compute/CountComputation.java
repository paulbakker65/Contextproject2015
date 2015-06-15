package operations.compute;

import table.value.NumberValue;
import table.value.Value;

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
