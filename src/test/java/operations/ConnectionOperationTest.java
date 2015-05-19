package operations;

import static org.junit.Assert.*;
import input.Column;
import input.DateColumn;
import input.NumberColumn;
import input.StringColumn;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import parsers.DateValue;
import parsers.NumberValue;
import parsers.StringValue;
import parsers.Value;
import table.DateConversion;
import table.Record;
import table.Table;

/**
 * ConnectionOperationTest class testing the ConnectionOperation class.
 *
 */
public class ConnectionOperationTest {

  Table dataTable;
  Table otherDataTable;
  ConnectionOperation co;

  @Before
  public void setUp() throws Exception {
    // Table with test data
    dataTable = new Table();
    otherDataTable = new Table();

    // Create table with 20 user id's.
    for (int i = 0; i < 20; i++) {
      ArrayList<Column> cols = new ArrayList<Column>();
      cols.add(new NumberColumn("userid"));
      cols.add(new NumberColumn("numberField"));
      cols.add(new StringColumn("stringField"));
      cols.add(new DateColumn("dateField"));
      Record r = new Record(cols, new Value[] { new NumberValue(i), new NumberValue(i * 10),
          new StringValue("String:" + i),
          new DateValue(DateConversion.fromExcelSerialToDate(40000 + i)) });
      dataTable.add(r);
    }

    // Create table with the same dates's but with different columns.
    for (int i = 0; i < 20; i++) {
      ArrayList<Column> cols = new ArrayList<Column>();
      cols.add(new DateColumn("otherDateField"));
      cols.add(new NumberColumn("someNumberBeingEqualToUserID"));
      Record r = new Record(cols, new Value[] {
          new DateValue(DateConversion.fromExcelSerialToDate(40000 + i)), new NumberValue(i) });
      otherDataTable.add(r);
    }

    co = new ConnectionOperation(dataTable);
  }

  @Test
  public void testGetResult() {
    assertEquals(new Table(), co.getResult());
  }

  @Test
  public void testExecute_connect_on_date() {
    assertEquals(false, co.operationParametersSet);
    co.setOperationParameters(otherDataTable, "dateField", "otherDateField");

    assertEquals(true, co.operationParametersSet);
    assertEquals(true, co.execute());

    Table resultTable = new Table();
    // Create table with 20 user id's.
    for (int i = 0; i < 20; i++) {
      ArrayList<Column> cols = new ArrayList<Column>();
      cols.add(new NumberColumn("userid"));
      cols.add(new NumberColumn("numberField"));
      cols.add(new StringColumn("stringField"));
      cols.add(new DateColumn("dateField"));
      cols.add(new DateColumn("otherDateField"));
      cols.add(new NumberColumn("someNumberBeingEqualToUserID"));
      Record r = new Record(cols, new Value[] { new NumberValue(i), new NumberValue(i * 10),
          new StringValue("String:" + i),
          new DateValue(DateConversion.fromExcelSerialToDate(40000 + i)),
          new DateValue(DateConversion.fromExcelSerialToDate(40000 + i)), new NumberValue(i) });
      resultTable.add(r);
    }
    assertEquals(resultTable, co.getResult());
  }

  @Test
  public void testExecute_empty() {
    co.execute();
    assertEquals(new Table(), co.getResult());
  }
}
