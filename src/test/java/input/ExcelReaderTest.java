package input;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import table.value.DateValue;
import table.Table;

/**
 * A test that runs a real excel file and checks if the parsing succeeded.
 */
public class ExcelReaderTest {

  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void test() throws Exception {
    String datafilename = "src/test/resources/website_test2.xlsx";
    String settingsfilename = "src/test/resources/settings_website.xml";

    DataFile df = new DataFile(new File(datafilename), new File(settingsfilename));

    Table t = df.getParser().parse(df.getReader());

    assertEquals(57, t.size());

    assertEquals("Ochtend", t.get(56).get("Moment").toString());
    
    GregorianCalendar d = ((DateValue) t.get(56).get("Date")).getValue();

    assertEquals(2011, d.get(GregorianCalendar.YEAR));
    assertEquals(5, d.get(GregorianCalendar.DAY_OF_MONTH));
    assertEquals(5, d.get(GregorianCalendar.MONTH));

  }
}
