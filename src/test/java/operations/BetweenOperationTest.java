package operations;

import static org.junit.Assert.assertEquals;
import input.Settings;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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

/**
 * Test for BetweenOperation.
 */
public class BetweenOperationTest {

  Table dataTable;
  Settings settings;
  BetweenOperation lo;
  ArrayList<Column> cols;

  /**
   * Creates dummy table and creates the operation.
   * @throws ParseException
   *         if the date is incorrect
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

    lo = new BetweenOperation(dataTable, "eventtype", "date", new StringValue("A"),
        new StringValue("B"));
  }

  @Test
  public void test() {
    lo.execute();
    Table res = lo.getResult();

    assertEquals(9, res.size());
    assertEquals(new NumberValue(86400 * 2 / (60 * 60)), res.get(1).get("time_before_B"));
    assertEquals(new NumberValue(86400 * 1 / (60 * 60)), res.get(6).get("time_before_B"));

  }

}
