package net.tudelft.hi.e.computation;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import net.tudelft.hi.e.data.Column;
import net.tudelft.hi.e.data.DateColumn;
import net.tudelft.hi.e.data.DateValue;
import net.tudelft.hi.e.data.Record;
import net.tudelft.hi.e.data.StringColumn;
import net.tudelft.hi.e.data.StringValue;
import net.tudelft.hi.e.data.Table;
import net.tudelft.hi.e.data.Value;

import org.junit.Before;
import org.junit.Test;

public class CombineOperationTest {

  private Table table;
  ArrayList<Column> columns;

  /**
   * Creates the dummy table.
   */
  @Before
  public void setUp() {
    table = new Table();
    columns = new ArrayList<Column>();
    columns.add(new StringColumn("Measurement"));
    columns.add(new DateColumn("Date"));

    table.add(new Record(columns, new Value[] { new StringValue("Crea"),
        new DateValue(new GregorianCalendar(2015, 1, 1)) }));
    table.add(new Record(columns, new Value[] { new StringValue("Kreatinine"),
        new DateValue(new GregorianCalendar(2015, 1, 1)) }));
    table.add(new Record(columns, new Value[] { new StringValue("Kreatinine"),
        new DateValue(new GregorianCalendar(2015, 1, 1)) }));
    table.add(new Record(columns, new Value[] { new StringValue("Crea"),
        new DateValue(new GregorianCalendar(2015, 1, 2)) }));
    table.add(new Record(columns, new Value[] { new StringValue("Kreatinine"),
        new DateValue(new GregorianCalendar(2015, 1, 2)) }));
    table.add(new Record(columns, new Value[] { new StringValue("Crea"),
        new DateValue(new GregorianCalendar(2015, 1, 3)) }));
    table.add(new Record(columns, new Value[] { new StringValue("Crea"),
        new DateValue(new GregorianCalendar(2015, 1, 4)) }));
    table.add(new Record(columns, new Value[] { new StringValue("Crea"),
        new DateValue(new GregorianCalendar(2015, 1, 5)) }));
    table.add(new Record(columns, new Value[] { new StringValue("Kreatinine"),
        new DateValue(new GregorianCalendar(2015, 1, 5)) }));
    table.add(new Record(columns, new Value[] { new StringValue("Crea"),
        new DateValue(new GregorianCalendar(2015, 1, 6)) }));
    table.add(new Record(columns, new Value[] { new StringValue("Kreatinine"),
        new DateValue(new GregorianCalendar(2015, 1, 10)) }));
    table.add(new Record(columns, new Value[] { new StringValue("Crea"),
        new DateValue(new GregorianCalendar(2015, 1, 10)) }));
    table.add(new Record(columns, new Value[] { new StringValue("Crea"),
        new DateValue(new GregorianCalendar(2015, 1, 11)) }));
    table.add(new Record(columns, new Value[] { new StringValue("Kreatinine"),
        new DateValue(new GregorianCalendar(2015, 1, 12)) }));
    table.add(new Record(columns, new Value[] { new StringValue("Kreatinine"),
        new DateValue(new GregorianCalendar(2015, 1, 13)) }));
    table.add(new Record(columns, new Value[] { new StringValue("Crea"),
        new DateValue(new GregorianCalendar(2015, 1, 14)) }));
  }

  @Test
  public void convertTest() {
    CombineOperation combineOperation =
        new CombineOperation(table, "Measurement", "Crea", "Kreatinine", "Date");
    combineOperation.execute();

    columns.add(new StringColumn("Measurement_1"));
    columns.add(new DateColumn("Date_1"));

    Table test = new Table();
    test.add(new Record(columns, new Value[] { new StringValue("Crea"),
        new DateValue(new GregorianCalendar(2015, 1, 1)), new StringValue("Kreatinine"),
        new DateValue(new GregorianCalendar(2015, 1, 1)) }));
    test.add(new Record(columns, new Value[] { new StringValue("Crea"),
        new DateValue(new GregorianCalendar(2015, 1, 2)), new StringValue("Kreatinine"),
        new DateValue(new GregorianCalendar(2015, 1, 2)) }));
    test.add(new Record(columns, new Value[] { new StringValue("Crea"),
        new DateValue(new GregorianCalendar(2015, 1, 5)), new StringValue("Kreatinine"),
        new DateValue(new GregorianCalendar(2015, 1, 5)) }));
    test.add(new Record(columns, new Value[] { new StringValue("Kreatinine"),
        new DateValue(new GregorianCalendar(2015, 1, 10)), new StringValue("Crea"),
        new DateValue(new GregorianCalendar(2015, 1, 10)) }));

    Table result = combineOperation.getResult();
    assertEquals(test, result);
  }

}
