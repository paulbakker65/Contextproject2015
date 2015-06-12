package table.value;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * ColumnTest class testing the table.Column class.
 * 
 */
public class ColumnTest {

  @Test
  public void testColumnConstructor() {
    final Column column = new StringColumn("testName");
    assertNotNull(column);
    assertEquals("testName", column.getName());
  }

  @Test
  public void testColumnEquals() {
    final Column column = new StringColumn("name");
    final Column columnSame = new StringColumn("name");
    final Column columnNotSameName = new StringColumn("name2");
    final Column columnNullName1 = new StringColumn(null);
    final Column columnNullName2 = new StringColumn(null);
    final String otherClass = "";

    assertEquals(column, column);
    assertEquals(column, columnSame);
    assertNotEquals(column, null);
    assertNotEquals(column, columnNotSameName);
    assertNotEquals(column, columnNullName1);
    assertNotEquals(columnNullName1, column);
    assertEquals(columnNullName1, columnNullName2);
    assertNotEquals(column, otherClass);
  }

  @Test
  public void testColumnGetSetName() {
    final Column column = new StringColumn("testName");
    assertEquals("testName", column.getName());

    column.setName("newName");
    assertEquals("newName", column.getName());
  }

  @Test
  public void testDateColumnConstructorDefaultFormat() {
    final DateColumn column = new DateColumn("testName");
    assertNotNull(column);
    assertEquals("testName", column.getName());
    assertEquals(DateColumn.isoFormatStr, column.getFormatStr());
    assertEquals(DateColumn.isoFormat, column.getFormat());
  }

  @Test
  public void testDateColumnConstructorWithFormat() {
    final DateColumn column = new DateColumn("testName", "dd-MM-yyyy");
    assertNotNull(column);
    assertEquals("testName", column.getName());
    assertEquals("dd-MM-yyyy", column.getFormatStr());
    assertEquals(new SimpleDateFormat("dd-MM-yyyy"), column.getFormat());
  }

  @Test
  public void testDateColumnConvertToValueCorrect() throws ColumnTypeMismatchException,
      ParseException {
    final DateColumn column = new DateColumn("testName", "dd-MM-yyyy");
    final DateValue value = new DateValue(new SimpleDateFormat("dd-MM-yyyy").parse("31-12-2014"));
    assertEquals(value, column.convertToValue("31-12-2014"));
  }

  @Test
  public void testDateColumnConvertToValueCorrectExcel() throws ColumnTypeMismatchException,
      ParseException {
    final DateColumn column = new DateColumn("testName", "excel");
    final DateValue value = new DateValue(new SimpleDateFormat("dd-MM-yyyy").parse("15-10-2012"));
    assertEquals(value, column.convertToValue("41197"));
  }

  @Test
  public void testDateColumnConvertToValueEmpty() throws ColumnTypeMismatchException {
    final DateColumn column = new DateColumn("testName", "dd-MM-yyyy");
    final NullValue value = new NullValue();
    assertEquals(value, column.convertToValue(""));
  }

  @Test(expected = ColumnTypeMismatchException.class)
  public void testDateColumnConvertToValueExcelException() throws ColumnTypeMismatchException,
      ParseException {
    new DateColumn("testName", "excel").convertToValue("text");
  }

  @Test(expected = ColumnTypeMismatchException.class)
  public void testDateColumnConvertToValueException() throws ColumnTypeMismatchException,
      ParseException {
    new DateColumn("testName", "dd-MM-yyyy").convertToValue("2014/12/31");
  }

  @Test
  public void testDateColumnConvertToValueNull() throws ColumnTypeMismatchException {
    final DateColumn column = new DateColumn("testName", "dd-MM-yyyy");
    final NullValue value = new NullValue();
    assertEquals(value, column.convertToValue("NULL"));
  }

  @Test
  public void testDateColumnToString() {
    final DateColumn column = new DateColumn("testName", "dd-MM-yyyy");
    assertEquals("name: testName,\ttype: date,\tformat: dd-MM-yyyy", column.toString());
  }

  @Test
  public void testDateColumnToStringTime() {
    final DateColumn column = new DateColumn("testName", "dd-MM-yyyy", "Date");
    assertEquals("name: testName,\ttype: date,\tformat: dd-MM-yyyy,\ttarget: Date",
        column.toString());
  }

  @Test
  public void testHashCode() {
    final Column column = new StringColumn("name");
    final Column columnNull = new StringColumn(null);

    assertEquals(31 + "name".hashCode(), column.hashCode());
    assertEquals(31, columnNull.hashCode());
  }

  @Test
  public void testNumberColumnConstructor() {
    final NumberColumn column = new NumberColumn("testName");
    assertNotNull(column);
    assertEquals("testName", column.getName());
  }

  @Test
  public void testNumberColumnConvertToValueCorrect() throws ColumnTypeMismatchException {
    final NumberColumn column = new NumberColumn("testName");
    final NumberValue value = new NumberValue(10.0);
    assertEquals(value, column.convertToValue("10"));
  }

  @Test
  public void testNumberColumnConvertToValueEmpty() throws ColumnTypeMismatchException {
    final NumberColumn column = new NumberColumn("testName");
    final NullValue value = new NullValue();
    assertEquals(value, column.convertToValue(""));
  }

  @Test(expected = ColumnTypeMismatchException.class)
  public void testNumberColumnConvertToValueException() throws ColumnTypeMismatchException {
    new NumberColumn("testName").convertToValue("text");
  }

  @Test
  public void testNumberColumnConvertToValueNull() throws ColumnTypeMismatchException {
    final NumberColumn column = new NumberColumn("testName");
    final NullValue value = new NullValue();
    assertEquals(value, column.convertToValue("NULL"));
  }

  @Test
  public void testNumberColumnToString() {
    final NumberColumn column = new NumberColumn("testName");
    assertEquals("name: testName,\ttype: number", column.toString());
  }

  @Test
  public void testStringColumnConstructor() {
    final StringColumn column = new StringColumn("testName");
    assertNotNull(column);
    assertEquals("testName", column.getName());
  }

  @Test
  public void testStringColumnConvertToValueCorrect() {
    final StringColumn column = new StringColumn("testName");
    final StringValue value = new StringValue("Correct");
    assertEquals(value, column.convertToValue("Correct"));
  }

  @Test
  public void testStringColumnConvertToValueEmpty() {
    final StringColumn column = new StringColumn("testName");
    final NullValue value = new NullValue();
    assertEquals(value, column.convertToValue(""));
  }

  @Test
  public void testStringColumnConvertToValueNull() {
    final StringColumn column = new StringColumn("testName");
    final NullValue value = new NullValue();
    assertEquals(value, column.convertToValue("NULL"));
  }

  @Test
  public void testStringColumnToString() {
    final StringColumn column = new StringColumn("testName");
    assertEquals("name: testName,\ttype: text", column.toString());
  }
}
