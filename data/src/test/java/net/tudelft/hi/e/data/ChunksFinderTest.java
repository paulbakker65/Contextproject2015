package net.tudelft.hi.e.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ChunksFinderTest {
	@Test
	public void testGetChunkListsEmpty() {
		Table table = new Table();
		List<List<Chunk>> expected = new ArrayList<List<Chunk>>();

		assertEquals(expected, ChunksFinder.getChunkLists(table, 1));
		assertEquals(expected, ChunksFinder.getChunkLists(table, 2));
	}

	@Test
	public void testGetChunkListsFirstDepth() {
		Table table = new Table();
		Chunk chunk = new Chunk(0, "Chunk 0");
		table.addChunk(chunk);
		
		List<List<Chunk>> expected = new ArrayList<List<Chunk>>();
		expected.add(new ArrayList<Chunk>(Arrays.asList(chunk)));

		assertEquals(expected, ChunksFinder.getChunkLists(table, 1));
		assertEquals(expected, ChunksFinder.getChunkLists(table, 2));
	}

	@Test
	public void testGetChunkListsSecondDepth() {
		Table table = new Table();
		Chunk chunk0 = new Chunk(0, "Chunk 0");
		Chunk chunk00 = new Chunk(1, "Chunk 0.0");
		table.addChunk(chunk0);
		chunk0.addChunk(chunk00);
		
		List<List<Chunk>> expected = new ArrayList<List<Chunk>>();
		expected.add(new ArrayList<Chunk>(Arrays.asList(chunk00)));

		assertEquals(expected, ChunksFinder.getChunkLists(table, 2));
		assertEquals(expected, ChunksFinder.getChunkLists(table, 6));
	}

	@Test
	public void testGetChunkListsThirdDepth() {
		Table table = new Table();
		Chunk chunk0 = new Chunk(0, "Chunk 0");
		Chunk chunk00 = new Chunk(1, "Chunk 0.0");
		Chunk chunk000 = new Chunk(2, "Chunk 0.0.0");
		table.addChunk(chunk0);
		chunk0.addChunk(chunk00);
		chunk00.addChunk(chunk000);
		
		List<List<Chunk>> expected = new ArrayList<List<Chunk>>();
		expected.add(new ArrayList<Chunk>(Arrays.asList(chunk000)));

		assertEquals(expected, ChunksFinder.getChunkLists(table, 3));

		expected = new ArrayList<List<Chunk>>();
		expected.add(new ArrayList<Chunk>(Arrays.asList(chunk0)));
		assertEquals(expected, ChunksFinder.getChunkLists(table, 1));
	}
	
	@Test
	public void testGetChunksEmpty() {
		Table table = new Table();
		List<Chunk> expected = new ArrayList<Chunk>();

		assertEquals(expected, ChunksFinder.getChunks(table, 1));
		assertEquals(expected, ChunksFinder.getChunks(table, 2));
	}
	
	@Test
	public void testGetChunksFirstDepth() {
		Table table = new Table();
		Chunk chunk = new Chunk(0, "Chunk 0");
		table.addChunk(chunk);
		
		List<Chunk> expected = new ArrayList<Chunk>(Arrays.asList(chunk));

		assertEquals(expected, ChunksFinder.getChunks(table, 1));
		assertEquals(expected, ChunksFinder.getChunks(table, 2));
	}
	
	@Test
	public void testGetChunksSecondDepth() {
		Table table = new Table();
		Chunk chunk0 = new Chunk(0, "Chunk 0");
		Chunk chunk00 = new Chunk(1, "Chunk 0.0");
		table.addChunk(chunk0);
		chunk0.addChunk(chunk00);
		
		List<Chunk> expected = new ArrayList<Chunk>(Arrays.asList(chunk00));

		assertEquals(expected, ChunksFinder.getChunks(table, 2));
		assertEquals(expected, ChunksFinder.getChunks(table, 6));
	}

	@Test
	public void testMaxDepth() {
		Table table = new Table();
		assertEquals(0, ChunksFinder.maxDepth(table));

		Chunk chunk = new Chunk(0, "Chunk 0");
		table.addChunk(chunk);
		assertEquals(1, ChunksFinder.maxDepth(table));

		Chunk chunkSecond = new Chunk(1, "Chunk 1");
		chunk.addChunk(chunkSecond);
		assertEquals(2, ChunksFinder.maxDepth(table));
	}

	@Test
	public void testMaxDepthDifferentDepths() {
		Table table = new Table();
		Chunk chunk0 = new Chunk(0, "Chunk 0.0");
		Chunk chunk1 = new Chunk(1, "Chunk 0.1");
		Chunk chunk2 = new Chunk(2, "Chunk 0.0.1");
		table.addChunk(chunk0);
		table.addChunk(chunk1);
		chunk0.addChunk(chunk2);
		
		assertEquals(2, ChunksFinder.maxDepth(table));
	}
	
	@Test
	  public void testConstructorIsPrivate() throws NoSuchMethodException, IllegalAccessException,
	      InvocationTargetException, InstantiationException {
	    Constructor<ChunksFinder> constructor = ChunksFinder.class.getDeclaredConstructor();
	    assertTrue(Modifier.isPrivate(constructor.getModifiers()));
	    constructor.setAccessible(true);
	    constructor.newInstance();
	  }
}
