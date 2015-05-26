package input;

import static org.junit.Assert.*;
import input.DataFile;
import input.FilesTableModel;
import input.Input;
import input.WrongXMLException;

import java.io.File;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 * FileTableModelTest class testing the main.FileTableModel class.
 * 
 */
public class FilesTableModelTest {
  private FilesTableModel ftm = new FilesTableModel();
  private final String datafilename = "src/test/resources/csvexample.csv";
  private final String settingsfilename = "src/test/resources/settings.xml";

  private File datafile = new File(datafilename);
  private File settingsfile = new File(settingsfilename);

  private DataFile df;

  @Before
  public void setUp() throws WrongXMLException {
    Input.files = new ArrayList<DataFile>();
    try {
      df = new DataFile(datafile, settingsfile);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  @Test
  public void testGetColumnClass() {
    assertEquals(String.class, ftm.getColumnClass(0));
    assertEquals(String.class, ftm.getColumnClass(1));
  }

  @Test
  public void testGetColumnCount() {
    assertEquals(2, ftm.getColumnCount());
  }

  @Test
  public void testGetColumnName() {
    String[] columnNames = { "Data filepath", "Settings filepath" };
    assertEquals(columnNames[0], ftm.getColumnName(0));
    assertEquals(columnNames[1], ftm.getColumnName(1));
  }

  @Test
  public void testGetRowCount() {
    assertEquals(0, ftm.getRowCount());
    Input.files.add(df);
    assertEquals(1, ftm.getRowCount());
    Input.files.add(df);
    Input.files.add(df);
    Input.files.add(df);
    assertEquals(4, ftm.getRowCount());
    Input.files.remove(3);
    assertEquals(3, ftm.getRowCount());
  }

  @Test
  public void testGetValueAt() {
    Input.files.add(df);
    assertEquals(datafile.getPath(), ftm.getValueAt(0, 0));
    assertEquals(settingsfile.getPath(), ftm.getValueAt(0, 1));
  }

  @Test
  public void testIsCellEditable() {
    assertFalse(ftm.isCellEditable(5, 20034));
  }

  @Test
  public void testSetValueAt() {
    // Not yet implemented in filesTableModel
  }

  @Test
  public void testAddTableModelListener() {
    // Not yet implemented in filesTableModel
  }

  @Test
  public void testRemoveTableModelListener() {
    // Not yet implemented in filesTableModel
  }

}
