package input;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * CsvReaderTest class testing the input.CsvReader class.
 * 
 */
public class CsvReaderTest {
  Settings settings;
  String filepath = "src/test/resources/csvexample.csv";
  String settingsfilepath = "src/test/resources/settings.xml";

  @Test
  public void csvexampleTest() throws IOException {
    final CsvReader reader = new CsvReader(filepath, settings.getDelimiter());

    final String[] row1Actual = reader.readRow();
    final String[] row1expected = { "appel", "aardappel", "appelmoes" };
    assertArrayEquals(row1expected, row1Actual);

    final String[] row2Actual = reader.readRow();
    final String[] row2expected = { "", "wortel", "mayonaise" };
    assertArrayEquals(row2expected, row2Actual);

    final String[] row3Actual = reader.readRow();
    final String[] row3expected = { "banaan", "bloemkool", "" };
    assertArrayEquals(row3expected, row3Actual);

    final String[] row4Actual = reader.readRow();
    final String[] row4expected = { "mango;mango", "zuurkool met worst", "appel" };
    assertArrayEquals(row4expected, row4Actual);

    reader.close();

  }

  @Test
  public void delimiterTest() throws IOException {
    final CsvReader reader = new CsvReader(filepath, settings.getDelimiter());
    assertEquals(reader.getDelimiter(), settings.getDelimiter());

    String newdelimiter = "\t";
    reader.setDelimiter(newdelimiter);
    assertEquals(reader.getDelimiter(), newdelimiter);

    newdelimiter = ";";
    reader.setDelimiter(newdelimiter);
    assertEquals(reader.getDelimiter(), newdelimiter);
    reader.close();
  }

  @Test
  public void filepathTest() throws IOException {
    final CsvReader reader = new CsvReader(filepath, settings.getDelimiter());
    assertEquals(reader.getFilepath(), filepath);
    reader.close();
  }

  @Before
  public void setUp() throws WrongXmlException {
    settings = XmlReader.readXmlFile(settingsfilepath);
  }

  @Test
  public void tostringTest() throws IOException {
    final CsvReader reader = new CsvReader(filepath, settings.getDelimiter());
    assertEquals("Reader [filepath=" + filepath + "]", reader.toString());

    reader.close();
  }
}
