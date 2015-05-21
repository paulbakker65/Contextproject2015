package table;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Simple chunk test, checking just the constructor/getter/setter.
 * @author paulbakker
 */
public class ChunkTest {
  
  @Test
  public void testConstructor() {
    Chunk c = new Chunk(1, "test");
    assertEquals(c.getIndex(), 1);
    assertEquals(c.getLabel(), "test");
  }
  
  @Test
  public void testGetterSetterIndexLabel() {
    Chunk c = new Chunk(5, "");
    c.setIndex(1);
    c.setLabel("testing");
    
    assertEquals(1, c.getIndex());
    assertEquals("testing", c.getLabel());
  }

}
