package operations.compute;

import table.value.NumberValue;
import table.value.Value;

import java.util.List;

/**
 * Case class of {@link Computation} for computing standard deviation.
 */
public class StdevComputation implements Computation {

  @Override
  public NumberValue compute(List<Value> values) {
    double average = new AvgComputation().compute(values).getValue();
    double sumOfSquares = 0.0;
    int count = 0;
    
    for (Value value : values) {
      if (value.isNumeric()) {
        sumOfSquares += Math.pow(average - ((NumberValue) value).getValue(), 2);
        count++;
      }
    }
    
    return new NumberValue(Math.sqrt(sumOfSquares / count)); 
  }

}
