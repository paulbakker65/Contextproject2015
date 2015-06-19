package net.tudelft.hi.e.computation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import net.tudelft.hi.e.data.Column;
import net.tudelft.hi.e.data.DateColumn;
import net.tudelft.hi.e.data.DateValue;
import net.tudelft.hi.e.data.NumberColumn;
import net.tudelft.hi.e.data.NumberValue;
import net.tudelft.hi.e.data.Record;
import net.tudelft.hi.e.data.StringValue;
import net.tudelft.hi.e.data.Table;
import net.tudelft.hi.e.data.Value;
import net.tudelft.hi.e.input.Settings;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

/**
 * Test for LsaOperation LSA stands for Lag Sequential Analysis.
 */
public class LsaOperationTest extends LsaOperation {

  /**
   * Testconstructor, so that LagTable can be tested.
   */
  public LsaOperationTest() {
    this(new Table(), null, 0, 0, null, null);
  }
  
  private LsaOperationTest(Table inputDataset, String eventcol, int from, int to, Value key,
      Value target) {
    super(inputDataset, eventcol, from, to, key, target);
  }

  public static List<Column> rescols = Arrays.asList(new Column[] { new NumberColumn("lag"),
      new NumberColumn("occur") });

  Table dataTable;
  Settings settings;
  LsaOperation lsa;
  List<Column> cols;

  /**
   * Creates a dummy table.
   *
   * @throws ParseException
   *           if data parsing goes wrong
   */
  @Before
  public void setUp() throws ParseException {

    cols = new ArrayList<Column>();
    cols.add(new NumberColumn("eventtype"));
    cols.add(new DateColumn("date"));
    // Table with test data

    dataTable = new Table();

    dataTable.add(new Record(cols, new Value[] { new StringValue("A"),
        new DateValue(new SimpleDateFormat("ddMMyy").parse("120599")) }));
    dataTable.add(new Record(cols, new Value[] { new StringValue("A"),
        new DateValue(new SimpleDateFormat("ddMMyy").parse("130599")) }));
    dataTable.add(new Record(cols, new Value[] { new StringValue("C"),
        new DateValue(new SimpleDateFormat("ddMMyy").parse("140599")) }));
    dataTable.add(new Record(cols, new Value[] { new StringValue("B"),
        new DateValue(new SimpleDateFormat("ddMMyy").parse("150599")) }));
    dataTable.add(new Record(cols, new Value[] { new StringValue("B"),
        new DateValue(new SimpleDateFormat("ddMMyy").parse("160599")) }));
    dataTable.add(new Record(cols, new Value[] { new StringValue("B"),
        new DateValue(new SimpleDateFormat("ddMMyy").parse("170599")) }));
    dataTable.add(new Record(cols, new Value[] { new StringValue("A"),
        new DateValue(new SimpleDateFormat("ddMMyy").parse("180599")) }));
    dataTable.add(new Record(cols, new Value[] { new StringValue("B"),
        new DateValue(new SimpleDateFormat("ddMMyy").parse("190599")) }));
    dataTable.add(new Record(cols, new Value[] { new StringValue("A"),
        new DateValue(new SimpleDateFormat("ddMMyy").parse("200599")) }));

    settings = new Settings();
    settings.getColumns().addAll(cols);
  }

  @Test
  public void testDayGrouperSameDay() throws ParseException {
    final GregorianCalendar calendar = new DateValue(new SimpleDateFormat("yyMMdd").parse("121110"))
        .getValue();
    final GregorianCalendar calendar2 = new DateValue(
        new SimpleDateFormat("yyMMdd").parse("121109")).getValue();
    final GregorianCalendar calendar3 = new DateValue(
        new SimpleDateFormat("yyMMdd").parse("121010")).getValue();
    final GregorianCalendar calendar4 = new DateValue(
        new SimpleDateFormat("yyMMdd").parse("111110")).getValue();

    assertTrue(DayGrouper.sameDay(calendar, calendar));
    assertFalse(DayGrouper.sameDay(calendar, calendar2));
    assertFalse(DayGrouper.sameDay(calendar, calendar3));
    assertFalse(DayGrouper.sameDay(calendar, calendar4));
  }

  @Test
  public void testDayLag() {
    lsa = new LsaOperation(dataTable, "eventtype", -2, 3, new StringValue("A"),
        new StringValue("B"), new DayGrouper("date"));
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
    lsa = new LsaOperation(dataTable, "eventtype", -2, 3, new StringValue("A"),
        new StringValue("B"));
    lsa.execute();
    final Table res = lsa.getResult();

    // System.out.println(res);
    assertEquals(new NumberValue(1), res.get(0).get("occur"));
    assertEquals(new NumberValue(2), res.get(1).get("occur"));
    assertEquals(new NumberValue(0), res.get(2).get("occur"));
    assertEquals(new NumberValue(1), res.get(3).get("occur"));
    assertEquals(new NumberValue(1), res.get(4).get("occur"));
  }

  @Test
  public void equalityTest() {
    LsaOperation o1 = new LsaOperation(dataTable, "0", 1, 2, new NumberValue(3),
        new NumberValue(4), new DayGrouper("5"));
    LsaOperation o2 = new LsaOperation(dataTable, "0", 1, 2, new NumberValue(3),
        new NumberValue(4), new DayGrouper("5"));
    assertEquals(o1, o2);
  }
  
  @Test
  public void equalInstanceTest() {
    LsaOperation o1 = new LsaOperation(dataTable, "0", 1, 2, new NumberValue(3),
        new NumberValue(4), new DayGrouper("5"));
    assertTrue(o1.equals(o1));
  }
  
  @Test
  public void equalityNullTest() {
    LsaOperation o1 = new LsaOperation(dataTable, "0", 1, 2, new NumberValue(3),
        new NumberValue(4), new DayGrouper("5"));
    assertFalse(o1.equals(null));
  }
  
  @Test
  public void equalityStringTest() {
    LsaOperation o1 = new LsaOperation(dataTable, "0", 1, 2, new NumberValue(3),
        new NumberValue(4), new DayGrouper("5"));
    assertFalse(o1.equals("foobar"));
  }
  
  @Test
  public void wrongPropertyTest() {
    LsaOperation o1 = new LsaOperation(dataTable, "0", 1, 2, new NumberValue(3),
        new NumberValue(4), new DayGrouper("5"));
    LsaOperation o2 = new LsaOperation(dataTable, "0", 1, 2, new NumberValue(3),
        new NumberValue(4), new DayGrouper("6"));
    assertFalse(o1.equals(o2));
  }
  
  @Test
  public void hashEqualsTest() {
    LsaOperation o1 = new LsaOperation(dataTable, "0", 1, 2, new NumberValue(3),
        new NumberValue(4), new DayGrouper("5"));
    LsaOperation o2 = new LsaOperation(dataTable, "0", 1, 2, new NumberValue(3),
        new NumberValue(4), new DayGrouper("5"));
    assertEquals(o1.hashCode(),o2.hashCode());
  }
  
  @Test
  public void testLagTableEquals() {
    final LagTable table = new LagTable(0, 5);
    final LagTable tableSame = new LagTable(0, 5);
    final LagTable tableNotSame = new LagTable(1, 4);
    final String otherClass = "";
    
    assertEquals(table, table);
    assertEquals(table, tableSame);
    assertNotEquals(table, tableNotSame);
    assertNotEquals(table, null);
    assertNotEquals(table, otherClass);
  }
  
  @Test
  public void testLagTableHashCode() {
    final LagTable table = new LagTable(0, 1);
    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    map.put(0, 0);
    assertEquals(89 + map.hashCode(), table.hashCode());
  }

}
