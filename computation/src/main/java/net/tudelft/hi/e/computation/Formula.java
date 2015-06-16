package net.tudelft.hi.e.computation;

import net.tudelft.hi.e.common.enums.CalcOperator;

/**
 * Formula object representing a formula.
 */
public class Formula {

  Object operand1;
  Object operand2;
  CalcOperator operator;

  /**
   * Formula constructor creates an Formula object containing two operands and a operator.
   *
   * @param operand1 The first operand
   * @param operator The operator
   * @param operand2 The second operand
   */
  public Formula(final Object operand1, final CalcOperator operator, final Object operand2) {
    this.operand1 = operand1;
    this.operand2 = operand2;
    this.operator = operator;
  }

  public Object getOperand1() {
    return operand1;
  }

  public void setOperand1(Object operand1) {
    this.operand1 = operand1;
  }

  public Object getOperand2() {
    return operand2;
  }

  public void setOperand2(Object operand2) {
    this.operand2 = operand2;
  }

  public CalcOperator getOperator() {
    return operator;
  }

  public void setOperator(CalcOperator operator) {
    this.operator = operator;
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Formula other = (Formula) obj;
    return this.deepEquals(other);
  }

  private boolean deepEquals(Formula other) {
    if (operator == other.operator) {
      return this.equalFirstOperand(other) && this.equalSecondOperand(other);
    } else {
      return false;
    }
  }
  private boolean equalFirstOperand(final Formula other) {
    if (operand1 == null) {
      if (other.operand1 != null) {
        return false;
      }
    } else if (!operand1.equals(other.operand1)) {
      return false;
    }
    return true;
  }

  private boolean equalSecondOperand(final Formula other) {
    if (operand2 == null) {
      if (other.operand2 != null) {
        return false;
      }
    } else if (!operand2.equals(other.operand2)) {
      return false;
    }
    return true;
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((operand1 == null) ? 0 : operand1.hashCode());
    result = prime * result + ((operand2 == null) ? 0 : operand2.hashCode());
    result = prime * result + ((operator == null) ? 0 : operator.hashCode());
    return result;
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "Formula [operand1=" + operand1 + ", operand2=" + operand2 + ", operator=" + operator
        + "]";
  }

}
