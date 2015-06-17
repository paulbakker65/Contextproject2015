package net.tudelft.hi.e.gui;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

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
    DefaultTableModel dtm = new DefaultTableModel(0, 1);
    dtm.setColumnIdentifiers(new Object[] { "Click 'Choose example' and then 'Preview' to get started." });
    theTable.setModel(dtm);
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
  public void setModel(TableModel cstm) {
    theTable.setModel(cstm);

    JComboBox<String> comboBox = new JComboBox<String>();
    comboBox.addItem("string");
    comboBox.addItem("number");
    comboBox.addItem("date");

    theTable.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(comboBox));
  }

  public void showPreviewHint() {
    DefaultTableModel dtm = new DefaultTableModel(0, 1);
    dtm.setColumnIdentifiers(new Object[] { "Set the header, starting line and delimiter, and then press 'Preview' to edit the columns" });
    theTable.setModel(dtm);
  }

}
