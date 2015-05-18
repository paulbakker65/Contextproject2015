package main;

import java.util.ArrayList;

import javax.swing.JTable;

/**
 * The JTable for displaying the selected files. Used in MainUI.
 */
public class FilesTable extends JTable {
  private static final long serialVersionUID = 1L;
  
  static FilesTableModel ftm = new FilesTableModel();

  public FilesTable() {
    super(ftm);
  }

  public FilesTable(FilesTableModel model) {
    super(model);
    ftm = model;
  }

  public void addRow(DataFile file) {
    ftm.addRow(file);
    repaint();
  }

  public void removeRow(int index) {
    ftm.removeRow(index);
    repaint();
  }

  public ArrayList<DataFile> getFiles() {
    return ftm.getFiles();
  }

}
