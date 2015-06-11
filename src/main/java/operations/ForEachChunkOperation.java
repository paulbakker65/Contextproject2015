package operations;

import table.Chunk;
import table.Table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Operation which executes another operation for each chunk on a certain depth.
 */
public class ForEachChunkOperation extends Operation {
  int chunkDepth;
  Operation operation;

  /**
   * Constructs a ForEachChunkOperation.
   * 
   * @param inputDataset
   *          the input data.
   * @param chunkDepth
   *          the depth to search chunks.
   * @param operation
   *          the operation to execute on the chunks.
   */
  public ForEachChunkOperation(Table inputDataset, int chunkDepth, Operation operation) {
    super(inputDataset);
    setOperationParameters(chunkDepth, operation);
  }

  @Override
  public void resetData(Table inputData) {
    this.inputData = inputData;
    this.resultData = new Table(inputData);
  }

  private boolean setOperationParameters(int chunkDepth, Operation operation) {
    this.operationParametersSet = !(chunkDepth <= 0 || operation == null);

    if (this.operationParametersSet) {
      this.chunkDepth = chunkDepth;
      this.operation = operation;
    }

    return this.operationParametersSet;
  }

  @Override
  public boolean execute() {
    if (!this.operationParametersSet) {
      return false;
    }

    for (List<Chunk> chunkList : getChunks()) {
      for (int i = 0; i < chunkList.size(); i++) {
        Chunk chunk = chunkList.get(i);
        operation.resetData(new Table(chunk));

        if (!operation.execute()) {
          return false;
        }
        Chunk newChunk = new Chunk(chunk, operation.getResult());
        chunkList.set(i, newChunk);
      }
    }

    return true;
  }

  /**
   * Returns all chunklists to apply the operation on. We need the chunklist so that we know on
   * which position to put the newly created chunk.
   * 
   * @return all chunklists to apply the operation on.
   */
  List<List<Chunk>> getChunks() {
    int maxDepth = maxDepth(resultData);
    int depth = chunkDepth;

    if (chunkDepth > maxDepth) {
      depth = maxDepth;
    }

    return getChunksListAtDepth(resultData, depth - 1);
  }

  /**
   * Returns the maximum depth found in the table.
   * 
   * @param table
   *          the table to search.
   * @return the maximum depth found in the table.
   */
  int maxDepth(Table table) {
    return maxDepth(table.getChunks());
  }

  private int maxDepth(List<Chunk> chunks) {
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
   * Returns all the chunklists at a certain depth (assuming that these lists are not empty).
   * 
   * @param table
   *          the table to search.
   * @param depth
   *          the depth to return the chunklists from.
   * @return all the chunklists at a certain depth.
   */
  private List<List<Chunk>> getChunksListAtDepth(Table table, int depth) {
    if (depth == -1) {
      return new ArrayList<List<Chunk>>();
    }
    if (depth == 0) {
      return new ArrayList<List<Chunk>>(Arrays.asList(table.getChunks()));
    }

    return getChunksListAtDepth(table.getChunks(), depth - 1);
  }

  private List<List<Chunk>> getChunksListAtDepth(List<Chunk> chunks, int depth) {
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

  @Override
  public Table getResult() {
    return resultData;
  }

  // public static void main(String[] args) throws ClassNotFoundException, IOException {
  // Table table = TableFile.readTable("demo_output/output_Website");
  // Pattern pattern = PatternFactory.createPattern("2 StatSensor");
  //
  // CodingOperation operation = new CodingOperation(table, pattern, "2Stat");
  // ForEachChunkOperation fecOperation = new ForEachChunkOperation(table, 1, operation);
  // fecOperation.execute();
  //
  // for (Chunk chunk : fecOperation.getResult().getChunks()) {
  // System.out.println(chunk.getLabel() + ": " + chunk.getCode("2Stat").getFrequency() +
  // "x 2Stat");
  // }
  //
  // // ChunkingOperation operation = new ChunkingOperation(table, "Date", ChunkType.DAY);
  // // ForEachChunkOperation fecOperation = new ForEachChunkOperation(table, 1, operation);
  // // fecOperation.execute();
  //
  // Exporter.export(fecOperation.getResult(), "demo_output/fec_output2", ".csv");
  // }

}
