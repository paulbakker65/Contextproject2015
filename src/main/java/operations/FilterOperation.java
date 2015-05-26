package operations;

import table.Record;
import table.Table;
import table.value.Value;

/**
 * Filters certain records out of the table.
 */
public class FilterOperation extends Operation {

  /**
   * 
   * the enum for constraint types.
   * 
   */
  public enum ConstraintComparatorEnum {
    /**
     * equal.
     */
    EQ,
    /**
     * greater than.
     */
    G,
    /**
     * greater than or equal.
     */
    GEQ,
    /**
     * less than.
     */
    L,
    /**
     * less than or equal.
     */
    LEQ,
    /**
     * not defined.
     */
    ND
  }

  /**
   * the column name relevant for this operation.
   */
  String columnName;
  /**
   * enum for the constraint type.
   */
  ConstraintComparatorEnum constraintType;
  /**
   * value to meet constraint.
   */
  Value constraintValue;
  
  public FilterOperation(Table dataset, String columnName, ConstraintComparatorEnum constraintType,
      Value constraintValue) {
    super(dataset);
    setOperationParameters(columnName, constraintType, constraintValue);
  }

  /**
   * set operation parameters for the constraint operation.
   * 
   * @param columnName
   *          the column name to which this constraint applies
   * @param constraintType
   *          the type of constraint as defined in {@link ConstraintComparatorEnum}
   * @param constraintValue
   *          the value which the constraint must meet
   * @return <b>true</b> iff this.operationParametersSet is <b>true</b><br>
   *         <b>false</b> iff this.operationParametersSet is <b>false</b></br>
   */
  public boolean setOperationParameters(String columnName, ConstraintComparatorEnum constraintType,
      Value constraintValue) {
    this.columnName = columnName;
    this.constraintType = constraintType;
    this.constraintValue = constraintValue;

    this.operationParametersSet = true;
    return true;
  }

  /**
   * calculates whether a record value meets a constraint or not, this function uses the compareTo
   * function of Value which in turn chooses which compareTo it uses based on the actual subclass.
   * 
   * @param recordValue
   *          record value, type generic Value
   * @param constraintComparatorEnum
   *          used constraint type (ENUM)
   * @param constraintValue
   *          constraint value to meet, type generic Value
   * @return <b>false</b> iff the record value does not meet the constraint, <br>
   *         <b>true</b> if the record value meets the constraint, <br>
   *         <b>true</b> if the constraint type is undefined
   */
  private boolean constraintFunction(Value recordValue,
      ConstraintComparatorEnum constraintComparatorEnum, Value constraintValue) {

    int compareResult = recordValue.compareTo(constraintValue);
    switch (constraintComparatorEnum) {
    case EQ:
      return compareResult == 0;
    case G:
      return compareResult > 0;
    case GEQ:
      return compareResult >= 0;
    case L:
      return compareResult < 0;
    case LEQ:
      return compareResult <= 0;
    case ND:
      return false;
    default:
      return false;
    }
  }

	/**
	 * 
	 * execute the operation. before the operation can be executed the
	 * setConstraint() function must be called. this function fails if
	 * this.comparator is not set or set to ND
	 * 
	 * @return <b>true</b> iff the operation executed correctly<br>
	 *         <b>false</b> iff the operation did not execute correctly<br>
	 * 
	 * @see {@link setConstraint()}
	 * 
	 */
	@Override
	public boolean execute() {
		if (!operationParametersSet) {
		  return false;
		} 
	  for (Record record : this.inputData) {
			if (record.containsKey(this.columnName)) {
        if (this.constraintFunction(record.get(this.columnName), this.constraintType,
            this.constraintValue)) {
					this.resultData.add(record);
				}
			}
		}
		return true;
	}

  /**
   * @return the resulting dataset after applying the operation on the input dataset
   */
  @Override
  public Table getResult() {
    return this.resultData;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((columnName == null) ? 0 : columnName.hashCode());
    result = prime * result + ((constraintType == null) ? 0 : constraintType.hashCode());
    result = prime * result + ((constraintValue == null) ? 0 : constraintValue.hashCode());
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
    FilterOperation other = (FilterOperation) obj;
    if (columnName == null) {
      if (other.columnName != null) {
        return false;
      }
    } else if (!columnName.equals(other.columnName)) {
      return false;
    }
    if (constraintType != other.constraintType) {
      return false;
    }
    if (constraintValue == null) {
      if (other.constraintValue != null) {
        return false;
      }
    } else if (!constraintValue.equals(other.constraintValue)) {
      return false;
    }
    return true;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "FilterOperation [columnName=" + columnName + ", constraintType=" + constraintType
        + ", constraintValue=" + constraintValue + "]";
  }
  

}
