package net.tudelft.hi.e.computation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import net.tudelft.hi.e.common.enums.CalcOperator;
import net.tudelft.hi.e.data.NumberValue;

import org.junit.Before;
import org.junit.Test;

/**
 * Testing the Formula class.
 *
 */
public class FormulaTest {

  Formula formula;

  @Before
  public void setUp() throws Exception {}

  @Test
  public void testEquals() {
    Formula anotherformula;

    formula = new Formula(new NumberValue(10), CalcOperator.MULTIPLY, new NumberValue(10));
    anotherformula = new Formula(new NumberValue(10), CalcOperator.MULTIPLY, new NumberValue(10));

    assertEquals(true, formula.equals(formula));
    assertEquals(false, formula.equals(null));
    assertEquals(false, formula.equals(new Object()));

    formula = new Formula(new NumberValue(10), CalcOperator.MULTIPLY, new NumberValue(10));
    anotherformula = new Formula(new NumberValue(10), CalcOperator.MULTIPLY, new NumberValue(10));
    formula.setOperand1(null);
    anotherformula.setOperand1(null);
    assertEquals(true, formula.equals(anotherformula));

    formula = new Formula(new NumberValue(10), CalcOperator.MULTIPLY, new NumberValue(10));
    anotherformula = new Formula(new NumberValue(10), CalcOperator.MULTIPLY, new NumberValue(10));
    formula.setOperand1(null);
    assertEquals(false, formula.equals(anotherformula));

    formula = new Formula(new NumberValue(10), CalcOperator.MULTIPLY, new NumberValue(10));
    anotherformula = new Formula(new NumberValue(10), CalcOperator.MULTIPLY, new NumberValue(10));
    assertEquals(true, formula.equals(anotherformula));
    formula.setOperand1(new NumberValue(20));
    assertEquals(false, formula.equals(anotherformula));

    formula = new Formula(new NumberValue(10), CalcOperator.MULTIPLY, new NumberValue(10));
    anotherformula = new Formula(new NumberValue(10), CalcOperator.MULTIPLY, new NumberValue(10));
    formula.setOperand2(null);
    anotherformula.setOperand2(null);
    assertEquals(true, formula.equals(anotherformula));

    formula = new Formula(new NumberValue(10), CalcOperator.MULTIPLY, new NumberValue(10));
    anotherformula = new Formula(new NumberValue(10), CalcOperator.MULTIPLY, new NumberValue(10));
    formula.setOperand2(null);
    assertEquals(false, formula.equals(anotherformula));

    formula = new Formula(new NumberValue(10), CalcOperator.MULTIPLY, new NumberValue(10));
    anotherformula = new Formula(new NumberValue(10), CalcOperator.MULTIPLY, new NumberValue(10));
    assertEquals(true, formula.equals(anotherformula));

    formula = new Formula(new NumberValue(10), CalcOperator.MULTIPLY, new NumberValue(10));
    anotherformula = new Formula(new NumberValue(10), CalcOperator.MULTIPLY, new NumberValue(10));
    formula.setOperand2(new NumberValue(20));
    assertEquals(false, formula.equals(anotherformula));

    formula = new Formula(new NumberValue(10), CalcOperator.MULTIPLY, new NumberValue(10));
    anotherformula = new Formula(new NumberValue(10), CalcOperator.MULTIPLY, new NumberValue(10));
    assertEquals(true, formula.equals(anotherformula));

    formula = new Formula(new NumberValue(10), CalcOperator.MULTIPLY, new NumberValue(10));
    anotherformula = new Formula(new NumberValue(10), CalcOperator.MULTIPLY, new NumberValue(10));
    formula.setOperator(CalcOperator.DIVIDE);
    assertEquals(false, formula.equals(anotherformula));
  }

  @Test
  public void testFormula() {
    assertEquals(null, formula);
    formula = new Formula(new NumberValue(0), CalcOperator.MULTIPLY, new NumberValue(0));
    assertNotEquals(null, formula);
    assertEquals(new NumberValue(0), formula.getOperand1());
    assertEquals(new NumberValue(0), formula.getOperand2());
    assertEquals(CalcOperator.MULTIPLY, formula.getOperator());
  }

  @Test
  public void testHashCode() {
    final int prime = 31;
    int result = 1;

    //
    formula = new Formula(new NumberValue(10), CalcOperator.MULTIPLY, new NumberValue(10));
    result = 1;
    result =
        prime * result + ((formula.getOperand1() == null) ? 0 : formula.getOperand1().hashCode());
    result =
        prime * result + ((formula.getOperand2() == null) ? 0 : formula.getOperand2().hashCode());
    result =
        prime * result + ((formula.getOperator() == null) ? 0 : formula.getOperator().hashCode());
    assertEquals(result, formula.hashCode());

    //
    formula = new Formula(new NumberValue(10), CalcOperator.MULTIPLY, new NumberValue(10));
    formula.setOperand1(null);
    result = 1;
    result =
        prime * result + ((formula.getOperand1() == null) ? 0 : formula.getOperand1().hashCode());
    result =
        prime * result + ((formula.getOperand2() == null) ? 0 : formula.getOperand2().hashCode());
    result =
        prime * result + ((formula.getOperator() == null) ? 0 : formula.getOperator().hashCode());
    assertEquals(result, formula.hashCode());

    formula = new Formula(new NumberValue(10), CalcOperator.MULTIPLY, new NumberValue(10));
    formula.setOperand1(null);
    formula.setOperand2(null);
    result = 1;
    result =
        prime * result + ((formula.getOperand1() == null) ? 0 : formula.getOperand1().hashCode());
    result =
        prime * result + ((formula.getOperand2() == null) ? 0 : formula.getOperand2().hashCode());
    result =
        prime * result + ((formula.getOperator() == null) ? 0 : formula.getOperator().hashCode());
    assertEquals(result, formula.hashCode());

    formula = new Formula(new NumberValue(10), CalcOperator.MULTIPLY, new NumberValue(10));
    formula.setOperand1(null);
    formula.setOperator(null);
    result = 1;
    result =
        prime * result + ((formula.getOperand1() == null) ? 0 : formula.getOperand1().hashCode());
    result =
        prime * result + ((formula.getOperand2() == null) ? 0 : formula.getOperand2().hashCode());
    result =
        prime * result + ((formula.getOperator() == null) ? 0 : formula.getOperator().hashCode());
    assertEquals(result, formula.hashCode());

    formula = new Formula(new NumberValue(10), CalcOperator.MULTIPLY, new NumberValue(10));
    formula.setOperand1(null);
    formula.setOperand2(null);
    formula.setOperator(null);
    result = 1;
    result =
        prime * result + ((formula.getOperand1() == null) ? 0 : formula.getOperand1().hashCode());
    result =
        prime * result + ((formula.getOperand2() == null) ? 0 : formula.getOperand2().hashCode());
    result =
        prime * result + ((formula.getOperator() == null) ? 0 : formula.getOperator().hashCode());
    assertEquals(result, formula.hashCode());
  }

  @Test
  public void testToString() {
    formula = new Formula(new NumberValue(10), CalcOperator.MULTIPLY, new NumberValue(10));
    assertEquals("Formula [operand1=10, operand2=10, operator=MULTIPLY]", formula.toString());
  }

}
