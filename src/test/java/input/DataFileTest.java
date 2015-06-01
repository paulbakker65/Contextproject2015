package input;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

/**
 * DataFileTest class testing main.DataFile class.
 * 
 */
public class DataFileTest {
  private final String datafilename = "src/test/resources/csvexample.csv";
  private final String settingsfilename = "src/test/resources/settings.xml";

  private final File datafile = new File(datafilename);
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
    final File file = df.getDatafile();
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
    df.setDatafile(newfile);

    final File file = df.getDatafile();
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
}
