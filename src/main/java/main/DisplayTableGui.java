package main;

import table.Table;

import java.awt.BorderLayout;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


/**
 * A GUI to display a Table.
 */
public class DisplayTableGui extends JPanel {
  private static final long serialVersionUID = 1L;

  private JTable jtable;
  
  public static void init(Table table) {
    //JFrame frame = new JFrame("Table preview: " + table.getName());
    //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    JDialog dialog = new JDialog(null, ModalityType.TOOLKIT_MODAL);
    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    dialog.setTitle("Table preview: " + table.getName());
    
    //Create and set up the content pane.
    JComponent contentPane = new DisplayTableGui(table);
    contentPane.setOpaque(true); //content panes must be opaque
    contentPane.setPreferredSize(new Dimension(1024, 720));
    dialog.setContentPane(contentPane);

    dialog.pack();
    GUI.setIconImage(dialog);
    GUI.centreWindow(dialog);
    dialog.setVisible(true);
  }
  
  /**
   * Creates a gui with a table in the center.
   * @param table The table object to display.
   */
  public DisplayTableGui(Table table) {
    super(new BorderLayout());

    jtable = new JTable(new DisplayTableModel(table));
    
    add(new JScrollPane(jtable), BorderLayout.CENTER);
    setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
  }
  
}
