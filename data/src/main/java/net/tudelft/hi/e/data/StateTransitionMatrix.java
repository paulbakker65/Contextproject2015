package net.tudelft.hi.e.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StateTransitionMatrix extends Table {

  /**
   * Default serial version ID.
   */
  private static final long serialVersionUID = 1L;
  Table table;
  String column;
  List<String> uniqueValues;

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
    this.setName("transition matrix for " + table.getName() + "." + column);
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
    List<Column> col = new ArrayList<Column>();

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
          codename = countTransitionsForRecord(codename, code);
        }
      }
    }
  }

  private String countTransitionsForRecord(final String codeName,
          final Value code) {
    for (Record rec : this) {
      if (rec.get("id").toString().equals(codeName)) {
        NumberValue num = (NumberValue) rec.get(code.toString());
        num.plusNumber(1);
        rec.put(code.toString(), num);
      }
    }
    return code.toString();
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 37 * hash + Objects.hashCode(this.table);
    hash = 37 * hash + Objects.hashCode(this.column);
    hash = 37 * hash + Objects.hashCode(this.uniqueValues);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final StateTransitionMatrix other = (StateTransitionMatrix) obj;
    return deepEquals(other);
  }

  private boolean deepEquals(StateTransitionMatrix other) {
    return equalTable(other) && equalColumn(other) && equalUniqueValues(other);
  }

  private boolean equalTable(StateTransitionMatrix other) {
    return Objects.equals(this.table, other.table);
  }

  private boolean equalColumn(StateTransitionMatrix other) {
    return Objects.equals(this.column, other.column);
  }

  private boolean equalUniqueValues(StateTransitionMatrix other) {
    return Objects.equals(this.uniqueValues, other.uniqueValues);
  }

}
