package net.tudelft.hi.e.gui;

import net.tudelft.hi.e.input.DataFile;
import net.tudelft.hi.e.input.Input;

import javax.swing.table.AbstractTableModel;

/**
 * A TableModel used by FilesTable to store the table data.
 */
class FilesTableModel extends AbstractTableModel {
  private static final long serialVersionUID = 1L;
  private static final String[] columnNames = {"Data filepath",
    "Settings filepath"};

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
      return file.getRawDataFile().getName();
    } else {
      return file.getSettingsfile().getName();
    }
  }

  @Override
  public boolean isCellEditable(final int rowIndex, final int columnIndex) {
    return false;
  }
}
