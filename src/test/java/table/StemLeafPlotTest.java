package table;

import org.junit.Before;
import org.junit.Test;

import table.value.Column;
import table.value.NumberColumn;
import table.value.NumberValue;
import table.value.Value;

import java.util.ArrayList;

public class StemLeafPlotTest {

  Table table;
 
  /**
   * Creation of the dummy table.
   */
  @Before
  public void setUp() {
    table = new Table();
    ArrayList<Column> col = new ArrayList<Column>();
    col.add(new NumberColumn("numbers"));
    
    table.add(new Record(col, new Value[]{new NumberValue(11)}));
    table.add(new Record(col, new Value[]{new NumberValue(13)}));
    table.add(new Record(col, new Value[]{new NumberValue(22)}));
    table.add(new Record(col, new Value[]{new NumberValue(28)}));
    table.add(new Record(col, new Value[]{new NumberValue(44)}));
    table.add(new Record(col, new Value[]{new NumberValue(23)}));
    table.add(new Record(col, new Value[]{new NumberValue(46)}));
    table.add(new Record(col, new Value[]{new NumberValue(56)}));
    table.add(new Record(col, new Value[]{new NumberValue(45)}));
    table.add(new Record(col, new Value[]{new NumberValue(45)}));
    table.add(new Record(col, new Value[]{new NumberValue(45)}));
    table.add(new Record(col, new Value[]{new NumberValue(34)}));
    table.add(new Record(col, new Value[]{new NumberValue(5)}));
    table.add(new Record(col, new Value[]{new NumberValue(23)}));
    table.add(new Record(col, new Value[]{new NumberValue(48)}));
    table.add(new Record(col, new Value[]{new NumberValue(50)}));
    table.add(new Record(col, new Value[]{new NumberValue(13)})); 
  }
  
  @Test
  public void testslPlot() {
    StemLeafPlot plot = new StemLeafPlot(table, "numbers", 2);
    System.out.println(plot);
  }
  
  
}
