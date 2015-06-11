package net.tudelft.hi.e.input;

import com.opencsv.CSVReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * CsvReader class is a Reader subclass that implements the reading of CSV files.
 *
 */
public class CsvReader extends Reader {
  private char delimiter = ';';

  private final CSVReader reader;

  /**
   * CsvReader constructor creates an CsvReader object that reads CSV files.
   *
   * @param filepath The CSV file path to be read.
   * @throws FileNotFoundException If there is no file found at the designated file path an
   *         FileNotFoundException is thrown.
   */
  public CsvReader(final String filepath) throws FileNotFoundException {
    this(filepath, ";");
  }

  /**
   * CsvReader constructor creates an CsvReader object that reads CSV files.
   *
   * @param filepath
   *          The CSV file path to be read.
   * @param delimiter
   *          the delimiter to use.
   * @throws FileNotFoundException
   *           If there is no file found at the designated file path a FileNotFoundException is
   *           thrown.
   */
  public CsvReader(final String filepath, final String delimiter) throws FileNotFoundException {
    super(filepath);

    setDelimiter(delimiter);
    FileReader fr = new FileReader(filepath);
    reader = new CSVReader(fr, this.delimiter);
  }

  @Override
  public void close() throws IOException {
    reader.close();
  }

  public String getDelimiter() {
    return delimiter + "";
  }

  /**
   * The readRow method reads the next row in the CSV file.
   *
   * @throws IOException
   *           when an I/O error occurs.
   */
  @Override
  public String[] readRow() throws IOException {
    return reader.readNext();
  }

  public void setDelimiter(final String delimiter) {
    this.delimiter = (delimiter.toCharArray().length == 1 ? delimiter.charAt(0) : ';');
  }
}
