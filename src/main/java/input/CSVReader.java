package input;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * CSVReader class is a Reader subclass that implements the reading of CSV files.
 *
 */
public class CSVReader extends Reader {
  private String delimiter = ";";

  private FileReader fr;
  private BufferedReader br;

  /**
   * CSVReader constructor creates an CSVReader object that reads CSV files.
   * 
   * @param filepath
   *          The CSV file path to be read.
   * @throws FileNotFoundException
   *           If there is no file found at the designated file path an FileNotFoundException is
   *           thrown.
   */
  @SuppressFBWarnings(value = "I18N", justification = "Assume unicode")
  public CSVReader(String filepath) throws FileNotFoundException {
    super(filepath);

    fr = new FileReader(filepath);
    br = new BufferedReader(fr);
  }

  public CSVReader(String filepath, String delimiter) throws FileNotFoundException {
    this(filepath);
    this.delimiter = delimiter;
  }

  /**
   * The readRow method reads the next row in the CSV file.
   */
  public String[] readRow() {
    String line = null;
    try {
      line = br.readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (line == null) {
      return null;
    }
    String[] record = line.split(delimiter + "(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
    return record;
  }

  public String getDelimiter() {
    return delimiter;
  }

  public void setDelimiter(String delimiter) {
    this.delimiter = delimiter;
  }

  @Override
  public void close() throws IOException {
    br.close();
  }
}
