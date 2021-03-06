package net.tudelft.hi.e.computation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import net.tudelft.hi.e.data.Chunk;
import net.tudelft.hi.e.data.Record;
import net.tudelft.hi.e.data.StringValue;
import net.tudelft.hi.e.data.Table;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.List;

/**
 * Class for testing the ForEachChunkOperation.
 */
public class ForEachChunkOperationTest {
  private Operation toExecute;
  private List<Chunk> chunks;
  private int chunkIndex;

  /**
   * Setup for the mock object.
   */
  @Before
  public void setUp() {
    toExecute = Mockito.mock(Operation.class);
    chunkIndex = 0;
    Mockito.when(toExecute.execute()).thenReturn(true);
    Mockito.when(toExecute.getResult()).thenAnswer(new Answer<Chunk>() {
      @Override
      public Chunk answer(final InvocationOnMock invocation) {
        return getChunk();
      }
    });
  }

  private Chunk getChunk() {
    return chunks.get(chunkIndex++);
  }

  @Test
  public void testConstructor() {
    Table table = new Table();
    ForEachChunkOperation operation = new ForEachChunkOperation(table, 1, toExecute);

    assertNotNull(operation);
    assertEquals(table, operation.inputData);
    assertEquals(1, operation.chunkDepth);
    assertEquals(toExecute, operation.operation);
    assertTrue(operation.operationParametersSet);
  }

  @Test
  public void testConstructorWrongParams() {
    Table table = new Table();
    ForEachChunkOperation operation = new ForEachChunkOperation(table, 0, toExecute);
    assertFalse(operation.operationParametersSet);

    operation = new ForEachChunkOperation(table, 1, null);
    assertFalse(operation.operationParametersSet);
  }

  /**
   * Test whether the operation is executed the number of chunks times.
   */
  @Test
  public void testExecute() {
    final Table table = new Table();
    final Chunk chunk0 = new Chunk(0, "Chunk 0");
    final Chunk chunk1 = new Chunk(1, "Chunk 1");
    final Chunk chunk2 = new Chunk(2, "Chunk 2");
    Record record0 = new Record();
    record0.put("key0", new StringValue("value0"));
    Record record1 = new Record();
    record1.put("key0", new StringValue("value1"));
    Record record2 = new Record();
    record2.put("key0", new StringValue("value2"));
    chunk0.add(record0);
    chunk1.add(record1);
    chunk2.add(record2);
    table.addChunk(chunk0);
    table.addChunk(chunk1);
    table.addChunk(chunk2);
    chunks = table.getChunks();

    ForEachChunkOperation operation = new ForEachChunkOperation(table, 1, toExecute);
    operation.execute();

    for (Chunk chunk : chunks) {
      Mockito.verify(toExecute, Mockito.times(1)).resetData(new Table(chunk));
    }
    Mockito.verify(toExecute, Mockito.times(chunks.size())).execute();
    Mockito.verify(toExecute, Mockito.times(chunks.size())).getResult();
  }

  @Test
  public void testExecuteNoParametersSet() {
    Table table = new Table();
    ForEachChunkOperation operation = new ForEachChunkOperation(table, 0, toExecute);
    assertFalse(operation.execute());
  }

  @Test
  public void testExecuteOperationFails() {
    Table table = new Table();
    Chunk chunk0 = new Chunk(0, "Chunk 0");
    table.addChunk(chunk0);
    Mockito.when(toExecute.execute()).thenReturn(false);
    ForEachChunkOperation operation = new ForEachChunkOperation(table, 1, toExecute);
    assertFalse(operation.execute());
  }

  @Test
  public void testGetResult() {
    Table table = new Table();
    Chunk chunk0 = new Chunk(0, "Chunk 0");
    table.addChunk(chunk0);
    chunks = table.getChunks();
    ForEachChunkOperation operation = new ForEachChunkOperation(table, 1, toExecute);
    operation.execute();
    assertEquals(table, operation.getResult());
  }
}
