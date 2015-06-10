package net.tudelft.hi.e.input;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 * A TableModel used by FilesTable to store the table data.
 */
public class FilesTableModel implements TableModel {
  private static final String[] columnNames = {"Data filepath",
    "Settings filepath"};

  @Override
  public void addTableModelListener(final TableModelListener arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public Class<?> getColumnClass(final int columnIndex) {
    return String.class;
  }

  @Override
  public int getColumnCount() {
    return columnNames.length;
  }

  @Override
  public String getColumnName(final int columnIndex) {
    return columnNames[columnIndex];
  }

  @Override
  public int getRowCount() {
    return Input.getFiles().size();
  }

  @Override
  public String getValueAt(final int rowIndex, final int columnIndex) {
    final DataFile file = Input.getFiles().get(rowIndex);
    if (columnIndex == 0) {
      return file.getFilepath();
    } else {
      return file.getSettingsfilepath();
    }
  }

  @Override
  public boolean isCellEditable(final int rowIndex, final int columnIndex) {
    return false;
  }

  @Override
  public void removeTableModelListener(final TableModelListener arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void setValueAt(final Object theValue, final int rowIndex, final int columnIndex) {
    // TODO Auto-generated method stub

  }
}
