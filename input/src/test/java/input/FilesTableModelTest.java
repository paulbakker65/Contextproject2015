package input;

import java.io.File;
import java.util.ArrayList;
import net.tudelft.hi.e.common.exceptions.WrongXmlException;
import net.tudelft.hi.e.input.DataFile;
import net.tudelft.hi.e.input.FilesTableModel;
import net.tudelft.hi.e.input.Input;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;

/**
 * FileTableModelTest class testing the main.FileTableModel class.
 *
 */
public class FilesTableModelTest {
  private final FilesTableModel ftm = new FilesTableModel();
  private final String datafilename = "src/test/resources/csvexample.csv";
  private final String settingsfilename = "src/test/resources/settings.xml";

  private final File datafile = new File(datafilename);
  private final File settingsfile = new File(settingsfilename);

  private DataFile df;

  /**
   * Calls the constructor with the datafile and settings file.
   *
   * @throws WrongXmlException if the settings file is incorrect.
   */
  @Before
  public void setUp() throws WrongXmlException {
    Input.setFiles(new ArrayList<DataFile>());
    try {
      df = new DataFile(datafile, settingsfile);
    } catch (final Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testAddTableModelListener() {
    // Not yet implemented in filesTableModel
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
    final String[] columnNames = {"Data filepath", "Settings filepath"};
    assertEquals(columnNames[0], ftm.getColumnName(0));
    assertEquals(columnNames[1], ftm.getColumnName(1));
  }

  @Test
  public void testGetRowCount() {
    assertEquals(0, ftm.getRowCount());
    Input.getFiles().add(df);
    assertEquals(1, ftm.getRowCount());
    Input.getFiles().add(df);
    Input.getFiles().add(df);
    Input.getFiles().add(df);
    assertEquals(4, ftm.getRowCount());
    Input.getFiles().remove(3);
    assertEquals(3, ftm.getRowCount());
  }

  @Test
  public void testGetValueAt() {
    Input.getFiles().add(df);
    assertEquals(datafile.getPath(), ftm.getValueAt(0, 0));
    assertEquals(settingsfile.getPath(), ftm.getValueAt(0, 1));
  }

  @Test
  public void testIsCellEditable() {
    assertFalse(ftm.isCellEditable(5, 20034));
  }

  @Test
  public void testRemoveTableModelListener() {
    // Not yet implemented in filesTableModel
  }

  @Test
  public void testSetValueAt() {
    // Not yet implemented in filesTableModel
  }

}
