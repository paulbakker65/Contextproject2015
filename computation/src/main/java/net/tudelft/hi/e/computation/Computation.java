package net.tudelft.hi.e.computation;

import java.util.List;
import net.tudelft.hi.e.data.Value;

/**
 * Interface for representing computations that can be executed for a list of Values.
 */
public interface Computation {
  /**
   * Performs a computation on a list of Values.
   *
   * @param values
   *          the Values to check.
   * @return the result of the computation.
   */
  Value compute(List<Value> values);
}
