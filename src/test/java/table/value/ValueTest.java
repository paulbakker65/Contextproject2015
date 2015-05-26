package table.value;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

/**
 * ValueTest class testing table.Value class.
 * 
 */
public class ValueTest {

  @Test
  public void testIsNull() {
    Value nullValue = new NullValue();
    Value numberValue = new NumberValue(1.0);
    Value dateValue = new DateValue(new Date());
    Value stringValue = new StringValue("text");
    Value timeValue = new TimeValue(new Date(), "target");

    assertTrue(nullValue.isNull());
    assertFalse(numberValue.isNull());
    assertFalse(dateValue.isNull());
    assertFalse(stringValue.isNull());
    assertFalse(timeValue.isNull());
  }

  @Test
  public void testIsNumeric() {
    Value nullValue = new NullValue();
    Value numberValue = new NumberValue(1.0);
    Value dateValue = new DateValue(new Date());
    Value stringValue = new StringValue("text");
    Value timeValue = new TimeValue(new Date(), "target");

    assertFalse(nullValue.isNumeric());
    assertTrue(numberValue.isNumeric());
    assertFalse(dateValue.isNumeric());
    assertFalse(stringValue.isNumeric());
    assertFalse(timeValue.isNumeric());
  }

  @Test
  public void testIsDate() {
    Value nullValue = new NullValue();
    Value numberValue = new NumberValue(1.0);
    Value dateValue = new DateValue(new Date());
    Value stringValue = new StringValue("text");
    Value timeValue = new TimeValue(new Date(), "target");

    assertFalse(nullValue.isDate());
    assertFalse(numberValue.isDate());
    assertTrue(dateValue.isDate());
    assertFalse(stringValue.isDate());
    assertFalse(timeValue.isDate());
  }

  @Test
  public void testIsString() {
    Value nullValue = new NullValue();
    Value numberValue = new NumberValue(1.0);
    Value dateValue = new DateValue(new Date());
    Value stringValue = new StringValue("text");
    Value timeValue = new TimeValue(new Date(), "target");

    assertFalse(nullValue.isString());
    assertFalse(numberValue.isString());
    assertFalse(dateValue.isString());
    assertTrue(stringValue.isString());
    assertFalse(timeValue.isString());
  }

  @Test
  public void testIsTime() {
    Value nullValue = new NullValue();
    Value numberValue = new NumberValue(1.0);
    Value dateValue = new DateValue(new Date());
    Value stringValue = new StringValue("text");
    Value timeValue = new TimeValue(new Date(), "target");

    assertFalse(nullValue.isTime());
    assertFalse(numberValue.isTime());
    assertFalse(dateValue.isTime());
    assertFalse(stringValue.isTime());
    assertTrue(timeValue.isTime());
  }
  
  @Test
  public void testCompareToString() {
    StringValue sv1 = new StringValue("a");
    StringValue sv2 = new StringValue("b");
    StringValue sv3 = new StringValue("a");
    NumberValue nv = new NumberValue(1.0);
    
    assertEquals(sv1.compareTo(sv2), -1);
    assertEquals(sv1.compareTo(sv3), 0);
    assertEquals(sv2.compareTo(sv1), 1);
    assertEquals(sv1.compareTo(nv), Integer.MAX_VALUE);
    assertEquals(nv.compareTo(sv1), Integer.MAX_VALUE);
  }
  
  @Test
  public void testCompareToNumber() {
    NumberValue nv1 = new NumberValue(1.0);
    NumberValue nv2 = new NumberValue(2.0);
    NumberValue nv3 = new NumberValue(1.0);
    DateValue dv = new DateValue(new Date());
    
    assertEquals(nv1.compareTo(nv2), -1);
    assertEquals(nv1.compareTo(nv3), 0);
    assertEquals(nv2.compareTo(nv1), 1);
    assertEquals(nv1.compareTo(dv), Integer.MAX_VALUE);
    assertEquals(dv.compareTo(nv1), Integer.MAX_VALUE);
  }
  
  @Test
  public void testCompareToDate() throws ParseException {
    Date d1 = new SimpleDateFormat("yy/MM/dd").parse("15/11/30");
    Date d2 = new SimpleDateFormat("yy/MM/dd").parse("15/12/10");
    
    DateValue dv1 = new DateValue(d1);
    DateValue dv2 = new DateValue(d2);
    DateValue dv3 = new DateValue(d1);
    NullValue nullv = new NullValue();
    
    assertEquals(dv1.compareTo(dv2), -1);
    assertEquals(dv1.compareTo(dv3), 0);
    assertEquals(dv2.compareTo(dv1), 1);
    assertEquals(dv1.compareTo(nullv), Integer.MAX_VALUE);
    assertEquals(nullv.compareTo(dv1), Integer.MAX_VALUE);
  }
  
  @Test
  public void testCompareToNull() {
    NullValue nv1 = new NullValue();
    NullValue nv2 = new NullValue();
    TimeValue tv = new TimeValue(new Date(), "target");
    
    assertEquals(nv1.compareTo(nv2), 0);
    assertEquals(nv2.compareTo(nv1), 0);
    assertEquals(nv1.compareTo(tv), Integer.MAX_VALUE);
    assertEquals(tv.compareTo(nv1), Integer.MAX_VALUE);
  }
  
  @Test
  public void testCompareToTime() throws ParseException {
    Date d1 = new SimpleDateFormat("HH:mm").parse("10:20");
    Date d2 = new SimpleDateFormat("HH:mm").parse("12:10");
    
    TimeValue tv1 = new TimeValue(d1, "target");
    TimeValue tv2 = new TimeValue(d2, "target");
    TimeValue tv3 = new TimeValue(d1, "target");
    StringValue sv = new StringValue("text");
    
    assertEquals(tv1.compareTo(tv2), -1);
    assertEquals(tv1.compareTo(tv3), 0);
    assertEquals(tv2.compareTo(tv1), 1);
    assertEquals(tv1.compareTo(sv), Integer.MAX_VALUE);
    assertEquals(sv.compareTo(tv1), Integer.MAX_VALUE);
  }
}
