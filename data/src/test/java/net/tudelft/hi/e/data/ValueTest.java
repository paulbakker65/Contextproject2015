package net.tudelft.hi.e.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * ValueTest class testing table.Value class.
 *
 */
public class ValueTest {

  @Test
  public void testCompareToDate() throws ParseException {
    final Date d1 = new SimpleDateFormat("yy/MM/dd").parse("15/11/30");
    final Date d2 = new SimpleDateFormat("yy/MM/dd").parse("15/12/10");

    final DateValue dv1 = new DateValue(d1);
    final DateValue dv2 = new DateValue(d2);
    final DateValue dv3 = new DateValue(d1);
    final NullValue nullv = new NullValue();

    assertEquals(dv1.compareTo(dv2), -1);
    assertEquals(dv1.compareTo(dv3), 0);
    assertEquals(dv2.compareTo(dv1), 1);
    assertEquals(dv1.compareTo(nullv), Integer.MAX_VALUE);
    assertEquals(nullv.compareTo(dv1), Integer.MAX_VALUE);
  }

  @Test
  public void testCompareToNull() {
    final NullValue nv1 = new NullValue();
    final NullValue nv2 = new NullValue();
    final StringValue tv = new StringValue("text");

    assertEquals(nv1.compareTo(nv2), 0);
    assertEquals(nv2.compareTo(nv1), 0);
    assertEquals(nv1.compareTo(tv), Integer.MAX_VALUE);
    assertEquals(tv.compareTo(nv1), Integer.MAX_VALUE);
  }

  @Test
  public void testCompareToNumber() {
    final NumberValue nv1 = new NumberValue(1.0);
    final NumberValue nv2 = new NumberValue(2.0);
    final NumberValue nv3 = new NumberValue(1.0);
    final DateValue dv = new DateValue(new Date());

    assertEquals(nv1.compareTo(nv2), -1);
    assertEquals(nv1.compareTo(nv3), 0);
    assertEquals(nv2.compareTo(nv1), 1);
    assertEquals(nv1.compareTo(dv), Integer.MAX_VALUE);
    assertEquals(dv.compareTo(nv1), Integer.MAX_VALUE);
  }

  @Test
  public void testCompareToString() {
    final StringValue sv1 = new StringValue("a");
    final StringValue sv2 = new StringValue("b");
    final StringValue sv3 = new StringValue("a");
    final NumberValue nv = new NumberValue(1.0);

    assertEquals(sv1.compareTo(sv2), -1);
    assertEquals(sv1.compareTo(sv3), 0);
    assertEquals(sv2.compareTo(sv1), 1);
    assertEquals(sv1.compareTo(nv), Integer.MAX_VALUE);
    assertEquals(nv.compareTo(sv1), Integer.MAX_VALUE);
  }

  @Test
  public void testIsDate() {
    final Value nullValue = new NullValue();
    assertFalse(nullValue.isDate());

    final Value numberValue = new NumberValue(1.0);
    assertFalse(numberValue.isDate());

    final Value dateValue = new DateValue(new Date(), new DateColumn("name", "ddMMyy", null));
    assertTrue(dateValue.isDate());

    final Value stringValue = new StringValue("text");
    assertFalse(stringValue.isDate());

    final Value timeValue = new DateValue(new Date(), new DateColumn("name", "ddMMyy", "Date"));
    assertTrue(timeValue.isDate());
  }

  @Test
  public void testIsNull() {
    final Value nullValue = new NullValue();
    assertTrue(nullValue.isNull());

    final Value numberValue = new NumberValue(1.0);
    assertFalse(numberValue.isNull());

    final Value dateValue = new DateValue(new Date());
    assertFalse(dateValue.isNull());

    final Value stringValue = new StringValue("text");
    assertFalse(stringValue.isNull());
  }

  @Test
  public void testIsNumeric() {
    final Value nullValue = new NullValue();
    assertFalse(nullValue.isNumeric());

    final Value numberValue = new NumberValue(1.0);
    assertTrue(numberValue.isNumeric());

    final Value dateValue = new DateValue(new Date());
    assertFalse(dateValue.isNumeric());

    final Value stringValue = new StringValue("text");
    assertFalse(stringValue.isNumeric());
  }

  @Test
  public void testIsString() {
    final Value nullValue = new NullValue();
    assertFalse(nullValue.isString());

    final Value numberValue = new NumberValue(1.0);
    assertFalse(numberValue.isString());

    final Value dateValue = new DateValue(new Date());
    assertFalse(dateValue.isString());

    final Value stringValue = new StringValue("text");
    assertTrue(stringValue.isString());
  }

  @Test
  public void testIsTime() {
    final Value nullValue = new NullValue();
    assertFalse(nullValue.isTime());

    final Value numberValue = new NumberValue(1.0);
    assertFalse(numberValue.isTime());

    final Value dateValue = new DateValue(new Date(), new DateColumn("name", "ddMMyy", null));
    assertFalse(dateValue.isTime());

    final Value stringValue = new StringValue("text");
    assertFalse(stringValue.isTime());

    final Value timeValue = new DateValue(new Date(), new DateColumn("name", "ddMMyy", "Date"));
    assertTrue(timeValue.isTime());
  }
}
