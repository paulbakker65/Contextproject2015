package scriptlang.extra;

/**
 * Formula object representing a formula.
 */
public class Formula {
  
  Object operand1;
  Object operand2;
  CalcOperator operator;
  
  public Formula(Object o1, CalcOperator calcOp, Object o2) {
    this.operand1 = o1;
    this.operand2 = o2;
    this.operator = calcOp;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "Formula [operand1=" + operand1 + ", operand2=" + operand2 + ", operator=" + operator
        + "]";
  }

  /* (non-Javadoc)
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

  /* (non-Javadoc)
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Formula other = (Formula) obj;
    if (operand1 == null) {
      if (other.operand1 != null) {
        return false;
      }
    } else if (!operand1.equals(other.operand1)) {
      return false;
    }
    if (operand2 == null) {
      if (other.operand2 != null) {
        return false;
      }
    } else if (!operand2.equals(other.operand2)) {
      return false;
    }
    if (operator != other.operator) {
      return false;
    }
    return true;
  }
  
}
