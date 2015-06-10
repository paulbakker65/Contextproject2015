package operations;

import enums.CompareOperator;

import operations.patterns.condition.RecordMatchesConditionCondition;

import scriptlang.extra.Condition;

import table.Record;
import table.Table;
import table.value.Value;

/**
 * Filters certain records out of the table.
 */
public class ConstraintOperation extends Operation {
  /**
   * The condition which compares a column value to another value.
   */
  private RecordMatchesConditionCondition recordCondition;

  /**
   * Creates an operation for filtering data.
   * @param dataset
   *        the table to filter.
   * @param columnName
   *        the column name to which this constraint applies.
   * @param constraintType
   *        the type of constraint as defined in {@link CompareOperator}.
   * @param constraintValue
   *        constraintValue the value which the constraint must meet.
   */
  public ConstraintOperation(final Table dataset, final String columnName,
      final CompareOperator constraintType, final Value constraintValue) {
    super(dataset);
    setOperationParameters(columnName, constraintType, constraintValue);
  }
  
  @Override
  public void resetData(Table inputData) {
    this.inputData = inputData;
    this.resultData = new Table(inputData);
    this.resultData.clear();
  }
  
  /**
   * Set operation parameters for the constraint operation.
   * 
   * @param columnName 
   *        the column name to which this constraint applies
   * @param constraintType 
   *        the type of constraint as defined in {@link ConstraintComparatorEnum}
   * @param constraintValue 
   *        the value which the constraint must meet
   * @return <b>true</b> iff this.operationParametersSet is <b>true</b><br>
   *         <b>false</b> iff this.operationParametersSet is <b>false</b><br>
   */
  public boolean setOperationParameters(final String columnName,
      final CompareOperator constraintType, final Value constraintValue) {
    if (columnName == null || constraintValue == null
        || constraintType == null || constraintType == CompareOperator.ND) {
      this.operationParametersSet = false;
    } else {
      Condition condition = new Condition(constraintType, constraintValue);
      this.recordCondition = new RecordMatchesConditionCondition(columnName, condition);
      this.operationParametersSet = true;
    }
    
    return this.operationParametersSet;
  }
  
  /**
   * Execute the operation. before the operation can be executed the setConstraint() function must
   * be called. this function fails if this.comparator is not set or set to ND
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
    for (final Record record : this.inputData) {
      if (this.recordCondition.matches(record)) {
        this.resultData.add(record);
      }
    }
    return true;
  }
  
  /**
   * Getter for the result data.
   * 
   * @return the resulting dataset after applying the operation on the input dataset
   */
  @Override
  public Table getResult() {
    return this.resultData;
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
    final ConstraintOperation other = (ConstraintOperation) obj;    
    
    if (recordCondition == null) {
      if (other.recordCondition != null) {
        return false;
      }
    } else if (!recordCondition.equals(other.recordCondition)) {
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
    result = prime * result + ((recordCondition == null) ? 0 : recordCondition.hashCode());
    return result;
  }  

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "ConstraintOperation [condition: " + recordCondition.toString() + "]";
  }

}
