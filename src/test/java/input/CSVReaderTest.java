package input;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

/**
 * CSVReaderTest class testing the input.CSVReader class.
 * 
 */
public class CSVReaderTest {
  Settings settings;
  String filepath = "src/test/resources/csvexample.csv";
  String settingsfilepath = "src/test/resources/settings.xml";

  @Before
  public void setUp() throws WrongXMLException {
    settings = XMLReader.readXMLFile(settingsfilepath);
  }

  @Test
  public void filepathTest() throws IOException {
    CSVReader reader = new CSVReader(filepath, settings.getDelimiter());
    assertEquals(reader.getFilepath(), filepath);
    reader.close();
  }

  @Test
  public void delimiterTest() throws IOException {
    CSVReader reader = new CSVReader(filepath, settings.getDelimiter());
    assertEquals(reader.getDelimiter(), settings.getDelimiter());

    String newdelimiter = "\t\t";
    reader.setDelimiter(newdelimiter);
    assertEquals(reader.getDelimiter(), newdelimiter);

    newdelimiter = ";";
    reader.setDelimiter(newdelimiter);
    assertEquals(reader.getDelimiter(), newdelimiter);
    reader.close();
  }

  @Test
  public void csvexampleTest() throws IOException {
    CSVReader reader = new CSVReader(filepath, settings.getDelimiter());

    String row1Actual[] = reader.readRow();
    String row1expected[] = { "appel", "aardappel", "appelmoes" };
    assertArrayEquals(row1expected, row1Actual);

    String row2Actual[] = reader.readRow();
    String row2expected[] = { "", "wortel", "mayonaise" };
    assertArrayEquals(row2expected, row2Actual);

    String row3Actual[] = reader.readRow();
    String row3expected[] = { "banaan", "bloemkool", "" };
    assertArrayEquals(row3expected, row3Actual);

    String row4Actual[] = reader.readRow();
    String row4expected[] = { "\"mango;mango\"", "zuurkool met worst", "appel" };
    assertArrayEquals(row4expected, row4Actual);

    reader.close();

  }

  @Test
  public void tostringTest() throws IOException {
    CSVReader reader = new CSVReader(filepath, settings.getDelimiter());
    assertEquals("Reader [filepath=" + filepath + "]", reader.toString());

    reader.close();
  }
}
