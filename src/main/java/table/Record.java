package table;

import input.Column;

import java.util.ArrayList;
import java.util.HashMap;

import parsers.Value;

public class Record extends HashMap<String, Value> {

  private static final long serialVersionUID = 1L;

  public Record() {
    super();
  }

  public Record(ArrayList<Column> col, Value[] val) {
    for (int i = 0; i < col.size(); i++) {
      if (!val[i].equals("NULL")) {
        this.put(col.get(i).getName(), val[i]);
      }
    }
  }

  public String toString() {
    String res = "";

    for (Value val : values()) {
      res += val + "\t";
    }

    return res;
  }

}
