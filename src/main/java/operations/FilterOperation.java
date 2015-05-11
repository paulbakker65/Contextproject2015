package operations;

import parsers.DateValue;
import parsers.NumberValue;
import parsers.Value;
import table.Record;
import table.Table;

public class FilterOperation extends Operation {

	/**
	 * 
	 * the enum for constraint types
	 * 
	 */
	public enum ConstraintComparatorEnum {
		/**
		 * equal
		 */
		EQ,
		/**
		 * greater than
		 */
		G,
		/**
		 * greater than or equal
		 */
		GEQ,
		/**
		 * less than
		 */
		L,
		/**
		 * less than or equal
		 */
		LEQ,
		/**
		 * not defined
		 */
		ND
	}

	/**
	 * the column name relevant for this operation
	 */
	String columnName;
	/**
	 * enum for the constraint type
	 */
	ConstraintComparatorEnum constraintType;
	/**
	 * value to meet constraint
	 */
	Value constraintValue;

	public FilterOperation(Table dataset) {
		super(dataset);
	}

	/**
	 * set operation parameters for the constraint operation
	 * 
	 * @param columnName
	 *            the column name to which this constraint applies
	 * @param constraintType
	 *            the type of constraint as defined in
	 *            {@link ConstraintComparatorEnum}
	 * @param constraintValue
	 *            the value which the constraint must meet
	 * @return <b>true iff this.operationParametersSet is <b>true</b><br>
	 *         <b>false</b> iff this.operationParametersSet is <b>false</br>
	 */
	public boolean setOperationParameters(String columnName,
			ConstraintComparatorEnum constraintType, Value constraintValue) {
		this.columnName = columnName;
		this.constraintType = constraintType;
		this.constraintValue = constraintValue;

		this.operationParametersSet = true;
		return true;
	}

	/**
	 * this method is called by
	 * {@link #constraintFunction(Value, ConstraintComparatorEnum, Value)
	 * constraintFunction(...)} if the Value is of type NumberValue. see
	 * {@link #constraintFunction(Value, ConstraintComparatorEnum, Value)
	 * constraintFunction(...)} for more information
	 * 
	 * @param recordValue
	 *            record value
	 * @param constraintComparatorEnum
	 *            used constraint type (ENUM)
	 * @param constraintValue
	 *            constraint value to meet
	 * @return <b>false</b> iff the record value does not meet the constraint, <br>
	 *         <b>true</b> if the record value meets the constraint, <br>
	 *         <b>true</b> if the constraint type is undefined
	 */
	private boolean constraintFunction(NumberValue recordValue,
			ConstraintComparatorEnum constraintComparatorEnum,
			NumberValue constraintValue) {
		// compare the values
		int compareResult = recordValue.compareTo(constraintValue);
		switch (constraintComparatorEnum) {
		case EQ:
			return (compareResult == 0);
		case G:
			return (compareResult > 0);
		case GEQ:
			return (compareResult >= 0);
		case L:
			return (compareResult < 0);
		case LEQ:
			return (compareResult <= 0);
		case ND:
			return false;
		default:
			return false;
		}
	}

	/**
	 * this method is called by
	 * {@link #constraintFunction(Value, ConstraintComparatorEnum, Value)
	 * constraintFunction(...)} if the Value is of type DateValue. see
	 * {@link #constraintFunction(Value, ConstraintComparatorEnum, Value)
	 * constraintFunction(...)} for more information
	 * 
	 * @param recordValue
	 *            record value
	 * @param constraintComparatorEnum
	 *            used constraint type (ENUM)
	 * @param constraintValue
	 *            constraint value to meet
	 * @return <b>false</b> iff the record value does not meet the constraint, <br>
	 *         <b>true</b> if the record value meets the constraint, <br>
	 *         <b>true</b> if the constraint type is undefined
	 */
	private boolean constraintFunction(DateValue recordValue,
			ConstraintComparatorEnum constraintComparatorEnum,
			DateValue constraintValue) {
		// compare the values
		int compareResult = recordValue.compareTo(constraintValue);
		switch (constraintComparatorEnum) {
		case EQ:
			return (compareResult == 0);
		case G:
			return (compareResult > 0);
		case GEQ:
			return (compareResult >= 0);
		case L:
			return (compareResult < 0);
		case LEQ:
			return (compareResult <= 0);
		case ND:
			return false;
		default:
			return false;
		}
	}

	/**
	 * calculates whether a record value meets a constraint or not, this
	 * function calls either
	 * {@link #constraintFunction(NumberValue, ConstraintComparatorEnum, NumberValue)}
	 * or
	 * {@link #constraintFunction(DateValue, ConstraintComparatorEnum, DateValue)}
	 * depending on the actual type of Value
	 * 
	 * @param recordValue
	 *            record value, type generic Value
	 * @param constraintComparatorEnum
	 *            used constraint type (ENUM)
	 * @param constraintValue
	 *            constraint value to meet, type generic Value
	 * @return <b>false</b> iff the record value does not meet the constraint, <br>
	 *         <b>true</b> if the record value meets the constraint, <br>
	 *         <b>true</b> if the constraint type is undefined
	 */
	private boolean constraintFunction(Value recordValue,
			ConstraintComparatorEnum constraintComparatorEnum,
			Value constraintValue) {
		// if recordValue is a NumberValue, call constraintFunction(NumberValue,NumberValue)
		if (recordValue.isNumeric()) {
			return this.constraintFunction((NumberValue)recordValue, constraintComparatorEnum, (NumberValue)constraintValue);
		}
		// if recordValue is a DateValue, call constraintFunction(DateValue,DateValue)
		if(recordValue.isDate()) {
			return this.constraintFunction((DateValue)recordValue, constraintComparatorEnum, (DateValue)constraintValue);
		}
		// else go with generic Value object
		// generic Value object only supports constraint type equal
		switch (constraintComparatorEnum) {
		case EQ:
			return recordValue.equals(constraintValue);
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
		for (Record record : this.inputData) {
			if (record.containsKey(this.columnName)) {
				if (this.constraintFunction(record.get(this.columnName), this.constraintType, this.constraintValue)) {
					this.resultData.add(record);
				}
			}
		}
		return true;
	}

	/**
	 * @return the resulting dataset after applying the operation on the input
	 *         dataset
	 */
	@Override
	public Table getResult() {
		return this.resultData;
	}

}
