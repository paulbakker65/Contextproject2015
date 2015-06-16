package net.tudelft.hi.e.computation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.tudelft.hi.e.common.enums.ComputeOperator;
import net.tudelft.hi.e.data.Column;
import net.tudelft.hi.e.data.Record;
import net.tudelft.hi.e.data.StringColumn;
import net.tudelft.hi.e.data.StringValue;
import net.tudelft.hi.e.data.Table;
import net.tudelft.hi.e.data.Value;

/**
 * Operation for doing computations on a table column.
 */
public class ComputeOperation extends Operation {
  Computation computation;
  ComputeOperator operator;
  String column;

  /**
   * Creates an operation for doing a computation.
   *
   * @param inputDataset
   *          the table to do the computation.
   * @param operator
   *          the computation to do.
   * @param column
   *          the column for the computation.
   */
  public ComputeOperation(Table inputDataset, ComputeOperator operator, String column) {
    super(inputDataset);
    setOperationParameters(operator, column);
  }

  private boolean setOperationParameters(ComputeOperator operator, String column) {
    this.operationParametersSet =
        column != null && inputData != null && !inputData.isEmpty()
            && inputData.get(0).get(column) != null;

    if (this.operationParametersSet) {
      this.computation = ComputationFactory.createComputation(operator);
      this.operator = operator;
      this.column = column;
    }

    return this.operationParametersSet;
  }

  @Override
  public boolean execute() {
    if (!this.operationParametersSet) {
      return false;
    }

    Value result = computation.compute(getValues());

    Column computationColumn = new StringColumn("Computation");
    Column resultColumn = result.getType("Result");
    List<Column> columns = new ArrayList<Column>(Arrays.asList(computationColumn, resultColumn));
    Value[] recordValues = new Value[] { getDescription(), result };
    resultData.add(new Record(columns, recordValues, "Computation"));

    return true;
  }

  private List<Value> getValues() {
    List<Value> values = new ArrayList<Value>();

    for (Record record : inputData) {
      if (record.get(column) != null) {
        values.add(record.get(column));
      }
    }

    return values;
  }

  private StringValue getDescription() {
    return new StringValue(operator + "(" + column + ")");
  }

  @Override
  public Table getResult() {
    return resultData;
  }

  @Override
  public void resetData(Table inputData) {
    this.inputData = inputData;
    this.resultData = new Table();
    this.resultData.setName("Computation");
  }

}
