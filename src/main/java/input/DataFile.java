package input;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Stores information about the data file and it's corresponding settings file. Creates the Reader,
 * Parser and Settings for the data file.
 */
public class DataFile {
  private File datafile;
  private File settingsfile;

  private Settings settings;
  private Reader reader;
  private final Parser parser;

  /**
   * Creates a new DataFile object.
   * 
   * @param datafile The data file.
   * @param settingsfile The corresponding settings file for the data file.
   * @throws Exception If the settings cannot be read an Exception is thrown.
   */
  public DataFile(final File datafile, final File settingsfile) throws Exception {
    this.datafile = datafile;
    this.settingsfile = settingsfile;

    settings = readSettings(settingsfile);

    reader = createReader(datafile, settings);

    parser = new Parser(settings);
  }

  /**
   * Finds and creates the correct reader for the given file.
   * 
   * @param file The data file to create the reader for.
   * @param settings The settings file corresponding to the data file.
   * @return Returns the correct reader for the file.
   * @throws Exception If the data file cannot be found an Exception is thrown.
   */
  private Reader createReader(final File file, final Settings settings) throws Exception {

    final String fileextension = findExtension(file).toLowerCase();

    if (fileextension.equals("xls")) {
      throw new Exception(
          "Old .xls not supported. Please manually convert to the new Excel 2007 format: .xlsx");
    } else if (fileextension.equals("xlsx")) {
      reader = new ExcelReader(file.getPath(), 0);
    } else {
      try {
        reader = new CsvReader(file.getPath(), settings.getDelimiter());
      } catch (final FileNotFoundException e) {
        System.out.println("Error, CsvReader can't find the following file: " + datafile.getPath());
        throw new Exception("Data file not found.");
      }
    }
    return reader;
  }

  /**
   * Finds the extension for the given file.
   * 
   * @param file The file to find the extension for.
   * @return Returns a String containing the file extension.
   */
  private String findExtension(final File file) {
    final String filename = file.getName();
    final int dot = filename.lastIndexOf(".");
    return filename.substring(dot + 1);
  }

  public File getDatafile() {
    return datafile;
  }

  public String getFilepath() {
    return datafile.getPath();
  }

  public Parser getParser() {
    return parser;
  }

  public Reader getReader() {
    return reader;
  }

  public Settings getSettings() {
    return settings;
  }

  public File getSettingsfile() {
    return settingsfile;
  }

  public String getSettingsfilepath() {
    return settingsfile.getPath();
  }

  /**
   * Opens the settings file using XmlReader.
   * 
   * @param file The file (xml) containing the settings.
   * @return Returns a Settings object.
   * @throws Exception If the settings cannot be read an Exception is thrown.
   */
  private Settings readSettings(final File file) throws Exception {
    Settings settings = null;
    try {
      settings = XmlReader.readXmlFile(file.getPath());
    } catch (final WrongXmlException e) {
      System.out.println("Error parsing settings file!");
      throw new Exception("XML parse error: " + e.getMessage());
    }

    return settings;
  }

  /**
   * Change the data file.
   * 
   * @param datafile The new data file.
   */
  public void setDatafile(final File datafile) {
    this.datafile = datafile;
    try {
      reader = createReader(datafile, settings);
    } catch (final Exception e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Change the settings file.
   * 
   * @param settingsfile The new settings file.
   */
  public void setSettingsfile(final File settingsfile) {
    this.settingsfile = settingsfile;
    try {
      settings = readSettings(settingsfile);
    } catch (final Exception e) {
      System.out.println(e.getMessage());
    }
  }

  @Override
  public String toString() {
    return "DataFile{" + "datafile='" + datafile.toString() + '\'' + ", settingsfile='"
        + settingsfile.toString() + '\'' + '}';
  }
}
