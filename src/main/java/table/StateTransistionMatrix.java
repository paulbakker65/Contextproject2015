package table;

import table.value.Column;
import table.value.NumberColumn;
import table.value.NumberValue;
import table.value.StringColumn;
import table.value.StringValue;
import table.value.Value;

import java.util.ArrayList;
import java.util.Set;

public class StateTransistionMatrix extends Table {

  /**
   * Default serial version ID.
   */
  private static final long serialVersionUID = 1L;
  Table table;

  public StateTransistionMatrix(Table input) {
    this.table = input;
    create();
  }

  public void create() {
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
      Value code = record.get("Code");
      if (!code.isNull()) {
        if (!code.toString().equals(codename) && codename.equals("")) {
          codename = record.get("Code").toString();
        } else if (!code.isNull()) {
          for (Record rec : this) {
            if (rec.get("id").equals(codename)) {
              NumberValue num = (NumberValue) rec.get(code.toString());
              num.plusNumber(1);
            }
          }
          codename = code.toString();
        }
      }
    }

  }

}
