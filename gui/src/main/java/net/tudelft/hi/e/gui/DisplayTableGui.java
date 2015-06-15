package net.tudelft.hi.e.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import net.tudelft.hi.e.data.Table;

/**
 * A GUI to display a Table.
 */
public class DisplayTableGui extends JPanel {
  private static final long serialVersionUID = 1L;

  private JTable jtable;

  /**
   * Creates a gui with a table in the center.
   *
   * @param table The table object to display.
   */
  public DisplayTableGui(Table table) {
    super(new BorderLayout());

    jtable = new JTable(new DisplayTableModel(table));

    add(new JScrollPane(jtable), BorderLayout.CENTER);
    setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
  }

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
   * JTable has no vertical header.
   * This method will set a custom renderer to the first column to mimic a header.
   */
  private void setVerticalHeader() {
    jtable.getColumnModel().getColumn(0).setPreferredWidth(30);
    jtable.getColumnModel().getColumn(0).setCellRenderer(
        new TableCellRenderer() {
          @Override
          public Component getTableCellRendererComponent(JTable table, Object value,
              boolean isSelected, boolean hasFocus, int row, int column) {
            TableCellRenderer renderer = table.getTableHeader().getDefaultRenderer();
            Component component = renderer.getTableCellRendererComponent(table, value,
                false, false, -1, -2);
            ((JLabel) component).setHorizontalAlignment(JLabel.CENTER);
            if (isSelected) {
              component.setFont(component.getFont().deriveFont(Font.BOLD));
            } else {
              component.setFont(component.getFont().deriveFont(Font.PLAIN));
            }
            return component;
          }
        }
    );
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
