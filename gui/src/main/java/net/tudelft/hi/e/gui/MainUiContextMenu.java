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

/**
 * A PopupMenu for the filesTable in MainUI.
 */
public class MainUiContextMenu extends MouseAdapter implements ActionListener{
    private JPopupMenu popupMenu = new JPopupMenu();;
    private JMenuItem menuRemove = new JMenuItem("Remove");
    private JMenuItem menuPreview = new JMenuItem("Preview");
    private JMenuItem menuVisualize = new JMenuItem("Visualize");
    private JMenuItem menuViewDir = new JMenuItem("View dir");

    private JTable table;

    public MainUiContextMenu(JTable filesTable) {
        super();
        this.table = filesTable;
        filesTable.addMouseListener(this);
        filesTable.getTableHeader().addMouseListener(this);

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
        if(!event.isPopupTrigger()) {
            return;
        }

        selectRow(event);

        showPopup(event);
    }

    private void selectRow(MouseEvent event) {
        int r = table.rowAtPoint(event.getPoint());
        if (r >= 0 && r < table.getRowCount()) {
            table.setRowSelectionInterval(r, r);
        } else {
            table.clearSelection();
        }
    }

    private void showPopup(MouseEvent event) {
        if (event.isPopupTrigger()) {
            popupMenu.show(event.getComponent(), event.getX(), event.getY());
        }
    }

    private boolean isOutOfBounds(int row) {
        int rowcount = table.getRowCount();
        if(row < 0 || row > rowcount){
            return true;
        }
        return false;
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
            System.out.println("Error trying to view the directory.");
            e1.printStackTrace();
        }
    }
}
