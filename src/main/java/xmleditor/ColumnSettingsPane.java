package xmleditor;

import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * A wrapper for a table. Table should be in a JScrollPane, so that columns are visible (this is
 * swing's implementation)
 * 
 *
 */
public class ColumnSettingsPane extends JScrollPane {

  private static final long serialVersionUID = 1L;

  private JTable theTable;

  public ColumnSettingsPane(JTable empty) {
    super(empty);
    theTable = empty;
  }

  public void setModel(ColumnSettingsTableModel cstm) {
    theTable.setModel(cstm);
  }

}
