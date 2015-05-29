package table.value;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

/**
 * ColumnTest class testing the table.Column class.
 * 
 */
public class ColumnTest {

  @Test
  public void testColumnConstructor() {
    Column column = new StringColumn("testName");
    assertNotNull(column);
    assertEquals("testName", column.getName());
  }

  @Test
  public void testColumnGetSetName() {
    Column column = new StringColumn("testName");
    assertEquals("testName", column.getName());

    column.setName("newName");
    assertEquals("newName", column.getName());
  }

  @Test
  public void testStringColumnConstructor() {
    StringColumn column = new StringColumn("testName");
    assertNotNull(column);
    assertEquals("testName", column.getName());
  }

  @Test
  public void testStringColumnToString() {
    StringColumn column = new StringColumn("testName");
    assertEquals("name: testName,\ttype: text", column.toString());
  }

  @Test
  public void testStringColumnConvertToValueCorrect() {
    StringColumn column = new StringColumn("testName");
    StringValue value = new StringValue("Correct");
    assertEquals(value, column.convertToValue("Correct"));
  }

  @Test
  public void testStringColumnConvertToValueNull() {
    StringColumn column = new StringColumn("testName");
    NullValue value = new NullValue();
    assertEquals(value, column.convertToValue("NULL"));
  }

  @Test
  public void testStringColumnConvertToValueEmpty() {
    StringColumn column = new StringColumn("testName");
    NullValue value = new NullValue();
    assertEquals(value, column.convertToValue(""));
  }

  @Test
  public void testNumberColumnConstructor() {
    NumberColumn column = new NumberColumn("testName");
    assertNotNull(column);
    assertEquals("testName", column.getName());
  }

  @Test
  public void testNumberColumnToString() {
    NumberColumn column = new NumberColumn("testName");
    assertEquals("name: testName,\ttype: number", column.toString());
  }

  @Test
  public void testNumberColumnConvertToValueCorrect() throws ColumnTypeMismatchException {
    NumberColumn column = new NumberColumn("testName");
    NumberValue value = new NumberValue(10.0);
    assertEquals(value, column.convertToValue("10"));
  }

  @Test
  public void testNumberColumnConvertToValueNull() throws ColumnTypeMismatchException {
    NumberColumn column = new NumberColumn("testName");
    NullValue value = new NullValue();
    assertEquals(value, column.convertToValue("NULL"));
  }

  @Test
  public void testNumberColumnConvertToValueEmpty() throws ColumnTypeMismatchException {
    NumberColumn column = new NumberColumn("testName");
    NullValue value = new NullValue();
    assertEquals(value, column.convertToValue(""));
  }

  @Test(expected = ColumnTypeMismatchException.class)
  public void testNumberColumnConvertToValueException() throws ColumnTypeMismatchException {
    new NumberColumn("testName").convertToValue("text");
  }

  @Test
  public void testDateColumnConstructorDefaultFormat() {
    DateColumn column = new DateColumn("testName");
    assertNotNull(column);
    assertEquals("testName", column.getName());
    assertEquals("yyyy-MM-dd", column.getFormatStr());
    assertEquals(new SimpleDateFormat("yyyy-MM-dd"), column.getFormat());
  }

  @Test
  public void testDateColumnConstructorWithFormat() {
    DateColumn column = new DateColumn("testName", "dd-MM-yyyy");
    assertNotNull(column);
    assertEquals("testName", column.getName());
    assertEquals("dd-MM-yyyy", column.getFormatStr());
    assertEquals(new SimpleDateFormat("dd-MM-yyyy"), column.getFormat());
  }

  @Test
  public void testDateColumnToString() {
    DateColumn column = new DateColumn("testName", "dd-MM-yyyy");
    assertEquals("name: testName,\ttype: date,\tformat: dd-MM-yyyy", column.toString());
  }

  @Test
  public void testDateColumnConvertToValueCorrect() throws ColumnTypeMismatchException,
      ParseException {
    DateColumn column = new DateColumn("testName", "dd-MM-yyyy");
    DateValue value = new DateValue(new SimpleDateFormat("dd-MM-yyyy").parse("31-12-2014"));
    assertEquals(value, column.convertToValue("31-12-2014"));
  }

  @Test
  public void testDateColumnConvertToValueCorrectExcel() throws ColumnTypeMismatchException,
      ParseException {
    DateColumn column = new DateColumn("testName", "excel");
    DateValue value = new DateValue(new SimpleDateFormat("dd-MM-yyyy").parse("15-10-2012"));
    assertEquals(value, column.convertToValue("41197"));
  }

  @Test
  public void testDateColumnConvertToValueNull() throws ColumnTypeMismatchException {
    DateColumn column = new DateColumn("testName", "dd-MM-yyyy");
    NullValue value = new NullValue();
    assertEquals(value, column.convertToValue("NULL"));
  }

  @Test
  public void testDateColumnConvertToValueEmpty() throws ColumnTypeMismatchException {
    DateColumn column = new DateColumn("testName", "dd-MM-yyyy");
    NullValue value = new NullValue();
    assertEquals(value, column.convertToValue(""));
  }

  @Test(expected = ColumnTypeMismatchException.class)
  public void testDateColumnConvertToValueException() throws ColumnTypeMismatchException,
      ParseException {
    new DateColumn("testName", "dd-MM-yyyy").convertToValue("2014/12/31");
  }

  @Test(expected = ColumnTypeMismatchException.class)
  public void testDateColumnConvertToValueExcelException() throws ColumnTypeMismatchException,
      ParseException {
    new DateColumn("testName", "excel").convertToValue("text");
  }

  @Test
  public void testTimeColumnConstructorDefaultFormatDefaultTarget() {
    TimeColumn column = new TimeColumn("testName");
    assertNotNull(column);
    assertEquals("testName", column.getName());
    assertEquals("hh:mm", column.getFormatStr());
    assertEquals(new SimpleDateFormat("hh:mm"), column.getFormat());
    assertEquals(null, column.getTargetDate());
  }

  @Test
  public void testTimeColumnConstructorWithFormatDefaultTarget() {
    TimeColumn column = new TimeColumn("testName", "hhmm");
    assertNotNull(column);
    assertEquals("testName", column.getName());
    assertEquals("hhmm", column.getFormatStr());
    assertEquals(new SimpleDateFormat("hhmm"), column.getFormat());
    assertEquals(null, column.getTargetDate());
  }

  @Test
  public void testTimeColumnConstructorWithFormatWithTarget() {
    TimeColumn column = new TimeColumn("testName", "hhmm", "Date");
    assertNotNull(column);
    assertEquals("testName", column.getName());
    assertEquals("hhmm", column.getFormatStr());
    assertEquals(new SimpleDateFormat("hhmm"), column.getFormat());
    assertEquals("Date", column.getTargetDate());
  }

  @Test
  public void testTimeColumnToString() {
    TimeColumn column = new TimeColumn("testName", "hhmm", "Date");
    assertEquals("name: testName,\ttype: time,\tformat: hhmm,\ttarget: Date", column.toString());
  }

  @Test
  public void testTimeColumnConvertToValueCorrect() throws ColumnTypeMismatchException,
      ParseException {
    TimeColumn column = new TimeColumn("testName", "hhmm", "Date");
    TimeValue value = new TimeValue(new SimpleDateFormat("hhmm").parse("1020"), "Date");
    assertEquals(value, column.convertToValue("1020"));
  }

  @Test
  public void testTimeColumnConvertToValueNull() throws ColumnTypeMismatchException {
    TimeColumn column = new TimeColumn("testName", "hhmm", "Date");
    NullValue value = new NullValue();
    assertEquals(value, column.convertToValue("NULL"));
  }

  @Test
  public void testTimeColumnConvertToValueEmpty() throws ColumnTypeMismatchException {
    TimeColumn column = new TimeColumn("testName", "hhmm", "Date");
    NullValue value = new NullValue();
    assertEquals(value, column.convertToValue(""));
  }

  @Test(expected = ColumnTypeMismatchException.class)
  public void testTimeColumnConvertToValueException() throws ColumnTypeMismatchException,
      ParseException {
    new TimeColumn("testName", "hhmm", "Date").convertToValue("31/12/2014");
  }

  @SuppressWarnings("unused")
  @Test(expected = ColumnTypeMismatchException.class)
  public void testTimeColumnConvertToValueExcelException() throws ColumnTypeMismatchException,
      ParseException {
    TimeColumn column = new TimeColumn("testName", "hhmm", "Date");
    Value value = column.convertToValue("text");
  }
}
