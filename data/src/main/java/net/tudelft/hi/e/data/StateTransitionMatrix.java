package net.tudelft.hi.e.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StateTransitionMatrix extends Table {

  /**
   * Default serial version ID.
   */
  private static final long serialVersionUID = 1L;
  private Table codeTable;

  /**
   * Creates a state transition matrix of the given table looking at the datecolumn specified.
   *
   * @param table
   *          table on which to check
   * @param dateColumn
   *          column where the date values are.
   */
  public StateTransitionMatrix(Table table, String dateColumn) {
    this.setName("transition matrix for " + table.getName());
    List<String> uniqueValues = determineUniqueCodes(table);
    createTable(uniqueValues);
    createCodeTable(table, dateColumn);
    countTransitions();
  }

  /**
   * Look at the codes and add them to an arraylist.
   */
  public List<String> determineUniqueCodes(Table table) {
    return new ArrayList<String>(table.getCodes().keySet());
  }

  /**
   * Create the table with all the values set to zero.
   */
  public void createTable(List<String> uniqueValues) {
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
   * Creates a separate code Table.
   *
   * @param dateColumn
   *          date.
   */
  public void createCodeTable(Table table, String dateColumn) {
    codeTable = new Table();
    for (Code code : table.getCodes().values()) {
      for (Table event : code.getEvents()) {
        List<Column> column = new ArrayList<Column>();
        column.add(new StringColumn("Code"));
        column.add(new StringColumn("Date"));
        Record record = new Record(column, new Value[] { new StringValue(code.getName()),
            event.get(0).get(dateColumn) });
        codeTable.add(record);
      }
    }
    Collections.sort(codeTable, new RecordComparator(dateColumn));
  }

  /**
   * Look at every transition and add one to the right value.
   */
  public void countTransitions() {
    String codename = "";

    for (Record record : codeTable) {
      Value code = record.get("Code");
      if (codename.isEmpty()) {
        codename = code.toString();
      } else {
        codename = countTransitionsForRecord(codename, code);
      }
    }

  }

  private String countTransitionsForRecord(final String codeName, final Value code) {
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
    // hash = 37 * hash + Objects.hashCode(this.column);
    hash = 37 * hash + this.getName().hashCode();
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
    return super.equals(other);
  }

}
