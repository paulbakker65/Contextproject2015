package table;

import table.value.Column;
import table.value.NumberColumn;
import table.value.NumberValue;
import table.value.StringColumn;
import table.value.StringValue;
import table.value.Value;

import java.util.ArrayList;
import java.util.Set;

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
    Set<String> keyset = table.getCodes().keySet();
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
        if (!code.toString().equals(codename) && codename.equals("")) {
          codename = code.toString();
        } else if (!code.toString().equals(codename)) {
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
