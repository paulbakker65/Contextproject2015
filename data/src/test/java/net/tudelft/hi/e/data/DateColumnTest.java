package net.tudelft.hi.e.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Objects;

/**
 * Created by mawdegroot on 18/06/15.
 */
public class DateColumnTest {

  private DateColumn someColumn;

  @Before
  public void setUp() {
    someColumn = new DateColumn("someColumn");
    someColumn.setFormat("dd/MM/yyyy");
  }

  @Test
  public void testHashCode() {
    int hash = 5;
    hash = 67 * hash + Objects.hashCode(someColumn.getFormat());
    hash = 67 * hash + Objects.hashCode(someColumn.getFormatStr());
    hash = 67 * hash + Objects.hashCode(DateColumn.ISO_FORMAT);
    assertEquals(hash, someColumn.hashCode());
  }

  @Test
  public void testEquals() {
    DateColumn someOtherColumn = new DateColumn("someColumn");
    someOtherColumn.setFormat("dd/MM/yyyy");

    assertEquals(someColumn, someColumn);
    assertNotEquals(someColumn, null);
    assertNotEquals(someColumn, new Table());
  }

  @Test
  public void testSetFormat() {
    assertEquals(new SimpleDateFormat("dd/MM/yyyy"), someColumn.getFormat());
    someColumn.setFormat("excel");
    assertEquals(null, someColumn.getFormat());
  }

  @Test
  public void testSetGetTargetDate() {
    assertEquals(null, someColumn.getTargetDate());
    someColumn.setTargetDate("another");
    assertEquals("another", someColumn.getTargetDate());
  }

  @Test
  public void testGetType() {
    assertEquals("date", someColumn.getType());
    assertNotEquals("null", someColumn.getType());
  }
}
