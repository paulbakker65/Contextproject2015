package main;

import table.Table;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

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

    packColumns(jtable);
    
    add(new JScrollPane(jtable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);
  }
  
  /**
   * Packs all columns of the table to their content size.
   * @param table The table who's columns will be packed.
   */
  public static void packColumns(JTable table) {
    for (int column = 0; column < table.getColumnCount(); column++) {
      packColumn(table, column);
    }
  }
  
  /**
   * Packs a column to its content size.
   * @param table A JTable containing the column.
   * @param column The column index.
   */
  public static void packColumn(JTable table, int column) {
    final TableColumnModel columnModel = table.getColumnModel();
    final TableColumn tablecolumn = columnModel.getColumn(column);

    int width = tablecolumn.getMinWidth();
    width = Math.max(getColumnHeaderWidth(table, tablecolumn), width);
    width = Math.max(getColumnCellsWidth(table, column), width);
        
    tablecolumn.setPreferredWidth(width + 1);
  }
  
  private static int getColumnHeaderWidth(JTable table, TableColumn tablecolumn) {
    TableCellRenderer renderer = tablecolumn.getHeaderRenderer();
    if (renderer == null) {
      renderer = table.getTableHeader().getDefaultRenderer();
    }
    Component component = renderer.getTableCellRendererComponent(table, 
        tablecolumn.getHeaderValue(), false, false, 0, 0);
    return component.getPreferredSize().width;
  }
  
  private static int getColumnCellsWidth(JTable table, int column) {
    TableCellRenderer renderer;
    Component component;
    int width = 0;
    for (int row = 0; row < table.getRowCount(); row++) {
      renderer = table.getCellRenderer(row, column);
      component = table.prepareRenderer(renderer, row, column);
      width = Math.max(component.getPreferredSize().width, width);
    }
    return width;
  }
}
