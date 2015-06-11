package net.tudelft.hi.e.input;

import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;
import net.tudelft.hi.e.data.DateValue;
import net.tudelft.hi.e.data.Table;
import net.tudelft.hi.e.input.DataFile;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * A test that runs a real excel file and checks if the parsing succeeded.
 */
public class ExcelReaderTest {

  @Before
  public void setUp() throws Exception {}

  @Test
  public void test() throws Exception {
    final String datafilename = "src/test/resources/website_test2.xlsx";
    final String settingsfilename = "src/test/resources/settings_website.xml";

    final DataFile df = new DataFile(new File(datafilename), new File(settingsfilename));

    final Table table = df.getParser().parse(df.getReader());

    assertEquals(57, table.size());

    assertEquals("Ochtend", table.get(56).get("Moment").toString());

    final GregorianCalendar date = ((DateValue) table.get(56).get("Date")).getValue();

    assertEquals(2011, date.get(Calendar.YEAR));
    assertEquals(5, date.get(Calendar.DAY_OF_MONTH));
    assertEquals(5, date.get(Calendar.MONTH));

  }
}
