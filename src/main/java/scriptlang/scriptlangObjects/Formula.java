package scriptlang.scriptlangObjects;

public class Formula {
  
  Object operand1;
  Object operand2;
  CalcOperator operator;
  
  public Formula(Object o1, CalcOperator calcOp, Object o2) {
    this.operand1 = o1;
    this.operand2 = o2;
    this.operator = calcOp;
  }
}
