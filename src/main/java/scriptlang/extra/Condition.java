package scriptlang.extra;

import enums.CompareOperator;

import table.value.Value;

/**
 * Representing an Condition.
 *
 */
public class Condition {

  public CompareOperator conditionOperator;
  public Value conditionValue;

  public Condition(final CompareOperator operator, final Value value) {
    this.conditionOperator = operator;
    this.conditionValue = value;
  }
  
  /**
   * Calculates whether a record value meets a constraint or not, this function uses the compareTo
   * function of Value which in turn chooses which compareTo it uses based on the actual subclass.
   * 
   * @param recordValue 
   *        record value, type generic Value
   * @return <b>false</b> iff the record value does not meet the constraint, <br>
   *         <b>true</b> if the record value meets the constraint, <br>
   *         <b>true</b> if the constraint type is undefined
   */
  public boolean matches(Value recordValue) {
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
    final Condition other = (Condition) obj;
    if (conditionOperator != other.conditionOperator) {
      return false;
    }
    if (conditionValue == null) {
      if (other.conditionValue != null) {
        return false;
      }
    } else if (!conditionValue.equals(other.conditionValue)) {
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
    result = prime * result + ((conditionOperator == null) ? 0 : conditionOperator.hashCode());
    result = prime * result + ((conditionValue == null) ? 0 : conditionValue.hashCode());
    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "Condition [condOperator=" + conditionOperator + ", condValue=" + conditionValue + "]";
  }
}
