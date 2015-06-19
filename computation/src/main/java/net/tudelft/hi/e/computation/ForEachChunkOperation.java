package net.tudelft.hi.e.computation;

import net.tudelft.hi.e.data.Chunk;
import net.tudelft.hi.e.data.ChunksFinder;
import net.tudelft.hi.e.data.Table;

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
    this.resultData.clear();
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

    for (List<Chunk> chunkList : ChunksFinder.getChunkLists(inputData, chunkDepth)) {
      for (int i = 0; i < chunkList.size(); i++) {
        Chunk chunk = chunkList.get(i);
        operation.resetData(new Table(chunk));

        if (!operation.execute()) {
          return false;
        }
        Chunk newChunk = new Chunk(chunk, operation.getResult());
        chunkList.set(i, newChunk);
        this.resultData.addAll(newChunk);
      }
    }
    
    

    return true;
  }

  @Override
  public Table getResult() {
    return resultData;
  }
}
