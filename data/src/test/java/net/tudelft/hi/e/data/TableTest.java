package net.tudelft.hi.e.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    record.put("date", new DateValue((Date) null, new DateColumn("date", "ddMMyy", null)));
    record.put("time", new DateValue((Date) null, new DateColumn("date", "ddMMyy", "date")));
    record.put("string2", new NullValue());

    Record record2 = new Record();

    record2.put("string", new NullValue());
    record2.put("number", new NullValue());
    record2.put("date", new NullValue());
    record2.put("time", new NullValue());
    record2.put("string2", new StringValue(null));

    table.add(record);
    table.add(record2);

    List<Column> expected =
        new ArrayList<Column>(Arrays.asList(new StringColumn("string"), new NumberColumn("number"),
            new DateColumn("date", "ddMMyy", null), new DateColumn("time", "ddMMyy", "date"),
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

  @Test
  public void testToString() {
    final Table table = new Table();
    Record record = new Record();

    record.put("string", new StringValue(""));
    record.put("number", new NumberValue(0));
    record.put("date", new DateValue((Date) GregorianCalendar.getInstance().getTime(),
        new DateColumn("date", "ddMMyy", null)));
    record.put("time", new DateValue((Date) GregorianCalendar.getInstance().getTime(),
        new DateColumn("date", "ddMMyy", "date")));
    record.put("string2", new StringValue(""));

    Record record2 = new Record();

    record2.put("string", new StringValue(""));
    record2.put("number", new NumberValue(0));
    record2.put("date", new DateValue((Date) GregorianCalendar.getInstance().getTime(),
        new DateColumn("date", "ddMMyy", null)));
    record2.put("time", new DateValue((Date) GregorianCalendar.getInstance().getTime(),
        new DateColumn("date", "ddMMyy", "date")));
    record2.put("string2", new StringValue(""));

    table.add(record);
    table.add(record2);

    final StringBuilder sb = new StringBuilder();
    for (final Record tableRecord : table) {
      sb.append(tableRecord.toString());
      sb.append(System.getProperty("line.separator"));
    }
    assertEquals(sb.toString(), table.toString());
  }

  @Test
  public void testSetChunks() {
    Table table = new Table();
    assertEquals(new ArrayList<Table>(), table.getChunks());
    ArrayList<Chunk> chunks = new ArrayList<Chunk>();
    chunks.add(new Chunk(0, "chunk0"));
    chunks.add(new Chunk(1, "chunk1"));
    table.setChunks(chunks);
    assertEquals(chunks, table.getChunks());

    table.setChunks(null);
    assertEquals(new ArrayList<Table>(), table.getChunks());
  }

  @Test
  public void testSetCodes() {
    Table table = new Table();
    assertEquals(new HashMap<String, Code>(), table.getCodes());

    HashMap<String, Code> map = new HashMap<String, Code>();
    map.put("booh", new Code("booh"));
    map.put("mooh", new Code("mooh"));

    table.setCodes(map);

    assertEquals(map, table.getCodes());
  }

  @Test
  public void testConstructorCopyRecords() {
    final Table table = new Table();
    Record record = new Record();

    record.put("string", new StringValue(""));
    record.put("number", new NumberValue(0));
    record.put("date", new DateValue((Date) GregorianCalendar.getInstance().getTime(),
        new DateColumn("date", "ddMMyy", null)));
    record.put("time", new DateValue((Date) GregorianCalendar.getInstance().getTime(),
        new DateColumn("date", "ddMMyy", "date")));
    record.put("string2", new StringValue(""));

    Record record2 = new Record();

    record2.put("string", new StringValue(""));
    record2.put("number", new NumberValue(0));
    record2.put("date", new DateValue((Date) GregorianCalendar.getInstance().getTime(),
        new DateColumn("date", "ddMMyy", null)));
    record2.put("time", new DateValue((Date) GregorianCalendar.getInstance().getTime(),
        new DateColumn("date", "ddMMyy", "date")));
    record2.put("string2", new StringValue(""));

    table.add(record);
    table.add(record2);

    assertEquals(table, table);
    assertEquals(table, new Table(table, true));
  }
}
