package export;

import static org.junit.Assert.assertEquals;

import input.DataFile;
import input.Input;
import input.Settings;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class SettingsWriterTest {
  private DataFile f1;
  private DataFile f2;
  private DataFile f3;
  private DataFile f4;
  
  final String path = "src/test/resources/";
  
  final File file1 = new File(path + "test1.csv");
  final File settings1 = new File(path + "test1.xml");  
  final File file2 = new File(path + "test2.csv");
  final File settings2 = new File(path + "test2.xml");
  final File file3 = new File(path + "test3.csv");
  final File settings3 = new File(path + "test3.xml");
  final File file4 = new File(path + "csvexample.csv");
  final File settings4 = new File(path + "settings.xml");
  
  private File output = new File(path + "SettingsWriterTest.xml");
  
  /**
   * Initialize input files for the tests.
   */
  @Before
  public void setUp() throws Exception {
    Input.clean();
    Input.addDataFile(file1, settings1);
    f1 = Input.getFiles().get(0);
    Input.addDataFile(file2, settings2);
    f2 = Input.getFiles().get(1);
    Input.addDataFile(file3, settings3);
    f3 = Input.getFiles().get(2);
    Input.addDataFile(file4, settings4);
    f4 = Input.getFiles().get(3);
  }
  
  @After
  public void cleanUp() {
    output.delete();
  }
  
  @Test
  public void testWriteSettingsTest1() throws Exception {
    Settings expected = f1.getSettings();
    SettingsWriter.writeSettings(expected, output);
    Input.addDataFile(file1, output);
    Settings actual = Input.getFiles().get(Input.getFiles().size() - 1).getSettings();
    assertEquals(expected, actual);
  }
  
  @Test
  public void testWriteSettingsTest2() throws Exception {
    Settings expected = f2.getSettings();
    SettingsWriter.writeSettings(expected, output);
    Input.addDataFile(file2, output);
    Settings actual = Input.getFiles().get(Input.getFiles().size() - 1).getSettings();
    assertEquals(expected, actual);
  }

  @Test
  public void testWriteSettingsTest3() throws Exception {
    Settings expected = f3.getSettings();
    SettingsWriter.writeSettings(expected, output);
    Input.addDataFile(file3, output);
    Settings actual = Input.getFiles().get(Input.getFiles().size() - 1).getSettings();
    assertEquals(expected, actual);
  }
  
  @Test
  public void testWriteSettingsCsvexample() throws Exception {
    Settings expected = f4.getSettings();
    SettingsWriter.writeSettings(expected, output);
    Input.addDataFile(file4, output);
    Settings actual = Input.getFiles().get(Input.getFiles().size() - 1).getSettings();
    assertEquals(expected, actual);
  }
}
