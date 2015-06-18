package net.tudelft.hi.e.computation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import net.tudelft.hi.e.data.Column;
import net.tudelft.hi.e.data.DateColumn;
import net.tudelft.hi.e.data.DateValue;
import net.tudelft.hi.e.data.NullValue;
import net.tudelft.hi.e.data.Record;
import net.tudelft.hi.e.data.StringColumn;
import net.tudelft.hi.e.data.StringValue;
import net.tudelft.hi.e.data.Table;
import net.tudelft.hi.e.data.Value;

import org.junit.Before;
import org.junit.Test;

public class CombineOperationTest {

  private Table table;
  private Table table2;
  ArrayList<Column> columns;

  /**
   * Creates the dummy table.
   */
  @Before
  public void setUp() {
    table = new Table();
    table2 = new Table();
    columns = new ArrayList<Column>();
    columns.add(new StringColumn("Measurement"));
    columns.add(new DateColumn("Date"));

    table.add(new Record(columns, new Value[] { new StringValue("Crea"),
        new DateValue(new GregorianCalendar(2015, 1, 1)) }));
    table.add(new Record(columns, new Value[] { new StringValue("Crea"),
        new NullValue() }));
    table2.add(new Record(columns, new Value[] { new StringValue("Kreatinine"),
        new DateValue(new GregorianCalendar(2015, 1, 1)) }));
    table2.add(new Record(columns, new Value[] { new StringValue("Bloeddruk"),
        new DateValue(new GregorianCalendar(2015, 1, 1)) }));
    table.add(new Record(columns, new Value[] { new StringValue("Crea"),
        new DateValue(new GregorianCalendar(2015, 1, 2)) }));
    table2.add(new Record(columns, new Value[] { new StringValue("Kreatinine"),
        new DateValue(new GregorianCalendar(2015, 1, 2)) }));
    table.add(new Record(columns, new Value[] { new StringValue("Crea"),
        new DateValue(new GregorianCalendar(2015, 1, 3)) }));
    table.add(new Record(columns, new Value[] { new StringValue("Crea"),    
        new DateValue(new GregorianCalendar(2015, 1, 4)) }));
    table.add(new Record(columns, new Value[] { new StringValue("Crea"),
        new DateValue(new GregorianCalendar(2015, 1, 5)) }));
    table2.add(new Record(columns, new Value[] { new StringValue("Kreatinine"),
        new DateValue(new GregorianCalendar(2015, 1, 5)) }));
    table2.add(new Record(columns, new Value[] { new StringValue("Kreatinine"),
        new NullValue() }));
    table2.add(new Record(columns, new Value[] { new StringValue("Bloeddruk"),
        new DateValue(new GregorianCalendar(2015, 1, 5)) }));
    table.add(new Record(columns, new Value[] { new StringValue("Crea"),
        new DateValue(new GregorianCalendar(2015, 1, 6)) }));
    table2.add(new Record(columns, new Value[] { new StringValue("Kreatinine"),
        new DateValue(new GregorianCalendar(2015, 1, 10)) }));
    table.add(new Record(columns, new Value[] { new StringValue("Crea"),
        new DateValue(new GregorianCalendar(2015, 1, 10)) }));
    table.add(new Record(columns, new Value[] { new StringValue("Crea"),
        new DateValue(new GregorianCalendar(2015, 1, 11)) }));
    table2.add(new Record(columns, new Value[] { new StringValue("Kreatinine"),
        new DateValue(new GregorianCalendar(2015, 1, 12)) }));
    table2.add(new Record(columns, new Value[] { new StringValue("Kreatinine"),
        new DateValue(new GregorianCalendar(2015, 1, 13)) }));
    table.add(new Record(columns, new Value[] { new StringValue("Crea"),
        new DateValue(new GregorianCalendar(2015, 1, 14)) }));
  }

  @Test
  public void convertTest() {
    CombineOperation combineOperation =
        new CombineOperation(table, table2, "Date", "Date");
    combineOperation.execute();

    columns.add(new StringColumn("Measurement_1"));
    columns.add(new DateColumn("Date_1"));

    Table test = new Table();
    test.add(new Record(columns, new Value[] { new StringValue("Crea"),
        new DateValue(new GregorianCalendar(2015, 1, 1)), new StringValue("Kreatinine"),
        new DateValue(new GregorianCalendar(2015, 1, 1)) }));
    test.add(new Record(columns, new Value[] { new StringValue("Crea"),
        new DateValue(new GregorianCalendar(2015, 1, 1)), new StringValue("Bloeddruk"),
        new DateValue(new GregorianCalendar(2015, 1, 1)) }));
    test.add(new Record(columns, new Value[] { new StringValue("Crea"),
        new DateValue(new GregorianCalendar(2015, 1, 2)), new StringValue("Kreatinine"),
        new DateValue(new GregorianCalendar(2015, 1, 2)) }));
    test.add(new Record(columns, new Value[] { new StringValue("Crea"),
        new DateValue(new GregorianCalendar(2015, 1, 5)), new StringValue("Kreatinine"),
        new DateValue(new GregorianCalendar(2015, 1, 5)) }));
    test.add(new Record(columns, new Value[] { new StringValue("Crea"),
        new DateValue(new GregorianCalendar(2015, 1, 5)), new StringValue("Bloeddruk"),
        new DateValue(new GregorianCalendar(2015, 1, 5)) }));
    test.add(new Record(columns, new Value[] { new StringValue("Crea"),
        new DateValue(new GregorianCalendar(2015, 1, 10)), new StringValue("Kreatinine"),
        new DateValue(new GregorianCalendar(2015, 1, 10)) }));

    Table result = combineOperation.getResult();
    assertEquals(test, result);
  }
  
  @Test
  public void testBadWeather() {
    CombineOperation combineOperation =
        new CombineOperation(table, table2, "Date", null);
    CombineOperation combineOperation2 =
        new CombineOperation(table, table2, null, "Date");
    
    assertFalse(combineOperation.operationParametersSet);
    assertFalse(combineOperation2.operationParametersSet);
    assertFalse(combineOperation.execute());
    assertFalse(combineOperation2.execute());
  }

}
