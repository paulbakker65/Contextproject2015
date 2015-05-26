package scriptlang.scriptlangObjects;

import parsers.Value;

public class Condition {
  
  public CompareOperator condOperator;
  public Value condValue;
  
  public Condition(CompareOperator compareo, Value v) {
    this.condOperator = compareo;
    this.condValue = v;
  }
}
