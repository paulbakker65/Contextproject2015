package net.tudelft.hi.e.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

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

    table.add(new Record(col, new Value[] { new NumberValue(11) }));
    table.add(new Record(col, new Value[] { new NumberValue(13) }));
    table.add(new Record(col, new Value[] { new NumberValue(22) }));
    table.add(new Record(col, new Value[] { new NumberValue(28) }));
    table.add(new Record(col, new Value[] { new NumberValue(44) }));
    table.add(new Record(col, new Value[] { new NumberValue(23) }));
    table.add(new Record(col, new Value[] { new NumberValue(46) }));
    table.add(new Record(col, new Value[] { new NumberValue(56) }));
    table.add(new Record(col, new Value[] { new NumberValue(45) }));
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
  public void testslPlot() {
    final StemLeafPlot plot = new StemLeafPlot(table, "numbers", 2);
    ArrayList<Column> column = new ArrayList<Column>();
    column.add(new StringColumn("Stem"));
    column.add(new StringColumn("Leaf"));

    Table test = new Table();
    test.add(new Record(column, new Value[] { new StringValue("1"), new StringValue("133") }));
    test.add(new Record(column, new Value[] { new StringValue("2"), new StringValue("2833") }));
    test.add(new Record(column, new Value[] { new StringValue("4"), new StringValue("465558") }));
    test.add(new Record(column, new Value[] { new StringValue("5"), new StringValue("60") }));
    test.add(new Record(column, new Value[] { new StringValue("3"), new StringValue("4") }));
    test.add(new Record(column, new Value[] { new StringValue("0"), new StringValue("5") }));

    for (Record record : plot) {
      switch (record.get("Stem").toString()) {
        case "1":
          assertEquals(record, test.get(0));
          break;
        case "2":
          assertEquals(record, test.get(1));
          break;
        case "4":
          assertEquals(record, test.get(2));
          break;
        case "5":
          assertEquals(record, test.get(3));
          break;
        case "3":
          assertEquals(record, test.get(4));
          break;
        case "0":
          assertEquals(record, test.get(5));
          break;
        default:
          assertTrue(false);
      }
    }
  }

  @Test
  public void testslPlotPowerThree() {
    final StemLeafPlot plot = new StemLeafPlot(table, "numbers", 3);
    ArrayList<Column> column = new ArrayList<Column>();
    column.add(new StringColumn("Stem"));
    column.add(new StringColumn("Leaf"));

    Table test = new Table();
    test.add(new Record(column, new Value[] { new StringValue("0"),
        new StringValue("1122424544430451") }));
    test.add(new Record(column, new Value[] { new StringValue("1"), new StringValue("2") }));

    for (Record record : plot) {
      switch (record.get("Stem").toString()) {
        case "0":
          assertEquals(record, test.get(0));
          break;
        case "1":
          assertEquals(record, test.get(1));
          break;
        default:
          assertTrue(false);
      }
    }
  }

  @Test
  public void testEqualsHashCode() {
    final StemLeafPlot plot = new StemLeafPlot(table, "numbers", 2);
    final StemLeafPlot plot2 = new StemLeafPlot(table, "numbers", 3);

    ArrayList<Column> column = new ArrayList<Column>();
    column.add(new StringColumn("Stem"));
    column.add(new StringColumn("Leaf"));

    StemLeafPlot test = new StemLeafPlot(new Table(), "numbers", 2);
    test.add(new Record(column, new Value[] { new StringValue("1"), new StringValue("133") }));
    test.add(new Record(column, new Value[] { new StringValue("2"), new StringValue("2833") }));
    test.add(new Record(column, new Value[] { new StringValue("4"), new StringValue("465558") }));
    test.add(new Record(column, new Value[] { new StringValue("5"), new StringValue("60") }));
    test.add(new Record(column, new Value[] { new StringValue("3"), new StringValue("4") }));
    test.add(new Record(column, new Value[] { new StringValue("0"), new StringValue("5") }));

    assertEquals(test, plot);

    StemLeafPlot test2 = new StemLeafPlot(new Table(), "numbers", 3);
    test2.add(new Record(column, new Value[] { new StringValue("0"),
        new StringValue("1122424544430451") }));
    test2.add(new Record(column, new Value[] { new StringValue("1"), new StringValue("2") }));

    assertEquals(test2, plot2);

    assertEquals(
        ((((((23 * 5 + table.hashCode()) * 23) + column.hashCode()) * 23) + "numbers".hashCode()) * 23) + 2,
        plot.hashCode());
    assertEquals(
        ((((((23 * 5 + table.hashCode()) * 23) + column.hashCode()) * 23) + "numbers".hashCode()) * 23) + 3,
        plot2.hashCode());

    assertNotEquals(test2, null);
    assertNotEquals(test2, new NumberValue(10));
  }

}
