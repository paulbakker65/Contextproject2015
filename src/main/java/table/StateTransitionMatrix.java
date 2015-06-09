package table;

import table.value.Column;
import table.value.NumberColumn;
import table.value.NumberValue;
import table.value.StringColumn;
import table.value.StringValue;
import table.value.Value;

import java.util.ArrayList;
import java.util.Collections;

public class StateTransitionMatrix extends Table {

  /**
   * Default serial version ID.
   */
  private static final long serialVersionUID = 1L;
  private Table table;
  private Table codeTable;
  private ArrayList<String> uniqueValues;

  /**
   * Creates a state transition matrix of the given table looking at the datecolumn specified.
   * 
   * @param input
   *          table on which to check
   * @param dateColumn
   *          column where the date values are.
   */
  public StateTransitionMatrix(Table input, String dateColumn) {
    this.table = input;
    this.setName("transition matrix for " + table.getName());
    determineUniqueCodes();
    createTable();
    createCodeTable(dateColumn);
    countTransitions();
  }

  /**
   * Look at the codes and add them to an arraylist.
   */
  public void determineUniqueCodes() {
    uniqueValues = new ArrayList<String>(table.getCodes().keySet());
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
   * Creates a separate code Table.
   * 
   * @param dateColumn
   *          date.
   */
  public void createCodeTable(String dateColumn) {
    codeTable = new Table();
    for (Code code : table.getCodes().values()) {
      for (Table event : code.getEvents()) {
        ArrayList<Column> column = new ArrayList<Column>();
        column.add(new StringColumn("Code"));
        column.add(new StringColumn("Date"));
        Record record =
            new Record(column, new Value[] { new StringValue(code.getName()),
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
