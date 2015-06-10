package net.tudelft.hi.e.gui;

import java.awt.BorderLayout;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
   * @param table
   *          the table to display.
   */
  public static void init(Table table) {
    // JFrame frame = new JFrame("Table preview: " + table.getName());
    // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JDialog dialog = new JDialog(null, ModalityType.TOOLKIT_MODAL);
    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    dialog.setTitle("Table preview: " + table.getName());

    // Create and set up the content pane.
    JComponent contentPane = new DisplayTableGui(table);
    contentPane.setOpaque(true); // content panes must be opaque

    Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
    Insets scnMax = Toolkit.getDefaultToolkit().getScreenInsets(dialog.getGraphicsConfiguration());

    Dimension preferredDimension = null;

    if (scnMax.bottom != 0) {
      preferredDimension = new Dimension(scrSize.width, scrSize.height - scnMax.bottom - 2 * 20);
    } else if (scnMax.top != 0) {
      preferredDimension = new Dimension(scrSize.width, scrSize.height - scnMax.top - 2 * 20);
    } else if (scnMax.left != 0) {
      preferredDimension = new Dimension(scrSize.width - scnMax.left - 2 * 20, scrSize.height);
    } else if (scnMax.right != 0) {
      preferredDimension = new Dimension(scrSize.width - scnMax.right - 2 * 20, scrSize.height);
    }

    contentPane.setPreferredSize(preferredDimension);
    dialog.setContentPane(contentPane);
    dialog.setLocation(0, 0);

    dialog.pack();
    GUI.setIconImage(dialog);
    dialog.setVisible(true);
  }
}
