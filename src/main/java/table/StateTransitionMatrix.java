package table;

import table.value.Column;
import table.value.NumberColumn;
import table.value.NumberValue;
import table.value.StringColumn;
import table.value.StringValue;
import table.value.Value;

import java.util.ArrayList;

public class StateTransitionMatrix extends Table {

  /**
   * Default serial version ID.
   */
  private static final long serialVersionUID = 1L;
  Table table;
  String column;
  ArrayList<String> uniqueValues;

  /**
   * Creates a state transition matrix of the given table looking at the column specified.
   * 
   * @param input
   *          table on which to check
   * @param column
   *          column where the unique values are.
   */
  public StateTransitionMatrix(Table input, String column) {
    this.table = input;
    this.column = column;
    determineUniqueValues();
    createTable();
    countTransitions();
  }

  /**
   * Look at the column for unique values and add them to an arraylist.
   */
  public void determineUniqueValues() {
    uniqueValues = new ArrayList<String>();
    for (Record record : table) {
      Value value = record.get(column);
      if (!uniqueValues.contains(value.toString()) && !value.isNull()) {
        uniqueValues.add(value.toString());
      }
    }
  }

  /**
   * Create the table with all the values set to zero.
   */
  public void createTable() {
    ArrayList<Column> col = new ArrayList<Column>();

    col.add(new StringColumn("id"));
    for (String id : uniqueValues) {
      col.add(new NumberColumn(id));
    }
    for (String id : uniqueValues) {
      Value[] values = new Value[uniqueValues.size() + 1];
      for (int i = 1; i <= uniqueValues.size(); i++) {
        values[i] = new NumberValue(0);
      }
      values[0] = new StringValue(id);
      this.add(new Record(col, values));
    }
  }

  /**
   * Look at every transition and add one to the right value.
   */
  public void countTransitions() {
    String codename = "";

    for (Record record : table) {
      Value code = record.get(column);
      if (!code.isNull()) {
        if (codename.isEmpty()) {
          codename = code.toString();
        } else {
          for (Record rec : this) {
            if (rec.get("id").toString().equals(codename)) {
              NumberValue num = (NumberValue) rec.get(code.toString());
              num.plusNumber(1);
              rec.put(code.toString(), num);
            }
          }
          codename = code.toString();
        }
      }
    }
  }
}
