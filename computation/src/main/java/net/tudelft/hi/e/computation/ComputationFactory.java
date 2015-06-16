package net.tudelft.hi.e.computation;

import net.tudelft.hi.e.common.enums.ComputeOperator;

/**
 * Factory for creating implementations of {@link Computation}.
 */
public class ComputationFactory {
  
  /**
   * Creates the correct Computation instance given an operator.
   * 
   * @param operator
   *          the given operator.
   * @return the correct Computation.
   */
  public static Computation createComputation(ComputeOperator operator) {
    switch (operator) {
      case AVG:   return new AvgComputation();      
      case COUNT: return new CountComputation(); 
      case MAX:   return new MaxComputation(); 
      case MIN:   return new MinComputation(); 
      case STDEV: return new StdevComputation(); 
      default:    return new SumComputation();
    }
  }
  
  private ComputationFactory() {    
  }
}
