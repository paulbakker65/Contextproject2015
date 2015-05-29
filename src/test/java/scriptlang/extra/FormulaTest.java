package scriptlang.extra;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import enums.CalcOperator;
import table.value.NumberValue;

/**
 * Testing the Formula class.
 *
 */
public class FormulaTest {

  Formula formula;

  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void testFormula() {
    assertEquals(null, formula);
    formula = new Formula(new NumberValue(0), CalcOperator.MULTIPLY, new NumberValue(0));
    assertNotEquals(null, formula);
    assertEquals(new NumberValue(0), formula.operand1);
    assertEquals(new NumberValue(0), formula.operand2);
    assertEquals(CalcOperator.MULTIPLY, formula.operator);
  }

  @Test
  public void testToString() {
    formula = new Formula(new NumberValue(10), CalcOperator.MULTIPLY, new NumberValue(10));
    assertEquals("Formula [operand1=10, operand2=10, operator=MULTIPLY]", formula.toString());
  }

  @Test
  public void testHashCode() {
    final int prime = 31;
    int result = 1;

    //
    formula = new Formula(new NumberValue(10), CalcOperator.MULTIPLY, new NumberValue(10));
    result = 1;
    result = prime * result + ((formula.operand1 == null) ? 0 : formula.operand1.hashCode());
    result = prime * result + ((formula.operand2 == null) ? 0 : formula.operand2.hashCode());
    result = prime * result + ((formula.operator == null) ? 0 : formula.operator.hashCode());
    assertEquals(result, formula.hashCode());

    //
    formula = new Formula(new NumberValue(10), CalcOperator.MULTIPLY, new NumberValue(10));
    formula.operand1 = null;
    result = 1;
    result = prime * result + ((formula.operand1 == null) ? 0 : formula.operand1.hashCode());
    result = prime * result + ((formula.operand2 == null) ? 0 : formula.operand2.hashCode());
    result = prime * result + ((formula.operator == null) ? 0 : formula.operator.hashCode());
    assertEquals(result, formula.hashCode());

    formula = new Formula(new NumberValue(10), CalcOperator.MULTIPLY, new NumberValue(10));
    formula.operand1 = null;
    formula.operand2 = null;
    result = 1;
    result = prime * result + ((formula.operand1 == null) ? 0 : formula.operand1.hashCode());
    result = prime * result + ((formula.operand2 == null) ? 0 : formula.operand2.hashCode());
    result = prime * result + ((formula.operator == null) ? 0 : formula.operator.hashCode());
    assertEquals(result, formula.hashCode());

    formula = new Formula(new NumberValue(10), CalcOperator.MULTIPLY, new NumberValue(10));
    formula.operand1 = null;
    formula.operator = null;
    result = 1;
    result = prime * result + ((formula.operand1 == null) ? 0 : formula.operand1.hashCode());
    result = prime * result + ((formula.operand2 == null) ? 0 : formula.operand2.hashCode());
    result = prime * result + ((formula.operator == null) ? 0 : formula.operator.hashCode());
    assertEquals(result, formula.hashCode());

    formula = new Formula(new NumberValue(10), CalcOperator.MULTIPLY, new NumberValue(10));
    formula.operand1 = null;
    formula.operand2 = null;
    formula.operator = null;
    result = 1;
    result = prime * result + ((formula.operand1 == null) ? 0 : formula.operand1.hashCode());
    result = prime * result + ((formula.operand2 == null) ? 0 : formula.operand2.hashCode());
    result = prime * result + ((formula.operator == null) ? 0 : formula.operator.hashCode());
    assertEquals(result, formula.hashCode());
  }

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
    formula.operand1 = null;
    anotherformula.operand1 = null;
    assertEquals(true, formula.equals(anotherformula));

    formula = new Formula(new NumberValue(10), CalcOperator.MULTIPLY, new NumberValue(10));
    anotherformula = new Formula(new NumberValue(10), CalcOperator.MULTIPLY, new NumberValue(10));
    formula.operand1 = null;
    assertEquals(false, formula.equals(anotherformula));

    formula = new Formula(new NumberValue(10), CalcOperator.MULTIPLY, new NumberValue(10));
    anotherformula = new Formula(new NumberValue(10), CalcOperator.MULTIPLY, new NumberValue(10));
    assertEquals(true, formula.equals(anotherformula));
    formula.operand1 = new NumberValue(20);
    assertEquals(false, formula.equals(anotherformula));

    formula = new Formula(new NumberValue(10), CalcOperator.MULTIPLY, new NumberValue(10));
    anotherformula = new Formula(new NumberValue(10), CalcOperator.MULTIPLY, new NumberValue(10));
    formula.operand2 = null;
    anotherformula.operand2 = null;
    assertEquals(true, formula.equals(anotherformula));

    formula = new Formula(new NumberValue(10), CalcOperator.MULTIPLY, new NumberValue(10));
    anotherformula = new Formula(new NumberValue(10), CalcOperator.MULTIPLY, new NumberValue(10));
    formula.operand2 = null;
    assertEquals(false, formula.equals(anotherformula));

    formula = new Formula(new NumberValue(10), CalcOperator.MULTIPLY, new NumberValue(10));
    anotherformula = new Formula(new NumberValue(10), CalcOperator.MULTIPLY, new NumberValue(10));
    assertEquals(true, formula.equals(anotherformula));

    formula = new Formula(new NumberValue(10), CalcOperator.MULTIPLY, new NumberValue(10));
    anotherformula = new Formula(new NumberValue(10), CalcOperator.MULTIPLY, new NumberValue(10));
    formula.operand2 = new NumberValue(20);
    assertEquals(false, formula.equals(anotherformula));

    formula = new Formula(new NumberValue(10), CalcOperator.MULTIPLY, new NumberValue(10));
    anotherformula = new Formula(new NumberValue(10), CalcOperator.MULTIPLY, new NumberValue(10));
    assertEquals(true, formula.equals(anotherformula));

    formula = new Formula(new NumberValue(10), CalcOperator.MULTIPLY, new NumberValue(10));
    anotherformula = new Formula(new NumberValue(10), CalcOperator.MULTIPLY, new NumberValue(10));
    formula.operator = CalcOperator.DIVIDE;
    assertEquals(false, formula.equals(anotherformula));
  }

}
