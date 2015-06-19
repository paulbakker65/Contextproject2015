package net.tudelft.hi.e.gui;

import net.tudelft.hi.e.data.Record;
import net.tudelft.hi.e.data.Table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/**
 * TableModel to display a Table in a JTable.
 */
class DisplayTableModel extends AbstractTableModel {
  private static final long serialVersionUID = 1L;
  private final Table table;
  private final List<String> columns;

  /**
   * Used as a TableModel for the JTable in DisplayTablGui.
   * @param table The table object to display.
   */
  public DisplayTableModel(Table table) {
    this.table = table;
    if (!table.isEmpty()) {
      columns = table.get(table.size() - 1).getKeysInOrder();
    } else {
      columns = new ArrayList<>();
    }
    columns.add(0, "");
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
    if (columnIndex == 0) {
      return rowIndex + 1;
    }
    Record record = table.get(rowIndex);
    return record.get(getColumnName(columnIndex));
  }
}
