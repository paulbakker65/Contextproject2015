package net.tudelft.hi.e.input;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * CsvReader class is a Reader subclass that implements the reading of CSV files.
 *
 */
public class CsvReader extends Reader {
  private String delimiter = ";";

  private final FileReader fr;
  private final BufferedReader br;

  /**
   * CsvReader constructor creates an CsvReader object that reads CSV files.
   *
   * @param filepath The CSV file path to be read.
   * @throws FileNotFoundException If there is no file found at the designated file path an
   *         FileNotFoundException is thrown.
   */
  public CsvReader(final String filepath) throws FileNotFoundException {
    super(filepath);
    fr = new FileReader(filepath);
    br = new BufferedReader(fr);
  }

  public CsvReader(final String filepath, final String delimiter) throws FileNotFoundException {
    this(filepath);
    this.delimiter = delimiter;
  }

  @Override
  public void close() throws IOException {
    br.close();
  }

  public String getDelimiter() {
    return delimiter;
  }

  /**
   * The readRow method reads the next row in the CSV file.
   */
  @Override
  public String[] readRow() throws IOException {
    String line = null;
    line = br.readLine();
    if (line == null) {
      return new String[0];
    }
    return line.split(delimiter + "(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
  }

  public void setDelimiter(final String delimiter) {
    this.delimiter = delimiter;
  }
}
