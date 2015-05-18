package main;

import java.util.ArrayList;

import javax.swing.JTable;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * The JTable for displaying the selected files. Used in MainUI.
 */
public class FilesTable extends JTable {
  private static final long serialVersionUID = 1L;
  
  static FilesTableModel ftm = new FilesTableModel();

  public FilesTable() {
    super(ftm);
  }

  @SuppressFBWarnings(value = "ST_WRITE_TO_STATIC_FROM_INSTANCE_METHOD", 
      justification = "Required to make the GUI builder work")
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
