package main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import input.DataFile;
import input.Input;

import org.junit.Before;
import org.junit.Test;

import table.Table;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Test for the DisplayTableModel.
 */
public class DisplayTableModelTest {
  DisplayTableModel model;
  private Table table;
  private final String datafilename = "src/test/resources/test1.csv";
  private final String settingsfilename = "src/test/resources/test1.xml";

  private File datafile = new File(datafilename);
  private File settingsfile = new File(settingsfilename);
  
  String[] columnNames = {"number1", "date1" , "string1", "null1", "number"};
  
  /**
   * Creates a table object from the test1.csv file.
   * @throws Exception gets thrown if an error occures reading the csv file.
   */
  @Before
  public void setUp() throws Exception {
    Input.clean();
    Input.addDataFile(datafile, settingsfile);
    DataFile datafile =  Input.getFiles().get(0);
    table = datafile.getParser().parse(datafile.getReader());
    model = new DisplayTableModel(table);
  }

  @Test
  public void testGetRowCount() {
    int actual = model.getRowCount();
    int expected = 10;
    assertEquals(expected, actual);
  }

  @Test
  public void testGetColumnCount() {
    int actual = model.getColumnCount();
    int expected = 5;
    assertEquals(expected, actual);
  }

  @Test
  public void testGetColumnName() {
    ArrayList<String> columns = new ArrayList<String>(Arrays.asList(columnNames));
    for (int i = 0; i < columnNames.length; i++) {
      String actual = model.getColumnName(i);
      assertTrue(columns.contains(actual));
      columns.remove(actual);
    }
  }

  @Test
  public void testGetColumnClass() {
    for (int i = 0; i < columnNames.length; i++) {
      assertEquals(String.class, model.getColumnClass(i));
    }
  }

  @Test
  public void testIsCellEditable() {
    for (int c = 0; c < columnNames.length; c++) {
      for (int r = 0; r < 10; r++) {
        assertFalse(model.isCellEditable(r, c));
      }
    }
  }

  @Test
  public void testGetValueAt() {
    assertEquals("czaz", model.getValueAt(3, findColumnIndex(columnNames[2])).toString());
    assertEquals("2014-02-13", model.getValueAt(7, findColumnIndex(columnNames[1])).toString());
    assertEquals("7", model.getValueAt(9, findColumnIndex(columnNames[0])).toString());
  }
  
  /**
   * Finds the column index for the specified name.
   * @param name the column name for wich the index should be found.
   * @return returns the index of the column, retuns -1 of it is not found.
   */
  public int findColumnIndex(String name) {
    for (int i = 0; i < model.getColumnCount(); i++) {
      if (name.equals(model.getColumnName(i))) {
        return i;
      }
    }
    return -1;
  }

}
