package net.tudelft.hi.e.gui;

import net.tudelft.hi.e.input.Input;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A PopupMenu for the filesTable in MainUI.
 */
class MainUiContextMenu extends MouseAdapter implements ActionListener{
    private static final Logger LOG = Logger.getLogger(Task.class.getName());
    private final JPopupMenu popupMenu = new JPopupMenu();
    private final JMenuItem menuRemove = new JMenuItem("Remove");
    private final JMenuItem menuPreview = new JMenuItem("Preview");
    private final JMenuItem menuVisualize = new JMenuItem("Visualize");
    private final JMenuItem menuViewDir = new JMenuItem("View dir");

    private final JTable table;

    /**
     * Creates a popup menu and sets the action listener for the filesTable.
     * @param filesTable The table to add the popup menu to
     */
    public MainUiContextMenu(JTable filesTable) {
        super();
        this.table = filesTable;
        filesTable.addMouseListener(this);

        menuRemove.addActionListener(this);
        menuRemove.setIcon(GUI.createImageIcon("exit.png"));
        popupMenu.add(menuRemove);

        menuPreview.addActionListener(this);
        menuPreview.setIcon(GUI.createImageIcon("table.png"));
        popupMenu.add(menuPreview);

        menuVisualize.addActionListener(this);
        menuVisualize.setIcon(GUI.createImageIcon("icon.png"));
        popupMenu.add(menuVisualize);

        menuViewDir.addActionListener(this);
        menuViewDir.setIcon(GUI.createImageIcon("folder.png"));
        popupMenu.add(menuViewDir);
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        if(!event.isPopupTrigger() || selectRow(event) == -1) {
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
        int r = table.rowAtPoint(event.getPoint());
        if (r >= 0 && r < table.getRowCount()) {
            table.setRowSelectionInterval(r, r);
            return r;
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
        if(source == menuRemove) { onMenuRemove(); }
        else if (source == menuPreview) { onMenuPreview(); }
        else if (source == menuVisualize) { onMenuVisualize(); }
        else if (source == menuViewDir) { onMenuViewDir(); }
    }

    private void onMenuRemove() {
        int selectedRow = table.getSelectedRow();
        if(isOutOfBounds(selectedRow)){
            return;
        }
        Input.getFiles().remove(selectedRow);
        table.repaint();
    }

    private void onMenuPreview() {
        int selectedRow = table.getSelectedRow();
        if(isOutOfBounds(selectedRow)) {
            return;
        }

        DisplayTableGui.init(Input.getFiles().get(selectedRow).getTable());
    }

    private void onMenuVisualize() {
        int selectedRow = table.getSelectedRow();
        if(isOutOfBounds(selectedRow)) {
            return;
        }

        VisualizationsGui.init(Input.getFiles().get(selectedRow).getTable());
    }

    private void onMenuViewDir() {
        int selectedRow = table.getSelectedRow();
        if(isOutOfBounds(selectedRow)){
            return;
        }
        try {
            Desktop.getDesktop().open(Input.getFiles().get(selectedRow).getRawDataFile().getParentFile());
        } catch (IOException e1) {
            LOG.log(Level.SEVERE, "Error trying to view the directory.");
        }
    }
}
