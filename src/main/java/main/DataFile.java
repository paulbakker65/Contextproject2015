package main;

import input.CSVReader;
import input.Reader;
import input.Settings;
import input.WrongXMLException;
import input.XMLReader;
import parsers.Parser;

import java.io.File;
import java.io.FileNotFoundException;


/**
 * Stores information about the data file and it's corresponding settings file.
 * Creates the Reader, Parser and Settings for the data file.
 */
public class DataFile {
  private File datafile;
  private File settingsfile;

  private Settings settings;
  private Reader reader;
  private Parser parser;

  public DataFile(File datafile, File settingsfile) throws Exception {
    this.datafile = datafile;
    this.settingsfile = settingsfile;

    settings = readSettings(settingsfile);

    reader = createReader(datafile, settings);

    parser = new Parser(settings);
  }

  /**
   * Finds the extension for the given file.
   * 
   * @param file
   *          The file to find the extension for.
   * @return Returns a String containing the file extension.
   */
  private String findExtension(File file) {
    String filename = file.getName();
    int dot = filename.lastIndexOf(".");
    return filename.substring(dot + 1);
  }

  /**
   * Opens the settings file using XMLReader.
   * 
   * @param file
   *          The file (xml) containing the settings.
   * @return Returns a Settings object.
   * @throws Exception 
   */
  private Settings readSettings(File file) throws Exception {
    Settings settings = null;
    try {
      settings = XMLReader.readXMLFile(settingsfile.getPath());
    } catch (WrongXMLException e) {
      System.out.println("Error parsing settings file!");
      e.printStackTrace();
      throw new Exception("XML parse error");
    }

    return settings;
  }

  /**
   * Finds and creates the correct reader for the given file.
   * 
   * @param file
   *          The data file to create the reader for.
   * @param settings
   *          The settings file corresponding to the data file.
   * @return Returns the correct reader for the file.
   */
  private Reader createReader(File file, Settings settings) {

    String fileextension = findExtension(file);

    if (fileextension.equals("xls")) {// excel reader
      System.out.println("Using following reader: XLSReader");
    } else {// Default reader = CSVReader
      System.out.println("Using default reader: CSVReader");
      try {
        reader = new CSVReader(file.getPath(), settings.getDelimiter());
      } catch (FileNotFoundException e) {
        System.out.println("Error, CSVReader can't find the following file: " + datafile.getPath());
        e.printStackTrace();
        System.exit(1);
      }
    }
    return reader;
  }

  @Override
  public String toString() {
    return "DataFile{" + "datafile='" + datafile.toString() + '\'' + ", settingsfile='"
        + settingsfile.toString() + '\'' + '}';
  }

  public File getDatafile() {
    return datafile;
  }

  public void setDatafile(File datafile) {
    this.datafile = datafile;
    reader = createReader(datafile, settings);
  }

  public File getSettingsfile() {
    return settingsfile;
  }

  public void setSettingsfile(File settingsfile) {
    this.settingsfile = settingsfile;
    try {
      settings = readSettings(settingsfile);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public Settings getSettings() {
    return settings;
  }

  public Reader getReader() {
    return reader;
  }

  public Parser getParser() {
    return parser;
  }

  public String getFilepath() {
    return datafile.getPath();
  }

  public String getSettingsfilepath() {
    return settingsfile.getPath();
  }
}
