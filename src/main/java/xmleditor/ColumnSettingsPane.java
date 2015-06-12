package xmleditor;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

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
  
  public static ColumnSettingsPane createPanel(){
    return new ColumnSettingsPane(new JTable());
  }

  public void setModel(ColumnSettingsTableModel cstm) {
    if (cstm==null){
      theTable.setModel(new DefaultTableModel(1,4));
    }
    else{
      theTable.setModel(cstm);
      TableColumn tc = theTable.getColumnModel().getColumn(1);

      JComboBox comboBox = new JComboBox();
      comboBox.addItem("text");
      comboBox.addItem("number");
      comboBox.addItem("date");
      
      tc.setCellEditor(new DefaultCellEditor(comboBox));
    }
  }

}



