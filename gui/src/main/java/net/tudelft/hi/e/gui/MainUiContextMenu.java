package net.tudelft.hi.e.gui;

import net.tudelft.hi.e.data.Table;
import net.tudelft.hi.e.input.Input;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

/**
 * A PopupMenu for the filesTable in MainUI.
 */
class MainUiContextMenu extends MouseAdapter implements ActionListener{
  private static final Logger LOG = Logger.getLogger(Task.class.getName());
  private final JPopupMenu popupMenu = new JPopupMenu();
  private final JMenuItem menuRemove = new JMenuItem("Remove");
  private final JMenuItem menuPreview = new JMenuItem("Preview Table");
  private final JMenuItem menuSettings = new JMenuItem("Preview Settings");
  private final JMenuItem menuVisualize = new JMenuItem("Visualize");
  private final JMenuItem menuViewDir = new JMenuItem("View dir");

  private final JTable table;
  private final JFrame frame;

  /**
   * Creates a popup menu and sets the action listener for the filesTable.
   * @param filesTable The table to add the popup menu to
   */
  private MainUiContextMenu(JTable filesTable, JFrame frame) {
    super();
    this.table = filesTable;
    this.frame = frame;

    menuRemove.addActionListener(this);
    menuRemove.setIcon(Gui.createImageIcon("remove.png"));
    popupMenu.add(menuRemove);

    menuPreview.addActionListener(this);
    menuPreview.setIcon(Gui.createImageIcon("table.png"));
    popupMenu.add(menuPreview);

    menuSettings.addActionListener(this);
    menuSettings.setIcon(Gui.createImageIcon("settings.png"));
    popupMenu.add(menuSettings);

    menuVisualize.addActionListener(this);
    menuVisualize.setIcon(Gui.createImageIcon("icon.png"));
    popupMenu.add(menuVisualize);

    menuViewDir.addActionListener(this);
    menuViewDir.setIcon(Gui.createImageIcon("folder.png"));
    popupMenu.add(menuViewDir);
  }

  public static void init(JTable table, JFrame frame) {
    MouseAdapter ma = new MainUiContextMenu(table, frame);
    table.addMouseListener(ma);
  }

  @Override
  public void mouseReleased(MouseEvent event) {
    if (!event.isPopupTrigger() || selectRow(event) == -1) {
      return;
    }

    popupMenu.show(event.getComponent(), event.getX(), event.getY());
  }

  /**
   * Selects the row at the location of the mouse pointer.
   * @param event The click event.
   * @return Returns the index of the selected row, if now row is selected -1 is returned.
   */
  private int selectRow(MouseEvent event) {
    int row = table.rowAtPoint(event.getPoint());
    if (row >= 0 && row < table.getRowCount()) {
      table.setRowSelectionInterval(row, row);
      return row;
    } else {
      table.clearSelection();
      return -1;
    }
  }

  /**
   * Checks if the row is in the bounds of the filesTable.
   * @param row The index to check.
   * @return Returns true if the index is not valid, false if the index is a valid row.
   */
  private boolean isOutOfBounds(int row) {
    int rowcount = table.getRowCount();
    return row < 0 || row > rowcount;
  }


  @Override
  public void actionPerformed(ActionEvent event) {
    Object source = event.getSource();
    if (source == menuRemove) {
      onMenuRemove(); 
    } else if (source == menuPreview) { 
      onMenuPreview();
    } else if (source == menuSettings) {
      onMenuSettings();
    } else if (source == menuVisualize) {
      onMenuVisualize();
    } else if (source == menuViewDir) {
      onMenuViewDir();
    }
  }

  private void onMenuRemove() {
    int selectedRow = table.getSelectedRow();
    if (isOutOfBounds(selectedRow)) {
      return;
    }

    Input.getFiles().remove(selectedRow);
    table.revalidate();
  }

  private void onMenuPreview() {
    invokeInit(DisplayTableGui.class);
  }

  private void onMenuSettings() {
    int selectedRow = table.getSelectedRow();
    if (isOutOfBounds(selectedRow)) {
      return;
    }

    Gui.openSystemEditor(Input.getFiles().get(selectedRow).getSettingsfile());
  }

  private void onMenuVisualize() {
    invokeInit(VisualizationsGui.class);
  }

  private void invokeInit(Class<?> type) {
    Cursor cursor = frame.getCursor();
    frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    
    int selectedRow = table.getSelectedRow();
    if (isOutOfBounds(selectedRow)) {
      return;
    }
    
    invokeInitOnRow(type, selectedRow);

    frame.setCursor(cursor);
  }
  
  private void invokeInitOnRow(Class<?> type, int selectedRow) {
    Method method;
    try {
      method = type.getMethod("init", Table.class);
      method.invoke(null, Input.getFiles().get(selectedRow).getTable());
    } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
      LOG.log(Level.SEVERE, e.getMessage(), e);
    }
  }

  private void onMenuViewDir() {
    int selectedRow = table.getSelectedRow();
    if (isOutOfBounds(selectedRow)) {
      return;
    }
    Gui.openSystemEditor(Input.getFiles().get(selectedRow).getRawDataFile().getParentFile());
  }
}
