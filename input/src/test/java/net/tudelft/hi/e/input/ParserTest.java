package net.tudelft.hi.e.input;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import net.tudelft.hi.e.common.exceptions.ParseFailedException;
import net.tudelft.hi.e.data.Column;
import net.tudelft.hi.e.data.DateColumn;
import net.tudelft.hi.e.data.DateValue;
import net.tudelft.hi.e.data.NumberColumn;
import net.tudelft.hi.e.data.Record;
import net.tudelft.hi.e.data.StringColumn;
import net.tudelft.hi.e.data.StringValue;
import net.tudelft.hi.e.data.Table;
import net.tudelft.hi.e.data.Value;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * ParserTest class testing the table.Parser class.
 *
 */
public class ParserTest {
  private final Reader reader = Mockito.mock(Reader.class);
  private String delimiter;
  private List<String> testFile;
  private int curIndex;

  /**
   * @return row of the test file.
   */
  public String[] getRow() {
    if (curIndex >= testFile.size()) {
      return null;
    }

    return testFile.get(curIndex++).split(delimiter);
  }

  /**
   * Sets up mockito.
   *
   * @throws IOException if file not found
   */
  @Before
  public void setUp() throws IOException {
    testFile = new ArrayList<String>();
    curIndex = 0;
    when(reader.readRow()).thenAnswer(new Answer<String[]>() {
      @Override
      public String[] answer(final InvocationOnMock invocation) {
        return getRow();
      }
    });
  }

  @Test
  public void testConstructor() {
    final Settings settings = new Settings();
    final ArrayList<Column> columns =
        new ArrayList<Column>(Arrays.asList(new NumberColumn("number")));
    settings.setColumns(columns);
    final Parser parser = new Parser(settings);

    assertNotNull(parser);
    assertEquals(settings, parser.getSettings());
    assertEquals(columns, parser.getColumns());
    assertEquals(columns.size(), parser.getNumberOfColumns());
  }

  @Test
  public void testParseCorrect() throws IOException, ParseFailedException {
    final ArrayList<Column> columns =
        new ArrayList<Column>(Arrays.asList(new StringColumn("groente"), new StringColumn("saus")));
    final Table expected = new Table();
    expected.add(new Record(columns, new Value[] {new StringValue("bloemkool"),
        new StringValue("mayonaise")}));
    expected.add(new Record(columns, new Value[] {new StringValue("spruitjes"),
        new StringValue("ketchup")}));

    final Settings settings = new Settings();
    settings.setName("");
    settings.setDelimiter(",");
    settings.setStartLine(1);
    settings.setColumns(columns);

    testFile.add("bloemkool,mayonaise");
    testFile.add("spruitjes,ketchup");
    delimiter = ",";

    final Parser parser = new Parser(settings);
    final Table actual = parser.parse(reader);

    assertEquals(expected, actual);
    verify(reader, times(testFile.size() + 1)).readRow();
  }

  @Test
  public void testParseCorrectLastLineTooShort() throws IOException, ParseFailedException {
    final ArrayList<Column> columns =
        new ArrayList<Column>(Arrays.asList(new StringColumn("groente"), new StringColumn("saus")));
    final Table expected = new Table();
    expected.add(new Record(columns, new Value[] {new StringValue("bloemkool"),
        new StringValue("mayonaise")}));
    expected.add(new Record(columns, new Value[] {new StringValue("spruitjes"),
        new StringValue("ketchup")}));

    final Settings settings = new Settings();
    settings.setDelimiter(",");
    settings.setStartLine(2);
    settings.setColumns(columns);
    settings.setName("");

    testFile.add("onzin, dit wordt overgeslagen {");
    testFile.add("bloemkool,mayonaise");
    testFile.add("spruitjes,ketchup");
    testFile.add("}");
    delimiter = ",";

    final Parser parser = new Parser(settings);
    final Table actual = parser.parse(reader);

    assertEquals(expected, actual);
    verify(reader, times(testFile.size())).readRow();
  }

  @Test
  public void testParseCorrectSkipFirstLine() throws IOException, ParseFailedException {
    final ArrayList<Column> columns =
        new ArrayList<Column>(Arrays.asList(new StringColumn("groente"), new StringColumn("saus")));
    final Table expected = new Table();
    expected.add(new Record(columns, new Value[] {new StringValue("bloemkool"),
        new StringValue("mayonaise")}));
    expected.add(new Record(columns, new Value[] {new StringValue("spruitjes"),
        new StringValue("ketchup")}));

    final Settings settings = new Settings();
    settings.setDelimiter(",");
    settings.setStartLine(2);
    settings.setColumns(columns);
    settings.setName("");

    testFile.add("onzin, dit wordt overgeslagen");
    testFile.add("bloemkool,mayonaise");
    testFile.add("spruitjes,ketchup");
    delimiter = ",";

    final Parser parser = new Parser(settings);
    final Table actual = parser.parse(reader);

    assertEquals(expected, actual);
    verify(reader, times(testFile.size() + 1)).readRow();
  }

  @Test
  public void testParseCorrectTimeDateLinks() throws IOException, ParseException,
      ParseFailedException {
    final ArrayList<Column> columns =
        new ArrayList<Column>(Arrays.asList(new DateColumn("time", "HH:mm", "date"),
            new DateColumn("date", "ddMMyy")));
    final Table expected = new Table();
    final GregorianCalendar dateCalendar = new GregorianCalendar();
    dateCalendar.setTime(new SimpleDateFormat("ddMMyy").parse("311214"));
    final DateValue dateValue = new DateValue(dateCalendar, new DateColumn("date", "ddMMyy", null));

    final GregorianCalendar timeCalendar = new GregorianCalendar();
    timeCalendar.setTime(new SimpleDateFormat("HH:mm").parse("10:20"));
    final DateValue timeValue =
        new DateValue(timeCalendar, new DateColumn("time", "HH:mm", "date"));

    dateValue.addTime(timeValue.getValue());
    timeValue.setValue(dateValue.getValue());

    expected.add(new Record(columns, new Value[] {timeValue, dateValue}, "tableName"));

    final Settings settings = new Settings();
    settings.setDelimiter(",");
    settings.setStartLine(1);
    settings.setColumns(columns);
    settings.setName("tableName");

    testFile.add("10:20,311214");
    delimiter = ",";

    final Parser parser = new Parser(settings);
    final Table actual = parser.parse(reader);

    assertEquals(expected, actual);
    verify(reader, times(testFile.size() + 1)).readRow();
  }
}
