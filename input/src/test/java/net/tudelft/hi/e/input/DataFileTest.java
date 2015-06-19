package net.tudelft.hi.e.input;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import net.tudelft.hi.e.common.exceptions.WrongXmlException;
import net.tudelft.hi.e.data.Table;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * DataFileTest class testing main.DataFile class.
 *
 */
public class DataFileTest {
  private final String datafilename = "src/test/resources/csvexample.csv";
  private final String datafilenameXls = "src/test/resources/website_test3.xls";
  private final String settingsfilename = "src/test/resources/settings.xml";

  private final File datafile = new File(datafilename);
  private final File datafileXls = new File(datafilenameXls);
  private final File settingsfile = new File(settingsfilename);

  private DataFile df;

  @Test
  public void getSettingsfilepath() {
    final String actual = df.getSettingsfilepath();
    assertEquals(settingsfile.getPath(), actual);
  }

  /**
   * Calls the constructor with the datafile and settings file.
   *
   * @throws WrongXmlException if the settings file is incorrect.
   */
  @Before
  public void setUp() throws WrongXmlException {
    try {
      df = new DataFile(datafile, settingsfile);
    } catch (final Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testGetDatafile() {
    final File file = df.getRawDataFile();
    assertEquals(datafile, file);
  }

  @Test
  public void testgetFilepath() {
    final String actual = df.getFilepath();
    assertEquals(datafile.getPath(), actual);
  }

  @Test
  public void testGetParser() {
    final Parser parser = df.getParser();
    assert (parser instanceof Parser);
    assertFalse(df.parse().isEmpty());
  }

  @Test
  public void testGetReader() {
    final Reader reader = df.getReader();
    assert (reader instanceof CsvReader);

    final String expected = ";";
    final String actual = ((CsvReader) reader).getDelimiter();
    assertEquals(expected, actual);

    final String filepath = reader.getFilepath();
    assertEquals(datafile.getPath(), filepath);
  }

  @Test
  public void testGetSettings() {
    Settings expected = null;
    try {
      expected = XmlReader.readXmlFile(settingsfile.getAbsolutePath());
    } catch (final WrongXmlException e) {
      assert (false);
      e.printStackTrace();
    }
    final Settings actual = df.getSettings();
    assertEquals(expected, actual);
  }

  @Test
  public void testGetSettingsfile() {
    final File file = df.getSettingsfile();
    assertEquals(settingsfile, file);
  }

  @Test
  public void testSetDatafile() {
    final File newfile = new File(datafilename);
    df.setRawDataFile(newfile);

    final File file = df.getRawDataFile();
    assertEquals(newfile, file);
  }

  @Test
  public void testSetSettingsfile() {
    final File newfile = new File(settingsfilename);
    df.setSettingsfile(newfile);

    final File file = df.getSettingsfile();
    assertEquals(newfile, file);
  }

  @Test
  public void testToString() {
    final String expected =
        "DataFile{datafile='" + datafile.toString() + "\', settingsfile='"
            + settingsfile.toString() + "\'}";
    final String actual = df.toString();
    assertEquals(expected, actual);
  }
  
  @Test
  public void testGetterSetterTable() {
    df.setTable(new Table());
    assertEquals(new Table(), df.getTable());
    
    df.setTable(null);
    assertFalse(df.getTable().isEmpty());
  }
  
  @Test (expected = IOException.class)
  public void testXls() throws IOException {
    df = new DataFile(datafileXls, settingsfile);
  }
}
