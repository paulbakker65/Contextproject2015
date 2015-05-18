package parsers;

import table.Record;
import table.Table;

/**
 * A chunk contains a table, index and a label.
 */
public class ChunkValue extends Value {

  private int index;
  private String label;
  private Table table;

  public ChunkValue(int i, String l, Table t) {
    index = i;
    label = l;
    table = t;
      
  }

  public void addRecord(Record r) {
    table.add(r);
  }

  public int getIndex() {
    return index;
  }

  public String getLabel() {
    return label;
  }

  public Table getTable() {
    return table;
  }

  @Override
  public String toString() {
    return table.toString();
  }

  @Override
  public boolean isNumeric() {
    return false;
  }

  @Override
  public boolean isDate() {
    return false;
  }

  @Override
  public boolean isString() {
    return false;
  }

  @Override
  public boolean isNull() {
    return false;
  }

  @Override
  public boolean isTime() {
    return false;
  }

  /**
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + index;
    result = prime * result + ((label == null) ? 0 : label.hashCode());
    result = prime * result + ((table == null) ? 0 : table.hashCode());
    return result;
  }

  /**
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    ChunkValue other = (ChunkValue) obj;
    if (table == null) {
      if (other.table != null) {
        return false;
      }
    } else if (!table.equals(other.table)) {
      return false;
    }
    return true;
  }

}
