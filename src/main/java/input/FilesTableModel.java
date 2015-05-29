package input;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 * A TableModel used by FilesTable to store the table data.
 */
public class FilesTableModel implements TableModel {
  private final String[] columnNames = { "Data filepath", "Settings filepath" };

  @Override
  public Class<?> getColumnClass(int columnIndex) {
    return String.class;
  }

  @Override
  public int getColumnCount() {
    return columnNames.length;
  }

  @Override
  public String getColumnName(int columnIndex) {
    return columnNames[columnIndex];
  }

  @Override
  public int getRowCount() {
    return Input.getFiles().size();
  }

  @Override
  public String getValueAt(int rowIndex, int columnIndex) {
    DataFile file = Input.getFiles().get(rowIndex);
    if (columnIndex == 0) {
      return file.getFilepath();
    } else {
      return file.getSettingsfilepath();
    }
  }

  @Override
  public boolean isCellEditable(int rowIndex, int columnIndex) {
    return false;
  }

  @Override
  public void setValueAt(Object theValue, int rowIndex, int columnIndex) {
    // TODO Auto-generated method stub

  }

  @Override
  public void addTableModelListener(TableModelListener arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void removeTableModelListener(TableModelListener arg0) {
    // TODO Auto-generated method stub

  }
}
