package net.tudelft.hi.e.computation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import net.tudelft.hi.e.computation.BetweenOperation;
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
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

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
   *
   * @throws ParseException if the date is incorrect
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

    lo =
        new BetweenOperation(dataTable, "eventtype", "date", new StringValue("A"), new StringValue(
            "B"));
  }

  @Test
  public void test() {
    lo.execute();
    final Table res = lo.getResult();

    assertEquals(9, res.size());
    assertEquals(new NumberValue(86400 * 2 / (60 * 60)), res.get(1).get("time_between"));
    assertEquals(new NumberValue(86400 * 1 / (60 * 60)), res.get(6).get("time_between"));

  }

}
