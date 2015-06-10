package xmleditor;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.xml.parsers.ParserConfigurationException;

public class ColumnSettingsPane extends JScrollPane {

  private static final long serialVersionUID = 1L;

  private JTable theTable;
  
  public ColumnSettingsPane(JTable empty) {
    super(empty);
    theTable = empty;
  }
  
  
  public void setModel(ColumnSettingsTableModel cstm){
    theTable.setModel(cstm);
  }

}
