package table;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
 * Class for testing the Table class.
 */
public class TableTest {

  @Test
  public void testConstructor() {
    Table table = new Table();
    
    assertNotNull(table);
    assertNotNull(table.getChunks());
    assertNotNull(table.getCodes());
  }
  
  @Test
  public void testClone() {
    Table table = new Table();
    
    Code code = new Code("test");
    table.addCode(code);
    
    Chunk chunk = new Chunk(1, "Chunk 1");
    table.addChunk(chunk);
    
    assertEquals(table, table.clone());
    
    Table clone = (Table) table.clone();
    clone.addCode(new Code("test2"));
    
    assertNotEquals(clone, table.clone());
  }
  
  @Test
  public void testGetSetChunks() {
    Table table = new Table();
    
    List<Chunk> chunks = new ArrayList<Chunk>();
    chunks.add(new Chunk(1, "Chunk 1"));
    
    assertNotEquals(chunks, table.getChunks());
    
    table.setChunks(chunks);
    
    assertEquals(chunks, table.getChunks());
  }
  
  @Test
  public void testSetChunksNull() {
    Table table = new Table();    
    table.setChunks(null);
    
    assertNotNull(table.getChunks());
  }
  
  @Test
  public void testGetAddChunk() {
    Table table = new Table();
    
    List<Chunk> chunks = new ArrayList<Chunk>();
    Chunk chunk = new Chunk(1, "Chunk 1");
    chunks.add(chunk);
    
    assertNotEquals(chunks, table.getChunks());
    
    table.addChunk(chunk);
    
    assertEquals(chunks, table.getChunks());
  }
  
  @Test
  public void testGetAddCode() {
    Table table = new Table();
    
    Map<String, Code> codes = new HashMap<String, Code>();
    Code code = new Code("test");
    codes.put("test", code);
    
    assertNotEquals(codes, table.getCodes());
    
    table.addCode(code);
    
    assertEquals(codes, table.getCodes());
  }
  
  @Test
  public void testGetCode() {
    Table table = new Table();    
    Code code = new Code("test");
    
    assertNull(table.getCode("test"));
    
    table.addCode(code);
    
    assertEquals(code, table.getCode("test"));
  }
  
  @Test
  public void testEquals() {
    Record record = new Record();
    
    Table table = new Table();
    table.add(record);
    Table tableSame = new Table();
    tableSame.add(record);
    Table tableNotSameRecords = new Table();
    ArrayList<Record> otherClass = new ArrayList<Record>();
    otherClass.add(record);

    assertEquals(table, table);
    assertNotEquals(table, null);
    assertEquals(table, tableSame);
    assertNotEquals(table, tableNotSameRecords);
    assertNotEquals(table, otherClass);
  }
  
  @Test
  public void testHashCode() {
    List<Chunk> chunks = new ArrayList<Chunk>();
    HashMap<String, Code> codes = new HashMap<String, Code>();
    
    Table table = new Table();

    assertEquals(table.hashCode(), (31 + chunks.hashCode()) * 31 + codes.hashCode());
  }

}
