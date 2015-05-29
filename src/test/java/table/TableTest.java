package table;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class for testing the Table class.
 */
public class TableTest {

  @Test
  public void testClone() {
    final Table table = new Table();

    final Code code = new Code("test");
    table.addCode(code);

    final Chunk chunk = new Chunk(1, "Chunk 1");
    table.addChunk(chunk);

    assertEquals(table, table.clone());

    final Table clone = (Table) table.clone();
    clone.addCode(new Code("test2"));

    assertNotEquals(clone, table.clone());
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
  public void testGetSetChunks() {
    final Table table = new Table();

    final List<Chunk> chunks = new ArrayList<Chunk>();
    chunks.add(new Chunk(1, "Chunk 1"));

    assertNotEquals(chunks, table.getChunks());

    table.setChunks(chunks);

    assertEquals(chunks, table.getChunks());
  }

  @Test
  public void testHashCode() {
    final List<Chunk> chunks = new ArrayList<Chunk>();
    final HashMap<String, Code> codes = new HashMap<String, Code>();

    final Table table = new Table();

    assertEquals(table.hashCode(), (31 + chunks.hashCode()) * 31 + codes.hashCode());
  }

  @Test
  public void testSetChunksNull() {
    final Table table = new Table();
    table.setChunks(null);

    assertNotNull(table.getChunks());
  }

}
