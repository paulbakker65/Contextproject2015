package table;

import input.Column;

import java.util.HashMap;
import java.util.List;

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

  public Record(List<Column> list, Value[] val) {
    for (int i = 0; i < list.size(); i++) {
      this.put(list.get(i).getName(), val[i]);
    }
  }

  public String toString() {
    StringBuilder b = new StringBuilder();
    for (Value val : values()) {
      b.append(val + "\t");
    }
    return b.toString();
  }
  
<<<<<<< Updated upstream
  public void rename(String oldname, String newname){
    Value value = get(oldname);
    remove(oldname);
    put(newname, value);
=======
  @Override
  public Value get(Object key) {
    Value res = super.get(key);
    
    if (res == null) {
      return new NullValue();
    }
    
    return res;
>>>>>>> Stashed changes
  }

}
