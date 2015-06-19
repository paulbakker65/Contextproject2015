package net.tudelft.hi.e.computation;

import net.tudelft.hi.e.common.enums.CompareOperator;
import net.tudelft.hi.e.data.Value;

/**
 * Representing an Condition.
 *
 */
public class Condition {

  private CompareOperator conditionOperator;
  private Value conditionValue;

  public Condition(final CompareOperator operator, final Value value) {
    this.conditionOperator = operator;
    this.conditionValue = value;
  }

  /**
   * Calculates whether a record value meets a constraint or not, this function uses the compareTo
   * function of Value which in turn chooses which compareTo it uses based on the actual subclass.
   *
   * @param recordValue record value, type generic Value
   * @return <b>false</b> if and only if the record value does not meet the constraint, <br>
   *         <b>true</b> if the record value meets the constraint, <br>
   *         <b>true</b> if the constraint type is undefined
   */
  public boolean matches(Value recordValue) {
    if (recordValue == null) {
      return false;
    }

    int compareResult = recordValue.compareTo(getConditionValue());
    return conditionOperator.matchesCompareResult(compareResult);
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
    return deepEquals(other);
  }

  private boolean deepEquals(Condition other) {
    return equalConditionValue(other) && equalConditionOperator(other);
  }

  private boolean equalConditionValue(Condition other) {
    if (conditionValue == null) {
      if (other.getConditionValue() != null) {
        return false;
      }
    } else if (!conditionValue.equals(other.getConditionValue())) {
      return false;
    }
    return true;
  }

  private boolean equalConditionOperator(Condition other) {
    if (conditionOperator == null) {
      if (other.getConditionOperator() != null) {
        return false;
      }
    } else if (!conditionOperator.equals(other.getConditionOperator())) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result =
        prime * result + ((getConditionOperator() == null) ? 0 : getConditionOperator().hashCode());
    result = prime * result + ((getConditionValue() == null) ? 0 : getConditionValue().hashCode());
    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public final String toString() {
    return getConditionOperator().toString() + " " + getConditionValue().toString();
  }
}
