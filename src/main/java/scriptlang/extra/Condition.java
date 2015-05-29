package scriptlang.extra;

import enums.CompareOperator;
import table.value.Value;

/**
 * Representing an Condition.
 *
 */
public class Condition {
  
  public CompareOperator condOperator;
  public Value condValue;
  
  public Condition(CompareOperator compareo, Value v) {
    this.condOperator = compareo;
    this.condValue = v;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "Condition [condOperator=" + condOperator + ", condValue=" + condValue + "]";
  }

  /* (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((condOperator == null) ? 0 : condOperator.hashCode());
    result = prime * result + ((condValue == null) ? 0 : condValue.hashCode());
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
    Condition other = (Condition) obj;
    if (condOperator != other.condOperator) {
      return false;
    }
    if (condValue == null) {
      if (other.condValue != null) {
        return false;
      }
    } else if (!condValue.equals(other.condValue)) {
      return false;
    }
    return true;
  }
}
