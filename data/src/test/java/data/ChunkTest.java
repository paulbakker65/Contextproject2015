package data;

import net.tudelft.hi.e.data.Chunk;
import net.tudelft.hi.e.data.Table;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 * Class for testing the Chunk class.
 */
public class ChunkTest {

  @Test
  public void testConstructor() {
    final Chunk chunk = new Chunk(1, "test");

    assertNotNull(chunk);
    assertEquals(chunk.getIndex(), 1);
    assertEquals(chunk.getLabel(), "test");
  }

  @Test
  public void testEquals() {
    final Chunk chunk = new Chunk(1, "Chunk 1");
    final Chunk chunkSame = new Chunk(1, "Chunk 1");
    final Chunk chunkNotSameIndex = new Chunk(2, "Chunk 1");
    final Chunk chunkNotSameLabel = new Chunk(1, "Chunk 2");
    final Chunk chunkNullLabel1 = new Chunk(1, null);
    final Chunk chunkNullLabel2 = new Chunk(1, null);
    final Table otherClass = new Table();

    assertEquals(chunk, chunk);
    assertNotEquals(chunk, null);
    assertEquals(chunk, chunkSame);
    assertNotEquals(chunk, chunkNotSameIndex);
    assertNotEquals(chunk, chunkNotSameLabel);
    assertNotEquals(chunk, chunkNullLabel1);
    assertNotEquals(chunkNullLabel1, chunk);
    assertEquals(chunkNullLabel1, chunkNullLabel2);
    assertNotEquals(chunk, otherClass);
  }

  @Test
  public void testGetSetIndex() {
    final Chunk chunk = new Chunk(5, "");

    assertEquals(5, chunk.getIndex());

    chunk.setIndex(1);

    assertEquals(1, chunk.getIndex());
  }

  @Test
  public void testGetSetLabel() {
    final Chunk chunk = new Chunk(5, "test");

    assertEquals("test", chunk.getLabel());

    chunk.setLabel("test2");

    assertEquals("test2", chunk.getLabel());
  }

  @Test
  public void testHashCode() {
    final int superHashCode = new Table().hashCode();

    final Chunk chunk = new Chunk(1, "Chunk 1");
    final Chunk chunkNullLabel = new Chunk(1, null);

    assertEquals(chunk.hashCode(), (31 * superHashCode + 1) * 31 + "Chunk 1".hashCode());
    assertEquals(chunkNullLabel.hashCode(), (31 * superHashCode + 1) * 31);
  }

}
