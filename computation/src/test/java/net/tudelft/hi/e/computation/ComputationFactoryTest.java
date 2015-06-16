package net.tudelft.hi.e.computation;

import net.tudelft.hi.e.computation.ComputationFactory;
import net.tudelft.hi.e.computation.CountComputation;
import net.tudelft.hi.e.computation.Computation;
import net.tudelft.hi.e.computation.SumComputation;
import net.tudelft.hi.e.computation.MinComputation;
import net.tudelft.hi.e.computation.AvgComputation;
import net.tudelft.hi.e.computation.StdevComputation;
import net.tudelft.hi.e.computation.MaxComputation;
import static org.junit.Assert.assertTrue;

import net.tudelft.hi.e.common.enums.ComputeOperator;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

/**
 * Class for testing ComputationFactory.
 */
public class ComputationFactoryTest {

  @Test
  public void testCreateComputation() {
    assertTrue(getComp(ComputeOperator.AVG) instanceof AvgComputation);
    assertTrue(getComp(ComputeOperator.COUNT) instanceof CountComputation);
    assertTrue(getComp(ComputeOperator.MAX) instanceof MaxComputation);
    assertTrue(getComp(ComputeOperator.MIN) instanceof MinComputation);
    assertTrue(getComp(ComputeOperator.SUM) instanceof SumComputation);
    assertTrue(getComp(ComputeOperator.STDEV) instanceof StdevComputation);
  }
  
  private Computation getComp(ComputeOperator operator) {
    return ComputationFactory.createComputation(operator);
  }
  
  @Test
  public void testConstructorIsPrivate() throws NoSuchMethodException, IllegalAccessException,
      InvocationTargetException, InstantiationException {
    Constructor<ComputationFactory> constructor = ComputationFactory.class.getDeclaredConstructor();
    assertTrue(Modifier.isPrivate(constructor.getModifiers()));
    constructor.setAccessible(true);
    constructor.newInstance();
  }
}
