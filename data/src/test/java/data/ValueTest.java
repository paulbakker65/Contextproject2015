package data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.tudelft.hi.e.data.DateValue;
import net.tudelft.hi.e.data.NullValue;
import net.tudelft.hi.e.data.NumberValue;
import net.tudelft.hi.e.data.StringValue;
import net.tudelft.hi.e.data.TimeValue;
import net.tudelft.hi.e.data.Value;
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
    final TimeValue tv = new TimeValue(new Date(), "target");

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
  public void testCompareToTime() throws ParseException {
    final Date d1 = new SimpleDateFormat("HH:mm").parse("10:20");
    final Date d2 = new SimpleDateFormat("HH:mm").parse("12:10");

    final TimeValue tv1 = new TimeValue(d1, "target");
    final TimeValue tv2 = new TimeValue(d2, "target");
    final TimeValue tv3 = new TimeValue(d1, "target");
    final StringValue sv = new StringValue("text");

    assertEquals(tv1.compareTo(tv2), -1);
    assertEquals(tv1.compareTo(tv3), 0);
    assertEquals(tv2.compareTo(tv1), 1);
    assertEquals(tv1.compareTo(sv), Integer.MAX_VALUE);
    assertEquals(sv.compareTo(tv1), Integer.MAX_VALUE);
  }

  @Test
  public void testIsDate() {
    final Value nullValue = new NullValue();
    assertFalse(nullValue.isDate());

    final Value numberValue = new NumberValue(1.0);
    assertFalse(numberValue.isDate());

    final Value dateValue = new DateValue(new Date());
    assertTrue(dateValue.isDate());

    final Value stringValue = new StringValue("text");
    assertFalse(stringValue.isDate());

    final Value timeValue = new TimeValue(new Date(), "target");
    assertFalse(timeValue.isDate());
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

    final Value timeValue = new TimeValue(new Date(), "target");
    assertFalse(timeValue.isNull());
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

    final Value timeValue = new TimeValue(new Date(), "target");
    assertFalse(timeValue.isNumeric());
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

    final Value timeValue = new TimeValue(new Date(), "target");
    assertFalse(timeValue.isString());
  }

  @Test
  public void testIsTime() {
    final Value nullValue = new NullValue();
    assertFalse(nullValue.isTime());

    final Value numberValue = new NumberValue(1.0);
    assertFalse(numberValue.isTime());

    final Value dateValue = new DateValue(new Date());
    assertFalse(dateValue.isTime());

    final Value stringValue = new StringValue("text");
    assertFalse(stringValue.isTime());

    final Value timeValue = new TimeValue(new Date(), "target");
    assertTrue(timeValue.isTime());
  }
}
