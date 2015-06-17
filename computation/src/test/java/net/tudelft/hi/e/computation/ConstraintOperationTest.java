package net.tudelft.hi.e.computation;

import java.util.ArrayList;
import net.tudelft.hi.e.common.enums.CompareOperator;
import net.tudelft.hi.e.computation.Condition;
import net.tudelft.hi.e.computation.ConstraintOperation;
import net.tudelft.hi.e.computation.RecordMatchesConditionCondition;
import net.tudelft.hi.e.data.Column;
import net.tudelft.hi.e.data.DateColumn;
import net.tudelft.hi.e.data.DateConversion;
import net.tudelft.hi.e.data.DateValue;
import net.tudelft.hi.e.data.NumberColumn;
import net.tudelft.hi.e.data.NumberValue;
import net.tudelft.hi.e.data.Record;
import net.tudelft.hi.e.data.StringColumn;
import net.tudelft.hi.e.data.StringValue;
import net.tudelft.hi.e.data.Table;
import net.tudelft.hi.e.data.Value;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * ConstraintOperationTest class testing the operations.ConstraintOperation.
 *
 */
public class ConstraintOperationTest {

  Table dataTable;
  ConstraintOperation fo;

  /**
   * Creates a dummy table.
   */
  @Before
  public void setUp() {
    // Table with test data
    dataTable = new Table();

    // Create table with 20 user id's.
    for (int i = 0; i < 20; i++) {
      final ArrayList<Column> cols = new ArrayList<Column>();
      cols.add(new NumberColumn("userid"));
      cols.add(new NumberColumn("numberField"));
      cols.add(new StringColumn("stringField"));
      cols.add(new DateColumn("dateField"));
      final Record record =
          new Record(cols, new Value[] {new NumberValue(i), new NumberValue(i * 10),
              new StringValue("String:" + i),
              new DateValue(DateConversion.fromExcelSerialToDate(40000 + i))});
      dataTable.add(record);
    }

    fo = new ConstraintOperation(dataTable, null, null, null);
  }

  @Test
  public void testExecute_date_eq() {
    fo.setOperationParameters("dateField", CompareOperator.EQ,
        new DateValue(DateConversion.fromExcelSerialToDate(40010)));
    assertEquals(true, fo.execute());

    final Table resultTable = new Table();
    // Create expected result table
    for (int i = 10; i < 11; i++) {
      final ArrayList<Column> cols = new ArrayList<Column>();
      cols.add(new NumberColumn("userid"));
      cols.add(new NumberColumn("numberField"));
      cols.add(new StringColumn("stringField"));
      cols.add(new DateColumn("dateField"));
      final Record record =
          new Record(cols, new Value[] {new NumberValue(i), new NumberValue(i * 10),
              new StringValue("String:" + i),
              new DateValue(DateConversion.fromExcelSerialToDate(40000 + i))});
      resultTable.add(record);
    }
    assertEquals(resultTable, fo.getResult());
  }
  
  @Test
  public void testExecute_date_neq() {
    fo.setOperationParameters("dateField", CompareOperator.NEQ,
        new DateValue(DateConversion.fromExcelSerialToDate(40010)));
    assertEquals(true, fo.execute());

    final Table resultTable = (Table) fo.inputData.clone();
    // Create expected result table
    resultTable.remove(10);
    
    assertEquals(resultTable, fo.getResult());
  }

  @Test
  public void testExecute_date_g() {
    fo.setOperationParameters("dateField", CompareOperator.G,
        new DateValue(DateConversion.fromExcelSerialToDate(40003)));
    assertEquals(true, fo.execute());

    final Table resultTable = new Table();
    // Create expected result table
    for (int i = 4; i < 20; i++) {
      final ArrayList<Column> cols = new ArrayList<Column>();
      cols.add(new NumberColumn("userid"));
      cols.add(new NumberColumn("numberField"));
      cols.add(new StringColumn("stringField"));
      cols.add(new DateColumn("dateField"));
      final Record record =
          new Record(cols, new Value[] {new NumberValue(i), new NumberValue(i * 10),
              new StringValue("String:" + i),
              new DateValue(DateConversion.fromExcelSerialToDate(40000 + i))});
      resultTable.add(record);
    }
    assertEquals(resultTable, fo.getResult());
  }

  @Test
  public void testExecute_date_geq() {
    fo.setOperationParameters("dateField", CompareOperator.GEQ,
        new DateValue(DateConversion.fromExcelSerialToDate(40005)));
    assertEquals(true, fo.execute());

    final Table resultTable = new Table();
    // Create expected result table
    for (int i = 5; i < 20; i++) {
      final ArrayList<Column> cols = new ArrayList<Column>();
      cols.add(new NumberColumn("userid"));
      cols.add(new NumberColumn("numberField"));
      cols.add(new StringColumn("stringField"));
      cols.add(new DateColumn("dateField"));
      final Record record =
          new Record(cols, new Value[] {new NumberValue(i), new NumberValue(i * 10),
              new StringValue("String:" + i),
              new DateValue(DateConversion.fromExcelSerialToDate(40000 + i))});
      resultTable.add(record);
    }
    assertEquals(resultTable, fo.getResult());
  }

  @Test
  public void testExecute_date_l() {
    fo.setOperationParameters("dateField", CompareOperator.L,
        new DateValue(DateConversion.fromExcelSerialToDate(40013)));
    assertEquals(true, fo.execute());

    final Table resultTable = new Table();
    // Create expected result table
    for (int i = 0; i < 13; i++) {
      final ArrayList<Column> cols = new ArrayList<Column>();
      cols.add(new NumberColumn("userid"));
      cols.add(new NumberColumn("numberField"));
      cols.add(new StringColumn("stringField"));
      cols.add(new DateColumn("dateField"));
      final Record record =
          new Record(cols, new Value[] {new NumberValue(i), new NumberValue(i * 10),
              new StringValue("String:" + i),
              new DateValue(DateConversion.fromExcelSerialToDate(40000 + i))});
      resultTable.add(record);
    }
    assertEquals(resultTable, fo.getResult());
  }

  @Test
  public void testExecute_date_leq() {
    fo.setOperationParameters("dateField", CompareOperator.LEQ,
        new DateValue(DateConversion.fromExcelSerialToDate(40013)));
    assertEquals(true, fo.execute());

    final Table resultTable = new Table();
    // Create expected result table
    for (int i = 0; i < 14; i++) {
      final ArrayList<Column> cols = new ArrayList<Column>();
      cols.add(new NumberColumn("userid"));
      cols.add(new NumberColumn("numberField"));
      cols.add(new StringColumn("stringField"));
      cols.add(new DateColumn("dateField"));
      final Record record =
          new Record(cols, new Value[] {new NumberValue(i), new NumberValue(i * 10),
              new StringValue("String:" + i),
              new DateValue(DateConversion.fromExcelSerialToDate(40000 + i))});
      resultTable.add(record);
    }
    assertEquals(resultTable, fo.getResult());
  }

  @Test
  public void testExecute_empty() {
    fo.execute();
    assertEquals(new Table(), fo.getResult());
  }

  @Test
  public void testExecute_field_notfound() {
    /*
     * In normal wording: get me all records where the randomStringField is equal to "Pi_is_great"
     * It doesn't matter if the field is not found or the value is not correct, the equality is not
     * found and thus the records shouldn't be added to the result set and return an empty Table()
     */
    fo.setOperationParameters("randomStringField", CompareOperator.EQ, new StringValue(
        "Pi_is_great"));
    assertEquals(true, fo.execute());

    final Table resultTable = new Table();

    assertEquals(resultTable, fo.getResult());
  }

  @Test
  public void testExecute_number_eq() {
    fo.setOperationParameters("userid", CompareOperator.EQ, new NumberValue(9));
    assertEquals(true, fo.execute());

    final Table resultTable = new Table();
    // Create table with 20 user id's.
    for (int i = 9; i < 10; i++) {
      final ArrayList<Column> cols = new ArrayList<Column>();
      cols.add(new NumberColumn("userid"));
      cols.add(new NumberColumn("numberField"));
      cols.add(new StringColumn("stringField"));
      cols.add(new DateColumn("dateField"));
      final Record record =
          new Record(cols, new Value[] {new NumberValue(i), new NumberValue(i * 10),
              new StringValue("String:" + i),
              new DateValue(DateConversion.fromExcelSerialToDate(40000 + i))});
      resultTable.add(record);
    }
    assertEquals(resultTable, fo.getResult());
  }

  @Test
  public void testExecute_number_g() {
    fo.setOperationParameters("userid", CompareOperator.G, new NumberValue(2));
    assertEquals(true, fo.execute());

    final Table resultTable = new Table();
    // Create table with 20 user id's.
    for (int i = 3; i < 20; i++) {
      final ArrayList<Column> cols = new ArrayList<Column>();
      cols.add(new NumberColumn("userid"));
      cols.add(new NumberColumn("numberField"));
      cols.add(new StringColumn("stringField"));
      cols.add(new DateColumn("dateField"));
      final Record record =
          new Record(cols, new Value[] {new NumberValue(i), new NumberValue(i * 10),
              new StringValue("String:" + i),
              new DateValue(DateConversion.fromExcelSerialToDate(40000 + i))});
      resultTable.add(record);
    }
    assertEquals(resultTable, fo.getResult());
  }

  @Test
  public void testExecute_number_geq() {
    fo.setOperationParameters("userid", CompareOperator.GEQ, new NumberValue(17));
    assertEquals(true, fo.execute());

    final Table resultTable = new Table();
    // Create table with 20 user id's.
    for (int i = 17; i < 20; i++) {
      final ArrayList<Column> cols = new ArrayList<Column>();
      cols.add(new NumberColumn("userid"));
      cols.add(new NumberColumn("numberField"));
      cols.add(new StringColumn("stringField"));
      cols.add(new DateColumn("dateField"));
      final Record record =
          new Record(cols, new Value[] {new NumberValue(i), new NumberValue(i * 10),
              new StringValue("String:" + i),
              new DateValue(DateConversion.fromExcelSerialToDate(40000 + i))});
      resultTable.add(record);
    }
    assertEquals(resultTable, fo.getResult());
  }

  @Test
  public void testExecute_number_l() {
    fo.setOperationParameters("userid", CompareOperator.L, new NumberValue(5));
    assertEquals(true, fo.execute());

    final Table resultTable = new Table();
    // Create table with 20 user id's.
    for (int i = 0; i < 5; i++) {
      final ArrayList<Column> cols = new ArrayList<Column>();
      cols.add(new NumberColumn("userid"));
      cols.add(new NumberColumn("numberField"));
      cols.add(new StringColumn("stringField"));
      cols.add(new DateColumn("dateField"));
      final Record record =
          new Record(cols, new Value[] {new NumberValue(i), new NumberValue(i * 10),
              new StringValue("String:" + i),
              new DateValue(DateConversion.fromExcelSerialToDate(40000 + i))});
      resultTable.add(record);
    }
    assertEquals(resultTable, fo.getResult());
  }

  @Test
  public void testExecute_number_leq() {
    fo.setOperationParameters("userid", CompareOperator.LEQ, new NumberValue(14));
    assertEquals(true, fo.execute());

    final Table resultTable = new Table();
    // Create table with 20 user id's.
    for (int i = 0; i < 15; i++) {
      final ArrayList<Column> cols = new ArrayList<Column>();
      cols.add(new NumberColumn("userid"));
      cols.add(new NumberColumn("numberField"));
      cols.add(new StringColumn("stringField"));
      cols.add(new DateColumn("dateField"));
      final Record record =
          new Record(cols, new Value[] {new NumberValue(i), new NumberValue(i * 10),
              new StringValue("String:" + i),
              new DateValue(DateConversion.fromExcelSerialToDate(40000 + i))});
      resultTable.add(record);
    }
    assertEquals(resultTable, fo.getResult());
  }

  @Test
  public void testExecute_string_found() {
    fo.setOperationParameters("stringField", CompareOperator.EQ, new StringValue("String:" + 7));
    assertEquals(true, fo.execute());

    final Table resultTable = new Table();

    final ArrayList<Column> cols = new ArrayList<Column>();
    cols.add(new NumberColumn("userid"));
    cols.add(new NumberColumn("numberField"));
    cols.add(new StringColumn("stringField"));
    cols.add(new DateColumn("dateField"));
    final Record record =
        new Record(cols, new Value[] {new NumberValue(7), new NumberValue(7 * 10),
            new StringValue("String:7"),
            new DateValue(DateConversion.fromExcelSerialToDate(40000 + 7))});
    resultTable.add(record);

    assertEquals(resultTable, fo.getResult());
  }

  @Test
  public void testExecute_string_notfound() {
    fo.setOperationParameters("stringField", CompareOperator.EQ, new StringValue("String:" + 25));
    assertEquals(true, fo.execute());

    final Table resultTable = new Table();

    assertEquals(resultTable, fo.getResult());
  }

  @Test
  public void testGetResult() {
    assertEquals(new Table(), fo.getResult());
  }

  @Test
  public void testSetOperationParameters() {
    Table input = new Table();
    String column = "columnName";
    CompareOperator operator = CompareOperator.EQ;
    Value toCompare = new StringValue("toCompare");

    ConstraintOperation operation = new ConstraintOperation(input, column, operator, toCompare);
    assertTrue(operation.isOperationParametersSet());
    operation = new ConstraintOperation(input, column, CompareOperator.ND, toCompare);
    assertFalse(operation.isOperationParametersSet());
    operation = new ConstraintOperation(input, column, null, toCompare);
    assertFalse(operation.isOperationParametersSet());
    operation = new ConstraintOperation(input, column, null, null);
    assertFalse(operation.isOperationParametersSet());
    operation = new ConstraintOperation(input, null, null, null);
    assertFalse(operation.isOperationParametersSet());
  }

  @Test
  public void testEquals() {
    Table input = new Table();
    String column = "columnName";
    CompareOperator operator = CompareOperator.EQ;
    Value toCompare = new StringValue("toCompare");

    final ConstraintOperation operation =
        new ConstraintOperation(input, column, operator, toCompare);
    final ConstraintOperation operationSame =
        new ConstraintOperation(input, column, operator, toCompare);
    final ConstraintOperation operationNotSameCond =
        new ConstraintOperation(input, column + "diff", operator, toCompare);
    final ConstraintOperation operationNullCond1 =
        new ConstraintOperation(input, null, operator, toCompare);
    final ConstraintOperation operationNullCond2 =
        new ConstraintOperation(input, null, operator, toCompare);
    final String otherClass = "";

    assertEquals(operation, operation);
    assertEquals(operation, operationSame);
    assertNotEquals(operation, null);
    assertNotEquals(operation, operationNotSameCond);
    assertNotEquals(operation, operationNullCond1);
    assertNotEquals(operationNullCond1, operation);
    assertEquals(operationNullCond1, operationNullCond2);
    assertNotEquals(operation, otherClass);
  }

  @Test
  public void testHashCode() {
    Table input = new Table();
    String column = "columnName";
    CompareOperator operator = CompareOperator.EQ;
    Value toCompare = new StringValue("toCompare");
    RecordMatchesConditionCondition condition =
        new RecordMatchesConditionCondition(column, new Condition(operator, toCompare));

    ConstraintOperation operation = new ConstraintOperation(input, column, operator, toCompare);
    ConstraintOperation operationNull = new ConstraintOperation(input, column, null, toCompare);

    assertEquals(31 + condition.hashCode(), operation.hashCode());
    assertEquals(31, operationNull.hashCode());
  }

  @Test
  public void testToString() {
    Table input = new Table();
    String column = "columnName";
    CompareOperator operator = CompareOperator.EQ;
    Value toCompare = new NumberValue(42);

    ConstraintOperation operation = new ConstraintOperation(input, column, operator, toCompare);
    assertEquals("ConstraintOperation [condition: \"columnName\" == 42]", operation.toString());
  }
}
