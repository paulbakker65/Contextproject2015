package operations;

import table.Chunk;
import table.Table;

import java.util.ArrayList;
import java.util.List;

public class ForEachChunkOperation extends Operation {
  private int chunkDepth;
  private Operation operation;

  public ForEachChunkOperation(Table inputDataset, int chunkDepth, Operation operation) {
    super(inputDataset);
    setOperationParameters(chunkDepth, operation);
  }
  
  private boolean setOperationParameters(int chunkDepth, Operation operation) {
    this.operationParametersSet = !(chunkDepth <= 0 | operation == null);
     
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
    
    this.resultData = (Table) inputData.clone();
    
    for (List<Chunk> chunkList : getChunks()) {
      for (int i = 0; i < chunkList.size(); i++) {
        Chunk chunk = chunkList.get(i);
        operation.inputData = chunk;
        
        if (!operation.execute()) {
          return false;
        }
        
        chunkList.set(i, new Chunk(chunk, operation.getResult()));
      }
    }
    
    return false;
  }
  
  private List<List<Chunk>> getChunks() {
    int depth = chunkDepth - 1;
    List<Table> tables = new ArrayList<Table>();
    tables.add(inputData);
    return getChunks(tables, depth);
  }
  
  private List<List<Chunk>> getChunks(List<Table> tables, int depth) {
    if (depth == 0) {
      List<List<Chunk>> res = new ArrayList<List<Chunk>>();
      for (Table table : tables) {
        res.add(table.getChunks());
      }
    }
    
    List<Table> newTables = new ArrayList<Table>();
    for (Table table : tables) {
      newTables.addAll(table.getChunks());
    }
    
    return getChunks(newTables, --depth);
  }

  @Override
  public Table getResult() {
    return resultData;
  }
  
  

}
