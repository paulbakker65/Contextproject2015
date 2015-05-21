package operations;

import static org.junit.Assert.*;
import input.Column;
import input.DateColumn;
import input.NumberColumn;
import input.StringColumn;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import main.DataFile;
import main.Input;

import org.junit.Before;
import org.junit.Test;

import parsers.DateValue;
import parsers.NullValue;
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
  
  Table t1;
  Table t2;

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
    
    
    String path = "src/test/resources/";
    File file1 = new File(path + "test1.csv");
    File file2 = new File(path + "test2.csv");
    File settings1 = new File(path + "test1.xml");
    File settings2 = new File(path + "test2.xml");
    
    Input.clean();
    Input.addDataFile(file1, settings1);
    Input.addDataFile(file2, settings2);
    DataFile f1 = Input.getFiles().get(0);
    DataFile f2 = Input.getFiles().get(1);
    t1 = f1.getParser().parse(f1.getReader());
    t2 = f2.getParser().parse(f2.getReader());

  }
  
  /**
   * If this test fails, there is something wrong with the required test files.
   */
  @Test
  public void testTestFiles(){
    assertEquals(10, t1.size());
    assertEquals(10, t2.size());
  }
 
  /**
   * Executes the connection operation and checks if the output of the test is correct.
   */
  public Table execAndCheck(){
    assertTrue(co.execute());
    
    Table result = co.getResult();
    assertEquals(20, result.size());
    double previous = 0;
    for (Record v : result){
      Value value = v.get("number1");
      if (value.isNull()){
        value = v.get("number2");
      }
      NumberValue number = (NumberValue) value;
      Double current = number.getValue();
      assertTrue(current >= previous);
      previous = number.getValue();
    }
    return result;
  }
  
  /**
   * Test connection on 2 NumberValue fields.
   */
  @Test
  public void testConnectNumberValue(){
    co = new ConnectionOperation(t1);
    co.setOperationParameters(t2, "number1", "number2");
    
    Table result = execAndCheck();
    
    String[] columns = {"number1", "date1", "date2", "string1", "string2", "null1"};
    assertTrue(result.get(0).keySet().containsAll(Arrays.asList(columns)));
  }
  
  /**
   * Test connection on 2 DateValueValue fields.
   */
  @Test
  public void testConnectDateValue(){
    co = new ConnectionOperation(t1);
    co.setOperationParameters(t2, "date1", "date2");
    
    Table result = execAndCheck();
    
    String[] columns = {"number1", "number2", "date1", "string1", "string2", "null1"};
    assertTrue(result.get(0).keySet().containsAll(Arrays.asList(columns)));
  }
  
  /**
   * Test connection on 2 StringValue fields.
   */
  @Test
  public void testConnectStringValue(){
    co = new ConnectionOperation(t1);
    co.setOperationParameters(t2, "string1", "string2");
    
    Table result = execAndCheck();
    
    String[] columns = {"number1", "number2", "date1", "date2", "string1", "null1"};
    assertTrue(result.get(0).keySet().containsAll(Arrays.asList(columns)));    
  }
  
  /**
   * Test when inputDataset table contains no records.
   */
  @Test
  public void testEmptyInputTable(){
    co = new ConnectionOperation(new Table(), t2, "number1", "number2");
    assertTrue(co.execute());
    assertEquals(t2, co.getResult());
  }
  
  /**
   * Tests when the otherTable contains no records.
   */
  @Test
  public void testEmptyOtherTable(){
    co = new ConnectionOperation(t2, new Table(), "number1", "number2");
    assertTrue(co.execute());
    assertEquals(t2, co.getResult());
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
      cols.add(new NumberColumn("someNumberBeingEqualToUserID"));
      Record r = new Record(cols, new Value[] {
          new NumberValue(i),
          new NumberValue(i * 10),
          new StringValue("String:" + i),
          new DateValue(DateConversion.fromExcelSerialToDate(40000 + i)),
          new NullValue()
          });
      
      resultTable.add(r);
      Record r2 = new Record(cols, new Value[] {
          new NullValue(),
          new NullValue(),
          new NullValue(),
          new DateValue(DateConversion.fromExcelSerialToDate(40000 + i)),
          new NumberValue(i)
          });
      resultTable.add(r2);
    }

    String[] columns = {"userid", "numberField", "stringField", "dateField", "someNumberBeingEqualToUserID"};
    assertTrue(resultTable.get(0).keySet().containsAll(Arrays.asList(columns)));
    assertEquals(resultTable, co.getResult());
  }

  @Test
  public void testExecute_empty() {
    co.execute();
    assertEquals(new Table(), co.getResult());
  }
}