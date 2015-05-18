package main;

import static org.junit.Assert.*;
import input.CSVReader;
import input.Reader;
import input.Settings;
import input.WrongXMLException;
import input.XMLReader;

import java.io.File;

import org.junit.Test;

import parsers.Parser;

public class DataFileTest {
  private final String datafilename = "src/test/resources/csvexample.csv";
  private final String settingsfilename = "src/test/resources/settings.xml";

  private File datafile = new File(datafilename);
  private File settingsfile = new File(settingsfilename);

  private DataFile df = new DataFile(datafile, settingsfile);

  @Test
  public void testToString() {
    String expected = "DataFile{datafile='" + datafile.toString() + "\', settingsfile='"
        + settingsfile.toString() + "\'}";
    String actual = df.toString();
    assertEquals(expected, actual);
  }

  @Test
  public void testGetDatafile() {
    File file = df.getDatafile();
    assertEquals(datafile, file);
  }

  @Test
  public void testSetDatafile() {
    File newfile = new File(datafilename);
    df.setDatafile(newfile);

    File file = df.getDatafile();
    assertEquals(newfile, file);
  }

  @Test
  public void testGetSettingsfile() {
    File file = df.getSettingsfile();
    assertEquals(settingsfile, file);
  }

  @Test
  public void testSetSettingsfile() {
    File newfile = new File(settingsfilename);
    df.setSettingsfile(newfile);

    File file = df.getSettingsfile();
    assertEquals(newfile, file);
  }

  @Test
  public void testGetSettings() {
    Settings expected = null;
    try {
      expected = XMLReader.readXMLFile(settingsfile.getAbsolutePath());
    } catch (WrongXMLException e) {
      assert (false);
      e.printStackTrace();
    }
    Settings actual = df.getSettings();
    assertEquals(expected, actual);
  }

  @Test
  public void testGetReader() {
    Reader reader = df.getReader();
    assert (reader instanceof CSVReader);

    String expected = ";";
    String actual = ((CSVReader) reader).getDelimiter();
    assertEquals(expected, actual);

    String filepath = reader.getFilepath();
    assertEquals(datafile.getPath(), filepath);
  }

  @Test
  public void testGetParser() {
    Parser parser = df.getParser();
    assert (parser instanceof Parser);
  }

  @Test
  public void testgetFilepath() {
    String actual = df.getFilepath();
    assertEquals(datafile.getPath(), actual);
  }

  @Test
  public void getSettingsfilepath() {
    String actual = df.getSettingsfilepath();
    assertEquals(settingsfile.getPath(), actual);
  }
}
