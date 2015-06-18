package net.tudelft.hi.e.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Helper class to retrieve chunks at a certain depth.
 */
public class ChunksFinder {
	public static final String DEFAULT_CHUNK_NAME = "Default Chunk";

	/**
	 * Returns all lists which link to chunks so that is known on which position
	 * newly created chunks can be inserted.
	 * 
	 * @param table
	 *            the table to search.
	 * @param chunkDepth
	 *            the depth to return the lists from.
	 * @return all lists which link to chunks.
	 */
	public static List<List<Chunk>> getChunkLists(Table table, int chunkDepth) {
		int correctDepth = getCorrectDepth(table, chunkDepth);
		return getChunksListAtDepth(table, correctDepth - 1);
	}

	/**
	 * Returns all chunks on a certain depth.
	 * 
	 * @param table
	 *            the table to search.
	 * @param chunkDepth
	 *            the depth to return the chunks from.
	 * @return all chunks on a certain depth.
	 */
	public static List<Chunk> getChunks(Table table, int chunkDepth) {
		int correctDepth = getCorrectDepth(table, chunkDepth);
		return getChunksAtDepth(table, correctDepth - 1);
	}

	/**
	 * Returns the maximum depth found in the table.
	 *
	 * @param table
	 *            the table to search.
	 * @return the maximum depth found in the table.
	 */
	public static int maxDepth(Table table) {
		return maxDepth(table.getChunks());
	}

	/**
	 * Extract the chunks in a table, or the table itself if there are no
	 * chunks.
	 * 
	 * @param table
	 *            table to extract from
	 * @param chunkDepth
	 * 			  the depth to search at
	 * @return list of chunks, or one chunk containing all the records
	 */
	public static List<Chunk> extractChunks(Table table, int chunkDepth) {
		List<Chunk> chunks = getChunks(table, chunkDepth);

		if (chunks.isEmpty()) {
			Chunk defaultChunk = new Chunk(0, DEFAULT_CHUNK_NAME);
			chunks.add(new Chunk(defaultChunk, table));
		}

		return chunks;
	}

	private static int maxDepth(List<Chunk> chunks) {
		if (chunks.isEmpty()) {
			return 0;
		}

		int max = Integer.MIN_VALUE;
		for (Chunk chunk : chunks) {
			int depth = maxDepth(chunk.getChunks());
			if (depth > max) {
				max = depth;
			}
		}
		return 1 + max;
	}

	/**
	 * Returns all the chunklists at a certain depth (assuming that these lists
	 * are not empty).
	 *
	 * @param table
	 *            the table to search.
	 * @param depth
	 *            the depth to return the chunklists from.
	 * @return all the chunklists at a certain depth.
	 */
	private static List<List<Chunk>> getChunksListAtDepth(Table table, int depth) {
		if (depth == -1) {
			return new ArrayList<List<Chunk>>();
		}
		if (depth == 0) {
			return new ArrayList<List<Chunk>>(Arrays.asList(table.getChunks()));
		}

		return getChunksListAtDepth(table.getChunks(), depth - 1);
	}

	private static List<List<Chunk>> getChunksListAtDepth(List<Chunk> chunks,
			int depth) {
		if (depth == 0) {
			List<List<Chunk>> res = new ArrayList<List<Chunk>>();
			for (Chunk chunk : chunks) {
				res.add(chunk.getChunks());
			}
			return res;
		}

		List<Chunk> newChunks = new ArrayList<Chunk>();
		for (Chunk chunk : chunks) {
			newChunks.addAll(chunk.getChunks());
		}
		return getChunksListAtDepth(newChunks, depth - 1);
	}

	private static List<Chunk> getChunksAtDepth(Table table, int depth) {
		if (depth == -1) {
			return new ArrayList<Chunk>();
		}
		if (depth == 0) {
			return table.getChunks();
		}
		return getChunksAtDepth(table.getChunks(), depth - 1);
	}

	private static List<Chunk> getChunksAtDepth(List<Chunk> chunks, int depth) {
		List<Chunk> newChunks = new ArrayList<Chunk>();
		for (Chunk chunk : chunks) {
			newChunks.addAll(chunk.getChunks());
		}
		if (newChunks.isEmpty()) {
			return chunks;
		}
		return getChunksAtDepth(newChunks, depth - 1);
	}

	private static int getCorrectDepth(Table table, int depth) {
		int maxDepth = maxDepth(table);

		if (depth > maxDepth) {
			return maxDepth;
		}

		return depth;
	}

	private ChunksFinder() {
	}
}
