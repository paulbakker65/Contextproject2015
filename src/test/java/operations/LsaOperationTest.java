package operations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import input.Settings;

import operations.lsa.DayGrouper;

import org.junit.Before;
import org.junit.Test;

import table.Record;
import table.Table;
import table.value.Column;
import table.value.DateColumn;
import table.value.DateValue;
import table.value.NumberColumn;
import table.value.NumberValue;
import table.value.StringValue;
import table.value.Value;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Test for LsaOperation LSA stands for Lag Sequential Analysis.
 */
public class LsaOperationTest {

  public static List<Column> rescols = Arrays.asList(new Column[] {new NumberColumn("lag"),
      new NumberColumn("occur")});

  Table dataTable;
  Settings settings;
  LsaOperation lsa;
  List<Column> cols;

  /**
   * Creates a dummy table.
   * 
   * @throws ParseException if data parsing goes wrong
   */
  @Before
  public void setUp() throws ParseException {

    cols = new ArrayList<Column>();
    cols.add(new NumberColumn("eventtype"));
    cols.add(new DateColumn("date"));
    // Table with test data

    dataTable = new Table();

    dataTable.add(new Record(cols, new Value[] {new StringValue("A"),
        new DateValue(new SimpleDateFormat("ddMMyy").parse("120599"))}));
    dataTable.add(new Record(cols, new Value[] {new StringValue("A"),
        new DateValue(new SimpleDateFormat("ddMMyy").parse("130599"))}));
    dataTable.add(new Record(cols, new Value[] {new StringValue("C"),
        new DateValue(new SimpleDateFormat("ddMMyy").parse("140599"))}));
    dataTable.add(new Record(cols, new Value[] {new StringValue("B"),
        new DateValue(new SimpleDateFormat("ddMMyy").parse("150599"))}));
    dataTable.add(new Record(cols, new Value[] {new StringValue("B"),
        new DateValue(new SimpleDateFormat("ddMMyy").parse("160599"))}));
    dataTable.add(new Record(cols, new Value[] {new StringValue("B"),
        new DateValue(new SimpleDateFormat("ddMMyy").parse("170599"))}));
    dataTable.add(new Record(cols, new Value[] {new StringValue("A"),
        new DateValue(new SimpleDateFormat("ddMMyy").parse("180599"))}));
    dataTable.add(new Record(cols, new Value[] {new StringValue("B"),
        new DateValue(new SimpleDateFormat("ddMMyy").parse("190599"))}));
    dataTable.add(new Record(cols, new Value[] {new StringValue("A"),
        new DateValue(new SimpleDateFormat("ddMMyy").parse("200599"))}));

    settings = new Settings();
    settings.getColumns().addAll(cols);

  }

  @Test
  public void testDayGrouperSameDay() throws ParseException {
    final GregorianCalendar calendar =
        new DateValue(new SimpleDateFormat("yyMMdd").parse("121110")).getValue();
    final GregorianCalendar calendar2 =
        new DateValue(new SimpleDateFormat("yyMMdd").parse("121109")).getValue();
    final GregorianCalendar calendar3 =
        new DateValue(new SimpleDateFormat("yyMMdd").parse("121010")).getValue();
    final GregorianCalendar calendar4 =
        new DateValue(new SimpleDateFormat("yyMMdd").parse("111110")).getValue();

    assertTrue(DayGrouper.sameDay(calendar, calendar));
    assertFalse(DayGrouper.sameDay(calendar, calendar2));
    assertFalse(DayGrouper.sameDay(calendar, calendar3));
    assertFalse(DayGrouper.sameDay(calendar, calendar4));
  }

  @Test
  public void testDayLag() {
    lsa =
        new LsaOperation(dataTable, "eventtype", -2, 3, new StringValue("A"), new StringValue("B"),
            new DayGrouper("date"));
    lsa.execute();
    final Table res = lsa.getResult();

    assertEquals(new NumberValue(1), res.get(0).get("occur"));
    assertEquals(new NumberValue(2), res.get(1).get("occur"));
    assertEquals(new NumberValue(0), res.get(2).get("occur"));
    assertEquals(new NumberValue(1), res.get(3).get("occur"));
    assertEquals(new NumberValue(1), res.get(4).get("occur"));
  }

  @Test
  public void testRecordLag() {
    lsa =
        new LsaOperation(dataTable, "eventtype", -2, 3, new StringValue("A"), new StringValue("B"));
    lsa.execute();
    final Table res = lsa.getResult();

    // System.out.println(res);
    assertEquals(new NumberValue(1), res.get(0).get("occur"));
    assertEquals(new NumberValue(2), res.get(1).get("occur"));
    assertEquals(new NumberValue(0), res.get(2).get("occur"));
    assertEquals(new NumberValue(1), res.get(3).get("occur"));
    assertEquals(new NumberValue(1), res.get(4).get("occur"));
  }

}
