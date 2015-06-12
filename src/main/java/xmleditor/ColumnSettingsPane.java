package xmleditor;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
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

  private ColumnSettingsPane(JTable empty) {
    super(empty);
    theTable = empty;
  }

  public static ColumnSettingsPane createPanel() {
    return new ColumnSettingsPane(new JTable());
  }

  /**
   * Changes the model of the table in the panel. Also sets the cellEditor of the second column to
   * combobox.
   * 
   * @param cstm
   *          the new model
   */
  public void setModel(ColumnSettingsTableModel cstm) {
    theTable.setModel(cstm);

    JComboBox<String> comboBox = new JComboBox<String>();
    comboBox.addItem("text");
    comboBox.addItem("number");
    comboBox.addItem("date");

    theTable.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(comboBox));
  }

}
