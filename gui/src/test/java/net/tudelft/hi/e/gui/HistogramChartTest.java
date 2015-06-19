package net.tudelft.hi.e.gui;

import static org.junit.Assert.assertEquals;

import net.tudelft.hi.e.data.Column;
import net.tudelft.hi.e.data.NullValue;
import net.tudelft.hi.e.data.NumberColumn;
import net.tudelft.hi.e.data.NumberValue;
import net.tudelft.hi.e.data.Record;
import net.tudelft.hi.e.data.Table;
import net.tudelft.hi.e.data.Value;

import org.jfree.data.statistics.HistogramDataset;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;


public class HistogramChartTest {

  private Table table;

  /**
   * Creating a table for testing.
   */
  @Before
  public void setUp() {
    table = new Table();
    ArrayList<Column> col = new ArrayList<>();
    col.add(new NumberColumn("numbers"));

    table.add(new Record(col, new Value[] { new NumberValue(11) }));
    table.add(new Record(col, new Value[] { new NumberValue(13) }));
    table.add(new Record(col, new Value[] { new NumberValue(22) }));
    table.add(new Record(col, new Value[] { new NumberValue(28) }));
    table.add(new Record(col, new Value[] { new NumberValue(44) }));
    table.add(new Record(col, new Value[] { new NumberValue(23) }));
    table.add(new Record(col, new Value[] { new NumberValue(46) }));
    table.add(new Record(col, new Value[] { new NumberValue(56) }));
    table.add(new Record(col, new Value[] { new NumberValue(45) }));
    table.add(new Record(col, new Value[] { new NumberValue(-2) }));
    table.add(new Record(col, new Value[] { new NumberValue(45) }));
    table.add(new Record(col, new Value[] { new NullValue() }));
    table.add(new Record(col, new Value[] { new NumberValue(45) }));
    table.add(new Record(col, new Value[] { new NumberValue(34) }));
    table.add(new Record(col, new Value[] { new NumberValue(5) }));
    table.add(new Record(col, new Value[] { new NumberValue(123) }));
    table.add(new Record(col, new Value[] { new NumberValue(48) }));
    table.add(new Record(col, new Value[] { new NumberValue(50) }));
    table.add(new Record(col, new Value[] { new NumberValue(13) }));
  }

  @Test
  public void testHistogram() {
    HistogramDataset dataset = HistogramChart.createDataset(table, "numbers", 2);

    assertEquals(dataset.getY(0, 0), 1.0);
    assertEquals(dataset.getY(0, 1), 3.0);
    assertEquals(dataset.getY(0, 2), 3.0);
    assertEquals(dataset.getY(0, 3), 1.0);
    assertEquals(dataset.getY(0, 4), 6.0);
    assertEquals(dataset.getY(0, 5), 2.0);
    assertEquals(dataset.getY(0, 6), 0.0);
    assertEquals(dataset.getY(0, 7), 0.0);
    assertEquals(dataset.getY(0, 8), 0.0);
    assertEquals(dataset.getY(0, 9), 0.0);

  }

}
