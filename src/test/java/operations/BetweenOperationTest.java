package operations;

import static org.junit.Assert.assertEquals;
import input.Settings;

import java.util.ArrayList;
import java.util.Date;

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

  @SuppressWarnings("deprecation")
  @Before
  public void setUp() {

    cols = new ArrayList<Column>();
    cols.add(new NumberColumn("eventtype"));
    cols.add(new DateColumn("date"));
    // Table with test data

    dataTable = new Table();

    dataTable.add(new Record(cols, new Value[] { new StringValue("A"),
        new DateValue(new Date(99, 05, 11)) }));
    dataTable.add(new Record(cols, new Value[] { new StringValue("A"),
        new DateValue(new Date(99, 05, 12)) }));
    dataTable.add(new Record(cols, new Value[] { new StringValue("C"),
        new DateValue(new Date(99, 05, 13)) }));
    dataTable.add(new Record(cols, new Value[] { new StringValue("B"),
        new DateValue(new Date(99, 05, 14)) }));
    dataTable.add(new Record(cols, new Value[] { new StringValue("B"),
        new DateValue(new Date(99, 05, 15)) }));
    dataTable.add(new Record(cols, new Value[] { new StringValue("B"),
        new DateValue(new Date(99, 05, 16)) }));
    dataTable.add(new Record(cols, new Value[] { new StringValue("A"),
        new DateValue(new Date(99, 05, 17)) }));
    dataTable.add(new Record(cols, new Value[] { new StringValue("B"),
        new DateValue(new Date(99, 05, 18)) }));
    dataTable.add(new Record(cols, new Value[] { new StringValue("A"),
        new DateValue(new Date(99, 05, 19)) }));

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
