package export;

import input.DataFile;
import input.Input;
import input.Settings;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import table.Table;
import table.value.Column;

import java.io.File;
import java.util.ArrayList;

public class SettingsBuilderTest {
  private Table t1;
  private Table t2;
  private Table t3;
  private Table t4;
  private DataFile f1;
  private DataFile f2;
  private DataFile f3;
  private DataFile f4;
  
  @Before
  public void setUp() throws Exception {
    final String path = "src/test/resources/";
    Input.clean();
    
    final File file1 = new File(path + "test1.csv");
    final File settings1 = new File(path + "test1.xml");
    Input.addDataFile(file1, settings1);
    f1 = Input.getFiles().get(0);
    t1 = f1.getParser().parse(f1.getReader());
    
    final File file2 = new File(path + "test2.csv");
    final File settings2 = new File(path + "test2.xml");
    Input.addDataFile(file2, settings2);
    f2 = Input.getFiles().get(1);
    t2 = f2.getParser().parse(f2.getReader());
    
    final File file3 = new File(path + "test3.csv");
    final File settings3 = new File(path + "test3.xml");
    Input.addDataFile(file3, settings3);
    f3 = Input.getFiles().get(2);
    t3 = f3.getParser().parse(f3.getReader());
    
    final File file4 = new File(path + "csvexample.csv");
    final File settings4 = new File(path + "settings.xml");
    Input.addDataFile(file4, settings4);
    f4 = Input.getFiles().get(3);
    t4 = f4.getParser().parse(f4.getReader());
  }

  @Test
  public void testGenerateSettingsTest1() {
    Settings expected = f1.getSettings();
    expected.getColumns().remove(3);
    Settings actual = SettingsBuilder.generateSettings(t1, expected.getDelimiter(), 2);
    assertEquals(expected, actual);
  }
  
  @Test
  public void testGenerateSettingsTest2() {
    Settings expected = f2.getSettings();
    Settings actual = SettingsBuilder.generateSettings(t2, expected.getDelimiter(), 2);
    assertEquals(expected, actual);
  }
  
  @Test
  public void testGenerateSettingsTest3() {
    Settings expected = f3.getSettings();
    Settings actual = SettingsBuilder.generateSettings(t3, expected.getDelimiter(), 2);
    assertEquals(expected, actual);
  }
  
  @Test
  public void testGenerateSettingsCsvexample() {
    Settings expected = f3.getSettings();
    Settings actual = SettingsBuilder.generateSettings(t3, expected.getDelimiter(), 2);
    assertEquals(expected, actual);
  }

}
