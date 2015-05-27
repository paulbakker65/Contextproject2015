package operations.coding;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import operations.patterns.PatternFactory;
import operations.patterns.PatternFactory.PatternEnum;
import operations.patterns.SingleOccurrencePattern;
import operations.patterns.SingleOccurrenceValuePattern;

import org.junit.Before;
import org.junit.Test;

import table.Record;
import table.Table;
import table.value.Column;
import table.value.NullValue;
import table.value.NumberColumn;
import table.value.NumberValue;
import table.value.StringColumn;
import table.value.StringValue;
import table.value.Value;

/**
 * Class for testing SingleOccurrenceValuePattern.
 */
public class SingleOccurrenceValuePatternTest {
  private Table table;
  private ArrayList<Column> cols;
  
  @Before
  public void setUp() {
    table = new Table();
    cols = new ArrayList<Column>();
    cols.add(new StringColumn("CMI_id"));
    cols.add(new NumberColumn("KAAI"));
    cols.add(new StringColumn("Useless"));
    cols.add(new StringColumn("Measurement"));
  }
  
  @Test
  public void testFindPatternSingleRecord() {
    Record record = new Record(cols, new Value[]{new NullValue(), new NullValue(), new NumberValue(1), new StringValue("Crea")});
    table.add(record);
    
    SingleOccurrenceValuePattern pattern = new SingleOccurrenceValuePattern("Useless", new NumberValue(1));
    Table event = new Table(); 
    assertTrue(pattern.findPattern(table, 0, event));
    assertEquals(table, event);
  }
  
   @Test
   public void testFindPatternSingleRecordNotFound() {
   Record record = new Record(cols, new Value[]{new NullValue(), new NullValue(), new NumberValue(1), new StringValue("Crea")});
   table.add(record);
  
   SingleOccurrenceValuePattern pattern = new SingleOccurrenceValuePattern("CMI_id", new NumberValue(3556));
   Table event = new Table();
   assertFalse(pattern.findPattern(table, 0, event));
   }
  
  @Test
   public void testFindPatternTwoDifferentRecords() {
   Record record = new Record(cols, new Value[]{new NullValue(), new NullValue(), new NumberValue(1), new StringValue("Crea")});
   table.add(record);
   record = new Record(cols, new Value[]{new NumberValue(23423), new NullValue(), new NullValue(), new StringValue("Kreatinine")});
   table.add(record);
  
   SingleOccurrenceValuePattern pattern = new SingleOccurrenceValuePattern("Useless", new NumberValue(1), new SingleOccurrenceValuePattern("CMI_id", new NumberValue(23423)));
   Table event = new Table();
   assertTrue(pattern.findPattern(table, 0, event));
   assertEquals(2, event.size());
  
   event = new Table();
   assertFalse(pattern.findPattern(table, 1, event));
   }
  
  @Test
  public void testFindActionDonePattern() {
  Record record = new Record(cols, new Value[]{new NullValue(), new NullValue(), new NumberValue(1), new StringValue("Crea")});
  Record record1 = new Record(cols, new Value[]{new NullValue(), new NullValue(), new NumberValue(1), new StringValue("Crea")});
  Record record2 = new Record(cols, new Value[]{new NumberValue(2), new NullValue(), new NullValue(), new StringValue("Gewicht")});
  Record record3 = new Record(cols, new Value[]{new NumberValue(2), new NullValue(), new NullValue(), new StringValue("Bloeddruk")});
  Record record4 = new Record(cols, new Value[]{new NumberValue(2), new NullValue(), new NullValue(), new StringValue("Nog wat")});
  Record record5 = new Record(cols, new Value[]{new NumberValue(2), new NullValue(), new NullValue(), new StringValue("Vergeet dit niet")});
  Record record6 = new Record(cols, new Value[]{new NumberValue(2), new NumberValue(1), new NullValue(), new StringValue("Kreatinine (stat)")});
  Record record7 = new Record(cols, new Value[]{new NumberValue(2), new NullValue(), new NullValue(), new StringValue("Kreatinine2 (stat)")});
  table.add(record2);
  table.add(record);
  table.add(record1);
  table.add(record2);
  table.add(record3);
  table.add(record4);
  table.add(record5);
  table.add(record6);
  table.add(record7);
  table.add(record);
  
  PatternFactory pf = new PatternFactory(PatternEnum.ACTION_DONE);
  Table event = new Table();
  pf.getPattern().findPattern(table, 1, event);
  assertEquals(8, event.size());
  
  }
  

  
  
  
  // @Test
  // public void testFindPatternTwoDifferentRecordsSecondPattern() {
  // Record record = new Record(cols, new Value[]{new StringValue("Crea"), new NullValue(), new
  // NullValue()});
  // table.add(record);
  // record = new Record(cols, new Value[]{new NullValue(), new NumberValue(140), new NullValue()});
  // table.add(record);
  //
  // SingleOccurrencePattern pattern = new SingleOccurrencePattern("WebsiteValue");
  // Table event = new Table();
  // assertTrue(pattern.findPattern(table, 1, event));
  // assertEquals(1, event.size());
  // }
  //
  // @Test
  // public void testFindPatternTwoSameRecords() {
  // Record record = new Record(cols, new Value[]{new StringValue("Crea"), new NullValue(), new
  // NullValue()});
  // table.add(record);
  // record = new Record(cols, new Value[]{new StringValue("Crea2"), new NullValue(), new
  // NullValue()});
  // table.add(record);
  //
  // SingleOccurrencePattern pattern = new SingleOccurrencePattern("StatSensor");
  // Table event = new Table();
  // assertFalse(pattern.findPattern(table, 0, event));
  //
  // event = new Table();
  // assertFalse(pattern.findPattern(table, 1, event));
  // }
  //
  // @Test
  // public void testFindPatternEndOfTable() {
  // Record record = new Record(cols, new Value[]{new StringValue("Crea"), new NullValue(), new
  // NullValue()});
  // table.add(record);
  // record = new Record(cols, new Value[]{new StringValue("Crea2"), new NullValue(), new
  // NullValue()});
  // table.add(record);
  //
  // SingleOccurrencePattern pattern = new SingleOccurrencePattern("StatSensor");
  // Table event = new Table();
  // assertFalse(pattern.findPattern(table, 2, event));
  // assertFalse(pattern.findPattern(table, 3, event));
  // }
  //
  // @Test
  // public void testFindPatternTwoPatterns() {
  // Record record = new Record(cols, new Value[]{new StringValue("Crea"), new NullValue(), new
  // NullValue()});
  // table.add(record);
  // record = new Record(cols, new Value[]{new StringValue("Crea2"), new NullValue(), new
  // NullValue()});
  // table.add(record);
  //
  // SingleOccurrencePattern endPattern = new SingleOccurrencePattern("StatSensor");
  // SingleOccurrencePattern firstPattern = new SingleOccurrencePattern("StatSensor", endPattern);
  // Table event = new Table();
  // assertTrue(firstPattern.findPattern(table, 0, event));
  // assertEquals(table, event);
  // }
  //
  // @Test
  // public void testFindPatternThreeSameRecords() {
  // Record record = new Record(cols, new Value[]{new StringValue("Crea"), new NullValue(), new
  // NullValue()});
  // table.add(record);
  // record = new Record(cols, new Value[]{new StringValue("Crea2"), new NullValue(), new
  // NullValue()});
  // table.add(record);
  // record = new Record(cols, new Value[]{new StringValue("Crea3"), new NullValue(), new
  // NullValue()});
  // table.add(record);
  //
  // SingleOccurrencePattern pattern = new SingleOccurrencePattern("StatSensor");
  // Table event = new Table();
  // assertFalse(pattern.findPattern(table, 0, event));
  //
  // event = new Table();
  // assertFalse(pattern.findPattern(table, 1, event));
  //
  // event = new Table();
  // assertFalse(pattern.findPattern(table, 2, event));
  // }

}
