package operations;

import static org.junit.Assert.assertEquals;
import input.Column;
import input.DateColumn;
import input.NumberColumn;
import input.Settings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import parsers.DateValue;
import parsers.NumberValue;
import parsers.StringValue;
import parsers.Value;
import table.Record;
import table.Table;

/**
 * Test for LSAOperation
 * LSA stands for Lag Sequential Analysis.
 */
public class LSAOperationTest {

  public static List<Column> rescols = Arrays.asList(new Column[] { new NumberColumn("lag"),
      new NumberColumn("occur") });
  
  
  Table dataTable;
  Settings settings;
  LSAOperation lsa;
  List<Column> cols;
  
  
  @SuppressWarnings("deprecation")
  @Before
  public void setUp() {
    
    
    cols = new ArrayList<Column>();
    cols.add(new NumberColumn("eventtype"));
    cols.add(new DateColumn("date"));
    // Table with test data

    dataTable = new Table();

    dataTable.add(new Record(cols, new Value[] {new StringValue("A"), new DateValue(new Date(99, 05, 11))}));
    dataTable.add(new Record(cols, new Value[] {new StringValue("A"), new DateValue(new Date(99, 05, 12))}));
    dataTable.add(new Record(cols, new Value[] {new StringValue("C"), new DateValue(new Date(99, 05, 13))}));
    dataTable.add(new Record(cols, new Value[] {new StringValue("B"), new DateValue(new Date(99, 05, 14))}));
    dataTable.add(new Record(cols, new Value[] {new StringValue("B"), new DateValue(new Date(99, 05, 15))}));
    dataTable.add(new Record(cols, new Value[] {new StringValue("B"), new DateValue(new Date(99, 05, 16))}));
    dataTable.add(new Record(cols, new Value[] {new StringValue("A"), new DateValue(new Date(99, 05, 17))}));
    dataTable.add(new Record(cols, new Value[] {new StringValue("B"), new DateValue(new Date(99, 05, 18))}));
    dataTable.add(new Record(cols, new Value[] {new StringValue("A"), new DateValue(new Date(99, 05, 19))}));
    
    settings = new Settings();
    settings.getColumns().addAll(cols);
    

    lsa = new LSAOperation(dataTable, "eventtype", -2, 3, new StringValue("A"), new StringValue("B"));
  }
  
  @Test
  public void test() {
    lsa.execute();
    Table res = lsa.getResult();
    
    //System.out.println(res);
    assertEquals(new NumberValue(1), res.get(0).get("occur"));
    assertEquals(new NumberValue(2), res.get(1).get("occur"));
    assertEquals(new NumberValue(0), res.get(2).get("occur"));
    assertEquals(new NumberValue(1), res.get(3).get("occur"));
    assertEquals(new NumberValue(1), res.get(4).get("occur"));
  }

}
