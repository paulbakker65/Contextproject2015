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

  public StateTransitionMatrix(Table input, String column) {
    this.table = input;
    create(column);
  }

  /**
   * Create the transitions matrix.
   */
  public void create(String column) {
    ArrayList<String> keyset = new ArrayList<String>();
    for (Record record : table) {
      Value value = record.get(column);
      if (!keyset.contains(value.toString()) && !value.isNull()) {
        keyset.add(value.toString());
      }
    }
    ArrayList<Column> col = new ArrayList<Column>();

    col.add(new StringColumn("id"));
    for (String id : keyset) {
      col.add(new NumberColumn(id));
    }
    for (String id : keyset) {
      Value[] values = new Value[keyset.size() + 1];
      for (int i = 1; i <= keyset.size(); i++) {
        values[i] = new NumberValue(0);
      }
      values[0] = new StringValue(id);
      this.add(new Record(col, values));
    }

    String codename = "";

    for (Record record : table) {
      Value code = record.get(column);
      if (!code.isNull()) {
        if (codename.equals("")) {
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
