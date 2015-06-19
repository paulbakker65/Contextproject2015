package net.tudelft.hi.e.input;

import net.tudelft.hi.e.common.exceptions.ParseFailedException;
import net.tudelft.hi.e.common.exceptions.WrongXmlException;
import net.tudelft.hi.e.data.Table;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Stores information about the data file and it's corresponding settings file. Creates the Reader,
 * Parser and Settings for the data file.
 */
public class DataFile {

  private static final Logger LOG = Logger.getLogger(DataFile.class.getName());

  private File rawDataFile;
  private File settingsfile;

  private Settings settings;
  private Reader reader;
  private final Parser parser;

  private Table table;

  /**
   * Creates a new DataFile object.
   *
   * @param datafile The data file.
   * @param settingsfile The corresponding settings file for the data file.
   * @throws Exception If the settings cannot be read an Exception is thrown.
   */
  public DataFile(final File datafile, final File settingsfile) throws IOException {
    this.rawDataFile = datafile;
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
  public static Reader createReader(final File file, final Settings settings) throws IOException {

    final String fileextension = findExtension(file).toLowerCase();

    Reader reader;

    if ("xls".equals(fileextension)) {
      throw new UnsupportedEncodingException(
          "Old .xls not supported. Please manually convert to the new Excel 2007 format: .xlsx");
    } else if ("xlsx".equals(fileextension)) {
      reader = new ExcelReader(file.getPath(), 0);
    } else {
      reader = new CsvReader(file.getPath(), settings.getDelimiter());
    }
    return reader;
  }

  /**
   * Finds the extension for the given file.
   *
   * @param file The file to find the extension for.
   * @return Returns a String containing the file extension.
   */
  private static String findExtension(final File file) {
    final String filename = file.getName();
    final int dot = filename.lastIndexOf(".");
    return filename.substring(dot + 1);
  }

  public File getRawDataFile() {
    return rawDataFile;
  }

  public String getFilepath() {
    return rawDataFile.getPath();
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
  private Settings readSettings(final File file) throws WrongXmlException {
    return XmlReader.readXmlFile(file.getPath());
  }

  /**
   * Change the data file.
   *
   * @param rawDataFile The new data file.
   */
  public void setRawDataFile(final File rawDataFile) {
    this.rawDataFile = rawDataFile;
    try {
      reader = createReader(rawDataFile, settings);
    } catch (final Exception ex) {
      LOG.log(Level.SEVERE, ex.getMessage(), ex);
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
    } catch (final Exception ex) {
      LOG.log(Level.SEVERE, ex.getMessage(), ex);
    }
  }

  /**
   * Getter of the table.
   * 
   * @return this table
   */
  public Table getTable() {
    if (table == null) {
      table = parse();
    }
    return table;
  }

  /**
   * Setter of the table.
   * 
   * @param table to be set
   */
  public void setTable(Table table) {
    this.table = table;
  }

  /**
   * Parses the table.
   * 
   * @return Table of the reader
   */
  public Table parse() {
    Table tableReturn;
    try {
      tableReturn = getParser().parse(getReader());
    } catch (ParseFailedException ex) {
      LOG.log(Level.SEVERE, ex.getMessage());
      return null;
    }
    return tableReturn;
  }


  @Override
  public String toString() {
    return "DataFile{" + "datafile='" + rawDataFile.toString() + '\'' + ", settingsfile='"
        + settingsfile.toString() + '\'' + '}';
  }
}
