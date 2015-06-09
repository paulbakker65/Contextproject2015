package main;

import table.Table;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * A GUI to display a Table.
 */
public class DisplayTableGui extends JPanel {
  private static final long serialVersionUID = 1L;

  private JTable jtable;

  /**
   * Creates a gui for the given table.
   * @param table The table to display.
   */
  public static void init(Table table) {
    JFrame frame = new JFrame("Table preview: " + table.getName());
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    JComponent contentPane = new DisplayTableGui(table);
    contentPane.setOpaque(true);
    contentPane.setPreferredSize(new Dimension(1024, 720));
    frame.setContentPane(contentPane);
    
    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

    frame.pack();
    GUI.setIconImage(frame);
    GUI.centreWindow(frame);
    frame.setVisible(true);
  }

  /**
   * Creates a gui with a table in the center.
   * 
   * @param table
   *          The table object to display.
   */
  public DisplayTableGui(Table table) {
    super(new BorderLayout());

    jtable = new JTable(new DisplayTableModel(table));
    jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

    add(new JScrollPane(jtable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);
  }
}
