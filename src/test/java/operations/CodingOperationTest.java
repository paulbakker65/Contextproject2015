package operations;

import static org.junit.Assert.assertEquals;
import input.Column;
import input.NumberColumn;
import input.StringColumn;

import java.util.ArrayList;

import operations.coding.Pattern;
import operations.coding.SingleOccurrencePattern;

import org.junit.Before;
import org.junit.Test;

import parsers.NullValue;
import parsers.NumberValue;
import parsers.StringValue;
import parsers.Value;
import table.Record;
import table.Table;

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

  }

  @Test
  public void testSimplePattern() {
    Pattern endPattern = new SingleOccurrencePattern("WebsiteValue");
    Pattern prevPattern = new SingleOccurrencePattern("WebsiteValue", endPattern);
    
    for (int i = 0; i < 3; i++) {
      prevPattern = new SingleOccurrencePattern("WebsiteValue", prevPattern);
    }
    
    Pattern firstPattern = new SingleOccurrencePattern("StatSensor", prevPattern);

    CodingOperation co = new CodingOperation(table);
    co.setOperationParameters(firstPattern, "1S5W");
    co.execute();
    
    assertEquals(co.getResult().getCode("1S5W").getFrequency(), 2);
  }
}
