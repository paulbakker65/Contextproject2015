package net.tudelft.hi.e.computation;

import java.util.Objects;
import net.tudelft.hi.e.common.enums.CompareOperator;
import net.tudelft.hi.e.data.Value;

/**
 * Representing an Condition.
 *
 */
public class Condition {

  CompareOperator conditionOperator;
  Value conditionValue;

  public Condition(final CompareOperator operator, final Value value) {
    this.conditionOperator = operator;
    this.conditionValue = value;
  }

  /**
   * Calculates whether a record value meets a constraint or not, this function
   * uses the compareTo function of Value which in turn chooses which compareTo
   * it uses based on the actual subclass.
   *
   * @param recordValue record value, type generic Value
   * @return <b>false</b> iff the record value does not meet the constraint,
   * <br>
   * <b>true</b> if the record value meets the constraint, <br>
   * <b>true</b> if the constraint type is undefined
   */
  public boolean matches(Value recordValue) {
    if (recordValue == null) {
      return false;
    }

    int compareResult = recordValue.compareTo(conditionValue);
    switch (conditionOperator) {
      case EQ:
        return compareResult == 0;
      case NEQ:
        return compareResult != 0;
      case G:
        return compareResult > 0;
      case GEQ:
        return compareResult >= 0;
      case L:
        return compareResult < 0;
      case LEQ:
        return compareResult <= 0;
      default:
        return false;
    }
  }

  public CompareOperator getConditionOperator() {
    return conditionOperator;
  }

  public void setConditionOperator(CompareOperator conditionOperator) {
    this.conditionOperator = conditionOperator;
  }

  public Value getConditionValue() {
    return conditionValue;
  }

  public void setConditionValue(Value conditionValue) {
    this.conditionValue = conditionValue;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Condition other = (Condition) obj;
    if (!Objects.equals(this.conditionOperator, other.conditionOperator)) {
      return false;
    }
    if (!Objects.equals(this.conditionValue, other.conditionValue)) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((conditionOperator == null) ? 0
        : conditionOperator.hashCode());
    result = prime * result + ((conditionValue == null) ? 0 : conditionValue.
        hashCode());
    return result;
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    switch (conditionOperator) {
      case EQ: return "== " + conditionValue.toString();
      case NEQ: return "!= " + conditionValue.toString();
      case LEQ: return "<= " + conditionValue.toString();
      case L: return "< " + conditionValue.toString();
      case GEQ: return ">= " + conditionValue.toString();
      case G: return "> " + conditionValue.toString();
      default: return "? " + conditionValue.toString();
    }
  }
}
