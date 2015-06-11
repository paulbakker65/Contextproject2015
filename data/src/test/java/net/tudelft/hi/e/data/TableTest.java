package net.tudelft.hi.e.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.tudelft.hi.e.data.Chunk;
import net.tudelft.hi.e.data.Code;
import net.tudelft.hi.e.data.Column;
import net.tudelft.hi.e.data.DateColumn;
import net.tudelft.hi.e.data.DateValue;
import net.tudelft.hi.e.data.NullValue;
import net.tudelft.hi.e.data.NumberColumn;
import net.tudelft.hi.e.data.NumberValue;
import net.tudelft.hi.e.data.Record;
import net.tudelft.hi.e.data.StringColumn;
import net.tudelft.hi.e.data.StringValue;
import net.tudelft.hi.e.data.Table;
import net.tudelft.hi.e.data.TimeColumn;
import net.tudelft.hi.e.data.TimeValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;

/**
 * Class for testing the Table class.
 */
public class TableTest {

  @Test
  public void testConstructorTable() {
    final Table table = new Table();

    final Code code = new Code("test");
    table.addCode(code);

    final Chunk chunk = new Chunk(1, "Chunk 1");
    table.addChunk(chunk);

    assertEquals(table, table.clone());

    final Table clone = new Table(table);
    assertEquals(clone, new Table(table));

    clone.addCode(new Code("test2"));
    assertNotEquals(clone, new Table(table));
  }

  @Test
  public void testConstructor() {
    final Table table = new Table();

    assertNotNull(table);
    assertNotNull(table.getChunks());
    assertNotNull(table.getCodes());
  }

  @Test
  public void testEquals() {
    final Record record = new Record();

    final Table table = new Table();
    table.add(record);
    final Table tableSame = new Table();
    tableSame.add(record);
    final Table tableNotSameRecords = new Table();
    final ArrayList<Record> otherClass = new ArrayList<Record>();
    otherClass.add(record);

    assertEquals(table, table);
    assertNotEquals(table, null);
    assertEquals(table, tableSame);
    assertNotEquals(table, tableNotSameRecords);
    assertNotEquals(table, otherClass);
  }

  @Test
  public void testGetAddChunk() {
    final Table table = new Table();

    final List<Chunk> chunks = new ArrayList<Chunk>();
    final Chunk chunk = new Chunk(1, "Chunk 1");
    chunks.add(chunk);

    assertNotEquals(chunks, table.getChunks());

    table.addChunk(chunk);

    assertEquals(chunks, table.getChunks());
  }

  @Test
  public void testGetAddCode() {
    final Table table = new Table();

    final Map<String, Code> codes = new HashMap<String, Code>();
    final Code code = new Code("test");
    codes.put("test", code);

    assertNotEquals(codes, table.getCodes());

    table.addCode(code);

    assertEquals(codes, table.getCodes());
  }

  @Test
  public void testGetCode() {
    final Table table = new Table();
    final Code code = new Code("test");

    assertNull(table.getCode("test"));

    table.addCode(code);

    assertEquals(code, table.getCode("test"));
  }

  @Test
  public void testHashCode() {
    final List<Chunk> chunks = new ArrayList<Chunk>();
    final HashMap<String, Code> codes = new HashMap<String, Code>();

    final Table table = new Table();

    assertEquals(table.hashCode(), (31 + chunks.hashCode()) * 31 + codes.hashCode());
  }

  @Test
  public void testGetColumns() {
    final Table table = new Table();
    Record record = new Record();

    record.put("string", new StringValue(null));
    record.put("number", new NumberValue(0));
    record.put("date", new DateValue((Date) null));
    record.put("time", new TimeValue((Date) null, null));
    record.put("string2", new NullValue());

    Record record2 = new Record();

    record2.put("string", new NullValue());
    record2.put("number", new NullValue());
    record2.put("date", new NullValue());
    record2.put("time", new NullValue());
    record2.put("string2", new StringValue(null));

    table.add(record);
    table.add(record2);

    List<Column> expected = new ArrayList<Column>(Arrays.asList(new StringColumn("string"),
        new NumberColumn("number"), new DateColumn("date"), new TimeColumn("time"),
        new StringColumn("string2")));

    assertEquals(expected, table.getColumns());
  }

  @Test
  public void testGetColumnsEmptyTable() {
    final Table table = new Table();
    List<Column> expected = new ArrayList<Column>();

    assertEquals(expected, table.getColumns());
  }

  @Test
  public void testGetColumnsNullColumn() {
    final Table table = new Table();
    Record record = new Record();

    record.put("string", new StringValue(null));
    record.put("string2", new NullValue());

    table.add(record);

    List<Column> expected = new ArrayList<Column>(Arrays.asList(new StringColumn("string")));

    assertEquals(expected, table.getColumns());
  }

  @Test
  public void testAddAllTable() {
    final Table table = new Table();
    final Table otherTable = new Table();

    final Code code = new Code("test");
    otherTable.addCode(code);
    final Chunk chunk = new Chunk(1, "Chunk 1");
    otherTable.addChunk(chunk);

    table.addAll(otherTable);

    assertEquals(table, otherTable);
  }

  @Test
  public void testAddAllNoTable() {
    final Table table = new Table();
    final Record record = new Record();
    final List<Record> otherTable = new ArrayList<Record>();

    otherTable.add(record);
    table.addAll(otherTable);

    assertEquals(table.size(), otherTable.size());
  }
}
