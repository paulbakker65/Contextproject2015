package main;

import table.Record;
import table.Table;

import java.util.ArrayList;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 * TableModel to display a Table in a JTable.
 */
public class DisplayTableModel implements TableModel{
  private Table table;
  private ArrayList<String> columns;

  /**
   * Used as a TableModel for the JTable in DisplayTablGui.
   * @param table The table object to display.
   */
  public DisplayTableModel(Table table) {
    this.table = table;
    if (!table.isEmpty()) {
      columns = new ArrayList<String>(table.get(0).keySet());
    } else {
      columns = new ArrayList<String>();
    }
  }
  
  @Override
  public int getRowCount() {
    return table.size();
  }

  @Override
  public int getColumnCount() {
    return columns.size();
  }

  @Override
  public String getColumnName(int columnIndex) {
    return columns.get(columnIndex);
  }

  @Override
  public Class<?> getColumnClass(int columnIndex) {
    return String.class;
  }

  @Override
  public boolean isCellEditable(int rowIndex, int columnIndex) {
    return false;
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    Record record = table.get(rowIndex);
    return record.get(getColumnName(columnIndex));
  }

  @Override
  public void setValueAt(Object value, int rowIndex, int columnIndex) {

  }

  @Override
  public void addTableModelListener(TableModelListener listener) {

  }

  @Override
  public void removeTableModelListener(TableModelListener listener) {

  }
}