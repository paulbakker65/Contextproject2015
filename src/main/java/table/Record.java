package table;

import input.Column;

import java.util.ArrayList;
import java.util.HashMap;

import parsers.NullValue;
import parsers.Value;

/**
 * A timed event that can contain various properties ("collumns").
 * Because it extends a HashMap new properties can be made on the fly.
 */
public class Record extends HashMap<String, Value> {

  private static final long serialVersionUID = 1L;

  public Record() {
    super();
  }

  public Record(ArrayList<Column> col, Value[] val) {
    for (int i = 0; i < col.size(); i++) {
      this.put(col.get(i).getName(), val[i]);
    }
  }

  public String toString() {
    StringBuilder b = new StringBuilder();
    for (Value val : values()) {
      b.append(val + "\t");
    }
    return b.toString();
  }
  
  @Override
  public Value get(Object key) {
    Value res = super.get(key);
    
    if (res == null) {
      return new NullValue();
    }
    
    return res;
  }

}
