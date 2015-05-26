package operations;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import operations.coding.MultipleOccurrencePattern;
import operations.coding.NullPattern;
import operations.coding.Pattern;
import operations.coding.SingleOccurrencePattern;

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
 * Test for the coding operation.
 * @author robin
 *
 */
public class CodingOperationTest {
  
  Table table;
  
  @Before
  public void setUp() {
      table = new Table();
      ArrayList<Column> col = new ArrayList<Column>();
      col.add(new StringColumn("StatSensor"));
      col.add(new NumberColumn("WebsiteValue"));
      col.add(new StringColumn("HospitalVisit"));
      table.add(new Record(col, new Value[]{new StringValue("Crea"), new NullValue(), new NullValue()}));
      table.add(new Record(col, new Value[]{new NullValue(), new NumberValue(140), new NullValue()}));
      table.add(new Record(col, new Value[]{new NullValue(), new NumberValue(150), new NullValue()}));
      table.add(new Record(col, new Value[]{new NullValue(), new NumberValue(160), new NullValue()}));
      table.add(new Record(col, new Value[]{new NullValue(), new NumberValue(170), new NullValue()}));
      table.add(new Record(col, new Value[]{new NullValue(), new NumberValue(180), new NullValue()}));
      table.add(new Record(col, new Value[]{new StringValue("Crea2"), new NullValue(), new NullValue()}));
      table.add(new Record(col, new Value[]{new NullValue(), new NumberValue(140), new NullValue()}));
      table.add(new Record(col, new Value[]{new NullValue(), new NumberValue(350), new NullValue()}));
      table.add(new Record(col, new Value[]{new NullValue(), new NumberValue(160), new NullValue()}));
      table.add(new Record(col, new Value[]{new NullValue(), new NumberValue(470), new NullValue()}));
      table.add(new Record(col, new Value[]{new NullValue(), new NumberValue(180), new NullValue()}));
      table.add(new Record(col, new Value[]{new StringValue("Crea2"), new NullValue(), new NullValue()}));
      table.add(new Record(col, new Value[]{new StringValue("Crea2"), new NullValue(), new NullValue()}));
      table.add(new Record(col, new Value[]{new StringValue("Crea2"), new NullValue(), new NullValue()}));
      table.add(new Record(col, new Value[]{new NullValue(), new NullValue(), new StringValue("Erg ziek hoor")}));
      table.add(new Record(col, new Value[]{new NullValue(), new NumberValue(160), new NullValue()}));
      table.add(new Record(col, new Value[]{new NullValue(), new NumberValue(170), new NullValue()}));
      table.add(new Record(col, new Value[]{new NullValue(), new NumberValue(160), new NullValue()}));
      table.add(new Record(col, new Value[]{new NullValue(), new NumberValue(170), new NullValue()}));
      table.add(new Record(col, new Value[]{new NullValue(), new NumberValue(180), new NullValue()}));
      table.add(new Record(col, new Value[]{new StringValue("Crea2"), new NullValue(), new NullValue()}));
      table.add(new Record(col, new Value[]{new NullValue(), new NumberValue(140), new NullValue()}));
      table.add(new Record(col, new Value[]{new NullValue(), new NumberValue(350), new NullValue()}));
      table.add(new Record(col, new Value[]{new NullValue(), new NumberValue(160), new NullValue()}));
      table.add(new Record(col, new Value[]{new StringValue("Crea2"), new NullValue(), new NullValue()}));
      table.add(new Record(col, new Value[]{new StringValue("Crea2"), new NullValue(), new NullValue()}));
  }

  @Test
  public void testSimplePattern() {
    Pattern endPattern = new SingleOccurrencePattern("WebsiteValue");
    Pattern prevPattern = new SingleOccurrencePattern("WebsiteValue", endPattern);
    
    for (int i = 0; i < 3; i++) {
      prevPattern = new SingleOccurrencePattern("WebsiteValue", prevPattern);
    }
    
    Pattern firstPattern = new SingleOccurrencePattern("StatSensor", prevPattern);

    CodingOperation co = new CodingOperation(table, firstPattern, "1S5W");
    co.execute();
    
    assertEquals(co.getResult().getCode("1S5W").getFrequency(), 2);
  }
  
  @Test
  public void testSimpleMultiplePattern() {
    Pattern endPattern = new MultipleOccurrencePattern("WebsiteValue");
    Pattern firstPattern = new SingleOccurrencePattern("StatSensor", endPattern);

    CodingOperation co = new CodingOperation(table, firstPattern, "1S?W");
    co.execute();
    
    assertEquals(co.getResult().getCode("1S?W").getFrequency(), 3);
  }
  
  @Test
  public void testMultiplePattern() {
    Pattern pattern = new MultipleOccurrencePattern("WebsiteValue", new NullPattern());

    CodingOperation co = new CodingOperation(table, pattern, "?W");
    co.execute();
    
    assertEquals(co.getResult().getCode("?W").getFrequency(), 4);
  }
  
  @Test
  public void testMultiplePatternEndNoHasNext() {
    Pattern pattern = new MultipleOccurrencePattern("StatSensor", new NullPattern());

    CodingOperation co = new CodingOperation(table, pattern, "?S");
    co.execute();
    
    assertEquals(co.getResult().getCode("?S").getFrequency(), 2);
  }
  
  @Test
  public void testMultiplePatternNoHasNext() {
    Pattern endPattern = new MultipleOccurrencePattern("StatSensor");
    Pattern middlePattern = new SingleOccurrencePattern("StatSensor", endPattern);
    Pattern firstPattern = new SingleOccurrencePattern("StatSensor", middlePattern);

    CodingOperation co = new CodingOperation(table, firstPattern, "S?S");
    co.execute();
    
    assertEquals(co.getResult().getCode("S?S").getFrequency(), 0);
  }
}
